package servent.message.remove;

import servent.message.BasicMessage;
import servent.message.MessageType;

public class UpdateSuccessorMessage extends BasicMessage {
    public UpdateSuccessorMessage(int senderPort, int receiverPort) {
        super(MessageType.UPDATE_SUCCESSOR, senderPort, receiverPort);
    }
}
