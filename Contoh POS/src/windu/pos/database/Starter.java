/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WinduPurnomo
 */
public class Starter {

    private String databaseName;

    public Starter(String databaseName) {
        this.databaseName = databaseName;
    }
    
    public Starter() {
        databaseName = "POS";
    }
    
    public void createDB(){
        Connection con = new DataConnection().buatKoneksi2();
        try {
            PreparedStatement ps = con.prepareStatement("grant all privileges on xxx.* to 'xxx' identified by 'c'");
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void givePrivilege(){
        Connection con = new DataConnection().buatKoneksi2();
        try {
            PreparedStatement ps = con.prepareStatement("grant all privileges on xxx.* to 'xxx' identified by 'c'");
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
