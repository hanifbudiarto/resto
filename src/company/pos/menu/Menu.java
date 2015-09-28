/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.pos.menu;

import company.pos.database.MysqlConnect;
import company.pos.login.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M Hanif Budiarto
 */
public class Menu {
    public Menu () {
    }
    
    public void insertMenu (String menuName, String price, String catName) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null){
            String query = "INSERT INTO menu (nama, harga, kategori_id) values ('"+menuName+"','"+
                    price+"',(select kategori_id from menu_kategori where upper(nama) = upper('"+catName+"') limit 1))";
            try {                
                int result = conn.insert(query);
                if (result>0) System.out.println ("Success");
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ResultSet getAllMenu () {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            String query = "SELECT m.nama, m.harga, mk.nama as kategori FROM menu m left join menu_kategori mk \n" +
"on m.kategori_id = mk.kategori_id order by m.nama;";
            try {
                ResultSet rset = conn.query(query);
                return rset;
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
