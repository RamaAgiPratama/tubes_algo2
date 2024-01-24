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
import Transaksi.Konfig;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ACER
 */
public class Pembelian extends javax.swing.JFrame {
    Connection conn;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    DefaultTableModel table = new DefaultTableModel();
    /**
     * Creates new form Pembelian
     */
    public Pembelian() {
        initComponents();
        otomatis();
        tampil();
        koneksi.getKoneksi();
        totalnya();
        tanggal();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jam();
        kosongkan_form();
        settanggal();
        autonumber();
    }
    
    public String Kodeba, Namaba, Ukuran, Hargabe, stock;

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
    public String Hargabel() {
        return Hargabe;
    }

    ;
    public String stock() {
        return stock;
    }
    
    private void autonumber(){
        try{
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM t_pembelian ORDER BY no_pembelian DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()){
                String NoID = r.getString("no_pembelian").substring(2);
                String ID = "" +(Integer.parseInt(NoID)+1);
                String Zero = "";
                
                if (ID.length()==1)
                {Zero = "00";}
                else if (ID.length()==2)
                {Zero = "0";}
                else if (ID.length()==3)
                {Zero = "";}
                
                nopembeliankelompok1.setText("NB" + Zero + ID);
            }else{
                nopembeliankelompok1.setText("NB001");
            }
            r.close();
            s.close();
        }catch(Exception e){
            System.out.println("");
        }
    }
    
    public void tampil(){
        Object header[]={"NO PEMBELIAN","TANGGAL BELI","KODE BARANG","NAMA BARANG","UKURAN","HARGA BELI","JUMLAH","TOTAL HARGA"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        datagpembeliankelompok1.setModel(data);
        setKoneksi();
        
        try {
            String cari = carikelompok1.getText();
            String sql="select*from t_pembelian WHERE no_pembelian LIKE'%"+cari+"%' OR `nama_barang` LIKE '%"+cari+"%'";
            ResultSet rs=st.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                String kolom8=rs.getString(8);
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }
    
    private void otomatis(){
        try {
            setKoneksi();
            String sql="select right (no_pembelian,2)+1 from t_pembelian";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    nopembeliankelompok1.setText("N"+no);}
                }
            else
            {
                nopembeliankelompok1.setText("N001"); 
        }
        } catch (Exception e) 
        {
        }
    }
    
    public void settanggal(){
        Date now = new Date();  
        tglkelompok1.setDate(now);    
    }
    
    public void tanggal(){
       java.util.Date skrg = new java.util.Date();
       java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("dd/MM/yyyy");
       tanggalkelompok1.setText(kal.format(skrg));   
    }
     
    private void clear(){
        nopembeliankelompok1.setText(null);
        tglkelompok1.setDate(null);
        idbarangkelompok1.setText(null);
        namabarangkelompok1.setText(null);
        ukurankelompok1.setText(null);
        hargabelikelompok1.setText(null);
        jumlahkelompok1.setText(null);
        totalhargakelompok1.setText(null);
    }
    
    private void totalnya(){
        String procedures = "CALL `total_harga_t_pembelian`()";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(procedures);//menjalanakn query\
                while(rslt.next()){
                    totalhargakelompok1.setText(rslt.getString(1));
                }
                
        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    private void total(){
        String harga = hargabelikelompok1.getText();
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
    
    public void kosongkan_form(){
        nopembeliankelompok1.setText(null);
        namabarangkelompok1.setText(null);
        idbarangkelompok1.setText(null);
        ukurankelompok1.setText(null);
        tglkelompok1.setDate(null);
        hargabelikelompok1.setText(null);
        jumlahkelompok1.setText(null);
        totalhargakelompok1.setText(null);   
    }
     
    public void tampilkan_data(){
        DefaultTableModel model = new DefaultTableModel ();
        
        model.addColumn("NO.");
        model.addColumn("NO PEMBELIAN");
        model.addColumn("TANGGAL BELI");
        model.addColumn("KODE BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("UKURAN");
        model.addColumn("HARGA BELI");
        model.addColumn("JUMLAH");
        model.addColumn("TOTAL HARGA");
        
        String cari = carikelompok1.getText();
        try {
            int no = 1;
            String sql = "Select * From t_pembelian  WHERE no_pembelian LIKE'%"+cari+"%' OR `nama_barang` LIKE '%"+cari+"%'";
            java.sql.Connection con =(Connection)Konfig.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while (res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8)});
                
                datagpembeliankelompok1.setModel(model);
                
            }
        }catch(SQLException e){
            System.out.println("ERROR :" +e.getMessage());
        }
        
    }
      
      public static Date getTanggalFromTable(JTable table, int kolom){
          JTable tabel = table;
          String str_tgl = String.valueOf(tabel.getValueAt(tabel.getSelectedRow(), kolom));
          Date tanggal = null;
          try{
              tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(str_tgl);
              
          }catch (ParseException ex){
              Logger.getLogger(Pembelian.class.getName()).log(Level.SEVERE, null, ex);
              
          }
          return tanggal;
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

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        waktukelompok1 = new javax.swing.JLabel();
        tanggalkelompok1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        datagpembeliankelompok1 = new javax.swing.JTable();
        tambahkelompok1 = new javax.swing.JButton();
        editkelompok1 = new javax.swing.JButton();
        hapuskelompok1 = new javax.swing.JButton();
        clearkelompok1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        carikelompok1 = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nopembeliankelompok1 = new javax.swing.JTextField();
        tglkelompok1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        idbarangkelompok1 = new javax.swing.JTextField();
        caribrgkelompok1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        namabarangkelompok1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ukurankelompok1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        hargabelikelompok1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jumlahkelompok1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        totalhargakelompok1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
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
        jLabel5.setText("Form Pembelian");

        waktukelompok1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        waktukelompok1.setForeground(new java.awt.Color(255, 255, 255));

        tanggalkelompok1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        tanggalkelompok1.setForeground(new java.awt.Color(255, 255, 255));

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
                    .addComponent(tanggalkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waktukelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(143, 176, 219));

        datagpembeliankelompok1.setBackground(new java.awt.Color(204, 255, 255));
        datagpembeliankelompok1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NO PEMBELIAN", "TANGGAL BELI", "KODE BARANG", "NAMA BARANG", "UKURAN", "HARGA BELI", "JUMLAH", "TOTAL HARGA"
            }
        ));
        datagpembeliankelompok1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datagpembeliankelompok1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(datagpembeliankelompok1);

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

        jPanel7.setBackground(new java.awt.Color(0, 0, 51));

        jLabel12.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText(" Data Pembelian");
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        carikelompok1.setBackground(new java.awt.Color(204, 255, 255));
        carikelompok1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carikelompok1KeyReleased(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(carikelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cari))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(tambahkelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(editkelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(hapuskelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(clearkelompok1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carikelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambahkelompok1)
                    .addComponent(editkelompok1)
                    .addComponent(hapuskelompok1)
                    .addComponent(clearkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(143, 176, 219));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("No Pembalian");

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tanggal Beli");

        nopembeliankelompok1.setEditable(false);
        nopembeliankelompok1.setBackground(new java.awt.Color(18, 30, 49));
        nopembeliankelompok1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nopembeliankelompok1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Kode Barang");

        idbarangkelompok1.setEditable(false);
        idbarangkelompok1.setBackground(new java.awt.Color(204, 255, 255));
        idbarangkelompok1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        idbarangkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idbarangkelompok1ActionPerformed(evt);
            }
        });

        caribrgkelompok1.setBackground(new java.awt.Color(51, 51, 51));
        caribrgkelompok1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        caribrgkelompok1.setForeground(new java.awt.Color(255, 255, 255));
        caribrgkelompok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cari2.png"))); // NOI18N
        caribrgkelompok1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                caribrgkelompok1MouseClicked(evt);
            }
        });
        caribrgkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caribrgkelompok1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama Barang");

        namabarangkelompok1.setEditable(false);
        namabarangkelompok1.setBackground(new java.awt.Color(204, 255, 255));
        namabarangkelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabarangkelompok1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Ukuran");

        ukurankelompok1.setEditable(false);
        ukurankelompok1.setBackground(new java.awt.Color(204, 255, 255));
        ukurankelompok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ukurankelompok1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Harga Beli");

        hargabelikelompok1.setEditable(false);
        hargabelikelompok1.setBackground(new java.awt.Color(204, 255, 255));

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

        jPanel6.setBackground(new java.awt.Color(0, 0, 51));

        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Input Data Pembelian");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(29, 29, 29)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nopembeliankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tglkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(namabarangkelompok1)
                                        .addComponent(ukurankelompok1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hargabelikelompok1)
                                        .addComponent(jumlahkelompok1)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalhargakelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7))
                                .addGap(36, 36, 36)
                                .addComponent(idbarangkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caribrgkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nopembeliankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tglkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idbarangkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(caribrgkelompok1))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namabarangkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(ukurankelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(hargabelikelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jumlahkelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(totalhargakelompok1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("TRANSAKSI PEMBELIAN BARANG");

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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tambahkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahkelompok1ActionPerformed
        // TODO add your handling code here:
        String tampilan = "yyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(tglkelompok1.getDate()));

        try{
            String sql = "INSERT INTO t_pembelian  VALUES ('"+nopembeliankelompok1.getText()+"','"+tanggal+"','"+idbarangkelompok1.getText()+"','"+namabarangkelompok1.getText()+"','"+ukurankelompok1.getText()+"','"+hargabelikelompok1.getText()+"','"+jumlahkelompok1.getText()+"','"+totalhargakelompok1.getText()+"')";
            java.sql.Connection conn = (Connection) Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Tambah Data Berhasil","TOKO IBU OTIH",JOptionPane.INFORMATION_MESSAGE);

        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, "Tambah Data Gagal","TOKO IBU OTIH",JOptionPane.WARNING_MESSAGE);
        }
        tampil();
        kosongkan_form();
        autonumber();
    }//GEN-LAST:event_tambahkelompok1ActionPerformed

    private void editkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editkelompok1ActionPerformed
        // TODO add your handling code here:
        String tampilan = "yyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(tglkelompok1.getDate()));
        try{
            String sql = "INSERT t_pembelian set no_pembelian ='"+nopembeliankelompok1.getText()+"' ,tanggal_beli ='"+tanggal+"',kode_barang ='"+idbarangkelompok1.getText()+"',nama_barang ='"+namabarangkelompok1.getText()+"' ,ukuran ='"+ukurankelompok1.getText()+"' ,harga_beli='"+hargabelikelompok1.getText()+"' ,jumlah='"+jumlahkelompok1.getText()+"' ,total_harga='"+totalhargakelompok1.getText()+"' on Duplicate Key Update no_pembelian ='"+nopembeliankelompok1.getText()+"' ,tanggal_beli ='"+tanggal+"',kode_barang ='"+idbarangkelompok1.getText()+"',nama_barang ='"+namabarangkelompok1.getText()+"' ,ukuran ='"+ukurankelompok1.getText()+"' ,harga_beli='"+hargabelikelompok1.getText()+"' ,jumlah='"+jumlahkelompok1.getText()+"' ,total_harga='"+totalhargakelompok1.getText()+"'";
            java.sql.Connection conn = (Connection)Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Edit Data Berhasil","TOKO IBU OTIH",JOptionPane.INFORMATION_MESSAGE);

        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampil();
        kosongkan_form();
        autonumber();
    }//GEN-LAST:event_editkelompok1ActionPerformed

    private void hapuskelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapuskelompok1ActionPerformed
        // TODO add your handling code here:
       try{
            String sql = "DELETE FROM t_pembelian  WHERE no_pembelian ='"+nopembeliankelompok1.getText()+"'";
            java.sql.Connection conn = (Connection)Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil","TOKO IBU OTIH",JOptionPane.INFORMATION_MESSAGE);

        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampil();
        kosongkan_form();
        autonumber();
    }//GEN-LAST:event_hapuskelompok1ActionPerformed

    private void clearkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearkelompok1ActionPerformed
        // TODO add your handling code here:
        kosongkan_form();
        autonumber();
    }//GEN-LAST:event_clearkelompok1ActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void carikelompok1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carikelompok1KeyReleased
        // TODO add your handling code here:
        tampilkan_data();
    }//GEN-LAST:event_carikelompok1KeyReleased

    private void jumlahkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahkelompok1ActionPerformed
        // TODO add your handling code here:
        double beli = Double.parseDouble(hargabelikelompok1.getText());
        double bayar = Double.parseDouble(jumlahkelompok1.getText());
        double kembali = bayar * beli  ;
        totalhargakelompok1.setText(""+kembali);
    }//GEN-LAST:event_jumlahkelompok1ActionPerformed

    private void caribrgkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caribrgkelompok1ActionPerformed
        // TODO add your handling code here:
       boolean closable = true;
        pgl fDB = new pgl(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
        idbarangkelompok1.setText(Kodeba);
        namabarangkelompok1.setText(Namaba);
        ukurankelompok1.setText(Ukuran);
        hargabelikelompok1.setText(Hargabe);
    }//GEN-LAST:event_caribrgkelompok1ActionPerformed

    private void idbarangkelompok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idbarangkelompok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idbarangkelompok1ActionPerformed

    private void jumlahkelompok1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahkelompok1KeyReleased
        // TODO add your handling code here:
        total();
    }//GEN-LAST:event_jumlahkelompok1KeyReleased

    private void caribrgkelompok1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caribrgkelompok1MouseClicked
        // TODO add your handling code here:
          
    }//GEN-LAST:event_caribrgkelompok1MouseClicked

    private void datagpembeliankelompok1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datagpembeliankelompok1MouseClicked
        // TODO add your handling code here:
        int baris =datagpembeliankelompok1.rowAtPoint(evt.getPoint());
        
         String NoBeli=datagpembeliankelompok1.getValueAt(baris,0).toString();
         nopembeliankelompok1.setText(NoBeli);
         
         tglkelompok1.setDate(getTanggalFromTable(datagpembeliankelompok1, 1));
         
         String KodeBarang =datagpembeliankelompok1.getValueAt(baris, 2).toString();
         idbarangkelompok1.setText(KodeBarang);
         
         String NamaBarang =datagpembeliankelompok1.getValueAt(baris, 3).toString();
         namabarangkelompok1.setText(NamaBarang);
        
         String Ukuran =datagpembeliankelompok1.getValueAt(baris, 4).toString();
         ukurankelompok1.setText(Ukuran);
         
         String  HargaBeli=datagpembeliankelompok1.getValueAt(baris, 5).toString();
         hargabelikelompok1.setText(HargaBeli);
         
         String Jumlah =datagpembeliankelompok1.getValueAt(baris, 6).toString();
         jumlahkelompok1.setText(Jumlah);
         
         String TotalHarga =datagpembeliankelompok1.getValueAt(baris, 7).toString();
         totalhargakelompok1.setText(TotalHarga);
          
    }//GEN-LAST:event_datagpembeliankelompok1MouseClicked

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
            java.util.logging.Logger.getLogger(Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pembelian().setVisible(true);
            }
        });
    }
    
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem LaporanBarangkelompok1;
    private javax.swing.JMenuItem LaporanLoginkelompok1;
    private javax.swing.JMenuItem LaporanPelanggankelompok1;
    private javax.swing.JMenuItem LaporanPembeliankelompok1;
    private javax.swing.JMenuItem LaporanPenjualankelompok1;
    private javax.swing.JMenuItem barangkelompok1;
    private javax.swing.JButton cari;
    private javax.swing.JButton caribrgkelompok1;
    private javax.swing.JTextField carikelompok1;
    private javax.swing.JButton clearkelompok1;
    private javax.swing.JTable datagpembeliankelompok1;
    private javax.swing.JButton editkelompok1;
    private javax.swing.JButton hapuskelompok1;
    private javax.swing.JTextField hargabelikelompok1;
    private javax.swing.JMenu homekelompok1;
    private javax.swing.JTextField idbarangkelompok1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jumlahkelompok1;
    private javax.swing.JMenuItem kembalikelompok1;
    private javax.swing.JMenuItem logoutkelompok1;
    private javax.swing.JMenu masterkelompok1;
    private javax.swing.JTextField namabarangkelompok1;
    private javax.swing.JTextField nopembeliankelompok1;
    private javax.swing.JMenuItem pelanggankelompok1;
    private javax.swing.JMenuItem pembeliankelompok1;
    private javax.swing.JMenuItem penjualankelompok1;
    private javax.swing.JButton tambahkelompok1;
    private javax.swing.JLabel tanggalkelompok1;
    private com.toedter.calendar.JDateChooser tglkelompok1;
    private javax.swing.JTextField totalhargakelompok1;
    private javax.swing.JMenu transaksikelompok1;
    private javax.swing.JTextField ukurankelompok1;
    private javax.swing.JLabel waktukelompok1;
    // End of variables declaration//GEN-END:variables

     static class dispose {

        public dispose() {
        }
    }
}


