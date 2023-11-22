package org.example.utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileInspector {
    private final String pathTo;
    private final String fileName;
    private static final char SEPARATOR =File.separatorChar;
    Logger logger = Logger.getLogger(getClass().getName());

    public FileInspector(String pathTo, String fileName) {
        this.pathTo = pathTo.replace('/', SEPARATOR);
        this.fileName = fileName;
    }

    public File getFile() {
        File f=new File(fileName);
        if (f.exists() && !f.isDirectory()){
            return f;
        }
        f=new File(String.format("%s%s%s", pathTo, SEPARATOR, fileName));
        if (f.exists() && !f.isDirectory()){
            return f;
        }
        logger.log(Level.SEVERE, () -> fileName+" not found");
        return null;
    }
}
