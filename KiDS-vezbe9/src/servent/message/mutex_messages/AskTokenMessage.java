package servent.message.mutex_messages;

import app.ServentInfo;
import servent.message.BasicMessage;
import servent.message.MessageType;

import java.util.Base64;

public class AskTokenMessage extends BasicMessage {
    private static final long serialVersionUID = 7474944371569484050L;
    private int rn;
    private ServentInfo senderInfo;
    private ServentInfo receiverInfo;
    public AskTokenMessage(ServentInfo senderInfo, ServentInfo receiverInfo, int rn) {
        super(MessageType.ASK_FOR_TOKEN, senderInfo, receiverInfo);
        this.rn = rn;
    }

    public int getRn() {
        return rn;
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
