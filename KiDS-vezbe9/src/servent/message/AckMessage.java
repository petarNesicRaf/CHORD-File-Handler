package servent.message;

public class AckMessage extends BasicMessage{

    private static final long serialVersionUID = -3399914702933902313L;

    public AckMessage(int senderPort, int receiverPort) {
        super(MessageType.ACK, senderPort, receiverPort);
    }

}
