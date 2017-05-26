package com.shall.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mohamed S. El-Shall on 26/5/2017.
 */

public class ForgotPassword implements Serializable {

    /**
     * password : 123456
     */
    @SerializedName("password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
