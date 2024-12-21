package cli.command;

import app.AppConfig;

public class ViewFriendsCommand implements CLICommand{
    @Override
    public String commandName() {
        return "view_friends";
    }

    @Override
    public void execute(String args) {
        AppConfig.myServentInfo.printFriends();
    }
}
