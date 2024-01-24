
package ConnectionDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class DatabaseConnection {
    public static Connection getKoneksi(String host, String port, String username, String password, String db) {
String konString = "jdbc:mysql://" + host + ":" + port + "/" + db;
Connection koneksi = null;
try {
Class.forName("com.mysql.jdbc.Driver");
koneksi = DriverManager.getConnection(konString, username, password);
System.out.println("Koneksi Berhasil");
} catch (ClassNotFoundException | SQLException ex) {
JOptionPane.showMessageDialog(null, "Koneksi Database Error");
koneksi = null;
}
return koneksi;
}

    Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
