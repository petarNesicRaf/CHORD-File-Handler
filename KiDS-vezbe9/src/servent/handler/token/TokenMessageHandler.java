package servent.handler.token;

import app.AppConfig;
import app.ServentInfo;
import mutex.DistributedMutex;
import mutex.SuzukiKasamiDistributedMutex;
import servent.handler.MessageHandler;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.mutex_messages.TokenMessage;
import servent.message.util.MessageUtil;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TokenMessageHandler  {
    /*
    private final Message clientMessage;
    private SuzukiKasamiDistributedMutex mutex;
    private static Set<Message> receivedBroadcasts = Collections.newSetFromMap(new ConcurrentHashMap<Message, Boolean>());

    public TokenMessageHandler(Message clientMessage, DistributedMutex mutex) {
        this.clientMessage = clientMessage;
        if (mutex instanceof SuzukiKasamiDistributedMutex) {
            this.mutex = (SuzukiKasamiDistributedMutex) mutex;
        } else {
            AppConfig.timestampedErrorPrint("Mutex is not Suzuki Kasami.");
        }
    }
    @Override
    public void run() {
        if (clientMessage.getMessageType() != MessageType.TOKEN) {
            AppConfig.timestampedStandardPrint("Expected type of message is TOKEN.");
            return;
        }
        TokenMessage tokenMessage = (TokenMessage) clientMessage;

        ServentInfo senderInfo =tokenMessage.getSenderInfo();

        if (senderInfo.getChordId() == AppConfig.myServentInfo.getChordId()) {
            AppConfig.timestampedStandardPrint("Got own message back. No rebroadcast.");
        } else {
            boolean didPut = receivedBroadcasts.add(clientMessage);

            if (didPut) {


                if (tokenMessage.getQueue().peek().getChordId() == AppConfig.myServentInfo.getChordId()) {
                    AppConfig.myServentInfo.setTokenOwner(true);
                    AppConfig.myServentInfo.setTokenMessage(tokenMessage);
                    AppConfig.timestampedStandardPrint("I received the TOKEN I requested.");
                    return;
                }

                AppConfig.timestampedStandardPrint("Rebroadcasting... " + receivedBroadcasts.size());
                broadcastMessage(clientMessage);
            } else {
                AppConfig.timestampedStandardPrint("Already had this. No rebroadcast.");
            }
        }
    }

    private void broadcastMessage(Message message) {
        MessageUtil.broadCastMessage(message);
    }


     */
}
