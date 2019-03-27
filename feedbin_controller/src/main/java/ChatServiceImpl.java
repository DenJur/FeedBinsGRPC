import io.grpc.stub.StreamObserver;
import u1467085.feedbins.ChatMessage;
import u1467085.feedbins.ChatMessageFromServer;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class ChatServiceImpl extends u1467085.feedbins.ChatServiceGrpc.ChatServiceImplBase {
    private static Set<StreamObserver<ChatMessageFromServer>> observers = ConcurrentHashMap.newKeySet();

    @Override
    public StreamObserver<ChatMessage> chat(StreamObserver<ChatMessageFromServer> responseObserver) {
        observers.add(responseObserver);

        return new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage value) {
                System.out.println(value);
                ChatMessageFromServer message = ChatMessageFromServer.newBuilder()
                        .setMessage(value)
                        .build();

                for (StreamObserver<ChatMessageFromServer> observer : observers) {
                    observer.onNext(message);
                }
            }

            @Override
            public void onError(Throwable t) {
                // do something;
            }

            @Override
            public void onCompleted() {
                observers.remove(responseObserver);
            }
        };
    }
}
