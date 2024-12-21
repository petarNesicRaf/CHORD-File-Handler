package cli.command;

import app.AppConfig;
import servent.message.ViewFilesRequestMessage;
import servent.message.util.MessageUtil;

public class ViewFilesCommand implements CLICommand{
    @Override
    public String commandName() {
        return "view_files";
    }

    @Override
    public void execute(String args) {
        String[] splitArgs = args.split(":");
        if (splitArgs.length != 2) {
            AppConfig.timestampedErrorPrint("Invalid arguments for view_files. Usage: view_files [address:port]");
            return;
        }

        String address = splitArgs[0];
        int port;
        try {
            port = Integer.parseInt(splitArgs[1]);
        } catch (NumberFormatException e) {
            AppConfig.timestampedErrorPrint("Invalid port number for view_files.");
            return;
        }

        // Send a request to the specified node to get its files
        ViewFilesRequestMessage vfrm = new ViewFilesRequestMessage(AppConfig.myServentInfo.getListenerPort(), port);
        MessageUtil.sendMessage(vfrm);
    }
}

