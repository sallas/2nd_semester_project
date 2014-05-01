package presentation;

import domain.GuiLogic;
import java.sql.Date;
import javax.swing.DefaultListModel;

public class Search extends javax.swing.JFrame {

    private String currentObject;
    private String currentVariable;
    private DefaultListModel model;
    private GuiLogic logic;
    private ReceptionistMenu menu;

    public Search() {
        init();
    }

    public Search(ReceptionistMenu menu) {
        init();
        this.menu = menu;
    }

    private void init() {
        initComponents();
        model = new DefaultListModel();
        resultList.setModel(model);
        logic = GuiLogic.getInstance();
        fillObjectComboBox();
    }

    public void fillObjectComboBox() {
        objectComboBox.removeAllItems();
        objectComboBox.addItem("Reservation");
        objectComboBox.addItem("Room");
        objectComboBox.addItem("Customer");
        objectComboBox.addItem("User");
        objectComboBox.addItem("Sports booking");
        objectComboBox.addItem("Facility");
    }

    public void fillVariableComboBox(String object) {
        variableComboBox.removeAllItems();
        if ("reservation".equalsIgnoreCase(currentObject)) {
            variableComboBox.addItem("ID");
            variableComboBox.addItem("Room_ID");
            variableComboBox.addItem("Customer_ID");
            variableComboBox.addItem("Checkin_Date");
            variableComboBox.addItem("Departure_Date");
        } else if ("room".equalsIgnoreCase(currentObject)) {
            variableComboBox.addItem("ID");
            variableComboBox.addItem("Type");
        } else if ("customer".equalsIgnoreCase(currentObject)) {
            variableComboBox.addItem("ID");
            variableComboBox.addItem("Address");
            variableComboBox.addItem("Country");
            variableComboBox.addItem("First_name");
            variableComboBox.addItem("Last_name");
            variableComboBox.addItem("Phone");
            variableComboBox.addItem("Email");
            variableComboBox.addItem("Travel_Agency");
        } else if ("user".equalsIgnoreCase(currentObject)) {
            variableComboBox.addItem("ID");
            variableComboBox.addItem("Username");
            variableComboBox.addItem("Status");
            variableComboBox.addItem("Reservation_ID");
        } else if ("sports booking".equalsIgnoreCase(currentObject)) {
            variableComboBox.addItem("ID");
            variableComboBox.addItem("Facility_ID");
            variableComboBox.addItem("Booking_Date");
            variableComboBox.addItem("Timeslot");
            variableComboBox.addItem("User_ID");
        } else if ("facility".equalsIgnoreCase(currentObject)) {
            variableComboBox.addItem("ID");
            variableComboBox.addItem("Name");
            variableComboBox.addItem("Type");
            variableComboBox.addItem("Capacity");
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        resultList = new javax.swing.JList();
        objectComboBox = new javax.swing.JComboBox();
        variableComboBox = new javax.swing.JComboBox();
        variableTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        statusTextField = new javax.swing.JLabel();
        backToMenuButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(resultList);

        objectComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        objectComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objectComboBoxActionPerformed(evt);
            }
        });

        variableComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        variableComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                variableComboBoxActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        statusTextField.setText("Status");

        backToMenuButton.setText("Back to menu");
        backToMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(variableComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(variableTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(objectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backToMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(objectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(variableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(variableTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backToMenuButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(statusTextField)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void objectComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_objectComboBoxActionPerformed
        if (objectComboBox.getComponentCount() == 0) {
            return;
        }
        currentObject = (String) objectComboBox.getSelectedItem();
        fillVariableComboBox(currentObject);
    }//GEN-LAST:event_objectComboBoxActionPerformed

    private void variableComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_variableComboBoxActionPerformed
        if (variableComboBox.getComponentCount() == 0) {
            return;
        }
        currentVariable = (String) variableComboBox.getSelectedItem();
    }//GEN-LAST:event_variableComboBoxActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        statusTextField.setText("");
        String variableText = variableTextField.getText();
        Object variable;
        if(currentVariable.contains("Date")) {
            if(variableText.matches("[0-9]{4}[-][0-9][0-9][-][0-9][0-9]")) {
                variable = Date.valueOf(variableText);
            } else {
                statusTextField.setText("Date format invalid use :YYYY-MM-DD");
                return;
            }
            
        } else {
            variable = variableText;
        }
        model = logic.search(currentObject, currentVariable,
                variable , model);
        resultList.setModel(model);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void backToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMenuButtonActionPerformed
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToMenuButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToMenuButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox objectComboBox;
    private javax.swing.JList resultList;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel statusTextField;
    private javax.swing.JComboBox variableComboBox;
    private javax.swing.JTextField variableTextField;
    // End of variables declaration//GEN-END:variables
}
