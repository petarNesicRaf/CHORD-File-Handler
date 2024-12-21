package cli.command;

import app.AppConfig;
import mutex.DistributedMutex;
import mutex.SuzukiMutex;

public class ViewReplicasCommand implements CLICommand{
    private SuzukiMutex suzukiMutex;

    public ViewReplicasCommand(DistributedMutex suzukiMutex) {
        if (suzukiMutex instanceof SuzukiMutex) {
            this.suzukiMutex = (SuzukiMutex) suzukiMutex;
        } else {
            AppConfig.timestampedErrorPrint("Error in view_replicas. Mutex is not Suzuki-Kasami.");
        }
    }
    @Override
    public String commandName() {
        return "view_replicas";
    }

    @Override
    public void execute(String args) {
        System.out.println("Printing replicas");
        AppConfig.chordState.printReplicasMap();
    }
}
