package servent.message;

import servent.message.util.MyFile;

import java.io.File;
import java.util.List;

public class TGetMessage extends BasicMessage {
    private int key;
    private List<MyFile> files;

    public TGetMessage(int senderPort, int receiverPort, int key, List<MyFile> files) {
        super(MessageType.TGET,senderPort, receiverPort);
        this.key = key;
        this.files = files;
    }

    public int getKey() {
        return key;
    }

    public List<MyFile> getFiles() {
        return files;
    }
}
