package presentation;

import domain.Controller;
import domain.Facility;
import domain.GuiLogic;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReceptionistFacilityBooking extends javax.swing.JFrame {

    private Controller control = Controller.getInstance();
    private List<Date> dates = new ArrayList<>();
    private Facility currentFacility;
    private LandingPage landingPage;
    private GuiLogic logic;

    public ReceptionistFacilityBooking() {
        constructor();
    }

    public ReceptionistFacilityBooking(LandingPage landingPage) {
        constructor();
        this.landingPage = landingPage;
    }

    private void constructor() {
        initComponents();
        logic = GuiLogic.getInstance();
        List<String> rooms = control.getAllFacilityNames();
        facilityChooser.setModel(new javax.swing.DefaultComboBoxModel(
                rooms.toArray()));
        String name = (String) facilityChooser.getSelectedItem();
        facilityNameLabel.setText(name);
        currentFacility = control.getFacility(name);
        facilitySpecsLabel.setText(currentFacility.toString());
        fillComboBoxes();
        setUpDates();
    }

    private void fillComboBoxes() {
        timeslotComboBox.setModel(new javax.swing.DefaultComboBoxModel(
                new String[]{"8 - 9", "9 - 10", "10 - 11", "11 - 12",
                    "12 - 13", "13 - 14", "14 - 15", "15 - 16",
                    "16 - 17", "17 - 18", "18 - 19", "19 - 20"}));
    }

    private void setUpDates() {
        String[] dateStrings = logic.setUpDates(dates);
        dayComboBox.setModel(new javax.swing.DefaultComboBoxModel(dateStrings));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        facilityChooser = new javax.swing.JComboBox();
        facilityNameLabel = new javax.swing.JLabel();
        facilitySpecsLabel = new javax.swing.JLabel();
        dayComboBox = new javax.swing.JComboBox();
        timeslotComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        checkActivtyBookingButton = new javax.swing.JButton();
        statusTextField = new javax.swing.JLabel();
        timeslotBookingButton = new javax.swing.JButton();
        backToMenuButton = new javax.swing.JButton();
        userIDField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        facilityChooser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        facilityChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilityChooserActionPerformed(evt);
            }
        });

        facilityNameLabel.setText("Facility");

        facilitySpecsLabel.setText("Facility Specifications");

        dayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dayComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayComboBoxActionPerformed(evt);
            }
        });

        timeslotComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        timeslotComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeslotComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Day");

        jLabel2.setText("Timeslot");

        checkActivtyBookingButton.setText("Check");
        checkActivtyBookingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActivtyBookingButtonActionPerformed(evt);
            }
        });

        statusTextField.setText("Status Text");

        timeslotBookingButton.setText("Book timeslot");
        timeslotBookingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeslotBookingButtonActionPerformed(evt);
            }
        });

        backToMenuButton.setText("Back to menu");
        backToMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMenuButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("User ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(facilitySpecsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(facilityChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(facilityNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(backToMenuButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkActivtyBookingButton))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(timeslotComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(timeslotBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(3, 3, 3)
                                .addComponent(userIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facilityChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facilityNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(facilitySpecsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeslotComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkActivtyBookingButton)
                    .addComponent(timeslotBookingButton))
                .addGap(35, 35, 35)
                .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(backToMenuButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facilityChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilityChooserActionPerformed
        String facility = (String) facilityChooser.getSelectedItem();
        facilityNameLabel.setText(facility);
        currentFacility = control.getFacility(facility);
    }//GEN-LAST:event_facilityChooserActionPerformed

    private void checkActivtyBookingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActivtyBookingButtonActionPerformed
        int date = dayComboBox.getSelectedIndex();
        Date checkDate = dates.get(date);
        int timeslot = timeslotComboBox.getSelectedIndex() + 1;
        int userID = logic.getUserID(userIDField.getText());
        if (userID == -1) {
            statusTextField.setText("Invalid user ID");
            return;
        }
        logic.setCurrentFacilityBooking(currentFacility.getID(), checkDate, timeslot, userID);
        if (!logic.checkAvailableCurrentFacilityBooking()) {
            statusTextField.setText("That timeslot is unavialable");
        } else {
            statusTextField.setText("That timeslot is avialable");
        }
    }//GEN-LAST:event_checkActivtyBookingButtonActionPerformed

    private void dayComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayComboBoxActionPerformed
    }//GEN-LAST:event_dayComboBoxActionPerformed

    private void timeslotBookingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeslotBookingButtonActionPerformed
        int date = dayComboBox.getSelectedIndex();
        Date checkDate = dates.get(date);
        int timeslot = timeslotComboBox.getSelectedIndex() + 1;
        
        int userID = logic.getUserID(userIDField.getText());
        if (userID == -1) {
            statusTextField.setText("Invalid user ID");
            return;
        }
        logic.setCurrentFacilityBooking(currentFacility.getID(), checkDate, timeslot, userID);
        if (!logic.checkAvailableCurrentFacilityBooking()) {
            statusTextField.setText("Sorry that timeslot has already been booked");
            return;
        }
        if (logic.saveCurrentFacilityBooking()) {
            statusTextField.setText("The booking has been saved");
        } else {
            statusTextField.setText("The booking wasn't saved");
        }
    }//GEN-LAST:event_timeslotBookingButtonActionPerformed

    private void backToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMenuButtonActionPerformed
        landingPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToMenuButtonActionPerformed

    private void timeslotComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeslotComboBoxActionPerformed
    }//GEN-LAST:event_timeslotComboBoxActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ReceptionistFacilityBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceptionistFacilityBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceptionistFacilityBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceptionistFacilityBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReceptionistFacilityBooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToMenuButton;
    private javax.swing.JButton checkActivtyBookingButton;
    private javax.swing.JComboBox dayComboBox;
    private javax.swing.JComboBox facilityChooser;
    private javax.swing.JLabel facilityNameLabel;
    private javax.swing.JLabel facilitySpecsLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel statusTextField;
    private javax.swing.JButton timeslotBookingButton;
    private javax.swing.JComboBox timeslotComboBox;
    private javax.swing.JTextField userIDField;
    // End of variables declaration//GEN-END:variables

}
