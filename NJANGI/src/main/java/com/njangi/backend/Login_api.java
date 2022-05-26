package com.njangi.backend;

import java.sql.*;
import com.njangi.statics.Path;

public class Login_api implements Path {

    public static ResultSet loginUser(String userCode, String njangiCode, String password) {
        ResultSet success = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT Njangi_User.user_id, Njangi_User.user_name, Njangi_User.user_email, Njangi_User.user_password, "
                    + "Njangi.njangi_name, User_Accounts.current_amount, User_Accounts.njangi_code FROM "
                    + "Njangi_User INNER JOIN (Njangi INNER JOIN User_Accounts ON Njangi.njangi_code = User_Accounts.njangi_code) "
                    + "ON Njangi_User.user_id = User_Accounts.account_id"
                    + " WHERE User_Accounts.account_id = ? "
                    + "AND Njangi_User.user_password = ? "
                    + "AND User_Accounts.njangi_code = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userCode);
            pst.setString(2, password);
            pst.setString(3, njangiCode);

            success = pst.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }
        return success;
    }

    public static ResultSet loginAdmin(String njangiCode, String password) {
        ResultSet success = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT * FROM Njangi"
                    + " WHERE Njangi.njangi_code = ? AND Njangi.njangi_password = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);
            pst.setString(2, password);

            success = pst.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }
        return success;
    }

    public static ResultSet getNjangiName(String njangiCode) {
        ResultSet success = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT njangi_name FROM Njangi "
                    + "WHERE njangi_code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);

            success = pst.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }
        return success;
    }

public static ResultSet checkRequestStatus(String userCode, String password) {
        ResultSet success = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT Njangi_Request.curr_status FROM Njangi_User INNER JOIN "
                    + "Njangi_Request ON Njangi_User.user_id = Njangi_Request.account_id"
                    + " WHERE Njangi_User.user_id = ? AND Njangi_User.user_password = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userCode);
            pst.setString(2, password);

            success = pst.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }
        return success;
    }
}
