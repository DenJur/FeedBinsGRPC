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

public class ProductionLineBin {
    public final String address;
    public final int port;
    private final ManagedChannel channel;
    private final FeedBinServiceGrpc.FeedBinServiceStub updateListenerStub;
    private final ClientUpdater clientUpdater;
    public final FeedBinServiceGrpc.FeedBinServiceFutureStub actionCallerStub;
    private final Context updateContext;

    public ProductionLineBin(String address, int port, ClientUpdater clientUpdater){
        this.address=address;
        this.port = port;
        this.clientUpdater=clientUpdater;
        channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();

        updateListenerStub = FeedBinServiceGrpc.newStub(channel);
        updateContext=Context.current().fork().withCancellation();
        updateContext.run(() -> updateListenerStub.registerForUpdates(Empty.newBuilder().build(), new NotificationObserver()));

        actionCallerStub = FeedBinServiceGrpc.newFutureStub(channel);
    }

    private class NotificationObserver implements StreamObserver<BinStatusUpdate> {

        @Override
        public void onNext(BinStatusUpdate value) {
            clientUpdater.NotifyClients(ControllerBinStatusUpdate.newBuilder()
                    .setRecord(BinId.newBuilder().setAddress(address).setPort(port))
                    .setAmount(value.getAmount().getStuffAmount())
                    .setStuff(Stuff.newBuilder().setStuffName(value.getStuff().getStuffName()))
                    .setMaxAmount(value.getMaxAmount())
                    .build());
        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void onCompleted() {

        }
    }
}
