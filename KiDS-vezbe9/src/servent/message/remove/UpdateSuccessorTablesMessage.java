package servent.message.remove;

import app.AppConfig;
import servent.message.BasicMessage;
import servent.message.MessageType;

public class UpdateSuccessorTablesMessage extends BasicMessage {
    private int portToDelete;
    public UpdateSuccessorTablesMessage(int receiverPort, int portToDelete) {
        super(MessageType.UPDATE_SUCCESSOR_TABLES, AppConfig.myServentInfo.getListenerPort(), receiverPort);
        this.portToDelete = portToDelete;
    }

    public int getPortToDelete() {
        return portToDelete;
    }
}
