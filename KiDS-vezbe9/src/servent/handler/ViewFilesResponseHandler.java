package servent.handler;

import app.AppConfig;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.ViewFilesResponseMessage;

import java.util.List;

public class ViewFilesResponseHandler implements MessageHandler{
    private Message clientMessage;

    public ViewFilesResponseHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }
    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.VIEW_FILES_RESPONSE) {
            ViewFilesResponseMessage vfrm = (ViewFilesResponseMessage) clientMessage;
            List<String> fileNames = vfrm.getFileNames();
            boolean isFriend = AppConfig.myServentInfo.isFriend("localhost"+":"+clientMessage.getSenderPort());
            AppConfig.timestampedStandardPrint("Files on node " + clientMessage.getSenderPort() + ":");

            //file1.txt:private
            //file1.txt:public
            for (String fileName : fileNames) {
                String[] split = fileName.split(":");

                if(split[1].equals("private") && isFriend)
                    AppConfig.timestampedStandardPrint(fileName);
                else if(split[1].equals("public"))
                    AppConfig.timestampedStandardPrint(fileName);
            }
        } else {
            AppConfig.timestampedErrorPrint("View files response handler got a message that is not VIEW_FILES_RESPONSE");
        }
    }
}
