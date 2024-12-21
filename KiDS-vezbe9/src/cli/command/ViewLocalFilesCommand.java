package cli.command;

import app.AppConfig;
import app.ServentInfo;
import mutex.DistributedMutex;
import mutex.SuzukiMutex;

public class ViewLocalFilesCommand implements CLICommand{
    private SuzukiMutex suzukiMutex;

    public ViewLocalFilesCommand(DistributedMutex suzukiMutex) {
        if (suzukiMutex instanceof SuzukiMutex) {
            this.suzukiMutex = (SuzukiMutex) suzukiMutex;
        } else {
            AppConfig.timestampedErrorPrint("Error in AskForTokenHandler. Mutex is not Suzuki-Kasami.");
        }
    }

    @Override
    public String commandName() {
        return "view_local_files";
    }

    @Override
    public void execute(String args) {
        AppConfig.chordState.printLocalFiles();
//        for(ServentInfo s : AppConfig.chordState.getAllNodeInfo()){
//            System.out.println(s.getListenerPort());
//        }
    }
}
