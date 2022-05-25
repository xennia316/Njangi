/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.njangi.backend;

import com.njangi.statics.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Admin_api implements Path {

    public static ResultSet getMemberCount(String njangiCode) {
        ResultSet result = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT COUNT(account_id) AS User_count FROM"
                    + " User_Accounts WHERE njangi_code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);

            result = pst.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }

    public static ResultSet getMemberRequest(String njangiCode) {
        ResultSet result = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT Njangi_User.user_id, Njangi_User.user_name "
                    + "FROM Njangi_User INNER JOIN Njangi_Request ON Njangi_User.user_id = Njangi_Request.account_id "
                    + "WHERE Njangi_Request.njangi_code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);

            result = pst.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }

    public static int addMember(String accountId, String njangiCode) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "INSERT INTO User_Accounts VALUES (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, accountId);
            pst.setString(2, njangiCode);
            pst.setInt(3, 10000);

            int rs1 = pst.executeUpdate();

            sql = "DELETE FROM Njangi_Request WHERE "
                    + "njangi_code = ? AND account_id = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);
            pst.setString(2, accountId);

            int rs2 = pst.executeUpdate();

            result = rs1 + rs2;
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }

    public static ResultSet getMembers(String njangiCode) {
        ResultSet result = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT Njangi_User.user_name, User_Accounts.account_id, User_Accounts.current_amount "
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

    public static int removeMember(String njangiCode, String accountId) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "DELETE FROM User_Accounts WHERE "
                    + "njangi_code = ? AND account_id = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);
            pst.setString(2, accountId);

            result = pst.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }
    
    public static ResultSet getLoanRequest(String njangiCode) {
        ResultSet result = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT * FROM Loan_Request WHERE njangi_code = ? AND loan_status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);
            pst.setString(2, "Pending");

            result = pst.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }
    
    public static ResultSet getCurrentLoans(String njangiCode) {
        ResultSet result = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT * FROM Loan_Request WHERE njangi_code = ? AND loan_status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);
            pst.setString(2, "Granted");

            result = pst.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }
    
    
    
    public static int approveLoan(String accountID, String njangiCode){
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "UPDATE Loan_Requests "
                    + "SET loan_status = ? "
                    + "WHERE account_id = ? AND njangi_code = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "Granted");
            pst.setString(2, accountID);
            pst.setString(3, njangiCode);
            
            result = pst.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }
    
    public static int recoverLoan(String accountID, String njangiCode){
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "UPDATE Loan_Requests "
                    + "SET loan_status = ? "
                    + "WHERE account_id = ? AND njangi_code = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "Complete");
            pst.setString(2, accountID);
            pst.setString(3, njangiCode);
            
            result = pst.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }

        return result;
    }
}

