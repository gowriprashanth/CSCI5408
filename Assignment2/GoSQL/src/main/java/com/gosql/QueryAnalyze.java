package com.gosql;

import java.util.*;

public class QueryAnalyze implements IQueryAnalyze{
    private String query;
    private String selectQuery;
    private String[] querySection;

    private String[] selectParts;
    private String[] updateParts;

    private String[] queryWhere;

    public QueryAnalyze(String query) {
        this.query = query.trim();
        this.querySection = query.split("\\s+");
        this.selectQuery = query.replace("SELECT","");
        this.selectParts = selectQuery.split("FROM");
        this.queryWhere = query.split("WHERE");
        this.updateParts = queryWhere[0].split("SET");
    }

    /**
     * this method splits query into array
     * @return string array
     */
    public String[] getQuery() {
        return querySection;
    }

    /**
     * this method finds query type
     * @return the string query type
     */
    public String getQueryType() {
        return querySection[0].toUpperCase();
    }

    /**
     * this method gets database name
     * @return the string database name
     */
    public String getDatabaseName(){
        if(querySection.length == 3){
            return querySection[2];
        }
        return null;
    }

    /**
     * this method finds the table name
     * @return the table string name
     */
    public String getTableName() {
        if (querySection.length > 1) {
            return querySection[2];
        }
        return null;
    }
    /**
     * this method finds the table name
     * @return the table string name
     */
    public String getTableNameSelect() {
        if (querySection.length > 1) {
            return selectParts[1].trim();
        }
        return null;
    }
    /**
     * this method finds the table name
     * @return the table string name
     */
    public String getTableNameUpdate() {
        if (querySection.length > 1) {
            return querySection[1];
        }
        return null;
    }
    /**
     * this method finds the table name
     * @return the table string name
     */
    public String[] getColumns() {
        if (querySection.length > 2) {
            String columnsString = query.substring(query.indexOf("(") + 1, query.indexOf(")"));
            String[] columns = columnsString.split(",");
            for (int i = 0; i < columns.length; i++) {
                columns[i] = columns[i].trim().split("\\s+")[0];
            }
            return columns;
        }
        return null;
    }
    /**
     * this method finds the table name
     * @return the table string name
     */
    public String[] getSelectColumns(){
        String[] columns = selectParts[0].trim().split(", ");
        return columns;
    }
    /**
     * this method finds the table name
     * @return the table string name
     */
    public String[] getDataTypes() {
        if (querySection.length > 2) {
            String columnsString = query.substring(query.indexOf("(") + 1, query.indexOf(")"));
            String[] columns = columnsString.split(",");
            for (int i = 0; i < columns.length; i++) {
                columns[i] = columns[i].trim().split("\\s+")[1];
            }
            return columns;
        }
        return null;
    }
    /**
     * this method finds the table name
     * @return the table string name
     */
    public String[] getValues() {
        if (querySection.length > 2) {
            String valuesString = query.substring(query.lastIndexOf("(") + 1, query.lastIndexOf(")"));
            return valuesString.split(",");
        }
        return null;
    }
    /**
     * this method finds the table name
     * @return the table string name
     */
    public List<String[]> getUpdates(){
        //String updateString = query.replace("UPDATE ", "");
        //updateParts = query.split(" SET ");
        String[] setClauseParts = updateParts[1].split(", ");
        List<String[]> updates = new ArrayList<>();

        for (String setPart : setClauseParts) {
            String[] columnValues = setPart.split("=");
            String columnName = columnValues[0].trim();
            String columnValue = columnValues[1].trim();

            updates.add(new String[]{columnName, columnValue});
        }
        return updates;
    }
    /**
     * this method finds the table name
     * @return the table string name
     */
    public String[] getWhereUpdate(){
        String[] where = new String[2];
        //if (updateParts.length > 2) {
            String whereClause = queryWhere[1].trim();
            String[] conditionParts = whereClause.split("=");
            where[0] = conditionParts[0].trim();
            where[1] = conditionParts[1].trim();
        //}

        return where;
    }
}
