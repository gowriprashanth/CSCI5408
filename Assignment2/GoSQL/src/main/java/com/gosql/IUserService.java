package com.gosql;

public interface IUserService {
    public boolean createUser(String username, String password, String question, String answer);
    public User loginUser(String username, String password, String question, String answer);
    public String getQuestionforUser(String userName);

}
