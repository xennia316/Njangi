package com.njangi.backend;

import com.njangi.statics.ImagePath;

import java.sql.*;

public class Login_api implements ImagePath {
    public static ResultSet login (String userCode, String njangiCode, String password) {
        ResultSet success = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ NAME+"?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT Njangi_User.user_id, Njangi_User.user_name, Njangi_User.user_email, Njangi_User.user_password Njangi.njangi_name, User_Accounts.current_amount, User_Accounts.njangi_code FROM Njangi_User INNER JOIN " +
                    "(User_Accounts ON Njangi.[njangi_code]) ON Njangi_User.[user_id] = User_Accounts.[account_id]" +
                    " WHERE Njangi_User.user_id = ? AND Njangi_User.user_password = ? AND User_Accounts.njangi_code = ?";
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
}
