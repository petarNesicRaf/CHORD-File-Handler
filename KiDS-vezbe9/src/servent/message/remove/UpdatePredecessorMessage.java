package servent.message.remove;

import servent.message.BasicMessage;
import servent.message.MessageType;

public class UpdatePredecessorMessage extends BasicMessage {
    public UpdatePredecessorMessage(int senderPort, int receiverPort) {
        super(MessageType.UPDATE_PREDECESSOR, senderPort, receiverPort);
    }
}
