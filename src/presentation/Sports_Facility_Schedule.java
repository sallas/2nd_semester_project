package presentation;

import domain.Controller;
import domain.Facility;
import domain.FacilityBooking;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Sports_Facility_Schedule extends javax.swing.JFrame {

    private Controller control = Controller.getInstance();
    private List<Date> dates = new ArrayList<>();
    private Facility currentFacility;

    public Sports_Facility_Schedule() {
        initComponents();
        List<String> rooms = control.getAllFacilityNames();
        facilityChooser.setModel(new javax.swing.DefaultComboBoxModel(
                rooms.toArray()));
        String name = (String) facilityChooser.getSelectedItem();
        facilityNameLabel.setText(name);
        currentFacility =control.getFacility(name);
        facilitySpecsLabel.setText(currentFacility.toString());
        fillComboBoxes();
        setUpDates();
    }

    private void fillComboBoxes() {
        timeslotComboBox.setModel(new javax.swing.DefaultComboBoxModel(
                new String[]{"8am - 9am", "9am - 10am", "10am - 11am", "11am - 12am",
                    "12am - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm",
                    "4pm - 5pm", "5pm - 6pm", "6pm - 7pm", "7pm - 8pm"}));
    }

    private void setUpDates() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH);
        int date = rightNow.get(Calendar.DATE);
        rightNow.clear();
        rightNow.set(year, month, date);
        String[] dateStrings = new String[8];
        for (int i = 0; i < 8; i++) {
            dates.add(new Date(rightNow.getTimeInMillis()));
            dateStrings[i] = dates.get(i).toString();
            rightNow.add(Calendar.DATE, 1);
        }
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

        timeslotComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Day");

        jLabel2.setText("Timeslot");

        checkActivtyBookingButton.setText("Check");
        checkActivtyBookingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActivtyBookingButtonActionPerformed(evt);
            }
        });

        statusTextField.setText("Status Text");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(facilityChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(facilityNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facilitySpecsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(timeslotComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(checkActivtyBookingButton))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facilityChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facilityNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(facilitySpecsLabel)
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeslotComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(checkActivtyBookingButton)
                .addGap(34, 34, 34)
                .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
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
        FacilityBooking fb = new FacilityBooking(99, currentFacility.getID(), checkDate, timeslot);
        if(!control.checkAvailableFacilityBooking(fb))
            statusTextField.setText("That timeslot is unavialable");
        else
            statusTextField.setText("That timeslot is avialable");
    }//GEN-LAST:event_checkActivtyBookingButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Sports_Facility_Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sports_Facility_Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sports_Facility_Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sports_Facility_Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sports_Facility_Schedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkActivtyBookingButton;
    private javax.swing.JComboBox dayComboBox;
    private javax.swing.JComboBox facilityChooser;
    private javax.swing.JLabel facilityNameLabel;
    private javax.swing.JLabel facilitySpecsLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel statusTextField;
    private javax.swing.JComboBox timeslotComboBox;
    // End of variables declaration//GEN-END:variables

}
