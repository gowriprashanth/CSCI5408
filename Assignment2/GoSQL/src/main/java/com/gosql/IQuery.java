package com.gosql;

public interface IQuery {
    public void CreateQuery(CustomFile customFile,QueryAnalyze qA);
    public void SelectQuery(CustomFile customFile,QueryAnalyze qA, String query);
    public void InsertQuery(CustomFile customFile,QueryAnalyze qA);
    public void DeleteQuery(CustomFile customFile,QueryAnalyze qA);
    public void UpdateQuery(CustomFile customFile, QueryAnalyze qA);

}
