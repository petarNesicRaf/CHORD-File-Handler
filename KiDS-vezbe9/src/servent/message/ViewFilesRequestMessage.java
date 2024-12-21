package servent.message;

public class ViewFilesRequestMessage extends BasicMessage{

    public ViewFilesRequestMessage(int senderPort, int receiverPort) {
        super(MessageType.VIEW_FILES_REQUEST,senderPort, receiverPort);
    }
}
