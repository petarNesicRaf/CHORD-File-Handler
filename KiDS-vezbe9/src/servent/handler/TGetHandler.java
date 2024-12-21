package servent.handler;

import app.AppConfig;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.TGetMessage;
import servent.message.util.MyFile;

import java.io.File;
import java.util.List;

public class TGetHandler implements MessageHandler{
    private Message clientMessage;
    public TGetHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }
    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.TGET) {
            TGetMessage tgm = (TGetMessage) clientMessage;
            List<MyFile> files = tgm.getFiles();

            if (!files.isEmpty()) {
                for (MyFile file : files) {
                    AppConfig.timestampedStandardPrint("File received: " + file.getFile().getName() + " is private "+ file.isPrivate());
                }
            } else {
                AppConfig.timestampedErrorPrint("Requested file not found.");
            }
        } else {
            AppConfig.timestampedErrorPrint("Tell get handler got a message that is not TELL_GET");
        }
    }
}
