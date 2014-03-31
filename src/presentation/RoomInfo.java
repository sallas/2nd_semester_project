package presentation;

import domain.Controller;
import java.util.List;

public class RoomInfo extends javax.swing.JFrame {

    private Controller instance = Controller.getInstance();
    private LandingPage landingPage;

    public RoomInfo() {
        initComponents();
        refreshList();
    }

    RoomInfo(LandingPage landingPage) {
        initComponents();
        refreshList();
        this.landingPage = landingPage;
    }

    private void refreshList() {
        List<String> roomList = instance.getRooms();
        int counter = 0;
        Object[][] ob = new Object[roomList.size()][3];
        for (String i : roomList) {
            String[] separated = i.split("_");
            ob[counter][0] = separated[0];
            ob[counter][1] = separated[1];
            ob[counter][2] = separated[2];
            counter++;
        }

        jTableRooms.setModel(new javax.swing.table.DefaultTableModel(
                ob,
                new String[]{
                    "ID", "Type", "Available"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelRooms = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRooms = new javax.swing.JTable();
        jButtonBackToMenu = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelRooms.setText("Rooms : ");

        jTableRooms.setAutoCreateRowSorter(true);
        jTableRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Type", "Available From"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableRooms.setToolTipText("");
        jTableRooms.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTableRooms);

        jButtonBackToMenu.setText("Back to Menu");
        jButtonBackToMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackToMenuActionPerformed(evt);
            }
        });

        jButtonNext.setText("Next");

        jButtonBack.setText("Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonBackToMenu)
                .addGap(53, 53, 53)
                .addComponent(jButtonBack)
                .addGap(60, 60, 60)
                .addComponent(jButtonNext)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelRooms)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelRooms)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBackToMenu)
                    .addComponent(jButtonBack)
                    .addComponent(jButtonNext)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonBackToMenuActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBackToMenuActionPerformed
    {//GEN-HEADEREND:event_jButtonBackToMenuActionPerformed
        landingPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonBackToMenuActionPerformed

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
            java.util.logging.Logger.getLogger(RoomInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonBackToMenu;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JLabel jLabelRooms;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRooms;
    // End of variables declaration//GEN-END:variables
}