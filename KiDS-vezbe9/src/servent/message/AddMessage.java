package servent.message;

public class AddMessage extends BasicMessage{
    private int key;
    private String fileName;
    private boolean isPrivate;
    public AddMessage(int senderPort, int receiverPort, int key, String fileName, boolean isPrivate) {
        super(MessageType.ADD, senderPort, receiverPort, fileName);
        this.key = key;
        this.fileName = fileName;
        this.isPrivate=isPrivate;
//		super(MessageType.PUT, senderPort, receiverPort, key + ":" + value);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
