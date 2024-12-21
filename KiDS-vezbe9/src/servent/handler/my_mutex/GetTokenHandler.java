package servent.handler.my_mutex;

import app.AppConfig;
import app.ServentInfo;
import mutex.DistributedMutex;
import mutex.SuzukiMutex;
import servent.handler.MessageHandler;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.my_mutex.TokenMessage;
import servent.message.util.MessageUtil;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GetTokenHandler implements MessageHandler {

    private final Message clientMessage;
    private SuzukiMutex mutex;
    private static Set<Message> receivedBroadcasts = Collections.newSetFromMap(new ConcurrentHashMap<Message, Boolean>());

    public GetTokenHandler(Message clientMessage, DistributedMutex mutex) {
        this.clientMessage = clientMessage;
        if(mutex instanceof SuzukiMutex){
            this.mutex = (SuzukiMutex) mutex;
        }
        else{
            AppConfig.timestampedErrorPrint("Mutex is not Suzuki Kasami.");
        }
    }


    @Override
    public void run() {
        TokenMessage tokenMessage = (TokenMessage) clientMessage;

        ServentInfo senderInfo = tokenMessage.getSenderInfo();

        if (senderInfo.getPort() == AppConfig.myServentInfo.getPort()) {
            AppConfig.timestampedStandardPrint("Got own message back. No rebroadcast.");
        } else {
            boolean didPut = receivedBroadcasts.add(clientMessage);

            if (didPut) {

                if(clientMessage.getMessageType() != MessageType.TOKEN) {
                    AppConfig.timestampedStandardPrint("Expected type of message is TOKEN.");
                    return;
                }
                if(tokenMessage.getQueue().peek().getChordId() == AppConfig.myServentInfo.getChordId()) {
                    AppConfig.myServentInfo.setTokenOwner(true);
                    AppConfig.myServentInfo.setTokenMessage(tokenMessage);
                    AppConfig.timestampedStandardPrint("I " +AppConfig.myServentInfo.getPort()+ " received the TOKEN.");
                    return;
                }

                for (ServentInfo neighbour : AppConfig.chordState.getAllNodeInfo()) {
                    if(!AppConfig.myServentInfo.equals(neighbour) )
                        MessageUtil.sendMessage(clientMessage);
                }
            } else {
                AppConfig.timestampedStandardPrint("Already had this. No rebroadcast.");
            }
        }
    }
}
