import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Empty;
import exceptions.BinOverflow;
import exceptions.BinUnderflow;
import io.grpc.Context;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.Test;
import u1467085.feedbins.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class BinServerTest {
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    private FeedBinServiceGrpc.FeedBinServiceFutureStub serviceStub;
    private Bin bin;
    private ManagedChannel channel;

    /**
     * Creates default server-client test configuration.
     *
     * @throws IOException
     * @throws BinUnderflow
     * @throws BinOverflow
     */
    private void TestSetup() throws IOException, BinUnderflow, BinOverflow {
        String serverName = InProcessServerBuilder.generateName();
        bin = new Bin(40);
        bin.changeStuffName("Product");
        bin.addAmount(20);
        grpcCleanup.register(InProcessServerBuilder.forName(serverName)
                .addService(new FeedBinServiceImpl(bin)).build().start());
        channel = grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build());
        serviceStub = FeedBinServiceGrpc.newFutureStub(channel);
    }

    /**
     * Check that server returns correct information on inspectBin call
     *
     * @throws BinOverflow
     * @throws BinUnderflow
     * @throws IOException
     */
    @Test
    public void TestInspectBin() throws BinOverflow, BinUnderflow, IOException {
        TestSetup();
        ListenableFuture<BinStatusUpdate> future = serviceStub.inspectBin(Empty.newBuilder().build());
        try {
            BinStatusUpdate result = future.get(1, TimeUnit.SECONDS);
            assertEquals(20, result.getAmount().getStuffAmount(), "Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "Bin inspection return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin server flush call
     *
     * @throws BinOverflow
     * @throws BinUnderflow
     * @throws IOException
     */
    @Test
    public void TestBinFlush() throws BinOverflow, BinUnderflow, IOException {
        TestSetup();
        try {
            OperationStatusResponse flushResult = serviceStub.flushBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, flushResult.getResult(), "Flush did not return success");

            //compare to information from inspect
            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(0, result.getAmount().getStuffAmount(), "Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "Bin inspection return wrong stuff name");

            //compare to information from bin object
            assertEquals(0, bin.getStuffAmount(), "Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin add call with a valid request
     *
     * @throws BinOverflow
     * @throws BinUnderflow
     * @throws IOException
     */
    @Test
    public void TestBinAdd() throws BinOverflow, BinUnderflow, IOException {
        TestSetup();
        try {
            OperationStatusResponse addResult = serviceStub.addStuff(StuffAmount.newBuilder().setStuffAmount(10).build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, addResult.getResult(), "Add did not return success");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(30, result.getAmount().getStuffAmount(), "Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "Bin inspection return wrong stuff name");

            assertEquals(30, bin.getStuffAmount(), "Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin add with an invalid request
     *
     * @throws BinOverflow
     * @throws BinUnderflow
     * @throws IOException
     */
    @Test
    public void TestBinAddInvalid() throws BinOverflow, BinUnderflow, IOException {
        TestSetup();
        try {
            OperationStatusResponse addResult = serviceStub.addStuff(StuffAmount.newBuilder().setStuffAmount(21).build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, addResult.getResult(), "Flush did not return fail");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(20, result.getAmount().getStuffAmount(), "Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "Bin inspection return wrong stuff name");

            assertEquals(20, bin.getStuffAmount(), "Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin add with a negative stuff amount
     *
     * @throws BinOverflow
     * @throws BinUnderflow
     * @throws IOException
     */
    @Test
    public void TestBinRemove() throws BinOverflow, BinUnderflow, IOException {
        TestSetup();
        try {
            OperationStatusResponse addResult = serviceStub.addStuff(StuffAmount.newBuilder().setStuffAmount(-10).build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, addResult.getResult(), "Remove did not return success");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(10, result.getAmount().getStuffAmount(), "Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "Bin inspection return wrong stuff name");

            assertEquals(10, bin.getStuffAmount(), "Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test bin add with a negative invalid amount of stuff
     *
     * @throws BinOverflow
     * @throws BinUnderflow
     * @throws IOException
     */
    @Test
    public void TestBinRemoveInvalid() throws BinOverflow, BinUnderflow, IOException {
        TestSetup();
        try {
            OperationStatusResponse addResult = serviceStub.addStuff(StuffAmount.newBuilder().setStuffAmount(-21).build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.FAIL, addResult.getResult(), "Flush did not return fail");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(20, result.getAmount().getStuffAmount(), "Bin inspection return wrong stuff amount");
            assertEquals("Product", result.getStuff().getStuffName(), "Bin inspection return wrong stuff name");

            assertEquals(20, bin.getStuffAmount(), "Bin getter return wrong stuff amount");
            assertEquals("Product", bin.getStuffName(), "Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test that bin product name is changed correctly
     *
     * @throws BinOverflow
     * @throws BinUnderflow
     * @throws IOException
     */
    @Test
    public void TestBinChangeStuff() throws BinOverflow, BinUnderflow, IOException {
        TestSetup();
        try {
            OperationStatusResponse addResult = serviceStub.changeStuff(Stuff.newBuilder().setStuffName("NewProduct").build()).get(1, TimeUnit.SECONDS);
            assertEquals(OperationStatusResponse.OperationStatus.SUCCESS, addResult.getResult(), "Rename did not return success");

            BinStatusUpdate result = serviceStub.inspectBin(Empty.newBuilder().build()).get(1, TimeUnit.SECONDS);
            assertEquals(0, result.getAmount().getStuffAmount(), "Bin inspection return wrong stuff amount");
            assertEquals("NewProduct", result.getStuff().getStuffName(), "Bin inspection return wrong stuff name");

            assertEquals(0, bin.getStuffAmount(), "Bin getter return wrong stuff amount");
            assertEquals("NewProduct", bin.getStuffName(), "Bin getter return wrong stuff name");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }

    /**
     * Test that server sends out notifications about bin changes correctly
     *
     * @throws BinOverflow
     * @throws BinUnderflow
     * @throws IOException
     */
    @Test
    public void TestBinUpdateMonitoring() throws BinOverflow, BinUnderflow, IOException {
        TestSetup();
        //need to create async stub for streaming
        FeedBinServiceGrpc.FeedBinServiceStub serviceAsyncStub = FeedBinServiceGrpc.newStub(channel);
        final boolean[] updated = {false};
        try {
            //cancelable context is required to graciously close streaming reuest when done
            Context.CancellableContext context = Context.current().withCancellation();
            context.run(() -> serviceAsyncStub
                    .registerForUpdates(Empty.newBuilder().build(), new StreamObserver<BinStatusUpdate>() {
                        @Override
                        public void onNext(BinStatusUpdate value) {
                            assertEquals(30, value.getAmount().getStuffAmount(), "Update return wrong amount");
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

            // run a test add command so that bin update can get propagated to clients
            serviceStub.addStuff(StuffAmount.newBuilder().setStuffAmount(10).build()).get(1, TimeUnit.SECONDS);
            // cancel notification stream
            context.cancel(Status.CANCELLED.asException());
            // check that any updates were performed
            assertTrue(updated[0], "Client was never updated");

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            fail(e);
        }
    }
}
