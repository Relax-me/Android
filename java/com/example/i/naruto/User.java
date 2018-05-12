package com.example.i.naruto;

import java.io.Serializable;

/**
 * Created by 或跃在渊i on 2017/11/11.
 */

public class User implements Serializable {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


}
