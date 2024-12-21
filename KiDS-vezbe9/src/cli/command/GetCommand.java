package cli.command;

import app.AppConfig;
import app.ChordState;

public class GetCommand implements CLICommand{

    @Override
    public String commandName() {
        return "get_file";
    }

    @Override
    public void execute(String args) {
        String fileName = args.trim();
        //AppConfig.myServentInfo.getLocalFiles().contains()

        int key = ChordState.chordHash(fileName.hashCode());
        int result = AppConfig.chordState.getValue2(key, fileName);

        if (result == -1) {
            AppConfig.timestampedErrorPrint("File not found: " + fileName);
        } else if (result == -2) {
            AppConfig.timestampedStandardPrint("Forwarding get request for file: " + fileName);
        }
    }
}
