package presentation;

import domain.Controller;
import domain.Facility;
import domain.FacilityBooking;
import domain.GuiLogic;
import domain.HotelUser;
import domain.QueueEntry;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utility.DateLogic;

public class SportsFacilitySchedule extends javax.swing.JFrame {

    private Controller control = Controller.getInstance();
    private List<Date> dates = new ArrayList<>();
    private Facility currentFacility;
    private int currentAmountOfBookingsOnSpecificDate;
    private LandingPage landingPage;
    private DefaultTableModel model;
    DefaultListModel<Object> queueModel = new DefaultListModel<>();
    private GuiLogic logic;

    public SportsFacilitySchedule() {
        constructor();
    }

    public SportsFacilitySchedule(LandingPage landingPage) {
        constructor();
        this.landingPage = landingPage;
    }

    private void constructor() {
        initComponents();
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                control.shutdownConnection();
            }
        });
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
        model = (DefaultTableModel) timeslotTable.getModel();
        queueJList.setModel(queueModel);
        refreshAll();
    }
    
    public void refreshAll(){
        fillUpAvailablityTable();
        updateBookedLabel();
        updateQueueList();
    }

    private void updateQueueList() {
        queueModel.clear();
        int date = dayComboBox.getSelectedIndex();
        Date checkDate = dates.get(date);
        int timeslot = timeslotComboBox.getSelectedIndex() + 1;
        List<FacilityBooking> currentBookings
                = control.getAllBookingsOfSpecificDateTimeslotFacility(
                        checkDate, timeslot, currentFacility);
        if (!currentBookings.isEmpty()) {
            List<QueueEntry> queueEntries
                    = control.getQueueEntriesOfSpecificBooking(currentBookings.get(0).getID());
            for (int i = 0; i < queueEntries.size(); i++) {
                queueModel.add(i, control.getUserForSpecificUserID(queueEntries.get(i).getUserID()).getUsername());
            }
        }
    }

    private void updateBookedLabel() {
        int date = dayComboBox.getSelectedIndex();
        Date checkDate = dates.get(date);
        int timeslot = timeslotComboBox.getSelectedIndex() + 1;
        List<FacilityBooking> currentBookings
                = control.getAllBookingsOfSpecificDateTimeslotFacility(
                        checkDate, timeslot, currentFacility);
        if (currentBookings.isEmpty()) {
            bookedLabel.setText("free");
        } else {
            HotelUser hu = control.getUserForSpecificUserID(
                    currentBookings.get(0).getUserID());
            bookedLabel.setText(hu.getUsername());
        }

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

    private void fillUpAvailablityTable() {
        int date = dayComboBox.getSelectedIndex();
        Date checkDate = dates.get(date);
        logic.fillAvailabilityTable(currentFacility.getID(), checkDate, model);
        currentAmountOfBookingsOnSpecificDate
                = logic.getCurrentAmountOfBookingsOnSpecificDate(checkDate);
    }

    private void removeRows() {
        for (int i = 11; i >= 0; i--) {
            model.removeRow(i);
        }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        timeslotTable = new javax.swing.JTable();
        timeslotBookingButton = new javax.swing.JButton();
        backToMenuButton = new javax.swing.JButton();
        viewOwnBookingButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bookedByLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bookedLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        queueJList = new javax.swing.JList();

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

        timeslotTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Timeslot", "Availibility"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        timeslotTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timeslotTable.setRowSelectionAllowed(false);
        timeslotTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(timeslotTable);
        if (timeslotTable.getColumnModel().getColumnCount() > 0) {
            timeslotTable.getColumnModel().getColumn(0).setResizable(false);
            timeslotTable.getColumnModel().getColumn(1).setResizable(false);
        }

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

        viewOwnBookingButton.setText("View Own Bookings");
        viewOwnBookingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewOwnBookingButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Booked by:");

        jLabel4.setText("Queue:");

        bookedLabel.setText("free");
        bookedLabel.setToolTipText("");

        queueJList.setToolTipText("");
        jScrollPane1.setViewportView(queueJList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(facilitySpecsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkActivtyBookingButton))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeslotBookingButton)
                                    .addComponent(jLabel2)
                                    .addComponent(timeslotComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(viewOwnBookingButton)))
                            .addComponent(statusTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(220, 220, 220)
                                .addComponent(bookedByLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bookedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(facilityChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(facilityNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(backToMenuButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facilityChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facilityNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(facilitySpecsLabel)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(bookedByLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(bookedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
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
                                .addGap(5, 5, 5)
                                .addComponent(viewOwnBookingButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(backToMenuButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facilityChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilityChooserActionPerformed
        String facility = (String) facilityChooser.getSelectedItem();
        facilityNameLabel.setText(facility);
        currentFacility = control.getFacility(facility);
        facilitySpecsLabel.setText(currentFacility.toString());
        removeRows();
        fillUpAvailablityTable();
        updateBookedLabel();
        updateQueueList();
    }//GEN-LAST:event_facilityChooserActionPerformed

    private void checkActivtyBookingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActivtyBookingButtonActionPerformed
        int date = dayComboBox.getSelectedIndex();
        Date checkDate = dates.get(date);
        int timeslot = timeslotComboBox.getSelectedIndex() + 1;
        logic.setCurrentFacilityBooking(currentFacility.getID(), checkDate, timeslot);
        boolean status = logic.checkAvailableCurrentFacilityBooking();
        if (!status) {
            statusTextField.setText("That timeslot is unavialable");
        } else {
            statusTextField.setText("That timeslot is avialable");
        }
    }//GEN-LAST:event_checkActivtyBookingButtonActionPerformed

    private void dayComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayComboBoxActionPerformed
        removeRows();
        fillUpAvailablityTable();
        updateBookedLabel();
        updateQueueList();
    }//GEN-LAST:event_dayComboBoxActionPerformed

    private void timeslotBookingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeslotBookingButtonActionPerformed
        
        System.out.println(logic.getCurrentUserID());
        int date = dayComboBox.getSelectedIndex();
        Date checkDate = dates.get(date);
        currentAmountOfBookingsOnSpecificDate =
                logic.getCurrentAmountOfBookingsOnSpecificDate(checkDate);
        if (currentAmountOfBookingsOnSpecificDate >= 4) {
            statusTextField.setText("You can't book anything more on this date");
            return;
        }
        
        int timeslot = timeslotComboBox.getSelectedIndex() + 1;
        if (logic.userHasBookingOnDateTimeslot(checkDate, timeslot)) {
            statusTextField.setText("You already have a booking on this timeslot");
            return;
        }
        Calendar rightNow = Calendar.getInstance();
        Calendar checkDateCalendar = DateLogic.sqlDateToCalendarDate(checkDate);
        checkDateCalendar.add(Calendar.HOUR, 6 + timeslot);
        if (rightNow.after(checkDateCalendar)) {
            statusTextField.setText("You are to late to book this timeslot");
            return;
        }
        logic.setCurrentFacilityBooking(currentFacility.getID(), checkDate, timeslot);
        if (!logic.checkAvailableCurrentFacilityBooking()) {
            statusTextField.setText("Sorry that timeslot has already been booked");
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to get in a queue for this timeslot?", 
                    "Warning",
                    dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                List<FacilityBooking> currentBookings
                = control.getAllBookingsOfSpecificDateTimeslotFacility(
                        checkDate, timeslot, currentFacility);
                control.queueUserForSpecificTimeslot(currentBookings.get(0).getID(),
                        logic.getCurrentUserID());
                statusTextField.setText("Queued for activity");
                updateQueueList();
            }
            return;
        }
        if (logic.saveCurrentFacilityBooking()) {
            statusTextField.setText("Your booking has been saved");
        } else {
            statusTextField.setText("Your booking wasn't saved");
        }
        removeRows();
        fillUpAvailablityTable();
        updateBookedLabel();
        updateQueueList();
    }//GEN-LAST:event_timeslotBookingButtonActionPerformed

    private void backToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMenuButtonActionPerformed
        landingPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToMenuButtonActionPerformed

    private void timeslotComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeslotComboBoxActionPerformed
        updateBookedLabel();
        updateQueueList();
    }//GEN-LAST:event_timeslotComboBoxActionPerformed

    private void viewOwnBookingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewOwnBookingButtonActionPerformed
        OwnBookings ownBooking = new OwnBookings(this);
        ownBooking.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_viewOwnBookingButtonActionPerformed

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
            java.util.logging.Logger.getLogger(SportsFacilitySchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SportsFacilitySchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SportsFacilitySchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SportsFacilitySchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SportsFacilitySchedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToMenuButton;
    private javax.swing.JLabel bookedByLabel;
    private javax.swing.JLabel bookedLabel;
    private javax.swing.JButton checkActivtyBookingButton;
    private javax.swing.JComboBox dayComboBox;
    private javax.swing.JComboBox facilityChooser;
    private javax.swing.JLabel facilityNameLabel;
    private javax.swing.JLabel facilitySpecsLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList queueJList;
    private javax.swing.JLabel statusTextField;
    private javax.swing.JButton timeslotBookingButton;
    private javax.swing.JComboBox timeslotComboBox;
    private javax.swing.JTable timeslotTable;
    private javax.swing.JButton viewOwnBookingButton;
    // End of variables declaration//GEN-END:variables

}
