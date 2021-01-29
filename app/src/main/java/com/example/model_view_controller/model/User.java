package com.example.model_view_controller.model;



public class User {

    public static int score = 0;
    private String userName;

    public void setUserName(String user) {
        this.userName = user;
    }
    public String getUserName() {
        return userName;
    }
    public int getScore() {
        return score;
    }


}
