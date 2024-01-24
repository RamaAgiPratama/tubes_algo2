/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import ConnectionDatabase.DatabaseConnection;
import Home.Home;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class Laporan extends javax.swing.JFrame {
Connection koneksi;
PreparedStatement pst;
DefaultTableModel model;
ResultSet rs;
String tglini, tglsampai;
    /**
     * Creates new form Pembelian
     */
    public Laporan() {
        initComponents();
        koneksi = DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_aplikasi_penjualan_uas");
        tampilkan1();
        tampilkan2();
        tampilkan3();
        tampilkan4();
        tampilkan5();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTanggal();
        jam();
    }
    
    public void setTanggal(){
    java.util.Date skrg = new java.util.Date();
    java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("dd/MM/yyyy");
    tanggalkelompok1.setText(kal.format(skrg));
    }
    
    public void tampilkan1(){
    String[] kolom = {"ID PELANGGAN","NAMA","NO HP","ALAMAT"};
    model = new DefaultTableModel(null, kolom);
    try {
    Statement stmt = koneksi.createStatement();
        String sql = "SELECT * FROM t_pelanggan ";
        ResultSet rs = stmt.executeQuery(sql);
        int no = 1;
    while(rs.next()){
        String id = rs.getString("id_pelanggan");
        String nama = rs.getString("nama");
        String hp = rs.getString("no_hp");
        String alamat = rs.getString("alamat");
    model.addRow(new String[]{id,nama,hp,alamat});
                no++;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        tabelpelanggankelompok1.setModel(model);
    }   
    
    public void tampilkan2(){
    String[] kolom = {"KODE BARANG","NAMA BARANG","UKURAN","HARGA BELI","HARGA JUAL","STOK"};
    model = new DefaultTableModel(null, kolom);
    try {
    Statement stmt = koneksi.createStatement();
        String sql = "SELECT * FROM t_barang ";
        ResultSet rs = stmt.executeQuery(sql);
        int no = 1;
    while(rs.next()){
        String kode = rs.getString("kode_barang");
        String nama = rs.getString("nama_barang");
        String ukuran = rs.getString("ukuran");
        String beli = rs.getString("harga_beli");
        String jual = rs.getString("harga_jual");
        String stok = rs.getString("stok");
    model.addRow(new String[]{kode,nama,ukuran,beli,jual,stok});
                no++;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        tabelbarangkelompok1.setModel(model);
    }   
    
    public void tampilkan3(){
    String[] kolom = {"NO PEMBELIAN","TANGGAL BELI","KODE BARANG","NAMA BARANG","UKURAN","HARGA BELI","JUMLAH","TOTAL HARGA"};
    model = new DefaultTableModel(null, kolom);
    try {
    Statement stmt = koneksi.createStatement();
        String sql = "SELECT * FROM t_pembelian ";
        ResultSet rs = stmt.executeQuery(sql);
        int no = 1;
    while(rs.next()){
        String pembelian = rs.getString("no_pembelian");
        String tanggal = rs.getString("tanggal_beli");
        String kode = rs.getString("kode_barang");
        String nama = rs.getString("nama_barang");
        String ukuran = rs.getString("ukuran");
        String beli = rs.getString("harga_beli");
        String jumlah = rs.getString("jumlah");
        String total = rs.getString("total_harga");
    model.addRow(new String[]{pembelian,tanggal,kode,nama,ukuran,beli,jumlah,total});
                no++;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        tabelpembeliankelompok1.setModel(model);
    }   
    
    public void tampilkan4(){
    String[] kolom = {"NO PENJUALAN","TANGGAL JUAL","PELANGGAN","KODE BARANG","NAMA BARANG","UKURAN","HARGA JUAL","JUMLAH","TOTAL HARGA","BAYAR","KEMBALIAN"};
    model = new DefaultTableModel(null, kolom);
    try {
    Statement stmt = koneksi.createStatement();
        String sql = "SELECT * FROM t_penjualan ";
        ResultSet rs = stmt.executeQuery(sql);
        int no = 1;
    while(rs.next()){
        String penjualan = rs.getString("no_penjualan");
        String tanggal = rs.getString("tanggal_jual");
        String pelanggan = rs.getString("pelanggan");
        String kode = rs.getString("kode_barang");
        String nama = rs.getString("nama_barang");
        String ukuran = rs.getString("ukuran");
        String jual = rs.getString("harga_jual");
        String jumlah = rs.getString("jumlah");
        String total = rs.getString("total_harga");
        String bayar = rs.getString("bayar");
        String kembalian = rs.getString("kembalian");
    model.addRow(new String[]{penjualan,tanggal,pelanggan,kode,nama,ukuran,jual,jumlah,total,bayar,kembalian});
                no++;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        tabelpenjualankelompok1.setModel(model);
    }   
    
    public void tampilkan5(){
    String[] kolom = {"ID","USERNAME","PASSWORD"};
    model = new DefaultTableModel(null, kolom);
    try {
    Statement stmt = koneksi.createStatement();
        String sql = "SELECT * FROM t_login ";
        ResultSet rs = stmt.executeQuery(sql);
        int no = 1;
    while(rs.next()){
        String id = rs.getString("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
    model.addRow(new String[]{id,username,password});
                no++;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        tabelloginkelompok1.setModel(model);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpelanggankelompok1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        clearkelompok1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelbarangkelompok1 = new javax.swing.JTable();
        clearkelompok2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelpembeliankelompok1 = new javax.swing.JTable();
        clearkelompok3 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabelpenjualankelompok1 = new javax.swing.JTable();
        clearkelompok4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Tanggalini = new javax.swing.JTextField();
        TanggalSampai = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TotalBarang = new javax.swing.JTextField();
        TotalTransaksi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TotalPendapatan = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tabelloginkelompok1 = new javax.swing.JTable();
        clearkelompok5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        date1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        date2 = new com.toedter.calendar.JDateChooser();
        carikelompok1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tanggalkelompok1 = new javax.swing.JLabel();
        waktukelompok1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        kembali21552011058 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(143, 176, 219));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelpelanggankelompok1.setBackground(new java.awt.Color(204, 255, 255));
        tabelpelanggankelompok1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelpelanggankelompok1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 820, 220));

        jLabel7.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("LAPORAN PELANGGAN");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        clearkelompok1.setBackground(new java.awt.Color(51, 51, 51));
        clearkelompok1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        clearkelompok1.setForeground(new java.awt.Color(255, 255, 255));
        clearkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cetak.png"))); // NOI18N
        clearkelompok1.setText("CETAK");
        clearkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearkelompok1ActionPerformed(evt);
            }
        });
        jPanel3.add(clearkelompok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, -1, -1));

        jTabbedPane1.addTab("PELANGGAN", jPanel3);

        jPanel4.setBackground(new java.awt.Color(0, 0, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("LAPORAN BARANG");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, -1));

        tabelbarangkelompok1.setBackground(new java.awt.Color(204, 255, 255));
        tabelbarangkelompok1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelbarangkelompok1);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 820, 220));

        clearkelompok2.setBackground(new java.awt.Color(51, 51, 51));
        clearkelompok2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        clearkelompok2.setForeground(new java.awt.Color(255, 255, 255));
        clearkelompok2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cetak.png"))); // NOI18N
        clearkelompok2.setText("CETAK");
        clearkelompok2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearkelompok2ActionPerformed(evt);
            }
        });
        jPanel4.add(clearkelompok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, -1, -1));

        jTabbedPane1.addTab("BARANG", jPanel4);

        jPanel8.setBackground(new java.awt.Color(0, 0, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel8.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel9.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("LAPORAN PEMBELIAN");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, -1));

        tabelpembeliankelompok1.setBackground(new java.awt.Color(204, 255, 255));
        tabelpembeliankelompok1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabelpembeliankelompok1);

        jPanel8.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 820, 220));

        clearkelompok3.setBackground(new java.awt.Color(51, 51, 51));
        clearkelompok3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        clearkelompok3.setForeground(new java.awt.Color(255, 255, 255));
        clearkelompok3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cetak.png"))); // NOI18N
        clearkelompok3.setText("CETAK");
        clearkelompok3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearkelompok3ActionPerformed(evt);
            }
        });
        jPanel8.add(clearkelompok3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 310, -1, -1));

        jTabbedPane1.addTab("PEMBELIAN", jPanel8);

        jPanel10.setBackground(new java.awt.Color(0, 0, 51));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("LAPORAN PENJUALAN");
        jPanel10.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, -1));

        tabelpenjualankelompok1.setBackground(new java.awt.Color(204, 255, 255));
        tabelpenjualankelompok1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(tabelpenjualankelompok1);

        jPanel10.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 820, 220));

        clearkelompok4.setBackground(new java.awt.Color(51, 51, 51));
        clearkelompok4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        clearkelompok4.setForeground(new java.awt.Color(255, 255, 255));
        clearkelompok4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cetak.png"))); // NOI18N
        clearkelompok4.setText("CETAK");
        clearkelompok4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearkelompok4ActionPerformed(evt);
            }
        });
        jPanel10.add(clearkelompok4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, -1, -1));

        jTabbedPane1.addTab("PENJUALAN", jPanel10);

        jPanel6.setBackground(new java.awt.Color(0, 0, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TANGGAL :");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("S/D TANGGAL :");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 11, -1, -1));

        Tanggalini.setEditable(false);
        Tanggalini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggaliniActionPerformed(evt);
            }
        });
        jPanel6.add(Tanggalini, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 109, -1));

        TanggalSampai.setEditable(false);
        jPanel6.add(TanggalSampai, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 11, 109, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PENGELUARAN BARANG");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TOTAL TRANSAKSI");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 98, -1, -1));

        TotalBarang.setEditable(false);
        jPanel6.add(TotalBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 72, 207, -1));

        TotalTransaksi.setEditable(false);
        jPanel6.add(TotalTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 121, 207, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("TOTAL PENDAPATAN");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 147, -1, -1));

        TotalPendapatan.setEditable(false);
        jPanel6.add(TotalPendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 174, 207, -1));

        jToggleButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton1.setText("CLEAR");
        jPanel6.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 213, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("TAMBAH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 213, -1, -1));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("HITUNG");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 170, 144, -1));
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 940, 260));

        jTabbedPane1.addTab("PENDAPATAN", jPanel6);

        jPanel11.setBackground(new java.awt.Color(0, 0, 51));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("LAPORAN LOGIN");
        jPanel11.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        tabelloginkelompok1.setBackground(new java.awt.Color(204, 255, 255));
        tabelloginkelompok1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(tabelloginkelompok1);

        jPanel11.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 820, 220));

        clearkelompok5.setBackground(new java.awt.Color(51, 51, 51));
        clearkelompok5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        clearkelompok5.setForeground(new java.awt.Color(255, 255, 255));
        clearkelompok5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cetak.png"))); // NOI18N
        clearkelompok5.setText("CETAK");
        clearkelompok5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearkelompok5ActionPerformed(evt);
            }
        });
        jPanel11.add(clearkelompok5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, -1, -1));

        jTabbedPane1.addTab("LOGIN", jPanel11);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("PENCARIAN DARI TANGGAL");

        date1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date1PropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("S/D TANGGAL");

        date2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date2PropertyChange(evt);
            }
        });

        carikelompok1.setBackground(new java.awt.Color(51, 51, 51));
        carikelompok1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        carikelompok1.setForeground(new java.awt.Color(255, 255, 255));
        carikelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        carikelompok1.setText("CARI");
        carikelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carikelompok1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(carikelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(carikelompok1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel6.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/laporan.png"))); // NOI18N
        jLabel6.setText("Laporan Toko Ibu Otih");

        tanggalkelompok1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        tanggalkelompok1.setForeground(new java.awt.Color(255, 255, 255));

        waktukelompok1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        waktukelompok1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tanggalkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waktukelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(waktukelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tanggalkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Home");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        kembali21552011058.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout.png"))); // NOI18N
        kembali21552011058.setText("Kembali");
        kembali21552011058.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali21552011058ActionPerformed(evt);
            }
        });
        jMenu1.add(kembali21552011058);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kembali21552011058ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali21552011058ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_kembali21552011058ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void TanggaliniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggaliniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggaliniActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String a = TotalBarang.getText();
        int aa = Integer.parseInt(a);
        int barang, transaksi, pendapatan;
        barang = Integer.parseInt(TotalBarang.getText().toString());
        transaksi = Integer.parseInt(TotalTransaksi.getText().toString());
        pendapatan = transaksi - barang;
        TotalPendapatan.setText(Integer.toString(pendapatan));

    }//GEN-LAST:event_jButton5ActionPerformed

    private void clearkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearkelompok1ActionPerformed
        // TODO add your handling code here:
    

    }//GEN-LAST:event_clearkelompok1ActionPerformed

    private void clearkelompok2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearkelompok2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearkelompok2ActionPerformed

    private void clearkelompok3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearkelompok3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearkelompok3ActionPerformed

    private void clearkelompok4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearkelompok4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearkelompok4ActionPerformed

    private void clearkelompok5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearkelompok5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearkelompok5ActionPerformed

    private void carikelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carikelompok1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_carikelompok1ActionPerformed

    private void date1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date1PropertyChange
        // TODO add your handling code here:
        if (date1.getDate() != null) {
            SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
            
            tglini = Format.format(date1.getDate());
        }
    }//GEN-LAST:event_date1PropertyChange

    private void date2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date2PropertyChange
        // TODO add your handling code here:
        if (date2.getDate() != null) {
            SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
            
            tglsampai = Format.format(date2.getDate());
        }

    }//GEN-LAST:event_date2PropertyChange

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
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TanggalSampai;
    private javax.swing.JTextField Tanggalini;
    private javax.swing.JTextField TotalBarang;
    private javax.swing.JTextField TotalPendapatan;
    private javax.swing.JTextField TotalTransaksi;
    private javax.swing.JButton carikelompok1;
    private javax.swing.JButton clearkelompok1;
    private javax.swing.JButton clearkelompok2;
    private javax.swing.JButton clearkelompok3;
    private javax.swing.JButton clearkelompok4;
    private javax.swing.JButton clearkelompok5;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JMenuItem kembali21552011058;
    private javax.swing.JTable tabelbarangkelompok1;
    private javax.swing.JTable tabelloginkelompok1;
    private javax.swing.JTable tabelpelanggankelompok1;
    private javax.swing.JTable tabelpembeliankelompok1;
    private javax.swing.JTable tabelpenjualankelompok1;
    private javax.swing.JLabel tanggalkelompok1;
    private javax.swing.JLabel waktukelompok1;
    // End of variables declaration//GEN-END:variables
}
