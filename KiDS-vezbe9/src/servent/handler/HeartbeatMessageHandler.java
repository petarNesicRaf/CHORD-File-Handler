package servent.handler;

import app.AppConfig;
import servent.message.Message;
import servent.message.MessageType;

public class HeartbeatMessageHandler implements MessageHandler{
    private Message clientMessage;

    public HeartbeatMessageHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }
    @Override
    public void run() {
        if(!clientMessage.getMessageType().equals(MessageType.HEARTBEAT)){
            AppConfig.timestampedErrorPrint("Heartbeat message handler received a message that is not type HEARTBEAT");
            return;
        }

        AppConfig.chordState.receiveHeartbeat(clientMessage.getSenderPort());
    }
}
