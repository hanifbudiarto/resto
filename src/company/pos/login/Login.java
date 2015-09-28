/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.pos.login;

import company.pos.database.MysqlConnect;
import java.sql.PreparedStatement;
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
    
    public Login (String username, String password) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            String query = "SELECT tipe_id, COUNT(*) as total FROM pengguna WHERE username = '"+username+"' and password = '"+password+"'";
            try {
                ResultSet rs = conn.query(query);
                rs.next();
                if (rs.getInt("total") == 1) {
                    System.out.println ("Success");
                    switch (rs.getInt("tipe_id")) {
                        case 1:
                            System.out.println("Kasir");
                            break;
                        case 2:
                            System.out.println("Non Kasir");
                            break;
                        default:
                            break;
                    }
                }
                else System.out.println("Failed");
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
