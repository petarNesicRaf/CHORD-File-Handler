package servent.message.mutex_messages;

import app.ServentInfo;
import servent.message.BasicMessage;
import servent.message.MessageType;

import java.util.Base64;

public class AskForToken extends BasicMessage {
    private int rn;
    private ServentInfo senderInfo;
    private ServentInfo receiverInfo;

    public AskForToken(ServentInfo senderInfo, ServentInfo receiverInfo, int rn) {
        super(MessageType.ASK_FOR_TOKEN,senderInfo, receiverInfo);
        this.rn = rn;
    }

    public int getRn() {
        return rn;
    }

    @Override
    public String toString() {
        return "AskForToken{" +
                "rn=" + rn +
                '}';
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public ServentInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(ServentInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public ServentInfo getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(ServentInfo receiverInfo) {
        this.receiverInfo = receiverInfo;
    }
}
