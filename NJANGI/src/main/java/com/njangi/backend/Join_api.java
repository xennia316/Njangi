package com.njangi.backend;



import java.sql.*;
import com.njangi.statics.Path;

public class Join_api implements Path {

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
    
    
    public static int joinNjangi(String userID, String njangiCode){
        int rs2 = 0;
        if(!checkNjangi(njangiCode)){
            return -1;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ NAME+"?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "INSERT INTO Njangi_Request VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangiCode);
            pst.setString(2, userID);
            pst.setString(3, "Pending");

            rs2 = pst.executeUpdate();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error::: "+e.getMessage());
        }
        return rs2;
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

            int rs2 = joinNjangi(userID, code);

            success = rs1 + rs2;
        }

        catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: "+e.getMessage());
        }

        return success;
    }
    
    public static int addNjangi (String njangi_name, String njangi_code,String njangi_password) {
        int success = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ NAME+"?serverTimezone=UTC", USERNAME, PASSWORD);
            String sql = "INSERT INTO Njangi VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, njangi_code);
            pst.setString(2, njangi_name);
            pst.setString(3, njangi_password);
            
            success = pst.executeUpdate();
        }

        catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error::: "+e.getMessage());
        }

        return success;
    }
}
