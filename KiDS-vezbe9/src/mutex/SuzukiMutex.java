package mutex;

import app.AppConfig;
import app.ChordState;
import app.ServentInfo;
import servent.message.Message;
import servent.message.mutex_messages.AskForToken;
import servent.message.mutex_messages.AskTokenMessage;
import servent.message.mutex_messages.SuzukiTokenMessage;
import servent.message.mutex_messages.TokenMessage;
import servent.message.util.MessageUtil;

import java.util.HashMap;
import java.util.Map;

public class SuzukiMutex implements DMutex{
    private ServentInfo myServentInfo;

    private int rn = 0;
    private boolean token = false;
    private boolean askedForToken = false;

    private Map<ServentInfo, Boolean> outstandingRequests = new HashMap<>();

    public SuzukiMutex(ServentInfo myServentInfo) {
        this.myServentInfo = myServentInfo;
    }

    @Override
    public synchronized void askForCriticalSection() {
        if (!token && !askedForToken) {
            askedForToken = true;
            rn++;

            for (ServentInfo neighbour : AppConfig.chordState.chordNeighbours()) {
                if (!outstandingRequests.containsKey(neighbour)) {
                    AskTokenMessage askForToken = new AskTokenMessage(myServentInfo, neighbour, rn);
                    MessageUtil.sendMessage(askForToken);
                    outstandingRequests.put(neighbour, true);
                }
            }
        }
    }

    @Override
    public synchronized void releaseCriticalSection() {
        token = false;
        askedForToken = false;
        outstandingRequests.clear();

        for (ServentInfo neighbour : AppConfig.chordState.chordNeighbours()) {
            SuzukiTokenMessage tokenMessage = new SuzukiTokenMessage(myServentInfo, neighbour);
            MessageUtil.sendMessage(tokenMessage);
        }
    }

    public synchronized void handleToken(Message tokenMessage) {
        token = true;
    }

    public synchronized void handleAskForToken(Message clientMessage) {
        if (!token && !askedForToken && clientMessage instanceof AskTokenMessage) {
             AskTokenMessage askForTokenMessage = (AskTokenMessage) clientMessage;
            int senderRn = askForTokenMessage.getRn();
            int senderId = askForTokenMessage.getSenderInfo().getChordId();

            if (senderRn > rn || (senderRn == rn && senderId > myServentInfo.getChordId())) {
                rn = senderRn;

                for (ServentInfo neighbour : AppConfig.chordState.chordNeighbours()) {
                    if (!outstandingRequests.containsKey(neighbour) && !neighbour.equals(askForTokenMessage.getSenderInfo())) {
                        AskForToken askForToken = new AskForToken(myServentInfo, neighbour, rn);
                        MessageUtil.sendMessage(askForToken);
                        outstandingRequests.put(neighbour, true);
                    }
                }
            }
        }
    }
}
