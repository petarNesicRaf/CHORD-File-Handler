package servent.message.mutex_messages;

import app.ServentInfo;
import servent.message.BasicMessage;
import servent.message.MessageType;

import java.util.*;

public class TokenMessage extends BasicMessage {

    private Map<ServentInfo, Integer> lnMap;
    private Queue<ServentInfo> queue;
    private ServentInfo senderInfo;
    private ServentInfo receiverInfo;
    public TokenMessage(ServentInfo senderInfo, ServentInfo receiverInfo,
                        Map<ServentInfo, Integer> lnMap, Queue<ServentInfo> queue) {
        super(MessageType.TOKEN,senderInfo.getListenerPort(), receiverInfo.getListenerPort());
        this.lnMap = lnMap != null ? new HashMap<>(lnMap) : new HashMap<>();
        this.queue = queue != null ? new LinkedList<>(queue) : new LinkedList<>();
    }

    public Map<ServentInfo, Integer> getLnMap() {
        return lnMap;
    }

    public Queue<ServentInfo> getQueue() {
        return queue;
    }

    @Override
    public String toString() {
        return "TokenMessage{" +
                "lnMap=" + lnMap +
                ", queue=" + queue +
                '}';
    }

    public void setLnMap(Map<ServentInfo, Integer> lnMap) {
        this.lnMap = lnMap;
    }

    public void setQueue(Queue<ServentInfo> queue) {
        this.queue = queue;
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
