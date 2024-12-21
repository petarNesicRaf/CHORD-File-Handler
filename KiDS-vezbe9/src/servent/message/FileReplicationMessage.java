package servent.message;

import servent.message.util.MyFile;

import java.util.List;

public class FileReplicationMessage extends BasicMessage{

    private static final long serialVersionUID = -7616392638388307884L;

    private final int key;
    private final List<MyFile> files;

    public FileReplicationMessage(int senderPort, int receiverPort, int key, List<MyFile> files) {
        super(MessageType.FILE_REPLICATION, senderPort, receiverPort);
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
