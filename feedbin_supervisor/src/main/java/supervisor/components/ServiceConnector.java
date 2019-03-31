package supervisor.components;

import com.google.protobuf.Empty;
import io.grpc.Context;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import u1467085.feedbincontroller.ControllerBinStatusUpdate;
import u1467085.feedbincontroller.FeedBinControllerServiceGrpc;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ServiceConnector {
    private static ServiceConnector instance;
    private ManagedChannel controllerChannel;
    private FeedBinControllerServiceGrpc.FeedBinControllerServiceFutureStub serviceStub;
    private ObservableList<ControllerBinStatusUpdate> binStatusList;
    private Context.CancellableContext statusObserverContext;

    private ServiceConnector() {
        binStatusList = FXCollections.observableArrayList();
    }

    public static ServiceConnector getInstance() {
        if (instance == null) {
            instance = new ServiceConnector();
        }
        return instance;
    }

    public ObservableList<ControllerBinStatusUpdate> getBinStatusList() {
        return binStatusList;
    }

    public FeedBinControllerServiceGrpc.FeedBinControllerServiceFutureStub getServiceStub() {
        return serviceStub;
    }

    public void connectToController(Pair<String, Integer> address) throws InterruptedException {
        disconnectCurrent();
        controllerChannel = ManagedChannelBuilder.forAddress(address.getKey(), address.getValue()).usePlaintext().build();
        FeedBinControllerServiceGrpc.FeedBinControllerServiceStub serviceAsyncStub =
                FeedBinControllerServiceGrpc.newStub(controllerChannel);

        statusObserverContext = Context.current().withCancellation();
        statusObserverContext.run(() -> serviceAsyncStub.registerForBinUpdates(Empty.newBuilder().build(),
                new BinStatusObserver()));

        serviceStub = FeedBinControllerServiceGrpc.newFutureStub(controllerChannel);
    }

    private void disconnectCurrent() throws InterruptedException {
        if (controllerChannel != null) {
            statusObserverContext.cancel(Status.CANCELLED.asException());
            controllerChannel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
            binStatusList.clear();
        }
    }

    public void updateBinRecord(ControllerBinStatusUpdate newStatus){
        Platform.runLater(() -> {
            if (binStatusList.stream().anyMatch(controllerBinStatusUpdate ->
                    Objects.equals(controllerBinStatusUpdate.getRecord().getAddress(), newStatus.getRecord().getAddress())
                            && controllerBinStatusUpdate.getRecord().getPort() == newStatus.getRecord().getPort()))
                binStatusList.replaceAll(controllerBinStatusUpdate -> {
                    if (Objects.equals(controllerBinStatusUpdate.getRecord().getAddress(), newStatus.getRecord().getAddress())
                            && controllerBinStatusUpdate.getRecord().getPort() == newStatus.getRecord().getPort()) {
                        return newStatus;
                    } else return controllerBinStatusUpdate;
                });
            else binStatusList.add(newStatus);
        });
    }

    private class BinStatusObserver implements StreamObserver<ControllerBinStatusUpdate> {

        @Override
        public void onNext(ControllerBinStatusUpdate value) {
           updateBinRecord(value);
        }

        @Override
        public void onError(Throwable t) {
//            t.printStackTrace();
        }

        @Override
        public void onCompleted() {
            binStatusList.clear();
        }
    }
}
