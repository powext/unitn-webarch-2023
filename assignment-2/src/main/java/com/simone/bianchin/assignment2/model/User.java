package com.simone.bianchin.assignment2.model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String hashedPassword;
    private boolean isAdmin = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public static User create(String username, String password) {
        User user = new User();
        user.hashedPassword = password;
        user.username = username;
        return user;
    }
}
