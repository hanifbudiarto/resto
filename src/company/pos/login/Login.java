/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.pos.login;

import company.pos.database.MysqlConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M Hanif Budiarto
 */
public class Login {
    public Login () {
    }
    
    public boolean getLogin (String username, String password) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            String query = "SELECT 1 as total FROM pengguna WHERE username = '"+username+"' and password = '"+password+"'";
            try {
                ResultSet rs = conn.query(query);
                if (rs.next()) return true;                
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
