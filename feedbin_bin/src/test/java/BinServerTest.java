import bin.Bin;
import bin.FeedBinServiceImpl;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Empty;
import bin.exceptions.BinOverflow;
import bin.exceptions.BinUnderflow;
import io.grpc.Context;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.Status;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import u1467085.feedbins.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class BinServerTest {

    private FeedBinServiceGrpc.FeedBinServiceFutureStub serviceStub;
    private Bin bin;
    private ManagedChannel channel;
    private Server server;

    /**
     * Creates default server-client test configuration.
     *
     * @throws IOException
     * @throws BinUnderflow
     * @throws BinOverflow
     */
    @BeforeEach
    public void TestSetup() throws IOException, BinUnderflow, BinOverflow {
        String serverName = InProcessServerBuilder.generateName();
        bin = new Bin(40);
        bin.changeStuffName("Product");
        bin.addAmount(20);
        server = InProcessServerBuilder.forName(serverName)
                .addService(new FeedBinServiceImpl(bin)).build().start();
        channel = InProcessChannelBuilder.forName(serverName).directExecutor().build();
        serviceStub = FeedBinServiceGrpc.newFutureStub(channel);
    }

    @AfterEach
    public void TestTeardown() throws InterruptedException {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Check that server returns correct information on inspectBin call
     */
    @Test
    public void TestInspectBin() {
        ListenableFuture<BinStatusUpdate> future = serviceStub.inspectBin(Empty.newBuilder().build());
        try {
            BinStatusUpdate result = future.get(1, TimeUnit.SECONDS);
            assertEquals(20, result.getAmount().getStuffAmount(), "bin.Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "bin.Bin inspection return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin server flush call
     */
    @Test
    public void TestBinFlush() {
        try {
            OperationStatusResponse flushResult = serviceStub.flushBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, flushResult.getResult(), "Flush did not return success");

            //compare to information from inspect
            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(0, result.getAmount().getStuffAmount(), "bin.Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "bin.Bin inspection return wrong stuff name");

            //compare to information from bin object
            assertEquals(0, bin.getStuffAmount(), "bin.Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "bin.Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin add call with a valid request
     */
    @Test
    public void TestBinAdd() {
        try {
            OperationStatusResponse addResult = serviceStub.addStuff(StuffAmount.newBuilder().setStuffAmount(10).build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, addResult.getResult(), "Add did not return success");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(30, result.getAmount().getStuffAmount(), "bin.Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "bin.Bin inspection return wrong stuff name");

            assertEquals(30, bin.getStuffAmount(), "bin.Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "bin.Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin add with an invalid request
     */
    @Test
    public void TestBinAddInvalid() {
        try {
            OperationStatusResponse addResult = serviceStub.addStuff(StuffAmount.newBuilder().setStuffAmount(21).build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, addResult.getResult(), "Flush did not return fail");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(20, result.getAmount().getStuffAmount(), "bin.Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "bin.Bin inspection return wrong stuff name");

            assertEquals(20, bin.getStuffAmount(), "bin.Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "bin.Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin add with a negative stuff amount
     */
    @Test
    public void TestBinRemove() {
        try {
            OperationStatusResponse addResult = serviceStub.addStuff(StuffAmount.newBuilder().setStuffAmount(-10).build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, addResult.getResult(), "Remove did not return success");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(10, result.getAmount().getStuffAmount(), "bin.Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "bin.Bin inspection return wrong stuff name");

            assertEquals(10, bin.getStuffAmount(), "bin.Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "bin.Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin add with a negative invalid amount of stuff
     */
    @Test
    public void TestBinRemoveInvalid() {
        try {
            OperationStatusResponse addResult = serviceStub.addStuff(StuffAmount.newBuilder().setStuffAmount(-21).build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, addResult.getResult(), "Flush did not return fail");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(20, result.getAmount().getStuffAmount(), "bin.Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "bin.Bin inspection return wrong stuff name");

            assertEquals(20, bin.getStuffAmount(), "bin.Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "bin.Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test that bin product name is changed correctly
     */
    @Test
    public void TestBinChangeStuff() {
        try {
            OperationStatusResponse addResult = serviceStub.changeStuff(Stuff.newBuilder().setStuffName("NewProduct").build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, addResult.getResult(), "Rename did not return success");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(0, result.getAmount().getStuffAmount(), "bin.Bin inspection return wrong stuff amount");
            assertEquals("NewProduct", result.getStuff().getStuffName(), "bin.Bin inspection return wrong stuff name");

            assertEquals(0, bin.getStuffAmount(), "bin.Bin getter return wrong stuff amount");
            assertEquals("NewProduct", bin.getStuffName(), "bin.Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test that server sends out notifications about bin changes correctly
     */
    @Test
    public void TestBinUpdateMonitoring() {
        //need to create async stub for streaming
        FeedBinServiceGrpc.FeedBinServiceStub serviceAsyncStub = FeedBinServiceGrpc.newStub(channel);
        final boolean[] updated = {false};
        try {
            //cancelable context is required to graciously close streaming request when done
            Context.CancellableContext context = Context.current().withCancellation();
            context.run(() -> serviceAsyncStub
                    .registerForUpdates(Empty.newBuilder().build(), new StreamObserver<BinStatusUpdate>() {
                        @Override
                        public void onNext(BinStatusUpdate value) {
                            assertEquals(20, value.getAmount().getStuffAmount(), "Update return wrong amount");
                            assertEquals("Product", value.getStuff().getStuffName(), "Update return wrong name");
                            updated[0] = true;
                        }

                        @Override
                        public void onError(Throwable t) {

                        }

                        @Override
                        public void onCompleted() {

                        }
                    }));

            synchronized (this){
                this.wait(1000);
            }
            // cancel notification stream
            context.cancel(Status.CANCELLED.asException());
            // check that any updates were performed
            assertTrue(updated[0], "Client was never updated");

        } catch (InterruptedException e) {
            fail(e);
        }
    }
}
