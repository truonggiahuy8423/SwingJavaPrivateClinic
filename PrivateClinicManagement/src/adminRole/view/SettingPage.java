/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Room;
import Model.Service;
import Model.Unit;
import adminRole.controller.RoomController;
import adminRole.controller.ServiceController;
import adminRole.controller.UnitController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GIAHUY
 */
public class SettingPage extends javax.swing.JPanel {

    /**
     * Creates new form SettingPage
     */
    private final RoomController roomController;
    private final ServiceController serviceController;
    private final UnitController unitController; 
    private List<Room> listOfRoom;
    private List<Service> listOfService;
    private List<Unit> listOfUnit;
    private Room currentRoom;
    private Service currentService;
    private Unit currentUnit;
    private final DefaultTableModel dataOfRoomTable;
    private final DefaultTableModel dataOfServiceTable;
    private final DefaultTableModel dataOfUnitTable;
    
    public SettingPage() {
        initComponents();
        
        roomController = new RoomController();
        serviceController = new ServiceController();
        unitController = new UnitController();
        listOfRoom = new ArrayList<>();
        listOfService = new ArrayList<>();
        listOfUnit = new ArrayList<>();
        
        btnAddRoom.setBackground(Color.white);
        btnDeleteRoom.setBackground(Color.white);
        btnUpdateRoom.setBackground(Color.white);
        btnAddService.setBackground(Color.white);
        btnDeleteService.setBackground(Color.white);
        btnUpdateService.setBackground(Color.white);
        btnAddUnit.setBackground(Color.white);
        btnDeleteUnit.setBackground(Color.white);
        btnUpdateUnit.setBackground(Color.white);
        btnRefreshPage.setBackground(Color.white);
        
        dataOfRoomTable = (DefaultTableModel)this.tbRoom.getModel();
        dataOfRoomTable.setColumnIdentifiers(new Object[]{"Room ID"});
        
        dataOfServiceTable = (DefaultTableModel)this.tbService.getModel();
        dataOfServiceTable.setColumnIdentifiers(new Object[]{"Service ID", "Name", "Cost"});
        
        dataOfUnitTable = (DefaultTableModel)this.tbUnit.getModel();
        dataOfUnitTable.setColumnIdentifiers(new Object[]{"Unit ID", "Name"});
        
        refreshPage();
    }

    public void queryDataRoom(){
        this.listOfRoom.clear();
        roomController.queryData(this.listOfRoom);
    }
    
    public void queryDataService(){
        this.listOfService.clear();
        serviceController.queryData(this.listOfService);
    }
    
    public void queryDataUnit(){
        this.listOfUnit.clear();
        unitController.queryData(this.listOfUnit);
    }
    
    public void displayDataRoom(){
        dataOfRoomTable.setRowCount(0);
        
        for(Room r : listOfRoom){
            dataOfRoomTable.addRow(new Object[]{r.getRoomID()});
        }
    }
    public void displayDataService(){
        dataOfServiceTable.setRowCount(0);
        for(Service s : listOfService){
            dataOfServiceTable.addRow(new Object[]{s.getServiceID(), s.getServiceName(), s.getServiceCost()});
        }
    }
    public void displayDataUnit(){
        dataOfUnitTable.setRowCount(0);        

        for(Unit u : listOfUnit){
            dataOfUnitTable.addRow(new Object[]{u.getUnitID(), u.getUnitName()});
        }
    }
    
    public boolean checkNullRoom(){
        boolean isOk = true;
        if(String.valueOf(txtRoomID.getText()).length() <= 0){
            errorRoomID.setText("No empty allowed");
            isOk = false;
        }
        return isOk;
    }
    
    public boolean checkNullService(){
        boolean isOk = true;
        if(String.valueOf(txtServiceID.getText()).length() <= 0){
            errorServiceID.setText("No empty allowed");
            isOk = false;
        }
        
        if(String.valueOf(txtServiceName.getText()).length() <= 0){
            errorServiceName.setText("No empty allowed");
            isOk = false;
        }
        
        if(String.valueOf(txtCost.getText()).length() <= 0){
            errorServiceCost.setText("No empty allowed");
        }
        return isOk;
    }

