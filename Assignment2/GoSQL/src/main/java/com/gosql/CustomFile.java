package com.gosql;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class CustomFile implements ICustomFile{
    private String databaseName="database/";
    private File file;

    /**
     * This method save user to file
     * @param user which stores user object
     * @param filePath stores path of file
     */
    public void saveUser(User user,String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(user.getUsername() + "/~/" + user.getPassword() + "/~/" + user.getQuestion() + "/~/" + user.getAnswer()+"/~/");
        } catch (IOException e) {
            System.out.println("Error storing user information: " + e.getMessage());
        }
    }
    /**
     * This method reads user from file
     * @param username which stores user object
     * @param filePath stores path of file
     * @return user object
     */
    public User readUser(String username,String filePath) {
        //String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/src/main/resources/"+username;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("/~/");
                if (parts[0].equals(username)) {
                    return new User(parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return null;
    }
    /**
     * This method creates database
     * @param databasePath which stores user objec
     * @param databaseName stores path of file
     * @return if database is created or not
     */
    public boolean createDatabase(String databasePath,String databaseName) {
        this.databaseName = databaseName;
        //String databasePath = "C:/Users/AVuser/IdeaProjects/GoSQL";
        File database = new File(databasePath, databaseName);
        boolean check = database.exists();
        boolean create = false;
         if(!check) {
            create = database.mkdirs();
        }
        if (create)
            return true;
        return create;
    }
    /**
     * This method creates file
     * @param filePath which stores user filepath
     * @param fileName which stores filename
     * @return if database is created or not
     */
    public boolean createFile(String filePath, String fileName) {
        //this.fileName = fileName;
        //String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/"+databaseName;
        // Create a File object representing the file
        file = new File(filePath,fileName);
        boolean created = false;
            try {
            // Create a new file
                created = file.createNewFile();
            } catch (IOException e) {
            System.out.println("Failed to create Table: " + e.getMessage());
            }
        return created;
    }
    /**
     * This method reads file
     * @param filePath which stores user filepath
     */
    public void readFile(String filePath){
        //String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/"+databaseName+"/"+fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String replacedLine = line.replace("/~/", " ");
                System.out.println(replacedLine);
            }
        } catch (IOException e) {
            System.out.println("Error reading table: " + e.getMessage());
        }
    }
    /**
     * This method writes file
     * @param data stores string array
     * @param filePath which stores filename
     */
    public void writeFile(String[] data, String filePath){

        //String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/"+databaseName+"/"+fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            for (int i = 0; i < data.length; i++) {
                writer.write(data[i]);
                if (i != data.length - 1) {
                    writer.write("/~/"); // Add a delimiter between elements
                }
            }
            writer.write("/~/");
            writer.newLine();
        }catch (IOException e){
            System.out.println("Error writing to table:"+e.getMessage());
        }

    }
    /**
     * This method writes file
     * @param data which data stores string array
     * @param filePath which stores filename
     */
    public void writeFileValues(String[] data,String filePath) {
        //String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/"+databaseName+"/"+fileName;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append("");
                for (int i = 0; i < data.length; i++) {
                    lineBuilder.append(data[i]);
                    if (i < data.length - 1) {
                        lineBuilder.append("/~/");
                    }
                }
                writer.write(lineBuilder.toString());
                writer.write("/~/");

            } catch (IOException e) {
                System.out.println("Error writing to table: " + e.getMessage());
            }
            System.out.println("Data written to tablr successfully.");

    }

    /**
     * This method writes to file
     * @param contents which stores contents
     * @param filePath which stores fileName
     */
    public void writeFileContentsUpdate(String[][] contents, String filePath) {
        //String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/"+databaseName+"/"+fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : contents) {
                StringBuilder lineBuilder = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    lineBuilder.append(row[i]);
                    if (i < row.length - 1) {
                        lineBuilder.append("/~/");
                    }
                }
                writer.write(lineBuilder.toString());
                writer.write("/~/");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to tablr: " + e.getMessage());
        }
        System.out.println("Data written to table successfully.");
    }
    /**
     * This method delete contents of file
     * @param filePath which stores user filepath
     */
    public void deleteFileContents(String filePath){
        //String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/"+databaseName+"/"+fileName;
        try (FileWriter writer = new FileWriter(filePath)){

                writer.write("");

        }catch (IOException e){
            System.out.println("Error deleting contents of table:"+e.getMessage());
        }
    }

    public List<String[]> readFileContents(String fileName) {
        List<String[]> lines = new ArrayList<>();
        String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/"+"database"+"/"+fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split("/~/");
                lines.add(columns);
            }
        } catch (IOException e) {
            System.out.println("Error reading table: " + e.getMessage());
        }
        return lines;
    }

    /**
     * This method creates file
     * @param filePath which stores user filepath
     * @return string array of update contents
     */
    public String[][] readFileContentsUpdate(String filePath) {
        List<String[]> lines = new ArrayList<>();
        //  String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/"+"database"+"/"+fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split("/~/");
                lines.add(columns);
            }
        } catch (IOException e) {
            System.out.println("Error reading table: " + e.getMessage());
        }

        // Convert the list of arrays to a 2D array
        int numRows = lines.size();
        int numCols = lines.get(0).length;
        String[][] contents = new String[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            contents[i] = lines.get(i);
        }
        return contents;
    }
}
