/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.njangi.models;

import com.njangi.backend.Admin_api;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Admin {
    private String njangiCode;
    private String njangiName;
    private String memberCount;

    public Admin(String njangiCode, String njangiName) {
        this.njangiCode = njangiCode;
        this.njangiName = njangiName;
        fetchMemberCount();
    }

    public String getNjangiCode() {
        return njangiCode;
    }

    public String getNjangiName() {
        return njangiName;
    }
    
    public String getMemberCount(){
        return memberCount;
    }
    
    public void fetchMemberCount(){
        try {
            ResultSet rs = Admin_api.getMemberCount(njangiCode);
            if(rs.next()){
                String result = rs.getString("User_count");
                this.memberCount = result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
