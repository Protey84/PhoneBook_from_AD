package org.example.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;

public class PropertyReader {
    private String url;
    private String base;
    private String domain;
    private String company;
    private boolean savePass;
    private final File file;
    private final Properties properties;

    public PropertyReader(File file) {
        this.file = file;
        properties = new Properties();
        try(FileReader fileReader = new FileReader(file)){
            properties.load(fileReader);
            this.url=properties.getProperty("url");
            this.base=properties.getProperty("base");
            this.domain=properties.getProperty("domain");
            this.company=new String(properties.getProperty("company").getBytes(StandardCharsets.UTF_8));
            this.savePass=Boolean.parseBoolean(properties.getProperty("savePass"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCompany() {
        return company;
    }

    public String getDomain() {
        return domain;
    }

    public String getUrl() {
        return url;
    }


    public String getBase() {
        return base;
    }

    public boolean isSavePass() {
        return savePass;
    }

    public void setSavePass(boolean savePass) {
        try (OutputStream out= Files.newOutputStream(file.toPath())){
            properties.setProperty("savePass", Boolean.toString(savePass));
            properties.store(out, "");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
