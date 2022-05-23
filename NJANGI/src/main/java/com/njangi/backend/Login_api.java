package com.njangi.backend;

import com.njangi.statics.ImagePath;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Login_api implements ImagePath {
    public static int login (String email, String password) {
        int success = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ NAME+"?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT * FROM Njangi_user WHERE user_email = ? AND user_password = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);

            success = pst.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: " + e.getMessage());
        }
        return success;
    }
}
