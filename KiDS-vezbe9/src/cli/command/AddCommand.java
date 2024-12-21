package cli.command;

import app.AppConfig;
import app.ChordState;
import servent.message.util.FileUtil;

import java.io.File;

public class AddCommand implements CLICommand{
    @Override
    public String commandName() {
        return "add_file";
    }

    /*
    @Override
    public void execute(String args) {
        String[] split = args.split(":");
        String fileName = split[0];
        boolean isPrivate;

        //add exception if args are bad


        File file = new File(AppConfig.ROOT_DIR + "\\" + fileName);
        if (!file.exists()) {
            AppConfig.timestampedErrorPrint("File at this path doesn't exist: " + AppConfig.ROOT_DIR + "\\" + fileName);
            return;
        }

        if(split[1].equals("private"))
            isPrivate = true;
        else if(split[1].equals("public"))
            isPrivate = false;
        else{
            AppConfig.timestampedErrorPrint("Private and public modifier are not correctly defined");
            return;
        }
        FileUtil.addMyFile(AppConfig.ROOT_DIR,fileName,AppConfig.myServentInfo.getChordId(),isPrivate);

    }


     */


    @Override
    public void execute(String args) {
        String[] split = args.split(":");
        String fileName = split[0];
        boolean isPrivate;

        //add exception if args are bad


        File file = new File(AppConfig.ROOT_DIR + "\\" + fileName);
        if (!file.exists()) {
            AppConfig.timestampedErrorPrint("File at this path doesn't exist: " + AppConfig.ROOT_DIR + "\\" + fileName);
            return;
        }

        if(split[1].equals("private"))
            isPrivate = true;
        else if(split[1].equals("public"))
            isPrivate = false;
        else{
            AppConfig.timestampedErrorPrint("Private and public modifier are not correctly defined");
            return;
        }


        int key = ChordState.chordHash(fileName.hashCode());
        AppConfig.chordState.putValue(key, file.getName(),isPrivate);
    }


}
