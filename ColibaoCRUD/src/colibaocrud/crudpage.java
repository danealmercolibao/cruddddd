package colibaocrud;

import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getString;
import javax.swing.table.DefaultTableModel;

public class crudpage extends javax.swing.JFrame {

    private int selectedUserId = -1; 
    private DefaultTableModel tableModel;
    private boolean isAddMode = false;
    private boolean isUpdateMode = false;
    
    public crudpage() {
        initComponents();
        setupTableModel();
        populatable();
        initializeComboBox();
        addTableSelectionListener();
        clearFields();
        updateButtonStates();
        
       
        setTitle("User Management System");
        setResizable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        setPreferredSize(new java.awt.Dimension(850, 650));
        pack(); 
        setLocationRelativeTo(null);
    }
    
    public void populatable(){
    int colCount;
    try{
        Connection conn = SqlConnection.getConnection();
        Statement st = conn.createStatement();
        String query = "Select * FROM users";
        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsData = rs.getMetaData();
        colCount = rsData.getColumnCount();

        DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
        tblModel.setRowCount(0); 
        while (rs.next()){
            Vector<Object> rowData = new Vector<>(); 
          
            rowData.add(rs.getInt("userID"));
            rowData.add(rs.getString("userName"));
            rowData.add(rs.getString("userPassword"));
            rowData.add(rs.getString("userLevel"));

            tblModel.addRow(rowData); 
        }
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e.getMessage()); 
    }
}
    public void setupTableModel() {
        tableModel = (DefaultTableModel) table.getModel();
        table.setRowHeight(25);
        table.setSelectionBackground(new java.awt.Color(52, 152, 219));
        table.setSelectionForeground(java.awt.Color.WHITE);
        table.setGridColor(new java.awt.Color(189, 195, 199));
    }
    
    public void initializeComboBox() {
        userlvl.removeAllItems();
        userlvl.addItem("Admin");
        userlvl.addItem("Guest");
    }
    
    public void clearFields() {
        username.setText("");
        pass.setText("");
        confirmpass.setText("");
        userlvl.setSelectedIndex(0);
        selectedUserId = -1;
        // Don't reset modes here, only reset button states
        updateButtonStates();
    }
    
    private void updateButtonStates() {
        if (isAddMode) {
            // Add New mode: disable update and delete
            add.setText("CANCEL ADD");
            add.setBackground(new java.awt.Color(255, 165, 0)); // Orange color for cancel
            update.setEnabled(false);
            delete.setEnabled(false);
            save.setEnabled(true);
            close.setEnabled(true);
        } else if (isUpdateMode) {
            // Update mode: disable add new and delete
            add.setEnabled(false);
            update.setText("CANCEL UPDATE");
            update.setBackground(new java.awt.Color(255, 165, 0)); // Orange color for cancel
            delete.setEnabled(false);
            save.setEnabled(true);
            close.setEnabled(true);
        } else {
            // Normal mode: all buttons enabled
            add.setText("ADD NEW");
            add.setBackground(new java.awt.Color(102, 255, 102));
            add.setEnabled(true);
            
            update.setText("UPDATE");
            update.setBackground(new java.awt.Color(0, 102, 255));
            update.setEnabled(true);
            
            delete.setEnabled(true);
            save.setEnabled(true);
            close.setEnabled(true);
        }
    }
    
    public void addTableSelectionListener() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    selectedUserId = (int) table.getValueAt(selectedRow, 0);
                    username.setText((String) table.getValueAt(selectedRow, 1));
                    pass.setText((String) table.getValueAt(selectedRow, 2));
                    confirmpass.setText((String) table.getValueAt(selectedRow, 2));
                    userlvl.setSelectedItem(table.getValueAt(selectedRow, 3));
                }
            }
        });
    }
    
    private boolean validateFields() {
        if (username.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username!");
            return false;
        }
        if (pass.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a password!");
            return false;
        }
        if (!pass.getText().equals(confirmpass.getText())) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return false;
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        userlvl = new javax.swing.JComboBox<>();
        search = new javax.swing.JTextField();
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        username = new javax.swing.JTextField();
        pass = new javax.swing.JTextField();
        confirmpass = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        update = new javax.swing.JButton();
        save = new javax.swing.JButton();
        close = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Maintenance");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "User ID", "Username", "Password", "User Level"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Enter Username:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Enter Password:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Confirm Password:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Enter User Level:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Search Username:");

        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userlvl, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(search)
                            .addComponent(pass)
                            .addComponent(confirmpass)
                            .addComponent(username))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(confirmpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(userlvl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        update.setBackground(new java.awt.Color(0, 102, 255));
        update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        save.setBackground(new java.awt.Color(0, 102, 255));
        save.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("SAVE");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        close.setBackground(new java.awt.Color(255, 0, 0));
        close.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        close.setForeground(new java.awt.Color(255, 255, 255));
        close.setText("CLOSE");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(255, 0, 0));
        delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        add.setBackground(new java.awt.Color(102, 255, 102));
        add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add.setText("ADD NEW");
        add.setPreferredSize(new java.awt.Dimension(75, 35));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if (isAddMode) {
            // Cancel Add mode
            isAddMode = false;
            clearFields();
            updateButtonStates();
            JOptionPane.showMessageDialog(this, "Add operation cancelled.");
            return;
        }
        
        if (isUpdateMode) {
            // Can't add in update mode
            JOptionPane.showMessageDialog(this, "Please finish or cancel the current update operation first.");
            return;
        }
        
        // Enter Add mode
        isAddMode = true;
        clearFields();
        updateButtonStates();
        username.requestFocus();
        JOptionPane.showMessageDialog(this, "Add mode activated. Fill the fields and click SAVE to add new user.");
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if (selectedUserId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user from the table to delete!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this user?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = SqlConnection.getConnection();
                String query = "DELETE FROM users WHERE userID = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, selectedUserId);
                
                int result = pst.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully!");
                    populatable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete user!");
                }
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No data in table to save!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "This will synchronize all table data with the database. Continue?", 
            "Confirm Save All", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        try {
            Connection conn = SqlConnection.getConnection();
            conn.setAutoCommit(false); 
            
            String clearQuery = "DELETE FROM users";
            PreparedStatement clearPst = conn.prepareStatement(clearQuery);
            clearPst.executeUpdate();
            
            String insertQuery = "INSERT INTO users (userName, userPassword, userLevel) VALUES (?, ?, ?)";
            PreparedStatement insertPst = conn.prepareStatement(insertQuery);
            
            int savedCount = 0;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String userName = tableModel.getValueAt(i, 1).toString();
                String userPassword = tableModel.getValueAt(i, 2).toString();
                String userLevel = tableModel.getValueAt(i, 3).toString();
                
                insertPst.setString(1, userName);
                insertPst.setString(2, userPassword);
                insertPst.setString(3, userLevel);
                insertPst.addBatch();
                savedCount++;
            }
            
            insertPst.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            
            JOptionPane.showMessageDialog(this, 
                "Successfully saved " + savedCount + " users to database!");
            
            populatable();
            clearFields();
            conn.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_saveActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        if (isUpdateMode) {
            // Cancel Update mode
            isUpdateMode = false;
            clearFields();
            updateButtonStates();
            JOptionPane.showMessageDialog(this, "Update operation cancelled.");
            return;
        }
        
        if (isAddMode) {
            // Can't update in add mode
            JOptionPane.showMessageDialog(this, "Please finish or cancel the current add operation first.");
            return;
        }
        
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user from the table to update!");
            return;
        }
        
        // Enter Update mode
        isUpdateMode = true;
        updateButtonStates();
        username.requestFocus();
        JOptionPane.showMessageDialog(this, "Update mode activated. Modify the fields and click SAVE to update the user.");
    }//GEN-LAST:event_updateActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to exit?", 
            "Confirm Exit", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_closeActionPerformed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        String searchText = search.getText().trim();
        if (searchText.isEmpty()) {
            populatable(); 
            return;
        }
        
        try {
            Connection conn = SqlConnection.getConnection();
            String query = "SELECT * FROM users WHERE userName LIKE ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, "%" + searchText + "%");
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
            tblModel.setRowCount(0);
            
            while (rs.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(rs.getInt("userID"));
                rowData.add(rs.getString("userName"));
                rowData.add(rs.getString("userPassword"));
                rowData.add(rs.getString("userLevel"));
                tblModel.addRow(rowData);
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching users: " + e.getMessage());
        }
    }//GEN-LAST:event_searchKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(crudpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crudpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crudpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crudpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                crudpage cp = new crudpage();
                cp.setLocationRelativeTo(null);
                cp.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton close;
    private javax.swing.JTextField confirmpass;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pass;
    private javax.swing.JButton save;
    private javax.swing.JTextField search;
    private javax.swing.JTable table;
    private javax.swing.JButton update;
    private javax.swing.JComboBox<String> userlvl;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
