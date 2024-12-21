package servent.handler;

import app.AppConfig;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.ViewFilesResponseMessage;
import servent.message.util.MessageUtil;
import servent.message.util.MyFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewFilesRequestHandler implements MessageHandler{
    private Message clientMessage;

    public ViewFilesRequestHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.VIEW_FILES_REQUEST) {
            Map<Integer, List<MyFile>> valueMap = AppConfig.chordState.getValueMap();
            List<String> fileNames = new ArrayList<>();

            for (List<MyFile> files : valueMap.values()) {
                for (MyFile file : files) {
                    fileNames.add(file.toString());
                }
            }

            ViewFilesResponseMessage vfrm = new ViewFilesResponseMessage(AppConfig.myServentInfo.getListenerPort(),
                    clientMessage.getSenderPort(), fileNames);
            MessageUtil.sendMessage(vfrm);
        } else {
            AppConfig.timestampedErrorPrint("View files request handler got a message that is not VIEW_FILES_REQUEST");
        }
    }
}
