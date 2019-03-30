package controller;

import com.google.protobuf.Empty;
import controller.interfaces.ClientUpdater;
import controller.models.ProductionLineBin;
import io.grpc.Status;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;
import u1467085.feedbincontroller.*;
import u1467085.feedbins.BinStatusUpdate;
import u1467085.feedbins.StuffAmount;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Implementation of the bin controller gRPC server
 */
public class FeedBinControllerServiceImpl extends FeedBinControllerServiceGrpc.FeedBinControllerServiceImplBase implements ClientUpdater {
    /**
     * Set of clients that need to receive bin update notifications
     */
    private final Set<ServerCallStreamObserver<ControllerBinStatusUpdate>> observers;
    /**
     * List of bin servers that were assigned to this controller
     */
    private final ArrayList<ProductionLineBin> productionLine;

    public FeedBinControllerServiceImpl() {
        observers = ConcurrentHashMap.newKeySet();
        productionLine = new ArrayList<>();
    }

    /**
     * Adds client to the observer list for later notifications and sends out updates for all current bins
     *
     * @param request          - request information. Uses protobuf.Empty because no information is provided
     * @param responseObserver - StreamObserver that will be added to the observer list
     */
    @Override
    public synchronized void registerForBinUpdates(Empty request, StreamObserver<ControllerBinStatusUpdate> responseObserver) {
        //cast to ServerCallStreamObserver be able to check if the connection was canceled
        ServerCallStreamObserver<ControllerBinStatusUpdate> castObserver =
                (ServerCallStreamObserver<ControllerBinStatusUpdate>) responseObserver;
        castObserver.setOnCancelHandler(() -> observers.remove(castObserver));
        observers.add(castObserver);
        //return status updates for all current bins
        productionLine.forEach(productionLineBin -> {
            try {
                //call inspectBin endpoint on the bin server
                BinStatusUpdate result = productionLineBin.actionCallerStub
                        .inspectBin(Empty.newBuilder().build()).get(10, TimeUnit.SECONDS);
                //transform bin update into more detailed form and pass
                responseObserver.onNext(ControllerBinStatusUpdate.newBuilder()
                        .setMaxAmount(result.getMaxAmount())
                        .setRecord(BinId.newBuilder().setAddress(productionLineBin.address).setPort(productionLineBin.port).build())
                        .setAmount(result.getAmount().getStuffAmount())
                        .setStuff(Stuff.newBuilder().setStuffName(result.getStuff().getStuffName())).build());
            } catch (InterruptedException | TimeoutException | ExecutionException e) {
                responseObserver.onError(e);
            }
        });
    }

    /**
     * Registers bin server with the controller
     *
     * @param request          - bin identification information
     * @param responseObserver - returns OperationStatusResponse back to the client
     */
    @Override
    public synchronized void addBin(BinId request, StreamObserver<OperationStatusResponse> responseObserver) {
        //check if the bin was previously added and fail if it was
        if (productionLine.stream().anyMatch(productionLineBin ->
                Objects.equals(productionLineBin.address, request.getAddress())
                        && productionLineBin.port == request.getPort())) {
            responseObserver.onNext(
                    OperationStatusResponse.newBuilder().setResult(OperationStatusResponse.OperationStatus.FAIL)
                            .setMessage("Bin already added to this production line.").build());
            responseObserver.onCompleted();
            return;
        }
        //add bin record and send out successful response
        productionLine.add(new ProductionLineBin(request.getAddress(), request.getPort(), this));
        responseObserver.onNext(
                OperationStatusResponse.newBuilder().setResult(OperationStatusResponse.OperationStatus.SUCCESS).build());
        responseObserver.onCompleted();
    }

    /**
     * Flush bin contents
     *
     * @param request          - bin identification information
     * @param responseObserver - returns OperationStatusResponse back to the client
     */
    @Override
    public synchronized void flushBin(BinId request, StreamObserver<OperationStatusResponse> responseObserver) {
        //try to find bin record based on provided id info
        Optional<ProductionLineBin> bin = productionLine.stream().filter(productionLineBin ->
                Objects.equals(request.getAddress(), productionLineBin.address)
                        && request.getPort() == productionLineBin.port).findFirst();
        //if bin was found
        if (bin.isPresent()) {
            //try to execute flushBin command
            try {
                u1467085.feedbins.OperationStatusResponse result = bin.get().actionCallerStub
                        .flushBin(Empty.newBuilder().build()).get(10, TimeUnit.SECONDS);
                //if command result was successful signal client about it
                if (result.getResult() == u1467085.feedbins.OperationStatusResponse.OperationStatus.SUCCESS) {
                    responseObserver.onNext(OperationStatusResponse.newBuilder()
                            .setResult(OperationStatusResponse.OperationStatus.SUCCESS).build());
                } else {
                    //forward the failure message from the bin to the controller client
                    responseObserver.onNext(OperationStatusResponse.newBuilder()
                            .setResult(OperationStatusResponse.OperationStatus.FAIL)
                            .setMessage("Bin failed to flush").build());
                }
            } catch (InterruptedException | TimeoutException | ExecutionException e) {
                responseObserver.onNext(OperationStatusResponse.newBuilder()
                        .setResult(OperationStatusResponse.OperationStatus.FAIL)
                        .setMessage("Bin flush failed with error: " + e.toString()).build());
            }
        } else {
            // bin was not found so return failure message
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Tried to flush invalid bin record").build());
        }

        responseObserver.onCompleted();
    }

