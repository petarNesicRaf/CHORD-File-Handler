package servent.handler.token;

import mutex.SuzukiMutex;
import servent.handler.MessageHandler;
import servent.message.Message;
import servent.message.mutex_messages.SuzukiTokenMessage;
import servent.message.mutex_messages.TokenMessage;

public class SuzukiTokenHandler implements MessageHandler {
    private final SuzukiTokenMessage tokenMessage;
    private final SuzukiMutex mutex;

    public SuzukiTokenHandler(Message message, SuzukiMutex mutex) {
        if (!(message instanceof SuzukiTokenMessage)) {
            throw new IllegalArgumentException("TokenMessageHandler can only handle TokenMessage messages.");
        }

        this.tokenMessage = (SuzukiTokenMessage) message;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        mutex.handleToken(tokenMessage);
    }
}
