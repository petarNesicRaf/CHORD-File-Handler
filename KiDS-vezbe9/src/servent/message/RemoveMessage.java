package servent.message;

public class RemoveMessage extends BasicMessage{
    private static final long serialVersionUID = -750309484748813299L;

    private final int key;
    private final String fileName;

    public RemoveMessage(int senderPort, int receiverPort, int key, String fileName) {
        super(MessageType.REMOVE, senderPort, receiverPort);
        this.key = key;
        this.fileName = fileName;
    }

    public int getKey() {
        return key;
    }

    public String getFileName() {
        return fileName;
    }
}
