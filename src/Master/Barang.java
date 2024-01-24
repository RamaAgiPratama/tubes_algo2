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
import java.sql.DriverManager;
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
public class Barang extends javax.swing.JFrame {
    Connection conn;
    Statement st;
    ResultSet rs;
    
    public void kosongkan_form(){
        kodebarangkelompok1.setText(null);
        namabarangkelompok1.setText(null);
        ukurankelompok1.setText(null);
        hargabelikelompok1.setText(null);
        hargajualkelompok1.setText(null);
        stokkelompok1.setText(null);
        
    }
    
    public void tampilkan_data(){
        DefaultTableModel model = new DefaultTableModel ();
        
        model.addColumn("NO.");
        model.addColumn("KODE BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("UKURAN");
        model.addColumn("HARGA BELI");
        model.addColumn("HARGA JUAL");
        model.addColumn("STOK");
        
        String cari = carikelompok1.getText();
        try {
            int no = 1;
            String sql = "Select * From t_barang  WHERE kode_barang LIKE'%"+cari+"%' OR `nama_barang` LIKE '%"+cari+"%' ";
            java.sql.Connection conn =(Connection)Konfig.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while (res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6)});
                
                databarangkelompok1.setModel(model);
                
            }
        }catch(SQLException e){
            System.out.println("ERROR :" +e.getMessage());
        }
        
    }

    
    
    /**
     * Creates new form Barang
     */
    public Barang() {
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
    tanggalkelompok1.setText(kal.format(skrg));
    }
    
    private void autonumber(){
        try{
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM t_barang ORDER BY kode_barang DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()){
                String NoID = r.getString("kode_barang").substring(2);
                String ID = "" +(Integer.parseInt(NoID)+1);
                String Zero = "";
                
                if (ID.length()==1)
                {Zero = "00";}
                else if (ID.length()==2)
                {Zero = "0";}
                else if (ID.length()==3)
                {Zero = "";}
                
                kodebarangkelompok1.setText("KB" + Zero + ID);
            }else{
                kodebarangkelompok1.setText("KB001");
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

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tanggalkelompok1 = new javax.swing.JLabel();
        waktukelompok1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        namabarangkelompok1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        kodebarangkelompok1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        hargabelikelompok1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        stokkelompok1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        hargajualkelompok1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ukurankelompok1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        refreshkelompok1 = new javax.swing.JButton();
        tambahkelompok1 = new javax.swing.JButton();
        editkelompok1 = new javax.swing.JButton();
        hapuskelompok1 = new javax.swing.JButton();
        clearkelompok1 = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        carikelompok1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        databarangkelompok1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        home21552011058 = new javax.swing.JMenu();
        kembalikelompok1 = new javax.swing.JMenuItem();
        logoutkelompok1 = new javax.swing.JMenuItem();
        master21552011058 = new javax.swing.JMenu();
        pelanggankelompok1 = new javax.swing.JMenuItem();
        barangkelompok1 = new javax.swing.JMenuItem();
        transaksi21552011058 = new javax.swing.JMenu();
        pembeliankelompok1 = new javax.swing.JMenuItem();
        penjualankelompok1 = new javax.swing.JMenuItem();
        laporan21552011058 = new javax.swing.JMenu();
        LaporanBarangkelompok1 = new javax.swing.JMenuItem();
        LaporanPelanggankelompok1 = new javax.swing.JMenuItem();
        LaporanPembeliankelompok1 = new javax.swing.JMenuItem();
        LaporanPenjualankelompok1 = new javax.swing.JMenuItem();
        LaporanLoginkelompok1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/br.png"))); // NOI18N
        jLabel5.setText("Form Data Barang");

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
                .addComponent(jLabel5)
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
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tanggalkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waktukelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(143, 176, 219));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Kode Barang");

        namabarangkelompok1.setBackground(new java.awt.Color(204, 255, 255));
        namabarangkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabarangkelompok1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nama Barang");

        kodebarangkelompok1.setEditable(false);
        kodebarangkelompok1.setBackground(new java.awt.Color(18, 30, 49));
        kodebarangkelompok1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        kodebarangkelompok1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Harga Beli");

        hargabelikelompok1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Stok");

        stokkelompok1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Harga Jual");

        hargajualkelompok1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Ukuran");

        ukurankelompok1.setBackground(new java.awt.Color(204, 255, 255));
        ukurankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ukurankelompok1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namabarangkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ukurankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(kodebarangkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(185, 185, 185)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hargabelikelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(hargajualkelompok1)
                    .addComponent(stokkelompok1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(kodebarangkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargabelikelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namabarangkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(hargajualkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(ukurankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(stokkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));

        refreshkelompok1.setBackground(new java.awt.Color(51, 51, 51));
        refreshkelompok1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        refreshkelompok1.setForeground(new java.awt.Color(255, 255, 255));
        refreshkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        refreshkelompok1.setText("REFRESH");
        refreshkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshkelompok1ActionPerformed(evt);
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
        carikelompok1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carikelompok1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(refreshkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tambahkelompok1)
                .addGap(18, 18, 18)
                .addComponent(editkelompok1)
                .addGap(18, 18, 18)
                .addComponent(hapuskelompok1)
                .addGap(18, 18, 18)
                .addComponent(clearkelompok1)
                .addGap(18, 18, 18)
                .addComponent(cari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(carikelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshkelompok1)
                            .addComponent(tambahkelompok1)
                            .addComponent(editkelompok1)
                            .addComponent(hapuskelompok1)
                            .addComponent(clearkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(carikelompok1))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 51));

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DATA BARANG");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(642, 642, 642)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(143, 176, 219));

        databarangkelompok1.setBackground(new java.awt.Color(204, 255, 255));
        databarangkelompok1.setForeground(new java.awt.Color(0, 0, 0));
        databarangkelompok1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "KODE BARANG", "NAMA BARANG", "UKURAN", "HARGA BELI", "HARGA JUAL", "STOK"
            }
        ));
        databarangkelompok1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                databarangkelompok1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(databarangkelompok1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        home21552011058.setText("Home");
        home21552011058.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home21552011058ActionPerformed(evt);
            }
        });

        kembalikelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        kembalikelompok1.setText("Kembali");
        kembalikelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalikelompok1ActionPerformed(evt);
            }
        });
        home21552011058.add(kembalikelompok1);

        logoutkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        logoutkelompok1.setText("Logout");
        logoutkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutkelompok1ActionPerformed(evt);
            }
        });
        home21552011058.add(logoutkelompok1);

        jMenuBar1.add(home21552011058);

        master21552011058.setText("Master");

        pelanggankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_admin.png"))); // NOI18N
        pelanggankelompok1.setText("Pelanggan");
        pelanggankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pelanggankelompok1ActionPerformed(evt);
            }
        });
        master21552011058.add(pelanggankelompok1);

        barangkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_penjualan.png"))); // NOI18N
        barangkelompok1.setText("Barang");
        barangkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barangkelompok1ActionPerformed(evt);
            }
        });
        master21552011058.add(barangkelompok1);

        jMenuBar1.add(master21552011058);

        transaksi21552011058.setText("Transaksi");

        pembeliankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_barang.png"))); // NOI18N
        pembeliankelompok1.setText("Pembelian");
        pembeliankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pembeliankelompok1ActionPerformed(evt);
            }
        });
        transaksi21552011058.add(pembeliankelompok1);

        penjualankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_barang.png"))); // NOI18N
        penjualankelompok1.setText("Penjualan");
        penjualankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penjualankelompok1ActionPerformed(evt);
            }
        });
        transaksi21552011058.add(penjualankelompok1);

        jMenuBar1.add(transaksi21552011058);

        laporan21552011058.setText("Laporan");

        LaporanBarangkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_penjualan.png"))); // NOI18N
        LaporanBarangkelompok1.setText("Laporan Barang");
        LaporanBarangkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanBarangkelompok1ActionPerformed(evt);
            }
        });
        laporan21552011058.add(LaporanBarangkelompok1);

        LaporanPelanggankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_admin.png"))); // NOI18N
        LaporanPelanggankelompok1.setText("Laporan Pelanggan");
        LaporanPelanggankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanPelanggankelompok1ActionPerformed(evt);
            }
        });
        laporan21552011058.add(LaporanPelanggankelompok1);

        LaporanPembeliankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_barang.png"))); // NOI18N
        LaporanPembeliankelompok1.setText("Laporan Pembelian");
        LaporanPembeliankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanPembeliankelompok1ActionPerformed(evt);
            }
        });
        laporan21552011058.add(LaporanPembeliankelompok1);

        LaporanPenjualankelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data_barang.png"))); // NOI18N
        LaporanPenjualankelompok1.setText("Laporan Penjualan");
        LaporanPenjualankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanPenjualankelompok1ActionPerformed(evt);
            }
        });
        laporan21552011058.add(LaporanPenjualankelompok1);

        LaporanLoginkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.png"))); // NOI18N
        LaporanLoginkelompok1.setText("Laporan Login");
        LaporanLoginkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanLoginkelompok1ActionPerformed(evt);
            }
        });
        laporan21552011058.add(LaporanLoginkelompok1);

        jMenuBar1.add(laporan21552011058);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void home21552011058ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home21552011058ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_home21552011058ActionPerformed

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

    private void hapuskelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapuskelompok1ActionPerformed
        // TODO add your handling code here:
        try{
            String sql = "DELETE FROM t_barang  WHERE kode_barang ='"+kodebarangkelompok1.getText()+"'";
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

    private void refreshkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshkelompok1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)databarangkelompok1.getModel();
        model.setRowCount(0);
        tampilkan_data();
        autonumber();
    }//GEN-LAST:event_refreshkelompok1ActionPerformed

    private void tambahkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahkelompok1ActionPerformed
        // TODO add your handling code here:
        try{
            String sql = "INSERT INTO t_barang  VALUES ('"+kodebarangkelompok1.getText()+"','"+namabarangkelompok1.getText()+"','"+ukurankelompok1.getText()+"','"+hargabelikelompok1.getText()+"','"+hargajualkelompok1.getText()+"','"+stokkelompok1.getText()+"')";
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
            String sql = "INSERT t_barang set kode_barang ='"+kodebarangkelompok1.getText()+"' ,nama_barang ='"+namabarangkelompok1.getText()+"' ,ukuran ='"+ukurankelompok1.getText()+"' ,harga_beli='"+hargabelikelompok1.getText()+"' ,harga_jual='"+hargajualkelompok1.getText()+"' ,stok='"+stokkelompok1.getText()+"' on Duplicate Key Update kode_barang ='"+kodebarangkelompok1.getText()+"' ,nama_barang ='"+namabarangkelompok1.getText()+"' ,ukuran ='"+ukurankelompok1.getText()+"' ,harga_beli='"+hargabelikelompok1.getText()+"' ,harga_jual='"+hargajualkelompok1.getText()+"' ,stok='"+stokkelompok1.getText()+"'";
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

    private void databarangkelompok1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databarangkelompok1MouseClicked
        // TODO add your handling code here:
        int baris = databarangkelompok1.rowAtPoint(evt.getPoint());
        
        String kode = databarangkelompok1.getValueAt(baris, 1).toString();
        kodebarangkelompok1.setText(kode);
        
        String nama = databarangkelompok1.getValueAt(baris, 2).toString();
        namabarangkelompok1.setText(nama);
        
        String ukuran = databarangkelompok1.getValueAt(baris, 3).toString();
        ukurankelompok1.setText(ukuran);
        
        String harga_beli = databarangkelompok1.getValueAt(baris, 4).toString();
        hargabelikelompok1.setText(harga_beli);
        
        String harga_jual = databarangkelompok1.getValueAt(baris, 5).toString();
        hargajualkelompok1.setText(harga_jual);
        
        String stok = databarangkelompok1.getValueAt(baris, 6).toString();
        stokkelompok1.setText(stok);
        
    }//GEN-LAST:event_databarangkelompok1MouseClicked

    private void clearkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearkelompok1ActionPerformed
        // TODO add your handling code here:
        kosongkan_form();
        
    }//GEN-LAST:event_clearkelompok1ActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void carikelompok1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carikelompok1KeyReleased
        // TODO add your handling code here:
        tampilkan_data();
    }//GEN-LAST:event_carikelompok1KeyReleased

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

    private void LaporanLoginkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanLoginkelompok1ActionPerformed
        // TODO add your handling code here:
        try{
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("reportLogin.jasper"), null, Koneksi1.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_LaporanLoginkelompok1ActionPerformed

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

    private void namabarangkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namabarangkelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namabarangkelompok1ActionPerformed

    private void ukurankelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ukurankelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ukurankelompok1ActionPerformed

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
            java.util.logging.Logger.getLogger(Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem LaporanBarangkelompok1;
    private javax.swing.JMenuItem LaporanLoginkelompok1;
    private javax.swing.JMenuItem LaporanPelanggankelompok1;
    private javax.swing.JMenuItem LaporanPembeliankelompok1;
    private javax.swing.JMenuItem LaporanPenjualankelompok1;
    private javax.swing.JMenuItem barangkelompok1;
    private javax.swing.JButton cari;
    private javax.swing.JTextField carikelompok1;
    private javax.swing.JButton clearkelompok1;
    private javax.swing.JTable databarangkelompok1;
    private javax.swing.JButton editkelompok1;
    private javax.swing.JButton hapuskelompok1;
    private javax.swing.JTextField hargabelikelompok1;
    private javax.swing.JTextField hargajualkelompok1;
    private javax.swing.JMenu home21552011058;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem kembalikelompok1;
    private javax.swing.JTextField kodebarangkelompok1;
    private javax.swing.JMenu laporan21552011058;
    private javax.swing.JMenuItem logoutkelompok1;
    private javax.swing.JMenu master21552011058;
    private javax.swing.JTextField namabarangkelompok1;
    private javax.swing.JMenuItem pelanggankelompok1;
    private javax.swing.JMenuItem pembeliankelompok1;
    private javax.swing.JMenuItem penjualankelompok1;
    private javax.swing.JButton refreshkelompok1;
    private javax.swing.JTextField stokkelompok1;
    private javax.swing.JButton tambahkelompok1;
    private javax.swing.JLabel tanggalkelompok1;
    private javax.swing.JMenu transaksi21552011058;
    private javax.swing.JTextField ukurankelompok1;
    private javax.swing.JLabel waktukelompok1;
    // End of variables declaration//GEN-END:variables

    public Connection setKoneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_aplikasi_penjualan_uas","root","");
            st=conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +e);
        }
       return conn; 
    }



}

