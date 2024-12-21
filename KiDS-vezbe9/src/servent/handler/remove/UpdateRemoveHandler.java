package servent.handler.remove;

import app.AppConfig;
import servent.handler.MessageHandler;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.remove.UpdatePredecessorMessage;
import servent.message.remove.UpdateSuccessorMessage;
import servent.message.remove.UpdateSuccessorTablesMessage;

public class UpdateRemoveHandler implements MessageHandler {
    private final Message clientMessage;

    public UpdateRemoveHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.UPDATE_PREDECESSOR) {
            UpdatePredecessorMessage updatePredecessorMessage = (UpdatePredecessorMessage) clientMessage;
            AppConfig.chordState.handleUpdatePredecessor(updatePredecessorMessage.getSenderPort());
        } else if (clientMessage.getMessageType() == MessageType.UPDATE_SUCCESSOR) {
            UpdateSuccessorMessage updateSuccessorMessage = (UpdateSuccessorMessage) clientMessage;
            AppConfig.chordState.handleUpdateSuccessor(updateSuccessorMessage.getSenderPort());
        } else if (clientMessage.getMessageType() == MessageType.UPDATE_SUCCESSOR_TABLES) {
            UpdateSuccessorTablesMessage updateSuccessorTablesMessage = (UpdateSuccessorTablesMessage) clientMessage;
            AppConfig.chordState.handleUpdateSuccessorTables(updateSuccessorTablesMessage.getPortToDelete());
        } else {
            AppConfig.timestampedErrorPrint("UpdateHandler received an unknown message type.");
        }
    }
}
