package org.example.utils;

import java.io.File;

public class FileVerifayer {
    private String pathTo;
    private String fileName;
    private char separator=File.separatorChar;

    public FileVerifayer(String pathTo, String fileName) {
        this.pathTo = pathTo.replace('/', separator);
        this.fileName = fileName;
    }

    public File getFile() {
        File f=new File(fileName);
        if (f.exists() && !f.isDirectory()){
            return f;
        }
        f=new File(new StringBuilder().append(pathTo).append(separator).append(fileName).toString());
        if (f.exists() && !f.isDirectory()){
            return f;
        }
        System.out.println(fileName+" not found");
        return null;
    }
}
