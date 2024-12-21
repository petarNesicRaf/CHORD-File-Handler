package servent.message;

import java.util.List;

public class ViewFilesResponseMessage extends BasicMessage{
    private List<String> fileNames;

    public ViewFilesResponseMessage(int senderPort, int receiverPort, List<String> fileNames) {
        super(MessageType.VIEW_FILES_RESPONSE,senderPort, receiverPort);
        this.fileNames = fileNames;
    }

    public List<String> getFileNames() {
        return fileNames;
    }
}
