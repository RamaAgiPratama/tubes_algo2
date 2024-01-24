/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import Master.Barang;
import Master.Pelanggan;
import Transaksi.Pembelian;
import Transaksi.Penjualan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author ACER
 */
public class Home extends javax.swing.JFrame {
    
    /**
     * Creates new form MenuBar
     */
    public Home() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTanggal();
        jam();
    }
    
    public void setTanggal(){
    java.util.Date skrg = new java.util.Date();
    java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("dd/MM/yyyy");
    tanggalkelompok1.setText(kal.format(skrg));
    }
    
    public void jam() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SimpleDateFormat tgl = new SimpleDateFormat("EEEE-dd-MMM-yyyy");
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                Date dt = new Date();
                int nilai_jam = dt.getHours();
                int nilai_menit = dt.getMinutes();
                int nilai_detik = dt.getSeconds();
                if (nilai_jam <= 9) {
                    nol_jam = "0";
                }
                if (nilai_menit <= 9) {
                    nol_menit = "0";
                }
                if (nilai_detik <= 9) {
                    nol_detik = "0";
                }
                String Jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                waktukelompok1.setText("  " + Jam + " : " + menit + " : " + detik + "  ");
                
            }
        };
        new javax.swing.Timer(1000, taskPerformer).start();
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
        jPanel3 = new javax.swing.JPanel();
        tanggalkelompok1 = new javax.swing.JLabel();
        toolBar = new javax.swing.JToolBar();
        developerkelompok1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bantuankelompok1 = new javax.swing.JButton();
        waktukelompok1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        homekelompok1 = new javax.swing.JButton();
        pelanggankelompok1 = new javax.swing.JButton();
        barangkelompok1 = new javax.swing.JButton();
        pembeliankelompok1 = new javax.swing.JButton();
        logoutkelompok1 = new javax.swing.JButton();
        penjualankelompok1 = new javax.swing.JButton();
        laporankelompok1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(143, 176, 219));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));

        tanggalkelompok1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        tanggalkelompok1.setForeground(new java.awt.Color(255, 255, 255));

        toolBar.setRollover(true);

        developerkelompok1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        developerkelompok1.setForeground(new java.awt.Color(255, 255, 255));
        developerkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user.png"))); // NOI18N
        developerkelompok1.setText("Developer");
        developerkelompok1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        developerkelompok1.setFocusable(false);
        developerkelompok1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        developerkelompok1.setOpaque(false);
        developerkelompok1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        developerkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                developerkelompok1ActionPerformed(evt);
            }
        });
        toolBar.add(developerkelompok1);

        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(jButton2);

        bantuankelompok1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bantuankelompok1.setForeground(new java.awt.Color(255, 255, 255));
        bantuankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/info.png"))); // NOI18N
        bantuankelompok1.setText("Help");
        bantuankelompok1.setFocusable(false);
        bantuankelompok1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bantuankelompok1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bantuankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bantuankelompok1ActionPerformed(evt);
            }
        });
        toolBar.add(bantuankelompok1);

        waktukelompok1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        waktukelompok1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tanggalkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waktukelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(waktukelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
                    .addComponent(tanggalkelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("\"TOKO PAKAIAN  IBU OTIH\"");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Jl. Kantor Pos No.44, Hegarsari, Kec. Pataruman, Kota Banjar, Jawa Barat 46322, Indonesia.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(348, 348, 348)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(219, 219, 219))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        homekelompok1.setBackground(new java.awt.Color(51, 255, 255));
        homekelompok1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        homekelompok1.setForeground(new java.awt.Color(0, 0, 0));
        homekelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/home.png"))); // NOI18N
        homekelompok1.setText("HOME");
        homekelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homekelompok1ActionPerformed(evt);
            }
        });

        pelanggankelompok1.setBackground(new java.awt.Color(51, 255, 255));
        pelanggankelompok1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        pelanggankelompok1.setForeground(new java.awt.Color(0, 0, 0));
        pelanggankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pelanggan.png"))); // NOI18N
        pelanggankelompok1.setText("PELANGGAN");
        pelanggankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pelanggankelompok1ActionPerformed(evt);
            }
        });

        barangkelompok1.setBackground(new java.awt.Color(51, 255, 255));
        barangkelompok1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        barangkelompok1.setForeground(new java.awt.Color(0, 0, 0));
        barangkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/barang.png"))); // NOI18N
        barangkelompok1.setText("BARANG");
        barangkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barangkelompok1ActionPerformed(evt);
            }
        });

        pembeliankelompok1.setBackground(new java.awt.Color(51, 255, 255));
        pembeliankelompok1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        pembeliankelompok1.setForeground(new java.awt.Color(0, 0, 0));
        pembeliankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buy.png"))); // NOI18N
        pembeliankelompok1.setText("PEMBELIAN");
        pembeliankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pembeliankelompok1ActionPerformed(evt);
            }
        });

        logoutkelompok1.setBackground(new java.awt.Color(51, 255, 255));
        logoutkelompok1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        logoutkelompok1.setForeground(new java.awt.Color(0, 0, 0));
        logoutkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        logoutkelompok1.setText("LOGOUT");
        logoutkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutkelompok1ActionPerformed(evt);
            }
        });

        penjualankelompok1.setBackground(new java.awt.Color(51, 255, 255));
        penjualankelompok1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        penjualankelompok1.setForeground(new java.awt.Color(0, 0, 0));
        penjualankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buy.png"))); // NOI18N
        penjualankelompok1.setText("PENJUALAN");
        penjualankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penjualankelompok1ActionPerformed(evt);
            }
        });

        laporankelompok1.setBackground(new java.awt.Color(51, 255, 255));
        laporankelompok1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        laporankelompok1.setForeground(new java.awt.Color(0, 0, 0));
        laporankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/laporan.png"))); // NOI18N
        laporankelompok1.setText("LAPORAN");
        laporankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporankelompok1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoutkelompok1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barangkelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pelanggankelompok1))
                    .addComponent(homekelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pembeliankelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(penjualankelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(laporankelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homekelompok1)
                .addGap(18, 18, 18)
                .addComponent(pelanggankelompok1)
                .addGap(18, 18, 18)
                .addComponent(barangkelompok1)
                .addGap(18, 18, 18)
                .addComponent(pembeliankelompok1)
                .addGap(18, 18, 18)
                .addComponent(penjualankelompok1)
                .addGap(18, 18, 18)
                .addComponent(laporankelompok1)
                .addGap(18, 18, 18)
                .addComponent(logoutkelompok1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutkelompok1ActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_logoutkelompok1ActionPerformed

    private void pembeliankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pembeliankelompok1ActionPerformed
        // TODO add your handling code here:
        new Pembelian().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_pembeliankelompok1ActionPerformed

    private void barangkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barangkelompok1ActionPerformed
        // TODO add your handling code here:
        new Barang().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_barangkelompok1ActionPerformed

    private void penjualankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penjualankelompok1ActionPerformed
        // TODO add your handling code here:
        new Penjualan().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_penjualankelompok1ActionPerformed

    private void laporankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporankelompok1ActionPerformed
        // TODO add your handling code here:
        new Laporan().setVisible(true);
        this.setVisible(false);
       
    }//GEN-LAST:event_laporankelompok1ActionPerformed

    private void pelanggankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pelanggankelompok1ActionPerformed
        // TODO add your handling code here:
        new Pelanggan().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_pelanggankelompok1ActionPerformed

    private void homekelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homekelompok1ActionPerformed
        // TODO add your handling code here:
        Home a = new Home();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_homekelompok1ActionPerformed

    private void developerkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_developerkelompok1ActionPerformed
        // TODO add your handling code here:
        new Developer().setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_developerkelompok1ActionPerformed

    private void bantuankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bantuankelompok1ActionPerformed
        // TODO add your handling code here:
        new Help().setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_bantuankelompok1ActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bantuankelompok1;
    private javax.swing.JButton barangkelompok1;
    private javax.swing.JButton developerkelompok1;
    private javax.swing.JButton homekelompok1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton laporankelompok1;
    private javax.swing.JButton logoutkelompok1;
    private javax.swing.JButton pelanggankelompok1;
    private javax.swing.JButton pembeliankelompok1;
    private javax.swing.JButton penjualankelompok1;
    private javax.swing.JLabel tanggalkelompok1;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JLabel waktukelompok1;
    // End of variables declaration//GEN-END:variables
}
