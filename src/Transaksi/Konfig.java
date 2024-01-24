/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
class Konfig {
    private static Connection MySQLConfig;
    
    public static Connection configDB() throws SQLException{
        try{
            
       
            String url = "jdbc:mysql://localhost:3306/db_aplikasi_penjualan_uas";
            String user = "root";
            String pass = "";
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            MySQLConfig = DriverManager.getConnection(url, user, pass);
        }catch(SQLException e){
            System.out.println("Koneksi Gagal" + e.getMessage());
        }
        return MySQLConfig;
    }

    static Connection getKoneksi(String localhost, String string, String root, String string0, String sekolah) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
