package com.gosql;

import java.util.ArrayList;
import java.util.List;

public class Query implements IQuery{

    String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/src/main/resources/";
    /**
     * This method helps to implement create query
     * @param customFile creates an object of custom file
     * @param qA creates an object of query analyze
     */
    public void CreateQuery(CustomFile customFile,QueryAnalyze qA) {
        if(qA.getQuery().length==3){
            String dataBaseName = qA.getDatabaseName();
            boolean created = customFile.createDatabase(filePath,dataBaseName);
            if(created)
                System.out.println("Created Database:"+created);
            else
                System.out.println("Databasee already exists");
        }
        else{
            String tableName = qA.getTableName();
            String metadatatableName = "metadata_" + tableName;
            System.out.println("tablename:" + tableName);
            boolean created = customFile.createFile(filePath+"/database",tableName);
            boolean createdMeta = customFile.createFile(filePath+"/database",metadatatableName);
            System.out.println("Created table:"+created);
            String[] columns;
            columns = qA.getColumns();
            //System.out.println();
            customFile.writeFile(columns, filePath+"/database/"+tableName);

            String[] dataTypes;
            dataTypes = qA.getDataTypes();
            customFile.writeFile(dataTypes, filePath+"/database/"+metadatatableName);
        }

    }
    /**
     * This method helps to implement select query
     * @param customFile stores an object of custom file
     * @param qA stores an object of query analyze
     * @param query stores query string
     */
    public void SelectQuery(CustomFile customFile,QueryAnalyze qA, String query){
        String tableName = qA.getTableNameSelect();
        Character c = query.charAt(7);
        if( !c.equals('*') )
        {
            String[] columns = qA.getSelectColumns();
            String[][] contents = customFile.readFileContentsUpdate(filePath+"database/"+tableName);

            // Find the indices of the selected columns
            List<Integer> columnIndices = new ArrayList<>();
            for (int i = 1; i < contents[0].length; i++) {
                for (String column : columns) {
                    if (contents[0][i].equals(column)) {
                        columnIndices.add(i);
                        break;
                    }
                }
            }

            if (columnIndices.isEmpty()) {
                System.out.println("No matching columns found.");
                return;
            }

            // Print the selected column names
            for (int index : columnIndices) {
                System.out.print(contents[0][index] + " ");
            }
            System.out.println();

            // Print the data for the selected columns
            for (int i = 1; i < contents.length; i++) {
                for (int index : columnIndices) {
                    System.out.print(contents[i][index] + " ");
                }
                System.out.println();
            }

        }
        else {
            customFile.readFile(filePath+"database/"+tableName);
        }
    }

    /**
     * This method helps to implement insert query
     * @param customFile stores an object of custom file
     * @param qA stores an object of query analyze
     */
    public void InsertQuery(CustomFile customFile,QueryAnalyze qA){
        String tableName = qA.getTableName();
        String[] values = qA.getValues();
        customFile.writeFileValues(values,filePath+"database/"+tableName);
    }
    /**
     * This method helps to implement delete query
     * @param customFile stores an object of custom file
     * @param qA stores an object of query analyze
     */
    public void DeleteQuery(CustomFile customFile,QueryAnalyze qA){
        String tableName = qA.getTableName();
        customFile.deleteFileContents(filePath+"database/"+tableName);
        customFile.deleteFileContents(filePath+"database/"+"metadata_"+tableName);
    }
    /**
     * This method helps to implement update query
     * @param customFile stores an object of custom file
     * @param qA stores an object of query analyze
     */
    public void UpdateQuery(CustomFile customFile, QueryAnalyze qA){

        String tableName = qA.getTableNameUpdate();
        List<String[]> updates = qA.getUpdates();
        String where[]=qA.getWhereUpdate();
        String conditionColumn = where[0];
        String conditionValue = where[1];
        String[][] contents = customFile.readFileContentsUpdate(filePath+"database/"+tableName);

        if (contents == null || contents.length == 0) {
            System.out.println("No data found in the file.");
            return;
        }

        String[] table = contents[0];

        // Find the index of the condition column
        int conditionColumnIndex = -1;
        for (int i = 0; i < table.length; i++) {
            if (table[i].equals(conditionColumn)) {
                conditionColumnIndex = i;
                break;
            }
        }

        if (conditionColumnIndex == -1) {
            System.out.println("Condition column not found in the table.");
            return;
        }

        // Apply updates to the table based on the condition
        for (String[] row : contents) {
            if (row[conditionColumnIndex].equals(conditionValue)) {
                for (String[] update : updates) {
                    String columnName = update[0];
                    String newValue = update[1];

                    // Find the index of the column
                    int columnIndex = -1;
                    for (int i = 1; i < row.length; i++) {
                        if (table[i].equals(columnName)) {
                            columnIndex = i;
                            break;
                        }
                    }

                    if (columnIndex == -1) {
                        System.out.println("Column not found in the table.");
                        continue;
                    }

                    // Update the value
                    row[columnIndex] = newValue;
                }
            }
        }

        // Write the updated contents back to the file
        customFile.writeFileContentsUpdate(contents,filePath+"database/"+tableName);
        System.out.println("Update query executed successfully.");
    }
}
