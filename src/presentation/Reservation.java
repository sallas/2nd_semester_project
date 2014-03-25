package presentation;

import domain.Controller;
import domain.WrongEmail;
import domain.WrongNumberOfNights;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class Reservation extends javax.swing.JFrame {

    Controller control = Controller.getInstance();
    private boolean status;
    private int currentBookingRoomID;
    private int currentBookingNumNights;
    private Date currentBookingArrivalDate = null;
    private String currentBookingType = null;

    /**
     * Creates new form Reservation
     */
    public Reservation() {
        initComponents();
        typeField.addItem("single");
        typeField.addItem("double");
        typeField.addItem("family");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reservationTitle = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        familyNameLabel = new javax.swing.JLabel();
        firstNameField = new javax.swing.JTextField();
        familyNameField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        countryLabel = new javax.swing.JLabel();
        countryField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        travelAgencyField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        travelAgencyLabel = new javax.swing.JLabel();
        checkinLabel = new javax.swing.JLabel();
        nightsLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        typeField = new javax.swing.JComboBox();
        submitButton = new javax.swing.JButton();
        checkDatePicker = new org.jdesktop.swingx.JXDatePicker();
        nightsCounter = new javax.swing.JSpinner();
        roomAvailability = new javax.swing.JButton();
        RoomNumLabel = new javax.swing.JLabel();
        statusText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registration of apartment");

        reservationTitle.setText("Reservation of apartment:");

        firstNameLabel.setText("First Name:");

        familyNameLabel.setText("Family Name:");

        firstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameFieldActionPerformed(evt);
            }
        });

        familyNameField.setToolTipText("");

        addressLabel.setText("Address:");

        countryLabel.setText("Country:");

        countryField.setToolTipText("");
        countryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countryFieldActionPerformed(evt);
            }
        });

        phoneLabel.setText("Phone:");

        emailLabel.setText("Email:");

        phoneField.setToolTipText("");
        phoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneFieldActionPerformed(evt);
            }
        });

        emailField.setToolTipText("");

        travelAgencyField.setToolTipText("");
        travelAgencyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travelAgencyFieldActionPerformed(evt);
            }
        });

        travelAgencyLabel.setText("Agency:");

        checkinLabel.setText("Check in:");

        nightsLabel.setText("No. of Nights:");

        jLabel2.setText("Ap. type:");

        submitButton.setText("Submit");
        submitButton.setToolTipText("");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        roomAvailability.setText("Check");
        roomAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomAvailabilityActionPerformed(evt);
            }
        });

        RoomNumLabel.setText("Room Number");

        statusText.setMaximumSize(new java.awt.Dimension(150, 15));
        statusText.setMinimumSize(new java.awt.Dimension(150, 15));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(phoneLabel)
                    .addComponent(addressLabel)
                    .addComponent(firstNameLabel)
                    .addComponent(countryLabel)
                    .addComponent(jLabel1)
                    .addComponent(travelAgencyLabel)
                    .addComponent(checkinLabel)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nightsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nightsCounter, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailField))
                    .addComponent(addressField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(countryField)
                    .addComponent(travelAgencyField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(roomAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(typeField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RoomNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(familyNameLabel)
                        .addGap(24, 24, 24)
                        .addComponent(familyNameField)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(reservationTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusText, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reservationTitle)
                    .addComponent(statusText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(familyNameLabel)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(familyNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countryLabel)
                    .addComponent(countryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(emailLabel)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(travelAgencyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(travelAgencyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkinLabel)
                        .addComponent(nightsLabel)
                        .addComponent(nightsCounter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomNumLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roomAvailability))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameFieldActionPerformed

    private void countryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countryFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countryFieldActionPerformed

    private void phoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneFieldActionPerformed

    private void travelAgencyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travelAgencyFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_travelAgencyFieldActionPerformed

    private void roomAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomAvailabilityActionPerformed
        statusText.setText(null);
        Calendar arrivalDate = Calendar.getInstance();
        arrivalDate.clear();
        arrivalDate.setTime(checkDatePicker.getDate());
        Calendar departureDate = (Calendar) arrivalDate.clone();
        currentBookingNumNights = (Integer) nightsCounter.getValue();
        if (currentBookingNumNights <= 0) {
            statusText.setText("Please choose a positive number of nights");
            return;
        }

        departureDate.add(Calendar.DATE, currentBookingNumNights);
        currentBookingArrivalDate = checkDatePicker.getDate();
        currentBookingRoomID = control.getAvailableRoomOfSpecificType((String) typeField.getSelectedItem(),
                arrivalDate, departureDate);
        currentBookingType = (String) typeField.getSelectedItem();
        if (currentBookingRoomID == -1) {
            RoomNumLabel.setText("No room");
            statusText.setText("No room is available on those dates");
        }
        else {
            RoomNumLabel.setText(currentBookingRoomID + "");
        }
    }//GEN-LAST:event_roomAvailabilityActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        statusText.setText(null);
        status = true;

        String firstName
                = textFieldChecker(firstNameField, "Please enter first name");
        String familyName
                = textFieldChecker(familyNameField, "Please Enter family name");
        String address
                = textFieldChecker(addressField, "Please enter address");
        String country
                = textFieldChecker(countryField, "Please enter country");
        String phone
                = textFieldChecker(phoneField, "Please enter phone number");
        String travelAgency
                = textFieldChecker(travelAgencyField,
                        "Please enter travel agency name");
        String email
                = textFieldChecker(emailField, "Please enter email");

        System.out.println(address);
        if (status == false) {
            return;
        }
        if (checkDatePicker.getDate() == null) {
            statusText.setText("Please choose a arrival date");
            return;
        }
        if (!checkDatePicker.getDate().equals(currentBookingArrivalDate)) {
            statusText.setText("This date hasn't been checked");
            return;
        }
        if ((int) nightsCounter.getValue() <= 0) {
            statusText.setText("Please choose a positive number of nights");
            return;
        }
        if ((int) nightsCounter.getValue() != currentBookingNumNights) {
            statusText.setText("Those number of nights hasn't been checked");
            return;
        }
        if (currentBookingRoomID == -1) {
            statusText.setText("Please check to find availble room");
            return;
        }
        String type = (String) typeField.getSelectedItem();
        if (!type.equals(currentBookingType)) {
            statusText.setText("That room type hasn't beeen checked");
            return;
        }
        java.sql.Date arrival = java.sql.Date.valueOf("2000-01-01");
        arrival.setTime(currentBookingArrivalDate.getTime());
        try {
            control.createNewReservation(firstName, familyName, address,
                    country, phone, email, travelAgency, arrival,
                    currentBookingNumNights, currentBookingRoomID);
        }
        catch (WrongNumberOfNights ex) {
            statusText.setText("Please choose a positive number of nights");
            return;
        }
        catch (WrongEmail ex) {
            statusText.setText("Please enter a correct email");
            return;
        }
        statusText.setText("Registered");
    }//GEN-LAST:event_submitButtonActionPerformed

    private String textFieldChecker(JTextField textField, String errorString) {
        String returnString = null;
        if (textField.getText().equals("")) {
            statusText.setText(errorString);
            status = false;
        }
        else {
            returnString = textField.getText();
        }
        return returnString;
    }

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
        }
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reservation().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RoomNumLabel;
    private javax.swing.JTextField addressField;
    private javax.swing.JLabel addressLabel;
    private org.jdesktop.swingx.JXDatePicker checkDatePicker;
    private javax.swing.JLabel checkinLabel;
    private javax.swing.JTextField countryField;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField familyNameField;
    private javax.swing.JLabel familyNameLabel;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSpinner nightsCounter;
    private javax.swing.JLabel nightsLabel;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel reservationTitle;
    private javax.swing.JButton roomAvailability;
    private javax.swing.JLabel statusText;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField travelAgencyField;
    private javax.swing.JLabel travelAgencyLabel;
    private javax.swing.JComboBox typeField;
    // End of variables declaration//GEN-END:variables
}
