package servent.message;

public class HeartbeatMessage extends BasicMessage{
    private static final long serialVersionUID = -1625132731318034900L;

    public HeartbeatMessage(int senderPort, int receiverPort) {
        super(MessageType.HEARTBEAT, senderPort, receiverPort, "<3");
    }
}