    /**
     * Returns specified bin status to the client
     *
     * @param request          - bin identification information
     * @param responseObserver - information about the bin status
     */
    @Override
    public void inspectBin(BinId request, StreamObserver<ControllerBinStatusUpdate> responseObserver) {
        //try to find bin record based on provided id info
        Optional<ProductionLineBin> bin = productionLine.stream().filter(productionLineBin ->
                Objects.equals(request.getAddress(), productionLineBin.address)
                        && request.getPort() == productionLineBin.port).findFirst();
        //if bin was found
        if (bin.isPresent()) {
            //try to invoke inspectBin command
            try {
                BinStatusUpdate result = bin.get().actionCallerStub
                        .inspectBin(Empty.newBuilder().build()).get(10, TimeUnit.SECONDS);
                // transform bin response to a more detailed form
                responseObserver.onNext(ControllerBinStatusUpdate.newBuilder()
                        .setMaxAmount(result.getMaxAmount())
                        .setRecord(request)
                        .setAmount(result.getAmount().getStuffAmount())
                        .setStuff(Stuff.newBuilder().setStuffName(result.getStuff().getStuffName())).build());
            } catch (InterruptedException | TimeoutException | ExecutionException e) {
                responseObserver.onError(e);
            }
        } else {
            // bin was not found so return failure message
            responseObserver.onError(Status.INVALID_ARGUMENT.asException());
        }

        responseObserver.onCompleted();
    }

    /**
     * Modifies amount of food stuff in the bin. Can be positive to add stuff or negative to remove stuff
     *
     * @param request          - bin identification information and amount of stuff to add to that bin
     * @param responseObserver - returns OperationStatusResponse back to the client
     */
    @Override
    public synchronized void addStuff(FillBin request, StreamObserver<OperationStatusResponse> responseObserver) {
        //try to find bin record based on provided id info
        Optional<ProductionLineBin> bin = productionLine.stream().filter(productionLineBin ->
                Objects.equals(request.getId().getAddress(), productionLineBin.address)
                        && request.getId().getPort() == productionLineBin.port).findFirst();
        //if bin was found
        if (bin.isPresent()) {
            //try to execute addStuff command
            try {
                u1467085.feedbins.OperationStatusResponse result = bin.get().actionCallerStub
                        .addStuff(StuffAmount.newBuilder().setStuffAmount(request.getAmount()).build())
                        .get(10, TimeUnit.SECONDS);
                //translate bin server response to controller status response
                if (result.getResult() == u1467085.feedbins.OperationStatusResponse.OperationStatus.SUCCESS) {
                    responseObserver.onNext(OperationStatusResponse.newBuilder()
                            .setResult(OperationStatusResponse.OperationStatus.SUCCESS).build());
                } else {
                    responseObserver.onNext(OperationStatusResponse.newBuilder()
                            .setResult(OperationStatusResponse.OperationStatus.FAIL)
                            .setMessage(result.getMessage()).build());
                }
            } catch (InterruptedException | TimeoutException | ExecutionException e) {
                responseObserver.onNext(OperationStatusResponse.newBuilder()
                        .setResult(OperationStatusResponse.OperationStatus.FAIL)
                        .setMessage("Error trying to contact bin server: " + e.toString()).build());
            }
        } else {
            // bin was not found so return failure message
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Trying to access invalid bin").build());
        }

        responseObserver.onCompleted();
    }

    /**
     * Change the stuff that is assigned to the bin. The bin will be flushed when new stuff is assigned
     *
     * @param request          - bin identification information and new stuff that should be put in the bin
     * @param responseObserver - returns OperationStatusResponse back to the client
     */
    @Override
    public synchronized void changeStuff(ChangeBinStuff request, StreamObserver<OperationStatusResponse> responseObserver) {
        //try to find bin record based on provided id info
        Optional<ProductionLineBin> bin = productionLine.stream().filter(productionLineBin ->
                Objects.equals(request.getId().getAddress(), productionLineBin.address)
                        && request.getId().getPort() == productionLineBin.port).findFirst();
        //if bin was found
        if (bin.isPresent()) {
            //try to execute changeStuff command
            try {
                u1467085.feedbins.OperationStatusResponse result = bin.get().actionCallerStub
                        .changeStuff(u1467085.feedbins.Stuff.newBuilder().setStuffName(request.getStuff().getStuffName())
                                .build()).get(10, TimeUnit.SECONDS);
                //translate bin server response to controller status response
                if (result.getResult() == u1467085.feedbins.OperationStatusResponse.OperationStatus.SUCCESS) {
                    responseObserver.onNext(OperationStatusResponse.newBuilder()
                            .setResult(OperationStatusResponse.OperationStatus.SUCCESS).build());
                } else {
                    responseObserver.onNext(OperationStatusResponse.newBuilder()
                            .setResult(OperationStatusResponse.OperationStatus.FAIL)
                            .setMessage(result.getMessage()).build());
                }
            } catch (InterruptedException | TimeoutException | ExecutionException e) {
                responseObserver.onNext(OperationStatusResponse.newBuilder()
                        .setResult(OperationStatusResponse.OperationStatus.FAIL)
                        .setMessage("Error trying to contact bin server: " + e.toString()).build());
            }
        } else {
            // bin was not found so return failure message
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Trying to access invalid bin").build());
        }

        responseObserver.onCompleted();
    }

    /**
     * Sends out status updates to all client that have registered to receive those updates
     *
     * @param update - status update that should be sent to the clients
     */
    @Override
    public void NotifyClients(ControllerBinStatusUpdate update) {
        observers.iterator().
                forEachRemaining(binStatusUpdateStreamObserver -> binStatusUpdateStreamObserver.onNext(update));
    }
}
