package cli.command;

import app.AppConfig;

public class AddFriendCommand implements CLICommand{

    @Override
    public String commandName() {
        return "add_friend";
    }

    @Override
    public void execute(String args) {
        String[] splitArgs = args.split(":");
        if (splitArgs.length != 2) {
            AppConfig.timestampedErrorPrint("Invalid arguments for add_friend. Use [ip:port].");
            return;
        }
        String ip = splitArgs[0];
        int port;
        try {
            port = Integer.parseInt(splitArgs[1]);
        } catch (NumberFormatException e) {
            AppConfig.timestampedErrorPrint("Invalid port number: " + splitArgs[1]);
            return;
        }


        if (!AppConfig.myServentInfo.isFriend(args)) {
            AppConfig.myServentInfo.addFriend(args);
            AppConfig.timestampedStandardPrint("Added friend: " + args);
        } else {
            AppConfig.timestampedStandardPrint("Node is already a friend: " + args);
        }
    }
}
