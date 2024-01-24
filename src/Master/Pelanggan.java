/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import Home.Koneksi1;
import Home.Home;
import Home.MenuUtama;
import Transaksi.Pembelian;
import Transaksi.Penjualan;
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
public class Pelanggan extends javax.swing.JFrame {
    public String dateChooser;
    
    
    public void kosongkan_form(){
        idpelanggankelompok1.setText(null);
        namakelompok1.setText(null);
        nohpkelompok1.setText(null);
        alamatkelompok1.setText(null); 
    }
    
    public void tampilkan_data(){
        DefaultTableModel model = new DefaultTableModel ();
        
        model.addColumn("NO.");
        model.addColumn("ID PELANGGAN");
        model.addColumn("NAMA");
        model.addColumn("NO. HP");
        model.addColumn("ALAMAT");
        
        String cari = carikelompok1.getText();
        try {
            int no = 1;
            String sql = "Select * From t_pelanggan  WHERE id_pelanggan LIKE'%"+cari+"%' OR `nama` LIKE '%"+cari+"%'";
            java.sql.Connection conn =(Connection)Konfig.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while (res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
                
                datapelanggankelompok1.setModel(model);
                
            }
        }catch(SQLException e){
            System.out.println("ERROR :" +e.getMessage());
        }
        
    }

