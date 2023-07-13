package com.gosql;

public class User implements IUser{
    private String username;
    private String password;

    private String question;

    private String answer;

    /**
     *
     * @param username
     * @param password
     * @param answer
     * @param question
     */
    public User(String username, String password, String question, String answer) {
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
    }

    /**
     * this method gets username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * this method gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * this method gets question
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * this method gets answerr
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }
}
