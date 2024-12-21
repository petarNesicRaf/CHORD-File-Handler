package servent.message.my_mutex;

import app.ServentInfo;
import servent.message.BasicMessage;
import servent.message.MessageType;

public class RequestTokenMessage extends BasicMessage {
    private static final long serialVersionUID = 2084490973699262440L;
    private Integer sm;
    private ServentInfo senderInfo;
    private ServentInfo receiverInfo;

    public RequestTokenMessage(ServentInfo senderInfo, ServentInfo receiverInfo, long timestamp, int sm) {
        super(MessageType.ASK_FOR_TOKEN, senderInfo, receiverInfo, String.valueOf(timestamp));
        this.sm = sm;
        this.senderInfo = senderInfo;
        this.receiverInfo = receiverInfo;
    }

    public Integer getSm() {
        return sm;
    }

    public void setSm(Integer sm) {
        this.sm = sm;
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
