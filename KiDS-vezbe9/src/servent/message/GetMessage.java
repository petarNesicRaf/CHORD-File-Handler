package servent.message;

public class GetMessage extends BasicMessage {
    private int key;
    private String fileName;

    public GetMessage(int senderPort, int receiverPort, String key, String fileName) {
        super(MessageType.GET,senderPort, receiverPort);
        this.key = Integer.parseInt(key);
        this.fileName = fileName;
    }

    public int getKey() {
        return key;
    }

    public String getFileName() {
        return fileName;
    }
}
