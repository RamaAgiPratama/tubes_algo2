/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Home.Koneksi1;
import Master.Barang;
import Master.Pelanggan;
import Transaksi.Pembelian;
import Transaksi.Penjualan;
import Home.Home;
import Home.MenuUtama;
import static Transaksi.Pembelian.getTanggalFromTable;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ACER
 */
public class Penjualan extends javax.swing.JFrame {
Connection conn;
    Statement st;
    ResultSet rs;
    /**
     * Creates new form Penjualan
     */
    public Penjualan() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTanggal();
        jam();
        tampilkan_data();
        kosongkan_form();
        tanggal();
        autonumber();
    }
    
    public String Kodeba, Namaba, Ukuran, Hargaju, stock;

    public String Kodebar() {
        return Kodeba;
    }

    ;
    public String Namabar() {
        return Namaba;
    }

    ;
    public String Ukuranbar() {
        return Ukuran;
    }

    ;
    public String Hargaju() {
        return Hargaju;
    }

    ;
    public String stock() {
        return stock;
    }
    public String Kodesu,Namasu;

    public String Kodesup() {
        return Kodesu;
    }

    ;
    
    public String Namasup() {
        return Namasu;
    }
    
    private void autonumber(){
        try{
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM t_penjualan ORDER BY no_penjualan DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()){
                String NoID = r.getString("no_penjualan").substring(2);
                String ID = "" +(Integer.parseInt(NoID)+1);
                String Zero = "";
                
                if (ID.length()==1)
                {Zero = "00";}
                else if (ID.length()==2)
                {Zero = "0";}
                else if (ID.length()==3)
                {Zero = "";}
                
                nopenjualankelompok1.setText("NJ" + Zero + ID);
            }else{
                nopenjualankelompok1.setText("NJ001");
            }
            r.close();
            s.close();
        }catch(Exception e){
            System.out.println("");
        }
    }
    
    public void setTanggal(){
    java.util.Date skrg = new java.util.Date();
    java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("dd/MM/yyyy");
    tanggal21552011058.setText(kal.format(skrg));
    }
    
   public void tanggal(){
        Date now = new Date();  
        tgljualkelompok1.setDate(now);    
    }
    
    public void kosongkan_form(){
        nopenjualankelompok1.setText(null);
        tgljualkelompok1.setDate(null);
        Pelanggankelompok1.setText(null);
        idbarangkelompok1.setText(null);
        namabarangkelompok1.setText(null);
        ukurankelompok1.setText(null);
        hargajualkelompok1.setText(null);
        jumlahkelompok1.setText(null);
        totalhargakelompok1.setText(null); 
        bayarkelompok1.setText(null); 
        kembaliankelompok1.setText(null); 
     
    }
     
    public void tampilkan_data(){
        DefaultTableModel model = new DefaultTableModel ();
        
        model.addColumn("NO.");
        model.addColumn("NPL");
        model.addColumn("TANGGAL");
        model.addColumn("PELANGGAN");
        model.addColumn("KODE");
        model.addColumn("NAMA BARANG");
        model.addColumn("UKURAN");
        model.addColumn("HARGA JUAL");
        model.addColumn("JUMLAH");
        model.addColumn("TOTAL");
        model.addColumn("BAYAR");
        model.addColumn("KEMBALIAN");
        
        String cari = carikelompok1.getText();
        try {
            int no = 1;
            String sql = "Select * From t_penjualan  WHERE no_penjualan LIKE'%"+cari+"%' OR `pelanggan` LIKE '%"+cari+"%'";
            java.sql.Connection conn =(Connection)Koneksi.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while (res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10),res.getString(11)});
                
                datapenjualankelompok1.setModel(model);
                
            }
        }catch(SQLException e){
            System.out.println("ERROR :" +e.getMessage());
        }
        
    }
    
    private void total(){
        String harga = hargajualkelompok1.getText();
        String jumlah = jumlahkelompok1.getText();
        
        int hargaa = Integer.parseInt(harga);
        try{
        int jumlahh = Integer.parseInt(jumlah);
        
        int total = hargaa * jumlahh;
        String total_harga = Integer.toString(total);
        
        totalhargakelompok1.setText(total_harga);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Only Number");
            jumlahkelompok1.setText(null);
        }
    }
    
    private void kembalian(){
        String total = totalhargakelompok1.getText();
        String bayar = bayarkelompok1.getText();
        
        int totals = Integer.parseInt(total);
        try{
        int bayars = Integer.parseInt(bayar);   
        
        int kembali = bayars - totals;
        String uang_total = Integer.toString(kembali);
        
        kembaliankelompok1.setText(uang_total);
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Transaksi Berhasil!");
           bayarkelompok1.setText(null);
        }
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
                waktu21552011058.setText("  " + Jam + " : " + menit + " : " + detik + "  ");
                
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

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tanggal21552011058 = new javax.swing.JLabel();
        waktu21552011058 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nopenjualankelompok1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Pelanggankelompok1 = new javax.swing.JTextField();
        tgljualkelompok1 = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        idbarangkelompok1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        hargajualkelompok1 = new javax.swing.JTextField();
        namabarangkelompok1 = new javax.swing.JTextField();
        caribrgkelompok1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        ukurankelompok1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jumlahkelompok1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        totalhargakelompok1 = new javax.swing.JTextField();
        carisup = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        datapenjualankelompok1 = new javax.swing.JTable();
        cari = new javax.swing.JButton();
        editkelompok1 = new javax.swing.JButton();
        tambahkelompok1 = new javax.swing.JButton();
        hapuskelompok1 = new javax.swing.JButton();
        clearkelompok1 = new javax.swing.JButton();
        carikelompok1 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        bayarkelompok1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        kembaliankelompok1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        homekelompok1 = new javax.swing.JMenu();
        kembalikelompok1 = new javax.swing.JMenuItem();
        logoutkelompok1 = new javax.swing.JMenuItem();
        masterkelompok1 = new javax.swing.JMenu();
        pelanggankelompok1 = new javax.swing.JMenuItem();
        barangkelompok1 = new javax.swing.JMenuItem();
        transaksikelompok1 = new javax.swing.JMenu();
        pembeliankelompok1 = new javax.swing.JMenuItem();
        penjualankelompok1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        LaporanBarangkelompok1 = new javax.swing.JMenuItem();
        LaporanPelanggankelompok1 = new javax.swing.JMenuItem();
        LaporanPembeliankelompok1 = new javax.swing.JMenuItem();
        LaporanPenjualankelompok1 = new javax.swing.JMenuItem();
        LaporanLoginkelompok1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bu.png"))); // NOI18N
        jLabel5.setText("Form Penjualan");

        tanggal21552011058.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        tanggal21552011058.setForeground(new java.awt.Color(255, 255, 255));

        waktu21552011058.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        waktu21552011058.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tanggal21552011058, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waktu21552011058, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tanggal21552011058, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waktu21552011058, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(143, 176, 219));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("No Penjualan");

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tanggal Jual");

        nopenjualankelompok1.setEditable(false);
        nopenjualankelompok1.setBackground(new java.awt.Color(18, 30, 49));
        nopenjualankelompok1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nopenjualankelompok1.setForeground(new java.awt.Color(255, 255, 255));
        nopenjualankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nopenjualankelompok1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Pelanggan");

        Pelanggankelompok1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel7.setBackground(new java.awt.Color(0, 0, 51));

        jLabel14.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Input Data Penjualan");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(108, 108, 108))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Kode Barang");

        idbarangkelompok1.setEditable(false);
        idbarangkelompok1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Nama Barang");

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Harga Jual");

        hargajualkelompok1.setEditable(false);
        hargajualkelompok1.setBackground(new java.awt.Color(204, 255, 255));

        namabarangkelompok1.setEditable(false);
        namabarangkelompok1.setBackground(new java.awt.Color(204, 255, 255));

        caribrgkelompok1.setBackground(new java.awt.Color(51, 51, 51));
        caribrgkelompok1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        caribrgkelompok1.setForeground(new java.awt.Color(255, 255, 255));
        caribrgkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cari2.png"))); // NOI18N
        caribrgkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caribrgkelompok1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Ukuran");

        ukurankelompok1.setEditable(false);
        ukurankelompok1.setBackground(new java.awt.Color(204, 255, 255));
        ukurankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ukurankelompok1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jumlah");

        jumlahkelompok1.setBackground(new java.awt.Color(204, 255, 255));
        jumlahkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahkelompok1ActionPerformed(evt);
            }
        });
        jumlahkelompok1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahkelompok1KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Total Harga");

        totalhargakelompok1.setEditable(false);
        totalhargakelompok1.setBackground(new java.awt.Color(204, 255, 255));
        totalhargakelompok1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        carisup.setBackground(new java.awt.Color(51, 51, 51));
        carisup.setForeground(new java.awt.Color(255, 255, 255));
        carisup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cari2.png"))); // NOI18N
        carisup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carisupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Pelanggankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(carisup, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(nopenjualankelompok1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tgljualkelompok1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(hargajualkelompok1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ukurankelompok1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(namabarangkelompok1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(idbarangkelompok1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(caribrgkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jumlahkelompok1)
                                        .addGap(34, 34, 34))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalhargakelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nopenjualankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(tgljualkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Pelanggankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(carisup))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idbarangkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(caribrgkelompok1))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namabarangkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(ukurankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hargajualkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jumlahkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(totalhargakelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );

        jPanel3.setBackground(new java.awt.Color(143, 176, 219));

        datapenjualankelompok1.setBackground(new java.awt.Color(204, 255, 255));
        datapenjualankelompok1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NO PEMBELIAN", "TANGGAL BELI", "PELANGGAN", "KODE BARANG", "NAMA BARANG", "UKURAN", "HARGA BELI", "JUMLAH", "TOTAL HARGA"
            }
        ));
        datapenjualankelompok1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datapenjualankelompok1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(datapenjualankelompok1);

        cari.setBackground(new java.awt.Color(51, 51, 51));
        cari.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        cari.setForeground(new java.awt.Color(255, 255, 255));
        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        cari.setText("CARI");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        editkelompok1.setBackground(new java.awt.Color(51, 51, 51));
        editkelompok1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        editkelompok1.setForeground(new java.awt.Color(255, 255, 255));
        editkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ubah.png"))); // NOI18N
        editkelompok1.setText("EDIT");
        editkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editkelompok1ActionPerformed(evt);
            }
        });

        tambahkelompok1.setBackground(new java.awt.Color(51, 51, 51));
        tambahkelompok1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        tambahkelompok1.setForeground(new java.awt.Color(255, 255, 255));
        tambahkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simpan.png"))); // NOI18N
        tambahkelompok1.setText("TAMBAH");
        tambahkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahkelompok1ActionPerformed(evt);
            }
        });

        hapuskelompok1.setBackground(new java.awt.Color(51, 51, 51));
        hapuskelompok1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        hapuskelompok1.setForeground(new java.awt.Color(255, 255, 255));
        hapuskelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hapus.png"))); // NOI18N
        hapuskelompok1.setText("HAPUS");
        hapuskelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapuskelompok1ActionPerformed(evt);
            }
        });

        clearkelompok1.setBackground(new java.awt.Color(51, 51, 51));
        clearkelompok1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        clearkelompok1.setForeground(new java.awt.Color(255, 255, 255));
        clearkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.png"))); // NOI18N
        clearkelompok1.setText("CLEAR");
        clearkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearkelompok1ActionPerformed(evt);
            }
        });

        carikelompok1.setBackground(new java.awt.Color(255, 255, 255));
        carikelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carikelompok1ActionPerformed(evt);
            }
        });
        carikelompok1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carikelompok1KeyReleased(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 0, 51));

        jLabel17.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(" Data Penjualan");
        jLabel17.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(carikelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cari)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(201, Short.MAX_VALUE)
                .addComponent(tambahkelompok1)
                .addGap(18, 18, 18)
                .addComponent(editkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hapuskelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clearkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carikelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editkelompok1)
                    .addComponent(tambahkelompok1)
                    .addComponent(hapuskelompok1))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(0, 0, 51));

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Bayar        :");

        bayarkelompok1.setBackground(new java.awt.Color(255, 255, 255));
        bayarkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarkelompok1ActionPerformed(evt);
            }
        });
        bayarkelompok1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayarkelompok1KeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kembalian :");

        kembaliankelompok1.setEditable(false);
        kembaliankelompok1.setBackground(new java.awt.Color(255, 255, 255));
        kembaliankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliankelompok1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bayarkelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                    .addComponent(kembaliankelompok1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bayarkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembaliankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        jLabel16.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("TRANSAKSI PENJUALAN BARANG");

        homekelompok1.setText("Home");
        homekelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homekelompok1ActionPerformed(evt);
            }
        });

        kembalikelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        kembalikelompok1.setText("Kembali");
        kembalikelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalikelompok1ActionPerformed(evt);
            }
        });
        homekelompok1.add(kembalikelompok1);

        logoutkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        logoutkelompok1.setText("Logout");
        logoutkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutkelompok1ActionPerformed(evt);
            }
        });
        homekelompok1.add(logoutkelompok1);

        jMenuBar1.add(homekelompok1);

        masterkelompok1.setText("Master");

        pelanggankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_admin.png"))); // NOI18N
        pelanggankelompok1.setText("Pelanggan");
        pelanggankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pelanggankelompok1ActionPerformed(evt);
            }
        });
        masterkelompok1.add(pelanggankelompok1);

        barangkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_penjualan.png"))); // NOI18N
        barangkelompok1.setText("Barang");
        barangkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barangkelompok1ActionPerformed(evt);
            }
        });
        masterkelompok1.add(barangkelompok1);

        jMenuBar1.add(masterkelompok1);

        transaksikelompok1.setText("Transaksi");

        pembeliankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_barang.png"))); // NOI18N
        pembeliankelompok1.setText("Pembelian");
        pembeliankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pembeliankelompok1ActionPerformed(evt);
            }
        });
        transaksikelompok1.add(pembeliankelompok1);

        penjualankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_barang.png"))); // NOI18N
        penjualankelompok1.setText("Penjualan");
        penjualankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penjualankelompok1ActionPerformed(evt);
            }
        });
        transaksikelompok1.add(penjualankelompok1);

        jMenuBar1.add(transaksikelompok1);

        jMenu1.setText("Laporan");

        LaporanBarangkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_penjualan.png"))); // NOI18N
        LaporanBarangkelompok1.setText("Laporan Barang");
        LaporanBarangkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanBarangkelompok1ActionPerformed(evt);
            }
        });
        jMenu1.add(LaporanBarangkelompok1);

        LaporanPelanggankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_admin.png"))); // NOI18N
        LaporanPelanggankelompok1.setText("Laporan Pelanggan");
        LaporanPelanggankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanPelanggankelompok1ActionPerformed(evt);
            }
        });
        jMenu1.add(LaporanPelanggankelompok1);

        LaporanPembeliankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_barang.png"))); // NOI18N
        LaporanPembeliankelompok1.setText("Laporan Pembelian");
        LaporanPembeliankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanPembeliankelompok1ActionPerformed(evt);
            }
        });
        jMenu1.add(LaporanPembeliankelompok1);

        LaporanPenjualankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_barang.png"))); // NOI18N
        LaporanPenjualankelompok1.setText("Laporan Penjualan");
        LaporanPenjualankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanPenjualankelompok1ActionPerformed(evt);
            }
        });
        jMenu1.add(LaporanPenjualankelompok1);

        LaporanLoginkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.png"))); // NOI18N
        LaporanLoginkelompok1.setText("Laporan Login");
        LaporanLoginkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanLoginkelompok1ActionPerformed(evt);
            }
        });
        jMenu1.add(LaporanLoginkelompok1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kembalikelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalikelompok1ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_kembalikelompok1ActionPerformed

    private void logoutkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutkelompok1ActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_logoutkelompok1ActionPerformed

    private void homekelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homekelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homekelompok1ActionPerformed

    private void pelanggankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pelanggankelompok1ActionPerformed
        // TODO add your handling code here:
        new Pelanggan().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_pelanggankelompok1ActionPerformed

    private void barangkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barangkelompok1ActionPerformed
        // TODO add your handling code here:
        new Barang().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_barangkelompok1ActionPerformed

    private void pembeliankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pembeliankelompok1ActionPerformed
        // TODO add your handling code here:
        new Pembelian().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_pembeliankelompok1ActionPerformed

    private void penjualankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penjualankelompok1ActionPerformed
        // TODO add your handling code here:
        new Penjualan().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_penjualankelompok1ActionPerformed

    private void jumlahkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahkelompok1ActionPerformed
        // TODO add your handling code here:
        double beli = Double.parseDouble(hargajualkelompok1.getText());
        double bayar = Double.parseDouble(jumlahkelompok1.getText());
        double kembali = bayar * beli  ;
        totalhargakelompok1.setText(""+kembali);
    }//GEN-LAST:event_jumlahkelompok1ActionPerformed

    private void caribrgkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caribrgkelompok1ActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        brg fDB = new brg(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
        idbarangkelompok1.setText(Kodeba);
        namabarangkelompok1.setText(Namaba);
        ukurankelompok1.setText(Ukuran);
        hargajualkelompok1.setText(Hargaju);
       
    }//GEN-LAST:event_caribrgkelompok1ActionPerformed

    private void datapenjualankelompok1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datapenjualankelompok1MouseClicked
        // TODO add your handling code here:
        int baris = datapenjualankelompok1.rowAtPoint(evt.getPoint());

        String no = datapenjualankelompok1.getValueAt(baris, 1).toString();
        nopenjualankelompok1.setText(no);
        
        tgljualkelompok1.setDate(getTanggalFromTable(datapenjualankelompok1, 2));
        
        String pelanggan = datapenjualankelompok1.getValueAt(baris, 3).toString();
        Pelanggankelompok1.setText(pelanggan);        


        String kode = datapenjualankelompok1.getValueAt(baris, 4).toString();
        idbarangkelompok1.setText(kode);

        String nama = datapenjualankelompok1.getValueAt(baris, 5).toString();
        namabarangkelompok1.setText(nama);
        
        String Ukuran =datapenjualankelompok1.getValueAt(baris, 6).toString();
         ukurankelompok1.setText(Ukuran);      


        String harga_jual = datapenjualankelompok1.getValueAt(baris, 7).toString();
        hargajualkelompok1.setText(harga_jual);

        String jumlah = datapenjualankelompok1.getValueAt(baris, 8).toString();
        jumlahkelompok1.setText(jumlah);

        String total = datapenjualankelompok1.getValueAt(baris, 9).toString();
        totalhargakelompok1.setText(total);
        
        String bayar = datapenjualankelompok1.getValueAt(baris, 10).toString();
        bayarkelompok1.setText(bayar);
        
        String kembalian = datapenjualankelompok1.getValueAt(baris, 11).toString();
        kembaliankelompok1.setText(kembalian);
    }//GEN-LAST:event_datapenjualankelompok1MouseClicked

    private void tambahkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahkelompok1ActionPerformed
        // TODO add your handling code here:
        String tampilan = "yyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(tgljualkelompok1.getDate()));

        try{
            String sql = "INSERT INTO t_penjualan  VALUES ('"+nopenjualankelompok1.getText()+"','"+tanggal+"','"+Pelanggankelompok1.getText()+"','"+idbarangkelompok1.getText()+"','"+namabarangkelompok1.getText()+"','"+ukurankelompok1.getText()+"','"+hargajualkelompok1.getText()+"','"+jumlahkelompok1.getText()+"','"+totalhargakelompok1.getText()+"','"+bayarkelompok1.getText()+"','"+kembaliankelompok1.getText()+"')";
            java.sql.Connection conn = (Connection) Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Tambah Data Berhasil","TOKO IBU OTIH",JOptionPane.INFORMATION_MESSAGE);

        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, "Tambah Data Gagal","TOKO IBU OTIH",JOptionPane.WARNING_MESSAGE);
        }
        tampilkan_data();
        kosongkan_form();
        autonumber();
    }//GEN-LAST:event_tambahkelompok1ActionPerformed

    private void editkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editkelompok1ActionPerformed
        // TODO add your handling code here:
        String tampilan = "yyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(tgljualkelompok1.getDate()));
        try{
            String sql = "INSERT t_penjualan set no_penjualan ='"+nopenjualankelompok1.getText()+"' ,tanggal_jual ='"+tanggal+"',pelanggan ='"+Pelanggankelompok1.getText()+"',kode_barang ='"+idbarangkelompok1.getText()+"',nama_barang ='"+namabarangkelompok1.getText()+"',ukuran ='"+ukurankelompok1.getText()+"' ,harga_jual='"+hargajualkelompok1.getText()+"' ,jumlah='"+jumlahkelompok1.getText()+"' ,total_harga='"+totalhargakelompok1.getText()+"',bayar='"+bayarkelompok1.getText()+"',kembalian='"+kembaliankelompok1.getText()+"' on Duplicate Key Update no_penjualan ='"+nopenjualankelompok1.getText()+"' ,tanggal_jual ='"+tanggal+"',pelanggan ='"+Pelanggankelompok1.getText()+"',kode_barang ='"+idbarangkelompok1.getText()+"',nama_barang ='"+namabarangkelompok1.getText()+"' ,ukuran ='"+ukurankelompok1.getText()+"',harga_jual='"+hargajualkelompok1.getText()+"' ,jumlah='"+jumlahkelompok1.getText()+"' ,total_harga='"+totalhargakelompok1.getText()+"',bayar='"+bayarkelompok1.getText()+"',kembalian='"+kembaliankelompok1.getText()+"'";
            java.sql.Connection conn = (Connection)Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Edit Data Berhasil","TOKO IBU OTIH",JOptionPane.INFORMATION_MESSAGE);

        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilkan_data();
        kosongkan_form();
        autonumber();
    }//GEN-LAST:event_editkelompok1ActionPerformed

    private void hapuskelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapuskelompok1ActionPerformed
        // TODO add your handling code here:
            try{
            String sql = "DELETE FROM t_penjualan  WHERE no_penjualan ='"+nopenjualankelompok1.getText()+"'";
            java.sql.Connection conn = (Connection)Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil","TOKO IBU OTIH",JOptionPane.INFORMATION_MESSAGE);

        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilkan_data();
        kosongkan_form();
        autonumber();
    }//GEN-LAST:event_hapuskelompok1ActionPerformed

    private void clearkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearkelompok1ActionPerformed
        // TODO add your handling code here:
         kosongkan_form();
         autonumber();
    }//GEN-LAST:event_clearkelompok1ActionPerformed

    private void carikelompok1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carikelompok1KeyReleased
        // TODO add your handling code here:
        tampilkan_data();
    }//GEN-LAST:event_carikelompok1KeyReleased

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void bayarkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarkelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarkelompok1ActionPerformed

    private void kembaliankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliankelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembaliankelompok1ActionPerformed

    private void jumlahkelompok1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahkelompok1KeyReleased
        // TODO add your handling code here:
        total();
    }//GEN-LAST:event_jumlahkelompok1KeyReleased

    private void bayarkelompok1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarkelompok1KeyReleased
        // TODO add your handling code here:
        kembalian();
    }//GEN-LAST:event_bayarkelompok1KeyReleased

    private void LaporanBarangkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanBarangkelompok1ActionPerformed
        // TODO add your handling code here:
        try{
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("reportBarang.jasper"), null, Koneksi1.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_LaporanBarangkelompok1ActionPerformed

    private void LaporanPelanggankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanPelanggankelompok1ActionPerformed
        // TODO add your handling code here:
        try{
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("reportPelanggan.jasper"), null, Koneksi1.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_LaporanPelanggankelompok1ActionPerformed

    private void LaporanPembeliankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanPembeliankelompok1ActionPerformed
        // TODO add your handling code here:
        try{
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("reportPembelian.jasper"), null, Koneksi1.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_LaporanPembeliankelompok1ActionPerformed

    private void LaporanPenjualankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanPenjualankelompok1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        try{
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("reportPenjualan.jasper"), null, Koneksi1.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_LaporanPenjualankelompok1ActionPerformed

    private void LaporanLoginkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanLoginkelompok1ActionPerformed
        // TODO add your handling code here:
        try{
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("reportLogin.jasper"), null, Koneksi1.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_LaporanLoginkelompok1ActionPerformed

    private void nopenjualankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nopenjualankelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nopenjualankelompok1ActionPerformed

    private void carikelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carikelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carikelompok1ActionPerformed

    private void ukurankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ukurankelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ukurankelompok1ActionPerformed

    private void carisupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carisupActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        sup fDB = new sup(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);

        Pelanggankelompok1.setText(Namasu);
    }//GEN-LAST:event_carisupActionPerformed

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
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem LaporanBarangkelompok1;
    private javax.swing.JMenuItem LaporanLoginkelompok1;
    private javax.swing.JMenuItem LaporanPelanggankelompok1;
    private javax.swing.JMenuItem LaporanPembeliankelompok1;
    private javax.swing.JMenuItem LaporanPenjualankelompok1;
    private javax.swing.JTextField Pelanggankelompok1;
    private javax.swing.JMenuItem barangkelompok1;
    private javax.swing.JTextField bayarkelompok1;
    private javax.swing.JButton cari;
    private javax.swing.JButton caribrgkelompok1;
    private javax.swing.JTextField carikelompok1;
    private javax.swing.JButton carisup;
    private javax.swing.JButton clearkelompok1;
    private javax.swing.JTable datapenjualankelompok1;
    private javax.swing.JButton editkelompok1;
    private javax.swing.JButton hapuskelompok1;
    private javax.swing.JTextField hargajualkelompok1;
    private javax.swing.JMenu homekelompok1;
    private javax.swing.JTextField idbarangkelompok1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jumlahkelompok1;
    private javax.swing.JTextField kembaliankelompok1;
    private javax.swing.JMenuItem kembalikelompok1;
    private javax.swing.JMenuItem logoutkelompok1;
    private javax.swing.JMenu masterkelompok1;
    private javax.swing.JTextField namabarangkelompok1;
    private javax.swing.JTextField nopenjualankelompok1;
    private javax.swing.JMenuItem pelanggankelompok1;
    private javax.swing.JMenuItem pembeliankelompok1;
    private javax.swing.JMenuItem penjualankelompok1;
    private javax.swing.JButton tambahkelompok1;
    private javax.swing.JLabel tanggal21552011058;
    private com.toedter.calendar.JDateChooser tgljualkelompok1;
    private javax.swing.JTextField totalhargakelompok1;
    private javax.swing.JMenu transaksikelompok1;
    private javax.swing.JTextField ukurankelompok1;
    private javax.swing.JLabel waktu21552011058;
    // End of variables declaration//GEN-END:variables
}