    public boolean checkNullUnit(){
        boolean isOk = true;
        if(String.valueOf(txtUnitID.getText()).length() <= 0){
            errorUnitID.setText("No empty allowed");
            isOk = false;
        }
        
        if(String.valueOf(txtUnitName.getText()).length() <= 0){
            errorUnitName.setText("No empty allowed");
            isOk = false;
        }
        
        return isOk;
    }
    
    public void setNullErrorRoom(){
        errorRoomID.setText("");
    }
    
    public void setNullErrorService(){
        errorServiceID.setText("");
        errorServiceName.setText("");
        errorServiceCost.setText("");
    }
    
    public void setNullErrorUnit(){
        errorUnitID.setText("");
        errorUnitName.setText("");
    }
    
    public void refreshPage(){
        txtRoomID.setText("");
        txtServiceID.setText("");
        txtServiceName.setText("");
        txtCost.setText("");
        txtUnitID.setText("");
        txtUnitName.setText("");

        setNullErrorRoom();
        setNullErrorService();
        setNullErrorUnit();
        
        queryDataRoom();
        queryDataService();
        queryDataUnit();
        displayDataRoom();
        displayDataService();
        displayDataUnit();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        tbRoom = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbService = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbUnit = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtRoomID = new javax.swing.JTextField();
        txtServiceID = new javax.swing.JTextField();
        txtUnitID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtServiceName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCost = new javax.swing.JTextField();
        btnAddService = new javax.swing.JButton();
        btnDeleteService = new javax.swing.JButton();
        btnAddRoom = new javax.swing.JButton();
        btnDeleteRoom = new javax.swing.JButton();
        btnDeleteUnit = new javax.swing.JButton();
        btnAddUnit = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtUnitName = new javax.swing.JTextField();
        btnUpdateRoom = new javax.swing.JButton();
        btnUpdateService = new javax.swing.JButton();
        btnUpdateUnit = new javax.swing.JButton();
        errorRoomID = new javax.swing.JLabel();
        errorServiceID = new javax.swing.JLabel();
        errorServiceName = new javax.swing.JLabel();
        errorServiceCost = new javax.swing.JLabel();
        errorUnitID = new javax.swing.JLabel();
        errorUnitName = new javax.swing.JLabel();
        btnRefreshPage = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1590, 765));

        tbRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRoomMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbRoom);

        tbService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbServiceMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbService);

        tbUnit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUnitMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbUnit);

        jLabel1.setText("Room Managemet");

        jLabel2.setText("Service Management");

        jLabel3.setText("Unit of Medicine Management");

        jLabel4.setText("Room ID:");

        jLabel5.setText("Service ID:");

        jLabel6.setText("Unit ID:");

        jLabel7.setText("Name:");

        jLabel8.setText("Cost:");

        btnAddService.setText("Add");
        btnAddService.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAddService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddServiceActionPerformed(evt);
            }
        });

        btnDeleteService.setText("Delete");
        btnDeleteService.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnDeleteService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteServiceActionPerformed(evt);
            }
        });

        btnAddRoom.setText("Add");
        btnAddRoom.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomActionPerformed(evt);
            }
        });

        btnDeleteRoom.setText("Delete");
        btnDeleteRoom.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnDeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomActionPerformed(evt);
            }
        });

        btnDeleteUnit.setText("Delete");
        btnDeleteUnit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnDeleteUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUnitActionPerformed(evt);
            }
        });

        btnAddUnit.setText("Add");
        btnAddUnit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAddUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUnitActionPerformed(evt);
            }
        });

        jLabel9.setText("Name:");

        btnUpdateRoom.setText("Update");
        btnUpdateRoom.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnUpdateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRoomActionPerformed(evt);
            }
        });

        btnUpdateService.setText("Update");
        btnUpdateService.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnUpdateService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateServiceActionPerformed(evt);
            }
        });

        btnUpdateUnit.setText("Update");
        btnUpdateUnit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnUpdateUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUnitActionPerformed(evt);
            }
        });

        errorRoomID.setForeground(new java.awt.Color(255, 0, 0));
        errorRoomID.setText("error");
        errorRoomID.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorServiceID.setForeground(new java.awt.Color(255, 0, 0));
        errorServiceID.setText("error");
        errorServiceID.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorServiceName.setForeground(new java.awt.Color(255, 0, 0));
        errorServiceName.setText("error");
        errorServiceName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorServiceCost.setForeground(new java.awt.Color(255, 0, 0));
        errorServiceCost.setText("error");
        errorServiceCost.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorUnitID.setForeground(new java.awt.Color(255, 0, 0));
        errorUnitID.setText("error");
        errorUnitID.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorUnitName.setForeground(new java.awt.Color(255, 0, 0));
        errorUnitName.setText("error");
        errorUnitName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        btnRefreshPage.setText("Refresh Page");
        btnRefreshPage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnRefreshPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshPageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(errorRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAddRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnDeleteRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnUpdateRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(588, 588, 588))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtUnitID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel9))
                                .addComponent(errorUnitID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUnitName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorUnitName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnDeleteUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnUpdateUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtServiceID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel7))
                                .addComponent(errorServiceID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel8))
                                .addComponent(errorServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(errorServiceCost, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                    .addComponent(btnAddService, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnDeleteService, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnUpdateService, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(52, Short.MAX_VALUE))))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(btnRefreshPage, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtServiceID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddService)
                    .addComponent(btnAddRoom)
                    .addComponent(btnDeleteRoom)
                    .addComponent(btnUpdateRoom)
                    .addComponent(btnUpdateService)
                    .addComponent(btnDeleteService))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(errorRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorServiceID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorServiceCost, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtUnitID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(txtUnitName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDeleteUnit)
                                .addComponent(btnUpdateUnit)
                                .addComponent(btnAddUnit)))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(errorUnitID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errorUnitName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addComponent(btnRefreshPage, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomActionPerformed
        if(checkNullRoom()){
            Integer roomID = Integer.valueOf(txtRoomID.getText());
            roomController.addRoom(new Room(roomID));
            queryDataRoom();
            displayDataRoom();
            setNullErrorRoom();
        }
    }//GEN-LAST:event_btnAddRoomActionPerformed

    private void btnDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomActionPerformed
        if(checkNullRoom()){
            int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
            if(confirmOption == JOptionPane.YES_OPTION){
                roomController.deleteRoom(txtRoomID.getText());
                queryDataRoom();
                displayDataRoom();
                setNullErrorRoom();
            }
        }
    }//GEN-LAST:event_btnDeleteRoomActionPerformed

    private void btnAddServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddServiceActionPerformed
        if(checkNullService()){
            Integer serviceID = Integer.valueOf(txtServiceID.getText());
            Integer serviceCost = Integer.valueOf(txtCost.getText());
            serviceController.addService(new Service(serviceID, txtServiceName.getText(), serviceCost));
            queryDataService();
            displayDataService();
            setNullErrorService();
        }
    }//GEN-LAST:event_btnAddServiceActionPerformed

    private void btnDeleteServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteServiceActionPerformed
        if(checkNullService()){
            int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
            if(confirmOption == JOptionPane.YES_OPTION){
                serviceController.deleteService(txtServiceID.getText());
                queryDataService();
                displayDataService();
                setNullErrorService();
            }
        }
    }//GEN-LAST:event_btnDeleteServiceActionPerformed

    private void btnAddUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUnitActionPerformed
        if(checkNullUnit()){
            Integer unitID = Integer.valueOf(txtUnitID.getText());
            unitController.addUnit(new Unit(unitID, txtUnitName.getText()));
            queryDataUnit();
            displayDataUnit();
            setNullErrorUnit();
        }
    }//GEN-LAST:event_btnAddUnitActionPerformed

    private void btnDeleteUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUnitActionPerformed
        if(checkNullUnit()){
            int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
            if(confirmOption == JOptionPane.YES_OPTION){
                unitController.deleteUnit(txtUnitID.getText());
                queryDataUnit();
                displayDataUnit();
                setNullErrorUnit();
            }
        }
    }//GEN-LAST:event_btnDeleteUnitActionPerformed

    private void tbRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRoomMouseClicked
        int n = tbRoom.getSelectedRow();
        txtRoomID.setText(String.valueOf(tbRoom.getValueAt(n, 0)));
        
        currentRoom = new Room();
        currentRoom.setRoomID(Integer.valueOf(String.valueOf(txtRoomID.getText())));
    }//GEN-LAST:event_tbRoomMouseClicked

    private void tbServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbServiceMouseClicked
        int n = tbService.getSelectedRow();
        txtServiceID.setText(String.valueOf(tbService.getValueAt(n, 0)));
        txtServiceName.setText(String.valueOf(tbService.getValueAt(n, 1)));
        txtCost.setText(String.valueOf(tbService.getValueAt(n, 2)));
        
        currentService = new Service();
        currentService.setServiceID(Integer.valueOf(String.valueOf(txtServiceID.getText())));
        currentService.setServiceName(String.valueOf(txtServiceName.getText()));
        currentService.setServiceCost(Integer.valueOf(String.valueOf(txtCost.getText())));
    }//GEN-LAST:event_tbServiceMouseClicked

    private void tbUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUnitMouseClicked
        int n = tbUnit.getSelectedRow();
        txtUnitID.setText(String.valueOf(tbUnit.getValueAt(n, 0)));
        txtUnitName.setText(String.valueOf(tbUnit.getValueAt(n, 1)));  
        
        currentUnit = new Unit();
        currentUnit.setUnitID(Integer.valueOf(String.valueOf(txtServiceID.getText())));
        currentUnit.setUnitName(String.valueOf(txtServiceName.getText()));
    }//GEN-LAST:event_tbUnitMouseClicked

    private void btnUpdateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRoomActionPerformed
        if(checkNullRoom()){
            int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn cập nhật?", "Cập nhật", JOptionPane.YES_NO_OPTION);
            if(confirmOption == JOptionPane.YES_OPTION){
                Room updateRoom = new Room();
                updateRoom.setRoomID(Integer.valueOf(String.valueOf(txtRoomID.getText())));
                roomController.updateRoom(updateRoom, currentRoom);
                queryDataRoom();
                displayDataRoom();
                setNullErrorRoom();
            }
        }
    }//GEN-LAST:event_btnUpdateRoomActionPerformed

    private void btnUpdateServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateServiceActionPerformed
        if(checkNullService()){
            int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn cập nhật?", "Cập nhật", JOptionPane.YES_NO_OPTION);
            if(confirmOption == JOptionPane.YES_OPTION){
                Service updateService = new Service();
                updateService.setServiceID(Integer.valueOf(String.valueOf(txtServiceID.getText())));
                updateService.setServiceName(String.valueOf(txtServiceName.getText()));
                updateService.setServiceCost(Integer.valueOf(String.valueOf(txtCost.getText())));
                serviceController.updateService(updateService, currentService);
                queryDataService();
                displayDataService();
                setNullErrorService();
            }
        }
    }//GEN-LAST:event_btnUpdateServiceActionPerformed

    private void btnUpdateUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUnitActionPerformed
        if(checkNullUnit()){
            int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn cập nhật?", "Cập nhật", JOptionPane.YES_NO_OPTION);
            if(confirmOption == JOptionPane.YES_OPTION){
                Unit updateUnit = new Unit();
                updateUnit.setUnitID(Integer.valueOf(String.valueOf(txtUnitID.getText())));
                updateUnit.setUnitName(String.valueOf(txtUnitName.getText()));
                unitController.updateUnit(updateUnit, currentUnit);
                queryDataUnit();
                displayDataUnit();
                setNullErrorUnit();
            }
        }
    }//GEN-LAST:event_btnUpdateUnitActionPerformed

    private void btnRefreshPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPageActionPerformed
        refreshPage();
    }//GEN-LAST:event_btnRefreshPageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JButton btnAddService;
    private javax.swing.JButton btnAddUnit;
    private javax.swing.JButton btnDeleteRoom;
    private javax.swing.JButton btnDeleteService;
    private javax.swing.JButton btnDeleteUnit;
    private javax.swing.JButton btnRefreshPage;
    private javax.swing.JButton btnUpdateRoom;
    private javax.swing.JButton btnUpdateService;
    private javax.swing.JButton btnUpdateUnit;
    private javax.swing.JLabel errorRoomID;
    private javax.swing.JLabel errorServiceCost;
    private javax.swing.JLabel errorServiceID;
    private javax.swing.JLabel errorServiceName;
    private javax.swing.JLabel errorUnitID;
    private javax.swing.JLabel errorUnitName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tbRoom;
    private javax.swing.JTable tbService;
    private javax.swing.JTable tbUnit;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtRoomID;
    private javax.swing.JTextField txtServiceID;
    private javax.swing.JTextField txtServiceName;
    private javax.swing.JTextField txtUnitID;
    private javax.swing.JTextField txtUnitName;
    // End of variables declaration//GEN-END:variables
}
