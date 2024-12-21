package servent.handler;

import app.AppConfig;
import servent.message.FileTransferMessage;
import servent.message.Message;
import servent.message.MessageType;

public class FileTransferHandler implements MessageHandler{
    private final Message clientMessage;

    public FileTransferHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.FILE_TRANSFER) {
            FileTransferMessage ftm = (FileTransferMessage) clientMessage;
            AppConfig.chordState.handleFileTransfer(ftm.getKey(), ftm.getFiles());
        } else {
            AppConfig.timestampedErrorPrint("FileTransferHandler got a message that is not FILE_TRANSFER.");
        }
    }
}
