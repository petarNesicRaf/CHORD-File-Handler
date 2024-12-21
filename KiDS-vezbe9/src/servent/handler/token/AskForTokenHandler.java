package servent.handler.token;

import app.AppConfig;
import app.ServentInfo;
import mutex.DistributedMutex;
import mutex.SuzukiKasamiDistributedMutex;
import servent.handler.MessageHandler;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.mutex_messages.AskForToken;
import servent.message.mutex_messages.TokenMessage;
import servent.message.util.MessageUtil;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AskForTokenHandler  {
    /*
    private final Message clientMessage;
    private SuzukiKasamiDistributedMutex mutex;
    private static Set<Message> receivedBroadcasts = Collections.newSetFromMap(new ConcurrentHashMap<Message, Boolean>());
    public AskForTokenHandler(Message clientMessage, DistributedMutex mutex) {
        this.clientMessage = clientMessage;
        if (mutex instanceof SuzukiKasamiDistributedMutex) {
            this.mutex = (SuzukiKasamiDistributedMutex) mutex;
        } else {
            AppConfig.timestampedErrorPrint("Mutex is not Suzuki Kasami.");
        }
    }

    @Override
    public void run() {
        AskForToken askForTokenMessage;

        if (clientMessage.getMessageType() != MessageType.ASK_FOR_TOKEN) {
            AppConfig.timestampedStandardPrint("Expected type of message is ASK_FOR_TOKEN.");
            return;
        }
        askForTokenMessage = (AskForToken) clientMessage;
        ServentInfo senderInfo = (ServentInfo) askForTokenMessage.getSenderInfo();

        if (senderInfo.getChordId() == AppConfig.myServentInfo.getChordId()) {
            AppConfig.timestampedStandardPrint("Got own message back. No rebroadcast.");
        } else {
            boolean didPut = receivedBroadcasts.add(clientMessage);

            if (didPut) {
               
                askForTokenMessage = (AskForToken) clientMessage;

                ServentInfo tempSender = (ServentInfo) ((AskForToken) clientMessage).getSenderInfo();
                long rnValue = AppConfig.myServentInfo.getRnMap().getOrDefault(tempSender, 0);
                if (askForTokenMessage.getRn() > rnValue) {
                    AppConfig.myServentInfo.getRnMap().put(tempSender, askForTokenMessage.getRn());
                }
                if (AppConfig.myServentInfo.isTokenOwner() &&
                        AppConfig.myServentInfo.getRnMap().getOrDefault(tempSender, 0) == AppConfig.myServentInfo.getLnMap().getOrDefault(tempSender, 0) + 1) {
                    // We are the token owner and the RN of the sender is as expected.
                    AppConfig.myServentInfo.getTokenMessage().getQueue().add(tempSender);
                    // Rebroadcast the token message
                    TokenMessage tokenMessage = new TokenMessage(
                            AppConfig.myServentInfo,
                            null,
                            AppConfig.myServentInfo.getToken().getLnMap(),
                            AppConfig.myServentInfo.getToken().getQueue()
                    );
                    for (ServentInfo neighbour : AppConfig.myServentInfo.getNeighbours()) {
                        MessageUtil.sendMessage(tokenMessage.changeReceiver(neighbour.getId()).makeMeASender());
                    }
                } else {
                    // Rebroadcast the ask for token message
                    for (ServentInfo neighbour : AppConfig.myServentInfo.getNeighbours()) {
                        MessageUtil.sendMessage(clientMessage.changeReceiver(neighbour.getId()).makeMeASender());
                    }
                }
            } else {
                // We already received this message.
                AppConfig.timestampedStandardPrint("Already had this. No rebroadcast.");
            }
        }
        
    }

     */
}
