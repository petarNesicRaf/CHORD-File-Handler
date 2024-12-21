package servent.handler.token;

import mutex.SuzukiMutex;
import servent.handler.MessageHandler;
import servent.message.Message;
import servent.message.mutex_messages.AskForToken;
import servent.message.mutex_messages.AskTokenMessage;

public class AskSuzukiTokenHandler implements MessageHandler {
    private final AskTokenMessage askForTokenMessage;
    private final SuzukiMutex mutex;

    public AskSuzukiTokenHandler(Message message, SuzukiMutex mutex) {
        if (!(message instanceof AskTokenMessage)) {
            throw new IllegalArgumentException("AskForTokenHandler can only handle AskForToken messages.");
        }
        this.mutex = mutex;
        this.askForTokenMessage = (AskTokenMessage) message;
    }

    @Override
    public void run() {
        mutex.handleAskForToken(askForTokenMessage);
    }
}
