package company.pos.menu;

import company.pos.database.MysqlConnect;
import company.pos.login.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author M Hanif Budiarto
 */
public class Category {
    public Category () {}
    
    public List<String> getAllCategory () {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            String query = "SELECT nama FROM menu_kategori";
            try {
                ResultSet rs = conn.query(query);
                List<String> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rs.getString("nama"));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public void insertCategory (String menuCategory) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null){
            String query = "INSERT INTO menu_kategori (nama) values ('"+menuCategory+"')";
            try {                
                int result = conn.insert(query);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
