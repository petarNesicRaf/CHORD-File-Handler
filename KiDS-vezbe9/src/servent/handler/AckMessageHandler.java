package servent.handler;

import app.AppConfig;
import app.ServentInfo;
import servent.message.AckMessage;
import servent.message.Message;

public class AckMessageHandler implements MessageHandler{
    private final AckMessage ackMessage;
    private final ServentInfo myServentInfo;

    public AckMessageHandler(Message message) {
        this.ackMessage = (AckMessage) message;
        this.myServentInfo = AppConfig.myServentInfo;
    }

    @Override
    public void run() {
        AppConfig.chordState.receiveAck(ackMessage.getSenderPort());
        //AppConfig.timestampedStandardPrint("Received ACK from " + ackMessage.getSenderPort());
    }
}
