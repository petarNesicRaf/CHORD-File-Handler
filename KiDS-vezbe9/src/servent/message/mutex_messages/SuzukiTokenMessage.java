package servent.message.mutex_messages;

import app.ServentInfo;
import servent.message.BasicMessage;
import servent.message.Message;
import servent.message.MessageType;

public class SuzukiTokenMessage extends BasicMessage {
    private static final long serialVersionUID = -2494019917888579003L;

    public SuzukiTokenMessage(ServentInfo senderInfo, ServentInfo receiverInfo) {
        super(MessageType.TOKEN, senderInfo, receiverInfo);
    }
}
