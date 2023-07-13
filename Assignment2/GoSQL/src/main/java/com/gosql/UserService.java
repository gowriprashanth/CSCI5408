package com.gosql;
import java.util.ArrayList;
import java.util.List;
public class UserService implements IUserService{
    //private List<User> usersArray;
    private User user;
    public UserService() {

        //usersArray = new ArrayList<>();
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean createUser(String username, String password, String question, String answer) {

        user = new User(username, password, question, answer);
        return true; // User created successfully
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public User loginUser(String username, String password, String question, String answer) {

        if (user.getUsername().equals(username) && user.getPassword().equals(password) &&
                    user.getQuestion().equals(question) && user.getAnswer().equals(answer)) {
                return user; // User found, login successful
            }
        return null; // User not found or incorrect credentials
    }

    /**
     *
     * @param userName
     * @return
     */
    public String getQuestionforUser(String userName){
        String question="";

        //for(User user :usersArray){
            if(user.getUsername().equals(userName)){
                question = user.getQuestion();
            }
        //}

        return question;
    }
}