    /**
     * Creates new form Pelanggan
     */
    public Pelanggan() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTanggal();
        jam();
        tampilkan_data();
        kosongkan_form();
        autonumber();
    }
   
    
    public void setTanggal(){
    java.util.Date skrg = new java.util.Date();
    java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("dd/MM/yyyy");
    tglkelompok1.setText(kal.format(skrg));
    }
    
    private void autonumber(){
        try{
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM t_pelanggan ORDER BY id_pelanggan DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()){
                String NoID = r.getString("id_pelanggan").substring(2);
                String ID = "" +(Integer.parseInt(NoID)+1);
                String Zero = "";
                
                if (ID.length()==1)
                {Zero = "00";}
                else if (ID.length()==2)
                {Zero = "0";}
                else if (ID.length()==3)
                {Zero = "";}
                
                idpelanggankelompok1.setText("ID" + Zero + ID);
            }else{
                idpelanggankelompok1.setText("ID001");
            }
            r.close();
            s.close();
        }catch(Exception e){
            System.out.println("");
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
                wktkelompok1.setText("  " + Jam + " : " + menit + " : " + detik + "  ");
                
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
        jLabel1 = new javax.swing.JLabel();
        namakelompok1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        idpelanggankelompok1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nohpkelompok1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamatkelompok1 = new javax.swing.JTextArea();
        tambahkelompok1 = new javax.swing.JButton();
        editkelompok1 = new javax.swing.JButton();
        hapuskelompok1 = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        carikelompok1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        datapelanggankelompok1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tglkelompok1 = new javax.swing.JLabel();
        wktkelompok1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
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

        jPanel1.setBackground(new java.awt.Color(143, 176, 219));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ID Pelanggan");

        namakelompok1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nama ");

        idpelanggankelompok1.setEditable(false);
        idpelanggankelompok1.setBackground(new java.awt.Color(18, 30, 49));
        idpelanggankelompok1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        idpelanggankelompok1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No. HP");

        nohpkelompok1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Alamat");

        alamatkelompok1.setBackground(new java.awt.Color(204, 255, 255));
        alamatkelompok1.setColumns(20);
        alamatkelompok1.setRows(5);
        jScrollPane1.setViewportView(alamatkelompok1);

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

        carikelompok1.setBackground(new java.awt.Color(204, 255, 255));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tambahkelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(editkelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(hapuskelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(carikelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addComponent(jLabel2))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(namakelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idpelanggankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nohpkelompok1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addComponent(jLabel6)
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(idpelanggankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namakelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nohpkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tambahkelompok1)
                        .addComponent(editkelompok1)
                        .addComponent(hapuskelompok1))
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(carikelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jPanel3.setBackground(new java.awt.Color(143, 176, 219));

        datapelanggankelompok1.setBackground(new java.awt.Color(204, 255, 255));
        datapelanggankelompok1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID PELANGGAN", "NAMA", "No. HP", "ALAMAT"
            }
        ));
        datapelanggankelompok1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datapelanggankelompok1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(datapelanggankelompok1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1337, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pll.png"))); // NOI18N
        jLabel5.setText("Form Pelanggan");

        tglkelompok1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        tglkelompok1.setForeground(new java.awt.Color(255, 255, 255));

        wktkelompok1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        wktkelompok1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tglkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wktkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tglkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wktkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 51));

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DATA PELANGGAN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(484, 484, 484))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tambahkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahkelompok1ActionPerformed
        // TODO add your handling code here:
        try{
            String sql = "INSERT INTO t_pelanggan  VALUES ('"+idpelanggankelompok1.getText()+"','"+namakelompok1.getText()+"','"+nohpkelompok1.getText()+"','"+alamatkelompok1.getText()+"')";
            java.sql.Connection conn = (Connection) Konfig.configDB();
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
        try{
            String sql = "INSERT t_pelanggan set id_pelanggan ='"+idpelanggankelompok1.getText()+"' ,nama ='"+namakelompok1.getText()+"' ,no_hp='"+nohpkelompok1.getText()+"' ,alamat='"+alamatkelompok1.getText()+"' on Duplicate Key Update id_pelanggan ='"+idpelanggankelompok1.getText()+"' ,nama ='"+namakelompok1.getText()+"' ,no_hp='"+nohpkelompok1.getText()+"' ,alamat='"+alamatkelompok1.getText()+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
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
            String sql = "DELETE FROM t_pelanggan WHERE id_pelanggan ='"+idpelanggankelompok1.getText()+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
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

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void carikelompok1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carikelompok1KeyReleased
        // TODO add your handling code here:
        tampilkan_data();
    }//GEN-LAST:event_carikelompok1KeyReleased

    private void datapelanggankelompok1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datapelanggankelompok1MouseClicked
        // TODO add your handling code here:
        int baris = datapelanggankelompok1.rowAtPoint(evt.getPoint());
        
        String id = datapelanggankelompok1.getValueAt(baris, 1).toString();
        idpelanggankelompok1.setText(id);
        
        String nama = datapelanggankelompok1.getValueAt(baris, 2).toString();
        namakelompok1.setText(nama);
        
        String no_hp = datapelanggankelompok1.getValueAt(baris, 3).toString();
        nohpkelompok1.setText(no_hp);
        
        String alamat = datapelanggankelompok1.getValueAt(baris, 4).toString();
        alamatkelompok1.setText(alamat);
        
    }//GEN-LAST:event_datapelanggankelompok1MouseClicked

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

    private void carikelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carikelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carikelompok1ActionPerformed

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
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem LaporanBarangkelompok1;
    private javax.swing.JMenuItem LaporanLoginkelompok1;
    private javax.swing.JMenuItem LaporanPelanggankelompok1;
    private javax.swing.JMenuItem LaporanPembeliankelompok1;
    private javax.swing.JMenuItem LaporanPenjualankelompok1;
    private javax.swing.JTextArea alamatkelompok1;
    private javax.swing.JMenuItem barangkelompok1;
    private javax.swing.JButton cari;
    private javax.swing.JTextField carikelompok1;
    private javax.swing.JTable datapelanggankelompok1;
    private javax.swing.JButton editkelompok1;
    private javax.swing.JButton hapuskelompok1;
    private javax.swing.JMenu homekelompok1;
    private javax.swing.JTextField idpelanggankelompok1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem kembalikelompok1;
    private javax.swing.JMenuItem logoutkelompok1;
    private javax.swing.JMenu masterkelompok1;
    private javax.swing.JTextField namakelompok1;
    private javax.swing.JTextField nohpkelompok1;
    private javax.swing.JMenuItem pelanggankelompok1;
    private javax.swing.JMenuItem pembeliankelompok1;
    private javax.swing.JMenuItem penjualankelompok1;
    private javax.swing.JButton tambahkelompok1;
    private javax.swing.JLabel tglkelompok1;
    private javax.swing.JMenu transaksikelompok1;
    private javax.swing.JLabel wktkelompok1;
    // End of variables declaration//GEN-END:variables
}
