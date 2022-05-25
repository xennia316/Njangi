/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.njangi.backend;

import static com.njangi.statics.Path.NAME;
import static com.njangi.statics.Path.PASSWORD;
import static com.njangi.statics.Path.USERNAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class User_api {

    public static ResultSet getMembers(String njangiCode) {
        ResultSet result = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT Njangi_User.user_name, User_Accounts.account_id, Njangi_User.user_email "
                    + "FROM Njangi_User INNER JOIN User_Accounts ON "
                    + "Njangi_User.user_id = User_Accounts.account_id "
                    + "WHERE User_Accounts.njangi_code = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);

            result = pst.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }

    public static ResultSet getShortieBalance(String shortieID, String njangiCode) {
        ResultSet result = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT current_amount FROM User_Accounts "
                    + "WHERE User_Accounts.account_id = ? AND User_Accounts.njangi_code = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, shortieID);
            pst.setString(2, njangiCode);

            result = pst.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }

    public static ResultSet getShortieName(String shortieID) {
        ResultSet result = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT user_name FROM Njangi_User "
                    + "WHERE user_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, shortieID);

            result = pst.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }

    public static int sendLoanRequest(String userID, String userName, String shortieID, String shortieName, String njangiCode, int loanAmount) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "INSERT INTO Loan_Request VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userID);
            pst.setString(2, userName);
            pst.setString(3, shortieID);
            pst.setString(4, shortieName);
            pst.setString(5, njangiCode);
            pst.setInt(6, loanAmount);
            pst.setString(7, "Pending");
            
            result = pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }

}
