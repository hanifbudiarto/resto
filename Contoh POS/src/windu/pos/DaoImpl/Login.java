/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import windu.pos.Dao.ILogin;
import windu.pos.database.DataConnection;

/**
 *
 * @author WinduPurnomo
 */
public class Login implements ILogin{

    @Override
    public boolean isAccept(String username, String password) {
        Connection con = new DataConnection().buatKoneksi();
        PreparedStatement ps;
        ResultSet rs = null;
        String s = "SELECT COUNT(*) FROM LOGIN where username = ? and password = ?";
        try {
            ps = con.prepareStatement(s);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
