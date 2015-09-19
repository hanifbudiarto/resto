/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.database;

/**
 *
 * @author WinduPurnomo
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DataConnection {
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/POS";
    private String user = "root";
    private String pass = "";

    /*
     * Membuka koneksi ke database
     * */
    public Connection buatKoneksi(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ada masalah dengan koneksi ke database.\n" +
                    "Pastikan database sudah aktif.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver tidak sesuai");
        }
        return con;
    }
    
    public Connection buatKoneksi2(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ada masalah dengan koneksi ke database.\n" +
                    "Pastikan database sudah aktif.");
        }
        return con;
    }

    /*
     * Menutup koneksi ke database.
     * */
    public void tutupKoneksi(){
        try{
            if (rs!=null) rs.close();
            if(st != null) st.close();
            if(con!=null) con.close();
        }catch(Exception e){
            e.getMessage();
        }
    }
    
}
