package com.gosql;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLogs{
    /**
     * This method creates Log
     * @param customFile which stores user filepath
     * @param username which stores message
     * @param fileName which stores filename
     * @param message which stores message
     * @return if database is created or not
     */
    public static void createLog(CustomFile customFile,String username,String fileName, String message){
        String filePath="C:/Users/AVuser/IdeaProjects/GoSQL/src/main/resources/";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        customFile.createFile(filePath,fileName+"Logs");
        String dbData = username+"/~/"+message+"/~/"+formattedDateTime+"/~/";
        String[] log = dbData.split("/~/");
        customFile.writeFile(log,filePath+fileName+"Logs");
    }
}
