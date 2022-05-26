/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.njangi.models;

/**
 *
 * @author Admin
 */
public class User {
    
    private String userID;
    private String userName;
    private String email;
    private String njangiCode;
    private int currAmount;

    public User(String userID, String userName, String email, String njangiCode, int currAmount) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.njangiCode = njangiCode;
        this.currAmount = currAmount;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getNjangiCode() {
        return njangiCode;
    }

    public int getCurrAmount() {
        return currAmount;
    }
    
    
}
