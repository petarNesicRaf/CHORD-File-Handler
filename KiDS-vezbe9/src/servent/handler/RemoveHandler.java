package servent.handler;

import app.AppConfig;
import app.ServentInfo;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.RemoveMessage;
import servent.message.util.MessageUtil;

import java.io.File;
import java.util.List;

public class RemoveHandler implements MessageHandler{
    private Message clientMessage;

    public RemoveHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }
    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.REMOVE) {
            RemoveMessage removeMessage = (RemoveMessage) clientMessage;
            int key = removeMessage.getKey();
            String fileName = removeMessage.getFileName();

            AppConfig.chordState.removeValue(key, fileName);
        } else {
            AppConfig.timestampedErrorPrint("Remove handler got a message that is not REMOVE");
        }
    }
}
