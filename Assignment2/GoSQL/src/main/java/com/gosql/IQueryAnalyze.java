package com.gosql;

import java.util.List;

public interface IQueryAnalyze {
    public String[] getQuery();
    public String getQueryType();
    public String getDatabaseName();
    public String getTableName();
    public String getTableNameSelect();
    public String getTableNameUpdate();
    public String[] getColumns();
    public String[] getSelectColumns();
    public String[] getDataTypes();
    public String[] getValues();
    public List<String[]> getUpdates();
    public String[] getWhereUpdate();
}
