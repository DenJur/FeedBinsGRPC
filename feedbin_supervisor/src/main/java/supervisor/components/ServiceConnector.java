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

/**
 * Singleton class tha manages bin statuses and the connection to the controller server
 */
public class ServiceConnector {
    //singleton instance
    private static ServiceConnector instance;
    //channel used to contact controller server
    private ManagedChannel controllerChannel;
    //stub used for invoking controller methods
    private FeedBinControllerServiceGrpc.FeedBinControllerServiceFutureStub serviceStub;
    //list of feed bin object statuses
    private ObservableList<ControllerBinStatusUpdate> binStatusList;
    //context used for streaming bin status updates
    private Context.CancellableContext statusObserverContext;

    private ServiceConnector() {
        binStatusList = FXCollections.observableArrayList();
    }

    /**
     * Singleton accessor method
     *
     * @return - singleton instance
     */
    public static ServiceConnector getInstance() {
        if (instance == null) {
            instance = new ServiceConnector();
        }
        return instance;
    }

    /**
     * Bin status list getter
     *
     * @return - list of bin statuses
     */
    public ObservableList<ControllerBinStatusUpdate> getBinStatusList() {
        return binStatusList;
    }

    /**
     * Controller service stub getter
     *
     * @return - future stub
     */
    public FeedBinControllerServiceGrpc.FeedBinControllerServiceFutureStub getServiceStub() {
        return serviceStub;
    }

    /**
     * Establishes a connection to the bin controller canceling previous connection in the process if necessary
     *
     * @param address - a string integer pair representing address and port of the controller service
     * @throws InterruptedException - exception can be thrown during disconnection process
     */
    public void connectToController(Pair<String, Integer> address) throws InterruptedException {
        disconnectCurrent();
        //create new channel to specified address
        controllerChannel = ManagedChannelBuilder.forAddress(address.getKey(), address.getValue()).usePlaintext().build();
        //create an async stub that will be used to register for bin status updates
        FeedBinControllerServiceGrpc.FeedBinControllerServiceStub serviceAsyncStub =
                FeedBinControllerServiceGrpc.newStub(controllerChannel);

        //create a separate cancellable context that will be used to register and stream bin status updates
        statusObserverContext = Context.current().withCancellation();
        statusObserverContext.run(() -> serviceAsyncStub.registerForBinUpdates(Empty.newBuilder().build(),
                new BinStatusObserver()));

        //create future stub for all other method invocation
        serviceStub = FeedBinControllerServiceGrpc.newFutureStub(controllerChannel);
    }

    /**
     * Disconnects from the current bin controller if connected.
     *
     * @throws InterruptedException - thrown if we are unable to shutdown connection in 10 seconds
     */
    private void disconnectCurrent() throws InterruptedException {
        if (controllerChannel != null) {
            statusObserverContext.cancel(Status.CANCELLED.asException());
            controllerChannel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
            binStatusList.clear();
            controllerChannel = null;
        }
    }

    /**
     * Updates or adds bin record inside the status list
     *
     * @param newStatus - status to be updated/added
     */
    public void updateBinRecord(ControllerBinStatusUpdate newStatus) {
        //run on UI thread as list is used for a ListView an concurrent modification can cause an error
        Platform.runLater(() -> {
            //if the bin record was found in current bin status list
            boolean foundRecord = false;
            for (int i = 0; i < binStatusList.size(); i++) {
                //found record for a bin with the same address
                if (Objects.equals(binStatusList.get(i).getRecord().getAddress(), newStatus.getRecord().getAddress())
                        && binStatusList.get(i).getRecord().getPort() == newStatus.getRecord().getPort()) {
                    //replace status record
                    binStatusList.set(i, newStatus);
                    foundRecord = true;
                }
            }
            //no record was found so just add it at the end
            if (!foundRecord) binStatusList.add(newStatus);
        });
    }

    /**
     * Stream observer used to add bin status updates to the list
     */
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
