/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package adminRole.view;
import Login.LoginView;
import adminRole.controller.MainViewController;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
/**
 *
 * @author GIAHUY
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    //components of frame
    private JPanel[] paneList;
    private MainViewController controller;
            
    
    public MainView() {
        initComponents();
        
        //set listener for event
        patientSwitchPaneButton.addActionListener(e -> switchPane(0)); 
        appointmentSwitchPaneButton.addActionListener(e -> switchPane(1)); 
        resultSwitchPaneButton.addActionListener(e -> switchPane(2));
        employeeSwitchPaneButton.addActionListener(e -> switchPane(3));
        scheduleSwitchPaneButton.addActionListener(e -> switchPane(4));
        medicineSwitchPaneButton.addActionListener(e -> switchPane(5));
        attendanceSwitchPaneButton.addActionListener(e -> switchPane(6));
        statisticSwitchPaneButton.addActionListener(e -> switchPane(7));
        settingSwitchPaneButton.addActionListener(e -> switchPane(8));
        logoutButton.addActionListener(e -> {
            controller.logout();
        });
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(MainView.this, "Do you want to close the program?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (choice == JOptionPane.OK_OPTION)
                    MainView.this.dispose();
            }         
        });
        
        
        //set properties for components
        this.setTitle("Private Clinic Management");
        this.setLocationRelativeTo(null);
        this.setSize(1550, 830); 
        this.setResizable(false);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.controller = new MainViewController(this); // controller
        this.paneList = new JPanel[9]; 
        paneList[0] = new PatientPage(); //patient
        paneList[1] = new JPanel(); //appointment
        paneList[2] = new JPanel(); 
        paneList[3] = new EmployeePage(); //employee
        paneList[4] = new JPanel();
        paneList[5] = new JPanel();
        paneList[6] = new JPanel();
        paneList[7] = new JPanel();
        paneList[8] = new JPanel();
        
        patientSwitchPaneButton.setBackground(Color.WHITE);
        appointmentSwitchPaneButton.setBackground(Color.WHITE);
        resultSwitchPaneButton.setBackground(Color.WHITE);
        employeeSwitchPaneButton.setBackground(Color.WHITE);
        scheduleSwitchPaneButton.setBackground(Color.WHITE);
        medicineSwitchPaneButton.setBackground(Color.WHITE);
        attendanceSwitchPaneButton.setBackground(Color.WHITE);
        statisticSwitchPaneButton.setBackground(Color.WHITE);
        settingSwitchPaneButton.setBackground(Color.WHITE);
        
        logoutButton.setBackground(Color.WHITE);


        //paneList[0].setBackground(new Color(255, 255, 0));
        paneList[1].setBackground(new Color(255, 0, 255));
        paneList[2].setBackground(new Color(0, 255, 255));
//        paneList[4].setBackground(new Color(255, 0, 255));
        paneList[5].setBackground(new Color(255, 255, 100));
        paneList[6].setBackground(new Color(255, 100, 255));
        paneList[7].setBackground(new Color(100, 255, 255));
        paneList[8].setBackground(new Color(255, 200, 255));
        this.switchPane(0);
        
    }
    private void switchPane(int index)
    {
        contentPane.removeAll();
        contentPane.add(this.paneList[index]);
        contentPane.revalidate();
        contentPane.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        patientSwitchPaneButton = new javax.swing.JButton();
        appointmentSwitchPaneButton = new javax.swing.JButton();
        attendanceSwitchPaneButton = new javax.swing.JButton();
        resultSwitchPaneButton = new javax.swing.JButton();
        settingSwitchPaneButton = new javax.swing.JButton();
        employeeSwitchPaneButton = new javax.swing.JButton();
        scheduleSwitchPaneButton = new javax.swing.JButton();
        medicineSwitchPaneButton = new javax.swing.JButton();
        statisticSwitchPaneButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        contentPane = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setFocusCycleRoot(false);
        setMinimumSize(new java.awt.Dimension(1100, 620));
        setSize(new java.awt.Dimension(1100, 620));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(null);

        patientSwitchPaneButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        patientSwitchPaneButton.setForeground(new java.awt.Color(153, 204, 255));
        patientSwitchPaneButton.setText("Patient");
        patientSwitchPaneButton.setToolTipText("");
        patientSwitchPaneButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        patientSwitchPaneButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        patientSwitchPaneButton.setMaximumSize(new java.awt.Dimension(50, 50));
        patientSwitchPaneButton.setMinimumSize(new java.awt.Dimension(50, 50));
        patientSwitchPaneButton.setName(""); // NOI18N
        patientSwitchPaneButton.setPreferredSize(new java.awt.Dimension(80, 80));
        jPanel1.add(patientSwitchPaneButton);
        patientSwitchPaneButton.setBounds(0, 0, 80, 50);

        appointmentSwitchPaneButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        appointmentSwitchPaneButton.setForeground(new java.awt.Color(153, 204, 255));
        appointmentSwitchPaneButton.setText("Appointment ");
        appointmentSwitchPaneButton.setToolTipText("");
        appointmentSwitchPaneButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        appointmentSwitchPaneButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        appointmentSwitchPaneButton.setMaximumSize(new java.awt.Dimension(80, 80));
        appointmentSwitchPaneButton.setMinimumSize(new java.awt.Dimension(80, 80));
        appointmentSwitchPaneButton.setPreferredSize(new java.awt.Dimension(80, 80));
        jPanel1.add(appointmentSwitchPaneButton);
        appointmentSwitchPaneButton.setBounds(80, 0, 110, 50);

        attendanceSwitchPaneButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        attendanceSwitchPaneButton.setForeground(new java.awt.Color(153, 204, 255));
        attendanceSwitchPaneButton.setText("Attendance");
        attendanceSwitchPaneButton.setToolTipText("");
        attendanceSwitchPaneButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        attendanceSwitchPaneButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        attendanceSwitchPaneButton.setMaximumSize(new java.awt.Dimension(80, 80));
        attendanceSwitchPaneButton.setMinimumSize(new java.awt.Dimension(80, 80));
        attendanceSwitchPaneButton.setPreferredSize(new java.awt.Dimension(80, 80));
        attendanceSwitchPaneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceSwitchPaneButtonActionPerformed(evt);
            }
        });
        jPanel1.add(attendanceSwitchPaneButton);
        attendanceSwitchPaneButton.setBounds(510, 0, 90, 50);

        resultSwitchPaneButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        resultSwitchPaneButton.setForeground(new java.awt.Color(153, 204, 255));
        resultSwitchPaneButton.setText("Result ");
        resultSwitchPaneButton.setToolTipText("");
        resultSwitchPaneButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        resultSwitchPaneButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        resultSwitchPaneButton.setMaximumSize(new java.awt.Dimension(80, 80));
        resultSwitchPaneButton.setMinimumSize(new java.awt.Dimension(80, 80));
        resultSwitchPaneButton.setPreferredSize(new java.awt.Dimension(80, 80));
        jPanel1.add(resultSwitchPaneButton);
        resultSwitchPaneButton.setBounds(190, 0, 70, 50);

        settingSwitchPaneButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        settingSwitchPaneButton.setForeground(new java.awt.Color(153, 204, 255));
        settingSwitchPaneButton.setText("Setting ");
        settingSwitchPaneButton.setToolTipText("");
        settingSwitchPaneButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        settingSwitchPaneButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        settingSwitchPaneButton.setMaximumSize(new java.awt.Dimension(80, 80));
        settingSwitchPaneButton.setMinimumSize(new java.awt.Dimension(80, 80));
        settingSwitchPaneButton.setPreferredSize(new java.awt.Dimension(80, 80));
        settingSwitchPaneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingSwitchPaneButtonActionPerformed(evt);
            }
        });
        jPanel1.add(settingSwitchPaneButton);
        settingSwitchPaneButton.setBounds(680, 0, 80, 50);

        employeeSwitchPaneButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        employeeSwitchPaneButton.setForeground(new java.awt.Color(153, 204, 255));
        employeeSwitchPaneButton.setText("Employee ");
        employeeSwitchPaneButton.setToolTipText("");
        employeeSwitchPaneButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        employeeSwitchPaneButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        employeeSwitchPaneButton.setMaximumSize(new java.awt.Dimension(80, 80));
        employeeSwitchPaneButton.setMinimumSize(new java.awt.Dimension(80, 80));
        employeeSwitchPaneButton.setPreferredSize(new java.awt.Dimension(80, 80));
        employeeSwitchPaneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeSwitchPaneButtonActionPerformed(evt);
            }
        });
        jPanel1.add(employeeSwitchPaneButton);
        employeeSwitchPaneButton.setBounds(260, 0, 90, 50);

        scheduleSwitchPaneButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        scheduleSwitchPaneButton.setForeground(new java.awt.Color(153, 204, 255));
        scheduleSwitchPaneButton.setText("Schedule");
        scheduleSwitchPaneButton.setToolTipText("");
        scheduleSwitchPaneButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        scheduleSwitchPaneButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        scheduleSwitchPaneButton.setMaximumSize(new java.awt.Dimension(80, 80));
        scheduleSwitchPaneButton.setMinimumSize(new java.awt.Dimension(80, 80));
        scheduleSwitchPaneButton.setPreferredSize(new java.awt.Dimension(80, 80));
        scheduleSwitchPaneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleSwitchPaneButtonActionPerformed(evt);
            }
        });
        jPanel1.add(scheduleSwitchPaneButton);
        scheduleSwitchPaneButton.setBounds(350, 0, 80, 50);

        medicineSwitchPaneButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        medicineSwitchPaneButton.setForeground(new java.awt.Color(153, 204, 255));
        medicineSwitchPaneButton.setText("Medicine");
        medicineSwitchPaneButton.setToolTipText("");
        medicineSwitchPaneButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        medicineSwitchPaneButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        medicineSwitchPaneButton.setMaximumSize(new java.awt.Dimension(80, 80));
        medicineSwitchPaneButton.setMinimumSize(new java.awt.Dimension(80, 80));
        medicineSwitchPaneButton.setPreferredSize(new java.awt.Dimension(80, 80));
        medicineSwitchPaneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineSwitchPaneButtonActionPerformed(evt);
            }
        });
        jPanel1.add(medicineSwitchPaneButton);
        medicineSwitchPaneButton.setBounds(430, 0, 80, 50);

        statisticSwitchPaneButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        statisticSwitchPaneButton.setForeground(new java.awt.Color(153, 204, 255));
        statisticSwitchPaneButton.setText("Statistic");
        statisticSwitchPaneButton.setToolTipText("");
        statisticSwitchPaneButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        statisticSwitchPaneButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        statisticSwitchPaneButton.setMaximumSize(new java.awt.Dimension(80, 80));
        statisticSwitchPaneButton.setMinimumSize(new java.awt.Dimension(80, 80));
        statisticSwitchPaneButton.setPreferredSize(new java.awt.Dimension(80, 80));
        statisticSwitchPaneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticSwitchPaneButtonActionPerformed(evt);
            }
        });
        jPanel1.add(statisticSwitchPaneButton);
        statisticSwitchPaneButton.setBounds(600, 0, 80, 50);

        logoutButton.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 51, 51));
        logoutButton.setText("Logout");
        logoutButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(logoutButton);
        logoutButton.setBounds(1410, 10, 70, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1540, 50);

        contentPane.setMaximumSize(new java.awt.Dimension(1550, 750));
        contentPane.setMinimumSize(new java.awt.Dimension(1550, 750));
        contentPane.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1550, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );

        contentPane.add(jPanel2, "card2");

        getContentPane().add(contentPane);
        contentPane.setBounds(0, 50, 1550, 760);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void attendanceSwitchPaneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendanceSwitchPaneButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attendanceSwitchPaneButtonActionPerformed

    private void settingSwitchPaneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingSwitchPaneButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_settingSwitchPaneButtonActionPerformed

    private void employeeSwitchPaneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeSwitchPaneButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeSwitchPaneButtonActionPerformed

    private void scheduleSwitchPaneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleSwitchPaneButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scheduleSwitchPaneButtonActionPerformed

    private void medicineSwitchPaneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineSwitchPaneButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineSwitchPaneButtonActionPerformed

    private void statisticSwitchPaneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticSwitchPaneButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statisticSwitchPaneButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appointmentSwitchPaneButton;
    private javax.swing.JButton attendanceSwitchPaneButton;
    private javax.swing.JLayeredPane contentPane;
    private javax.swing.JButton employeeSwitchPaneButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton medicineSwitchPaneButton;
    private javax.swing.JButton patientSwitchPaneButton;
    private javax.swing.JButton resultSwitchPaneButton;
    private javax.swing.JButton scheduleSwitchPaneButton;
    private javax.swing.JButton settingSwitchPaneButton;
    private javax.swing.JButton statisticSwitchPaneButton;
    // End of variables declaration//GEN-END:variables
}
