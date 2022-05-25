/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.njangi.forms;

import com.njangi.backend.User_api;
import com.njangi.models.User;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import com.njangi.statics.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 *
 * @author Admin
 */
public class Borrow extends javax.swing.JPanel implements Path {

    /**
     * Creates new form Borrow
     */
    private User njangiUser;
    
    public Borrow(User u) {
        this.njangiUser = u;
        initComponents();
        initLogo();
        lb_alert.setForeground(Color.red);
    }

    public final void initLogo() {
        ImageIcon ic = new ImageIcon(PREFIX + "withdraw.png");
        Image scaledImg = ic.getImage().getScaledInstance(280, 330, java.awt.Image.SCALE_SMOOTH);
        ic = new ImageIcon(scaledImg);
        lb_icon.setIcon(ic);
    }

    public int getShortieBalance() {
        int balance = 0;
        try {
            ResultSet res = User_api.getShortieBalance(in_shortieID.getText(), njangiUser.getNjangiCode());
            if (res.next()) {
                    balance = res.getInt("current_amount");
            }
        } catch (SQLException ex) {
            System.out.println("Error::: "+ex.getMessage());
        }
        
        return balance;
    }
    
    public String getShortieName() {
        String name = "";
        try {
            ResultSet res = User_api.getShortieName(in_shortieID.getText());
            if (res.next()) {
                    name = res.getString("user_name");
            }
        } catch (SQLException ex) {
            System.out.println("Error::: "+ex.getMessage());
        }
        
        return name;
    }
    
    public boolean checkLoan(int shortieBalance){
        int totalAmount = Integer.parseInt(in_amount.getText());
        int totalBalance = njangiUser.getCurrAmount() + shortieBalance;
        int minimum = (int) (0.7 * totalAmount);
        
        return (totalBalance > minimum);
    }
    
    public void sendLoanRequest(){
        int shortieBalance = getShortieBalance();
        if(shortieBalance == 0){
            lb_alert.setForeground(Color.red);
            lb_alert.setText("Shortie Not Found");
        }
        else{
            lb_alert.setForeground(Color.green);
            lb_alert.setText("Shortie Found");
            if(checkLoan(shortieBalance) == false){
                JOptionPane.showMessageDialog(null, "User and Shortie balance Insufficient", "Insufficient Funds", ERROR_MESSAGE);
                return;
            }
            String shortieName = getShortieName();
            int result = User_api.sendLoanRequest(njangiUser.getUserID(), njangiUser.getUserName(), in_shortieID.getText(), shortieName, njangiUser.getNjangiCode(), Integer.parseInt(in_amount.getText()));
            if(result > 0){
                JOptionPane.showMessageDialog(null, "Request Sent", "Success", INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Database Error","Error",ERROR_MESSAGE);
            }
        }
        in_shortieID.setText("");
        in_amount.setText("");
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        curvesPanel1 = new com.njangi.swingcomponents.CurvesPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_separator = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        in_amount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        in_shortieID = new javax.swing.JTextField();
        btn_submit = new javax.swing.JButton();
        lb_alert = new javax.swing.JLabel();
        lb_icon = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setText("Borrow");

        lb_separator.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setText("Amount");

        in_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_amountActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setText("Shortie ID");

        in_shortieID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_shortieIDActionPerformed(evt);
            }
        });

        btn_submit.setBackground(new java.awt.Color(94, 84, 142));
        btn_submit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btn_submit.setText("Submit");
        btn_submit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });

        lb_alert.setText("Shortie Prompt");

        javax.swing.GroupLayout curvesPanel1Layout = new javax.swing.GroupLayout(curvesPanel1);
        curvesPanel1.setLayout(curvesPanel1Layout);
        curvesPanel1Layout.setHorizontalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_separator, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lb_alert, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, curvesPanel1Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(btn_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(in_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(in_shortieID, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)
                        .addComponent(lb_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        curvesPanel1Layout.setVerticalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lb_separator, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(in_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(in_shortieID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_alert, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btn_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void in_shortieIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_shortieIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_in_shortieIDActionPerformed

    private void in_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_in_amountActionPerformed

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        // TODO add your handling code here:
        sendLoanRequest();
    }//GEN-LAST:event_btn_submitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_submit;
    private com.njangi.swingcomponents.CurvesPanel curvesPanel1;
    private javax.swing.JTextField in_amount;
    private javax.swing.JTextField in_shortieID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lb_alert;
    private javax.swing.JLabel lb_icon;
    private javax.swing.JLabel lb_separator;
    // End of variables declaration//GEN-END:variables
}
