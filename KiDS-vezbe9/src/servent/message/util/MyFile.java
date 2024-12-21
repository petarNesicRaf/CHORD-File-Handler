package servent.message.util;

import app.ServentInfo;

import java.io.File;

public class MyFile {
    private final File file;
    private final boolean isPrivate;
    private final ServentInfo owner;

    public MyFile(File file, boolean isPrivate, ServentInfo owner) {
        this.file = file;
        this.isPrivate = isPrivate;
        this.owner = owner;
    }

    public File getFile() {
        return file;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public ServentInfo getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        if(isPrivate)
            return file.getName()+":"+"private";
        return file.getName()+":"+"public";
    }
}
