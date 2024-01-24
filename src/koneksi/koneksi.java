package koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class koneksi {
    Connection connect;
    Statement sttmnt;
    ResultSet rslt;
    private static Connection koneksi;  
    public static Connection getKoneksi(){
        try{
            String url = "jdbc:mysql://localhost:3306/db_aplikasi_penjualan_uas";
            String user = "root";
            String password = "";
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            koneksi = DriverManager.getConnection(url,user,password);
            System.out.println("Koneksi Berhasil");
            
        }catch(Exception e){
            System.out.println("Koneksi Gagal");
        }return koneksi;
    
    }
 

}