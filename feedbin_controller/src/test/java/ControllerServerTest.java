import bin.Bin;
import bin.FeedBinServiceImpl;
import bin.exceptions.BinOverflow;
import bin.exceptions.BinUnderflow;
import com.google.protobuf.Empty;
import controller.FeedBinControllerServiceImpl;
import controller.models.ProductionLineBin;
import io.grpc.*;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import u1467085.feedbincontroller.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerServerTest {

    private Bin bin;
    private Server binServer;
    private Server controllerServer;
    private ManagedChannel controllerChannel;
    private FeedBinControllerServiceGrpc.FeedBinControllerServiceFutureStub serviceStub;
    private FeedBinControllerServiceImpl service;

    @BeforeEach
    public void TestSetup() throws IOException, BinUnderflow, BinOverflow {
        //create a test bin server
        bin = new Bin(40);
        bin.changeStuffName("Product");
        bin.addAmount(20);
        binServer = ServerBuilder.forPort(9090).addService(new FeedBinServiceImpl(bin)).build().start();

        //create test bin controller server, channels and stubs to call the methods
        String serverName = InProcessServerBuilder.generateName();
        service = new FeedBinControllerServiceImpl();
        controllerServer = InProcessServerBuilder.forName(serverName)
                .addService(service).build().start();
        controllerChannel = InProcessChannelBuilder.forName(serverName).directExecutor().build();
        serviceStub = FeedBinControllerServiceGrpc.newFutureStub(controllerChannel);
    }

    @AfterEach
    public void TestTeardown() throws InterruptedException {
        controllerChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        controllerServer.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        binServer.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Tests that bin are correctly registered with the controller and that bins cannot be double registered
     */
    @Test
    public void TestAddBin() {
        try {
            OperationStatusResponse response = serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, response.getResult(),
                    "Adding bin command did not succeed");

            //use reflection to check that the bin record was added to the controller list
            Field field = FeedBinControllerServiceImpl.class.getDeclaredField("productionLine");
            field.setAccessible(true);
            ArrayList<ProductionLineBin> binList = (ArrayList<ProductionLineBin>) field.get(service);
            assertEquals(1, binList.size(), "Bin was not added to the production line list.");

            //try to add a duplicate of the bin to the list
            response = serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, response.getResult(),
                    "Duplicate bin was added to the list");
            assertEquals(1, binList.size(), "Duplicate bin was added to the list");
        } catch (InterruptedException | ExecutionException | TimeoutException | NoSuchFieldException | IllegalAccessException e) {
            fail(e);
        }
    }

    /**
     * Test that inspection endpoint is returning correct results
     */
    @Test
    public void TestInspectBin() {
        try {
            serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);

            ControllerBinStatusUpdate result = serviceStub.inspectBin(BinId.newBuilder()
                    .setAddress("localhost").setPort(9090).build()).get(10, TimeUnit.SECONDS);

            assertEquals(20, result.getAmount(), "Returned amount does not match");
            assertEquals("Product", result.getStuff().getStuffName(), "Returned stuff name does not match");
            assertEquals(40, result.getMaxAmount(), "Returned max amount does not match");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test that flush call is passed through correctly and the bin is actually flushed
     */
    @Test
    public void TestFlushBin() {
        try {
            serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);

            OperationStatusResponse result = serviceStub.flushBin(BinId.newBuilder()
                    .setAddress("localhost").setPort(9090).build()).get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, result.getResult(),
                    "Flush bin operation did not return success");
            assertEquals(0, bin.getStuffAmount(), "Bin contents were not flushed");

            //Try to flush bin that was not previously registered with the controller
            result = serviceStub.flushBin(BinId.newBuilder()
                    .setAddress("localhost").setPort(9091).build()).get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, result.getResult(),
                    "Flushing invalid bin should fail");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test that the add stuff request gets correctly passed to the bin
     */
    @Test
    public void TestAddBinStuff() {
        try {
            serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);

            OperationStatusResponse result = serviceStub.addStuff(FillBin.newBuilder()
                    .setId(BinId.newBuilder().setAddress("localhost").setPort(9090))
                    .setAmount(10).build()).get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, result.getResult(),
                    "Add stuff to bin operation did not return success");
            assertEquals(30, bin.getStuffAmount(), "Bin contents were not changed");

            //Try to add stuff to the bin that was not previously registered with the controller
            result = serviceStub.addStuff(FillBin.newBuilder()
                    .setId(BinId.newBuilder().setAddress("localhost").setPort(9091))
                    .setAmount(10).build()).get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, result.getResult(),
                    "Method on invalid bin should fail");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test that the invalid add stuff request returns fail result as expected
     */
    @Test
    public void TestAddBinStuffInvalid() {
        try {
            serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);

            OperationStatusResponse result = serviceStub.addStuff(FillBin.newBuilder()
                    .setId(BinId.newBuilder().setAddress("localhost").setPort(9090))
                    .setAmount(21).build()).get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, result.getResult(),
                    "Add stuff to bin should have returned fail");
            assertEquals(20, bin.getStuffAmount(), "Bin contents changed");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test that the add stuff request with a negative amount gets correctly passed to the bin
     */
    @Test
    public void TestRemoveBinStuff() {
        try {
            serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);

            OperationStatusResponse result = serviceStub.addStuff(FillBin.newBuilder()
                    .setId(BinId.newBuilder().setAddress("localhost").setPort(9090))
                    .setAmount(-10).build()).get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, result.getResult(),
                    "Remove stuff from bin operation did not return success");
            assertEquals(10, bin.getStuffAmount(), "Bin contents were not changed");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test that the add stuff request with an invalid negative amount fails as expected
     */
    @Test
    public void TestRemoveBinStuffInvalid() {
        try {
            serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);

            OperationStatusResponse result = serviceStub.addStuff(FillBin.newBuilder()
                    .setId(BinId.newBuilder().setAddress("localhost").setPort(9090))
                    .setAmount(-21).build()).get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, result.getResult(),
                    "Remove stuff from bin operation should have returned fail");
            assertEquals(20, bin.getStuffAmount(), "Bin contents changed");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Check that change contents request gets correctly passed to the bin and the contents of the bin get changed
     */
    @Test
    public void TestChangeBinStuff() {
        try {
            serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);

            OperationStatusResponse result = serviceStub.changeStuff(ChangeBinStuff.newBuilder()
                    .setId(BinId.newBuilder().setAddress("localhost").setPort(9090))
                    .setStuff(Stuff.newBuilder().setStuffName("NewProduct")).build()).get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, result.getResult(),
                    "Expected change content operation to succeed");
            assertEquals(0, bin.getStuffAmount(), "Expected bin contents to be empty");
            assertEquals("NewProduct", bin.getStuffName(), "Expected bin stuff name to change");

            //Try to change contents of the bin that was not previously registered with the controller
            result = serviceStub.changeStuff(ChangeBinStuff.newBuilder()
                    .setId(BinId.newBuilder().setAddress("localhost").setPort(9091))
                    .setStuff(Stuff.newBuilder().setStuffName("NewProduct")).build()).get(10, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, result.getResult(),
                    "Operation on invalid bin should fail");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Check that controller correctly sends out notifications upon bin changes and upon the initial connection
     */
    @Test
    public void TestBinUpdateMonitoring() {

        FeedBinControllerServiceGrpc.FeedBinControllerServiceStub serviceAsyncStub = FeedBinControllerServiceGrpc.newStub(controllerChannel);
        final boolean[] updated = {false};

        try {
            serviceStub
                    .addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build())
                    .get(10, TimeUnit.SECONDS);

            //cancelable context is required to graciously close streaming reuest when done
            Context.CancellableContext context = Context.current().withCancellation();
            context.run(() -> serviceAsyncStub.registerForBinUpdates(Empty.newBuilder().build(),
                    new StreamObserver<ControllerBinStatusUpdate>() {
                        @Override
                        public void onNext(ControllerBinStatusUpdate value) {
                            updated[0] = true;
                        }

                        @Override
                        public void onError(Throwable t) {
                        }

                        @Override
                        public void onCompleted() {

                        }
                    }));

            //wait for updates to go through since we are using async API
            synchronized (this) {
                this.wait(1000);
            }
            // check that any updates were performed
            assertTrue(updated[0], "Client was not updated upon first registration");

            //Try to change bin contents and see if update is sent out
            updated[0] = false;
            serviceStub.addStuff(FillBin.newBuilder()
                    .setId(BinId.newBuilder().setAddress("localhost").setPort(9090))
                    .setAmount(10).build()).get(10, TimeUnit.SECONDS);
            //wait for updates to go through since we are using async API
            synchronized (this) {
                this.wait(1000);
            }
            // check that any updates were performed
            assertTrue(updated[0], "Client was not notified about bin state change");

            // cancel notification stream
            context.cancel(Status.CANCELLED.asException());

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }
}
