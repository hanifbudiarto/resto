/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.pos.login;

import company.pos.database.MysqlConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M Hanif Budiarto
 */
public class Login {
    public Login () { }
    
    public String getLogin (String username, String password) {
        String category = "";
        
        MysqlConnect conn = MysqlConnect.getDbCon();        
        if (conn != null) {       
            String query = "SELECT kategori as total FROM pengguna WHERE username = ? and password = ?";
            ArrayList<String> parameter = new ArrayList<>();
            parameter.add(username);
            parameter.add(password);
            try {
                ResultSet rs = conn.query(query, parameter);
                rs.next();
                category = rs.getString(1);                
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);                
            }
        }
        return category;
    }
}
