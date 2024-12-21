package servent.handler;

import app.AppConfig;
import app.ServentInfo;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.RemoveNodeMessage;

public class RemoveNodeHandler implements MessageHandler{
    private Message clientMessage;

    public RemoveNodeHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }
    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.REMOVE_NODE) {
            RemoveNodeMessage removalMessage = (RemoveNodeMessage) clientMessage;
            ServentInfo removedNode = removalMessage.getRemovedNode();

            AppConfig.chordState.getAllNodeInfo().remove(removedNode);

            AppConfig.timestampedStandardPrint("Removed node from allNodeInfo: " + removedNode + " on node with port " + AppConfig.myServentInfo);
        } else {
            AppConfig.timestampedErrorPrint("Remove node handler got a message that is not REMOVE_NODE.");
        }
    }
}
