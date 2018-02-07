package com.butter.dinnerssc.model;

/**
 * Created by zgyi on 2018-01-17.
 */

public class User {

    public User(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    private String email;
    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
