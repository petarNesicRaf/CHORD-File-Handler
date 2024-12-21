package servent.handler;

import app.AppConfig;
import servent.message.AddMessage;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.util.FileUtil;

public class AddHandler implements MessageHandler{



    private Message clientMessage;

    public AddHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }
    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.ADD) {
            AddMessage pm = (AddMessage) clientMessage;
            int key = pm.getKey();
            String fileName = pm.getFileName();

            FileUtil.addMyFile(AppConfig.ROOT_DIR, fileName, key, pm.isPrivate());
        } else {
            AppConfig.timestampedErrorPrint("Add handler got a message that is not ADD");
        }
    }
}
