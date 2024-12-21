package servent.handler;

import app.AppConfig;
import servent.message.FileReplicationMessage;
import servent.message.Message;
import servent.message.MessageType;

public class FileReplicationHandler implements MessageHandler{
    private final Message clientMessage;

    public FileReplicationHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.FILE_REPLICATION) {
            FileReplicationMessage frm = (FileReplicationMessage) clientMessage;
            AppConfig.chordState.handleFileReplication(frm.getKey(), frm.getFiles());
        } else {
            AppConfig.timestampedErrorPrint("FileReplicationHandler got a message that is not FILE_REPLICATION.");
        }
    }
}
