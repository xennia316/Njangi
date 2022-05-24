package com.njangi.backend;


import com.njangi.statics.ImagePath;

import java.sql.*;

public class Join_api implements ImagePath {

    public static boolean checkNjangi(String code){
        boolean status = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NAME + "?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "SELECT * from Njangi " +
                    "WHERE njangi_code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,code);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                status = true;
            }
        }
        catch(Exception e){
            System.out.println("Error::: "+e.getMessage());
        }
        return status;
    }
    public static int addMember (String njangiName, String userName, String code, String userID, String email, String password) {
        int success = 0;
        if(!checkNjangi(code)){
            return -1;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ NAME+"?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "INSERT INTO Njangi_User VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userID);
            pst.setString(2, userName);
            pst.setString(3, email);
            pst.setString(4, password);

            int rs1 = pst.executeUpdate();

            sql = "INSERT INTO User_Accounts VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, userID);
            pst.setString(2, code);
            pst.setInt(3, 10000);

            int rs2 = pst.executeUpdate();

            success = rs1 + rs2;
        }

        catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: "+e.getMessage());
        }

        return success;
    }
}
