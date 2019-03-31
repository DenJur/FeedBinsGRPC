package controller.models;

import com.google.protobuf.Empty;
import controller.interfaces.ClientUpdater;
import io.grpc.Context;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import u1467085.feedbincontroller.BinId;
import u1467085.feedbincontroller.ControllerBinStatusUpdate;
import u1467085.feedbincontroller.Stuff;
import u1467085.feedbins.BinStatusUpdate;
import u1467085.feedbins.FeedBinServiceGrpc;

/**
 * Bin record for the controller. Contains an address:port pair that acts as unique identifier for that bin and
 * also manages all the connections to the bin server
 */
public class ProductionLineBin {
    public final String address;
    public final int port;
    /**
     * channel used for communication with bin server
     */
    private final ManagedChannel channel;
    /**
     * stub used to register with bin server to receive change updates
     */
    private final FeedBinServiceGrpc.FeedBinServiceStub updateListenerStub;
    /**
     * callback to notify controller clients about the bin change
     */
    private final ClientUpdater clientUpdater;
    /**
     * stub used for invoking all other bin actions
     */
    public final FeedBinServiceGrpc.FeedBinServiceFutureStub actionCallerStub;
    /**
     * cancellable context to allow client to cancel serer-side stream from the bin
     */
    private final Context.CancellableContext updateContext;

    public ProductionLineBin(String address, int port, ClientUpdater clientUpdater){
        this.address=address;
        this.port = port;
        this.clientUpdater=clientUpdater;
        //connect to the bin server
        channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();

        //create async stub to call registerForUpdates method and create server-side stream
        updateListenerStub = FeedBinServiceGrpc.newStub(channel);
        //need to fork current context and make it cancelable otherwise stream gets canceled by other requests
        updateContext=Context.current().fork().withCancellation();
        updateContext.run(() -> updateListenerStub.registerForUpdates(Empty.newBuilder().build(),
                new NotificationObserver(this)));

        actionCallerStub = FeedBinServiceGrpc.newFutureStub(channel);
    }

    private class NotificationObserver implements StreamObserver<BinStatusUpdate> {

        private final ProductionLineBin bin;

        private NotificationObserver(ProductionLineBin bin){
            this.bin=bin;
        }

        @Override
        public void onNext(BinStatusUpdate value) {
            //Construct a bin update object and return it back to the callback interface
            clientUpdater.NotifyClients(ControllerBinStatusUpdate.newBuilder()
                    .setRecord(BinId.newBuilder().setAddress(address).setPort(port))
                    .setAmount(value.getAmount().getStuffAmount())
                    .setStuff(Stuff.newBuilder().setStuffName(value.getStuff().getStuffName()))
                    .setMaxAmount(value.getMaxAmount())
                    .build());
        }

        @Override
        public void onError(Throwable t) {
            clientUpdater.ForgetNotifier(bin);
        }

        @Override
        public void onCompleted() {

        }
    }
}
