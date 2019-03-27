import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import u1467085.feedbins.ChatMessage;
import u1467085.feedbins.ChatMessageFromServer;
import u1467085.feedbins.ChatServiceGrpc;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
        ChatServiceGrpc.ChatServiceStub chatService = ChatServiceGrpc.newStub(channel);
        CountDownLatch finishedLatch = new CountDownLatch(1);

        StreamObserver<ChatMessage> chat = chatService.chat(new StreamObserver<ChatMessageFromServer>() {
            @Override
            public void onNext(ChatMessageFromServer value) {
                System.out.println(value.getMessage().getMessage());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                System.out.println("Disconnected");
            }

            @Override
            public void onCompleted() {
                System.out.println("Disconnected");
                finishedLatch.countDown();
            }
        });

        Scanner scanner = new Scanner(System. in);
        String inputString = scanner. nextLine();
        while (!inputString.equalsIgnoreCase("quit")){
            chat.onNext(ChatMessage.newBuilder().setMessage(inputString).build());
            inputString=scanner.nextLine();
        }
        // Mark the end of requests
        chat.onCompleted();

    }
}
