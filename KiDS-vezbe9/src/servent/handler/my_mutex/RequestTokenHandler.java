package servent.handler.my_mutex;

import app.AppConfig;
import app.ServentInfo;
import mutex.DistributedMutex;
import mutex.SuzukiMutex;
import servent.handler.MessageHandler;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.my_mutex.RequestTokenMessage;
import servent.message.my_mutex.TokenMessage;
import servent.message.util.MessageUtil;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RequestTokenHandler implements MessageHandler {
    private final Message clientMessage;
    private SuzukiMutex mutex;
    private static Set<Message> receivedBroadcasts = Collections.newSetFromMap(new ConcurrentHashMap<Message, Boolean>());

    public RequestTokenHandler(Message clientMessage, DistributedMutex mutex) {
        this.clientMessage = clientMessage;
        if (mutex instanceof SuzukiMutex) {
            this.mutex = (SuzukiMutex) mutex;
        } else {
            AppConfig.timestampedErrorPrint("Error in request token handler. Mutex is not Suzuki-Kasami.");
        }
    }


    @Override
    public void run() {
        long messageTimeStamp = Long.parseLong(clientMessage.getMessageText());
        mutex.updateTimeStamp(messageTimeStamp);
        if (clientMessage.getMessageType() != MessageType.ASK_FOR_TOKEN) {
            AppConfig.timestampedErrorPrint("Expected message type ASK_FOR_TOKEN.");
            return;
        }
        RequestTokenMessage aMessage = (RequestTokenMessage) clientMessage;
        ServentInfo senderInfo = aMessage.getSenderInfo();

        if (senderInfo.getChordId() == AppConfig.myServentInfo.getChordId()) {
            AppConfig.timestampedStandardPrint("Got own message back.");
        } else {
            boolean isPresent = receivedBroadcasts.add(clientMessage);
            if (isPresent) {
                Map<ServentInfo, Integer> rn = AppConfig.myServentInfo.getRnMap();

                rn.putIfAbsent(senderInfo, 0);

                if (aMessage.getSm() > rn.get(senderInfo)) {
                    AppConfig.myServentInfo.getRnMap().put(senderInfo, aMessage.getSm());
                } else {
                    return;
                }


                if (AppConfig.myServentInfo.isTokenOwner()) {
                    if (this.mutex.isInitiated().get()) {
                        AppConfig.myServentInfo.getTokenMessage().getQueue().add(senderInfo);
                    } else {
                        ServentInfo nextReceiver = AppConfig.myServentInfo.getTokenMessage().getQueue().peek();
                        if (nextReceiver == null) {
                            nextReceiver = aMessage.getSenderInfo();
                        }
                        TokenMessage tokenMessage = new TokenMessage(
                                AppConfig.myServentInfo,
                                nextReceiver,
                                AppConfig.myServentInfo.getTokenMessage().getLnMap(),
                                AppConfig.myServentInfo.getTokenMessage().getQueue());
                        //nije potreban token
                        AppConfig.myServentInfo.setTokenOwner(false);
                        if (AppConfig.myServentInfo.getTokenMessage().getQueue().peek() == null) {
                            tokenMessage.getQueue().add(aMessage.getSenderInfo());
                        }
                        for (ServentInfo neighbour : AppConfig.chordState.getAllNodeInfo()) {
                            if (!AppConfig.myServentInfo.equals(neighbour))
                                MessageUtil.sendMessage(tokenMessage);
                        }
                    }
                    return;
                }
                // salji dalje zahtev
                AppConfig.timestampedStandardPrint("Rebroadcasting... " + receivedBroadcasts.size());
                for (ServentInfo neighbour : AppConfig.chordState.getAllNodeInfo()) {
                    if (AppConfig.myServentInfo.equals(neighbour)) {
                        continue;
                    }
                    MessageUtil.sendMessage(clientMessage);
                }

            } else {
                AppConfig.timestampedStandardPrint("Already had this. No rebroadcast.");
            }
        }
    }
}
