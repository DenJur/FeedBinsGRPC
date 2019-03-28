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
    private final Set<ServerCallStreamObserver<BinStatusUpdate>> observers;

    public FeedBinServiceImpl(Bin bin) {
        this.bin = bin;
        this.observers = ConcurrentHashMap.newKeySet();
    }

    @Override
    public synchronized void registerForUpdates(Empty request, StreamObserver<BinStatusUpdate> responseObserver) {
        super.registerForUpdates(request, responseObserver);
        ServerCallStreamObserver<BinStatusUpdate> castObserver = (ServerCallStreamObserver<BinStatusUpdate>) responseObserver;
        castObserver.setOnCancelHandler(() -> observers.remove(castObserver));
        observers.add(castObserver);
    }

    @Override
    public synchronized void flushBin(Empty request, StreamObserver<OperationStatusResponse> responseObserver) {
        super.flushBin(request, responseObserver);
        bin.flush();
        notifyObservers();
        responseObserver.onCompleted();
   }

    @Override
    public synchronized void inspectBin(Empty request, StreamObserver<BinStatusUpdate> responseObserver) {
        super.inspectBin(request, responseObserver);
        responseObserver.onNext(buildNewStatus());
        responseObserver.onCompleted();
    }

    @Override
    public synchronized void addStuff(StuffAmount request, StreamObserver<OperationStatusResponse> responseObserver) {
        super.addStuff(request, responseObserver);
        try{
            bin.addAmount(request.getStuffAmount());
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.SUCCESS)
                    .build());
            notifyObservers();
        } catch (BinUnderflow binUnderflow) {
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Specified amount is less than current contents of the bin.")
                    .build());
        } catch (BinOverflow binOverflow) {
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Specified amount is more than the bin can hold.")
                    .build());
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public synchronized void changeStuff(Stuff request, StreamObserver<OperationStatusResponse> responseObserver) {
        super.changeStuff(request, responseObserver);
        bin.changeStuffName(request.getStuffName());
        responseObserver.onNext(OperationStatusResponse.newBuilder()
                .setResult(OperationStatusResponse.OperationStatus.SUCCESS)
                .build());
        notifyObservers();
        responseObserver.onCompleted();
    }

    private BinStatusUpdate buildNewStatus() {
        return BinStatusUpdate.newBuilder()
                .setAmount(StuffAmount.newBuilder().setStuffAmount(bin.getStuffAmount()).build())
                .setStuff(Stuff.newBuilder().setStuffName(bin.getStuffName()).build())
                .build();
    }

    private void notifyObservers(){
        BinStatusUpdate newStatus=buildNewStatus();
        observers.iterator().forEachRemaining(binStatusUpdateStreamObserver -> binStatusUpdateStreamObserver.onNext(newStatus));
    }
}