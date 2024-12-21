package servent.handler;

import app.AppConfig;
import app.ServentInfo;
import servent.message.GetMessage;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.TGetMessage;
import servent.message.util.MessageUtil;
import servent.message.util.MyFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetHandler implements MessageHandler{
    private Message clientMessage;

    public GetHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.GET) {
            GetMessage agm = (GetMessage) clientMessage;
            int key = agm.getKey();
            String fileName = agm.getFileName();

            AppConfig.timestampedStandardPrint("Handling GET message for key: " + key + ", fileName: " + fileName);

            if (AppConfig.chordState.isKeyMine(key)) {
                Map<Integer, List<MyFile>> valueMap = AppConfig.chordState.getValueMap();
                List<MyFile> fileList = new ArrayList<>();

                if (valueMap.containsKey(key)) {
                    for (MyFile file : valueMap.get(key)) {
                        if (file.getFile().getName().equals(fileName)) {
                            fileList.add(file);
                        }
                    }
                }

                TGetMessage tgm = new TGetMessage(AppConfig.myServentInfo.getListenerPort(), clientMessage.getSenderPort(),
                        key, fileList);

                AppConfig.timestampedStandardPrint("Sending TGET message to: " + clientMessage.getSenderPort());

                MessageUtil.sendMessage(tgm);
            } else {
                ServentInfo nextNode = AppConfig.chordState.getNextNodeForKey(key);
                GetMessage agmForward = new GetMessage(clientMessage.getSenderPort(), nextNode.getListenerPort(), String.valueOf(key), fileName);
                MessageUtil.sendMessage(agmForward);
            }
        } else {
            AppConfig.timestampedErrorPrint("Ask get handler got a message that is not ASK_GET");
        }
    }
}
