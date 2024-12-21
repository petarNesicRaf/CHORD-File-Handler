package cli.command;

import app.AppConfig;
import app.ServentInfo;

import static app.AppConfig.chordState;
import static app.AppConfig.myServentInfo;

public class RemoveServentCommand implements CLICommand{

    @Override
    public String commandName() {
        return "remove_servent";
    }

    @Override
    public void execute(String args) {
        AppConfig.chordState.removeNode(AppConfig.myServentInfo);
    }
}
