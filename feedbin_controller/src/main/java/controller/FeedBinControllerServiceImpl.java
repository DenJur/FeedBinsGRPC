package controller;

import com.google.common.util.concurrent.ListenableFuture;
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

public class FeedBinControllerServiceImpl extends FeedBinControllerServiceGrpc.FeedBinControllerServiceImplBase implements ClientUpdater {

    private final Set<ServerCallStreamObserver<ControllerBinStatusUpdate>> observers;
    private final ArrayList<ProductionLineBin> productionLine;

    public FeedBinControllerServiceImpl() {
        observers = ConcurrentHashMap.newKeySet();
        productionLine = new ArrayList<>();
    }

    @Override
    public synchronized void registerForBinUpdates(Empty request, StreamObserver<ControllerBinStatusUpdate> responseObserver) {
        ServerCallStreamObserver<ControllerBinStatusUpdate> castObserver =
                (ServerCallStreamObserver<ControllerBinStatusUpdate>) responseObserver;
        castObserver.setOnCancelHandler(() -> observers.remove(castObserver));
        observers.add(castObserver);
        productionLine.forEach(productionLineBin -> {
            ListenableFuture<BinStatusUpdate> future = productionLineBin.actionCallerStub
                    .inspectBin(Empty.newBuilder().build());
            try {
                BinStatusUpdate result = future.get(10, TimeUnit.SECONDS);
                responseObserver.onNext(ControllerBinStatusUpdate.newBuilder()
                        .setMaxAmount(result.getMaxAmount())
                        .setRecord(BinId.newBuilder().setAddress(productionLineBin.address).setPort(productionLineBin.port).build())
                        .setAmount(result.getAmount().getStuffAmount())
                        .setStuff(Stuff.newBuilder().setStuffName(result.getStuff().getStuffName())).build());
            } catch (InterruptedException | TimeoutException | ExecutionException ignored) {
                responseObserver.onError(ignored);
            }
        });
    }

    @Override
    public synchronized void addBin(BinId request, StreamObserver<OperationStatusResponse> responseObserver) {
        if (productionLine.stream().anyMatch(productionLineBin ->
                Objects.equals(productionLineBin.address, request.getAddress())
                        && productionLineBin.port == request.getPort())) {
            responseObserver.onNext(
                    OperationStatusResponse.newBuilder().setResult(OperationStatusResponse.OperationStatus.FAIL)
                            .setMessage("Bin already added to this production line.").build());
            responseObserver.onCompleted();
            return;
        }
        productionLine.add(new ProductionLineBin(request.getAddress(), request.getPort(), this));

        responseObserver.onNext(
                OperationStatusResponse.newBuilder().setResult(OperationStatusResponse.OperationStatus.SUCCESS).build());
        responseObserver.onCompleted();
    }

    @Override
    public synchronized void flushBin(BinId request, StreamObserver<OperationStatusResponse> responseObserver) {
        Optional<ProductionLineBin> bin = productionLine.stream().filter(productionLineBin ->
                Objects.equals(request.getAddress(), productionLineBin.address)
                        && request.getPort() == productionLineBin.port).findFirst();
        if (bin.isPresent()) {
            ListenableFuture<u1467085.feedbins.OperationStatusResponse> future = bin.get().actionCallerStub
                    .flushBin(Empty.newBuilder().build());
            try {
                u1467085.feedbins.OperationStatusResponse result = future.get(10, TimeUnit.SECONDS);
                if (result.getResult() == u1467085.feedbins.OperationStatusResponse.OperationStatus.SUCCESS) {
                    responseObserver.onNext(OperationStatusResponse.newBuilder()
                            .setResult(OperationStatusResponse.OperationStatus.SUCCESS).build());
                } else {
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

            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Tried to flush invalid bin record").build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void inspectBin(BinId request, StreamObserver<ControllerBinStatusUpdate> responseObserver) {
        Optional<ProductionLineBin> bin = productionLine.stream().filter(productionLineBin ->
                Objects.equals(request.getAddress(), productionLineBin.address)
                        && request.getPort() == productionLineBin.port).findFirst();
        if (bin.isPresent()) {
            ListenableFuture<BinStatusUpdate> future = bin.get().actionCallerStub
                    .inspectBin(Empty.newBuilder().build());
            try {
                BinStatusUpdate result = future.get(10, TimeUnit.SECONDS);
                responseObserver.onNext(ControllerBinStatusUpdate.newBuilder()
                        .setMaxAmount(result.getMaxAmount())
                        .setRecord(request)
                        .setAmount(result.getAmount().getStuffAmount())
                        .setStuff(Stuff.newBuilder().setStuffName(result.getStuff().getStuffName())).build());
            } catch (InterruptedException | TimeoutException | ExecutionException e) {
                responseObserver.onError(e);
            }
        } else {
            responseObserver.onError(Status.INVALID_ARGUMENT.asException());
        }

        responseObserver.onCompleted();
    }

    @Override
    public synchronized void addStuff(FillBin request, StreamObserver<OperationStatusResponse> responseObserver) {
        Optional<ProductionLineBin> bin = productionLine.stream().filter(productionLineBin ->
                Objects.equals(request.getId().getAddress(), productionLineBin.address)
                        && request.getId().getPort() == productionLineBin.port).findFirst();
        if (bin.isPresent()) {
            ListenableFuture<u1467085.feedbins.OperationStatusResponse> future = bin.get().actionCallerStub
                    .addStuff(StuffAmount.newBuilder().setStuffAmount(request.getAmount()).build());
            try {
                u1467085.feedbins.OperationStatusResponse result = future.get(10, TimeUnit.SECONDS);
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
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Trying to access invalid bin").build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public synchronized void changeStuff(ChangeBinStuff request, StreamObserver<OperationStatusResponse> responseObserver) {
        Optional<ProductionLineBin> bin = productionLine.stream().filter(productionLineBin ->
                Objects.equals(request.getId().getAddress(), productionLineBin.address)
                        && request.getId().getPort() == productionLineBin.port).findFirst();
        if (bin.isPresent()) {
            ListenableFuture<u1467085.feedbins.OperationStatusResponse> future = bin.get().actionCallerStub
                    .changeStuff(u1467085.feedbins.Stuff.newBuilder().setStuffName(request.getStuff().getStuffName()).build());
            try {
                u1467085.feedbins.OperationStatusResponse result = future.get(10, TimeUnit.SECONDS);
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
            responseObserver.onNext(OperationStatusResponse.newBuilder()
                    .setResult(OperationStatusResponse.OperationStatus.FAIL)
                    .setMessage("Trying to access invalid bin").build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void NotifyClients(ControllerBinStatusUpdate update) {
        observers.iterator().
                forEachRemaining(binStatusUpdateStreamObserver -> binStatusUpdateStreamObserver.onNext(update));
    }
}
