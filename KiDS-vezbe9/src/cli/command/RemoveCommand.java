package cli.command;

import app.AppConfig;
import app.ChordState;

public class RemoveCommand implements CLICommand {
    @Override
    public String commandName() {
        return "remove_file";
    }

    @Override
    public void execute(String args) {
        String fileName = args.trim();
        if (fileName.isEmpty()) {
            AppConfig.timestampedErrorPrint("Usage: remove_file [filename]");
            return;
        }

        int key = ChordState.chordHash(fileName.hashCode());
        AppConfig.chordState.removeValue(key, fileName);
    }
}
