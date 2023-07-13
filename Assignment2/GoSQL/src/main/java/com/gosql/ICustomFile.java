package com.gosql;

import java.util.List;

public interface ICustomFile {
    public void saveUser(User user,String filePath);
    public User readUser(String username,String filePath);
    public boolean createDatabase(String databasePath,String databaseName);
    public boolean createFile(String filePath, String fileName);
    public void readFile(String filePath);
    public void writeFileValues(String[] data,String filePath);
    public void writeFileContentsUpdate(String[][] contents, String filePath);
    public void deleteFileContents(String filePath);
    public List<String[]> readFileContents(String fileName);
    public String[][] readFileContentsUpdate(String filePath);

}
