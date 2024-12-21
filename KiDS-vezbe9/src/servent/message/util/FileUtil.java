package servent.message.util;

import app.AppConfig;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileUtil {
    /*
    public static boolean rootContainsFile(String fileName){
        Path folderPath = Paths.get(AppConfig.ROOT_DIR, fileName);
        // Check if the folder exists
        if (Files.exists(folderPath))
            return true;

        return false;
    }

    public static boolean containsFile(String path){
        boolean res = false;
        Map<Integer, List<File>> fileListMap = AppConfig.chordState.getValueMap();
        for(Integer key : fileListMap.keySet()){
            List<File> fileList = fileListMap.get(key);
            if(fileList!=null){
                for(File f: fileList){
                    if(path.equals(f.getName())) res = true;
                }
            }
        }
        return false;
    }
    /*

     slab add file
    public static void addFile(String absPath, String fileName, int key){
        Integer myId = AppConfig.myServentInfo.getChordId();
        Map<Integer, List<File>> fileListMap = AppConfig.chordState.getValueMap();
        List<File> fileList = fileListMap.get(key);

        if(fileList == null) AppConfig.chordState.getValueMap().put(key, new ArrayList<>());
        File file = new File(absPath + "\\"+fileName);

        //ako fajl ne postoji fizicki
        if (!file.exists()) {
            AppConfig.timestampedErrorPrint("The file does not exist.");
            return;
        }

        Path targetDir = Path.of(AppConfig.ROOT_DIR);
        if(!file.isDirectory()){
            //fileList.add(file);
            AppConfig.chordState.getValueMap().get(key).add(file);
            /*
            List<File> files = AppConfig.chordState.getValueMap().get(key);
            for(File f:files){
                String name = f.getName();
            }


        }
    }

     */
    /*
    dobar add file
    public static void addFile2(String rootDir, String fileName, int key) {
        // Simulate adding the file to the specified node's storage
        // In practice, this might involve actual file system operations
        File file = new File(rootDir + "\\" + fileName);
        if (!file.exists()) {
            AppConfig.timestampedErrorPrint("File does not exist: " + file.getPath());
            return;
        }

        // Simulating the storage in the correct node's data structure
        AppConfig.timestampedStandardPrint("Storing file " + fileName + " at node with key " + key + " and port "+AppConfig.myServentInfo.getListenerPort());
        List<File> filesForThisNode = AppConfig.chordState.getValueMap().getOrDefault(key, new ArrayList<>());
        filesForThisNode.add(file);
        AppConfig.chordState.getValueMap().put(key, filesForThisNode);
    }

     */

    public static void addMyFile(String rootDir, String fileName, int key, boolean isPriate){
        File file = new File(rootDir+"\\"+fileName);
        if(!file.exists()){
            AppConfig.timestampedErrorPrint("File does not exist: " + file.getPath());
            return;
        }

        //dodajemo fajl u DHT
        MyFile myFile = new MyFile(file, isPriate, AppConfig.myServentInfo);
        AppConfig.timestampedStandardPrint("Storing file " + fileName +" at node with key " + key);
        List<MyFile> filesForThisNode = AppConfig.chordState.getValueMap().getOrDefault(key, new ArrayList<>());
        filesForThisNode.add(myFile);
        AppConfig.chordState.getValueMap().put(key, filesForThisNode);

        //dodajemo fajl u listu lokalnih fajlova serventa
        AppConfig.myServentInfo.addLocalFile(myFile);

    }

}
