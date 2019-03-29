import com.google.protobuf.Empty;
import exceptions.BinOverflow;
import exceptions.BinUnderflow;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;
import u1467085.feedbins.*;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FeedBinServiceImpl extends FeedBinServiceGrpc.FeedBinServiceImplBase {

    private final Bin bin;
    /**
     * Clients that have called registerForUpdates method and need to be notified about any bin changes
     */
    private final Set<ServerCallStreamObserver<BinStatusUpdate>> observers;

    /**
     * Feed bin service constructor
     *
     * @param bin - feed bin that the service exposes
     */
    public FeedBinServiceImpl(Bin bin) {
        this.bin = bin;
        this.observers = ConcurrentHashMap.newKeySet();
    }

    /**
     * Method called by the clients to receive updates about bin changes
     *
     * @param request          - request information. Uses protobuf.Empty because no information is provided
     * @param responseObserver - stream to send bin updates back to the client
     */
    @Override
    public synchronized void registerForUpdates(Empty request, StreamObserver<BinStatusUpdate> responseObserver) {
        //cast to ServerCallStreamObserver be able to check if the connection was canceled
        ServerCallStreamObserver<BinStatusUpdate> castObserver = (ServerCallStreamObserver<BinStatusUpdate>) responseObserver;
        castObserver.setOnCancelHandler(() -> observers.remove(castObserver));
        observers.add(castObserver);
    }

    /**
     * Flushes managed bin contents
     *
     * @param request          - request information. Uses protobuf.Empty because no information is provided
     * @param responseObserver - returns OperationStatusResponse back to the client
     */
    @Override
    public synchronized void flushBin(Empty request, StreamObserver<OperationStatusResponse> responseObserver) {
        bin.flush();
        notifyObservers();
        responseObserver.onNext(OperationStatusResponse.newBuilder()
                .setResult(OperationStatusResponse.OperationStatus.SUCCESS).build());
        responseObserver.onCompleted();
    }

    /**
     * Returns current bin status to the client
     *
     * @param request          - request information. Uses protobuf.Empty because no information is provided
     * @param responseObserver - returns bin status
     */
    @Override
    public synchronized void inspectBin(Empty request, StreamObserver<BinStatusUpdate> responseObserver) {
        responseObserver.onNext(buildNewStatus());
        responseObserver.onCompleted();
    }

    /**
     * Modifies amount of food stuff in the bin
     *
     * @param request          - amount of food stuff to be added to the bin. Can be negate to remove stuff
     * @param responseObserver - returns OperationStatusResponse back to the client
     */
    @Override
    public synchronized void addStuff(StuffAmount request, StreamObserver<OperationStatusResponse> responseObserver) {
        // wrap in a try-catch as bin object throws in cases when invalid amount is provided
        try {
            bin.addAmount(request.getStuffAmount());
            notifyObservers();
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.SUCCESS)
                    .build());
        } catch (BinUnderflow binUnderflow) {
            // return message to the client that bin does not have enough stuff to remove
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Specified amount is less than current contents of the bin.")
                    .build());
        } catch (BinOverflow binOverflow) {
            // return message to the client that adding specified amount would overflow the bin
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Specified amount is more than the bin can hold.")
                    .build());
        } finally {
            responseObserver.onCompleted();
        }
    }

    /**
     * Change the name of the stuff inside the bin
     *
     * @param request          - new name of the stuff to be set
     * @param responseObserver - returns OperationStatusResponse back to the client
     */
    @Override
    public synchronized void changeStuff(Stuff request, StreamObserver<OperationStatusResponse> responseObserver) {
        bin.changeStuffName(request.getStuffName());
        notifyObservers();
        responseObserver.onNext(OperationStatusResponse.newBuilder()
                .setResult(OperationStatusResponse.OperationStatus.SUCCESS)
                .build());
        responseObserver.onCompleted();
    }

    /**
     * Builds a bin status object that containes the name and the amount of the stuff in the bin
     *
     * @return - current bin status
     */
    private BinStatusUpdate buildNewStatus() {
        return BinStatusUpdate.newBuilder()
                .setAmount(StuffAmount.newBuilder().setStuffAmount(bin.getStuffAmount()).build())
                .setStuff(Stuff.newBuilder().setStuffName(bin.getStuffName()).build())
                .setMaxAmount(bin.MAX_AMOUNT)
                .build();
    }

    /**
     * Sends bin status updates to all clients that registered fot notifications
     */
    private void notifyObservers() {
        BinStatusUpdate newStatus = buildNewStatus();
        observers.iterator().forEachRemaining(binStatusUpdateStreamObserver -> binStatusUpdateStreamObserver.onNext(newStatus));
    }
}
