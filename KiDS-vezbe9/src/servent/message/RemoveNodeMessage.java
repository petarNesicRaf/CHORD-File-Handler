package servent.message;

import app.ServentInfo;

public class RemoveNodeMessage extends BasicMessage{
    private static final long serialVersionUID = -1234567890123456789L;
    private final ServentInfo removedNode;

    public RemoveNodeMessage(int senderPort, int receiverPort, ServentInfo removedNode) {
        super(MessageType.REMOVE_NODE, senderPort, receiverPort);
        this.removedNode = removedNode;
    }

    public ServentInfo getRemovedNode() {
        return removedNode;
    }
}
