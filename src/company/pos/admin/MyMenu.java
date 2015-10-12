/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.pos.admin;

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
public class MyMenu {
    
    private final ArrayList<String> parameter;       
    private final ArrayList<ArrayList<String>> paramTransaction;
    
    public MyMenu () {
        this.parameter = new ArrayList<>();
        this.paramTransaction = new ArrayList<>();
    }
    
    public boolean insert (Object [][] menus) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null){
            String query = "TRUNCATE MENU";           
            parameter.clear();
            paramTransaction.clear();

            parameter.add(query);
            paramTransaction.add(null);

            int rowLength = menus.length;
            for (int i=0; i<rowLength; i++){
                parameter.add("INSERT INTO menu (nama, harga, kategori_id) values (?,?,(select kategori_id from menu_kategori where upper(nama) = upper(?) limit 1)) ");
                ArrayList<String> temp = new ArrayList<>();
                temp.add(menus[i][0].toString());
                temp.add(menus[i][1].toString());
                temp.add(menus[i][2].toString());

                paramTransaction.add(temp);
            }
            boolean finalResult = conn.insertTransaction(parameter, paramTransaction);
            return finalResult;
        }
        return false;
    }
    
    public boolean insertMenu (String menuName, String price, String catName) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null){
            String query = "INSERT INTO menu (nama, harga, kategori_id) values (?,?,(select kategori_id from menu_kategori where upper(nama) = upper(?) limit 1)) ";
            query += "ON DUPLICATE KEY UPDATE harga=?, kategori_id=(select kategori_id from menu_kategori where upper(nama) = upper(?) limit 1)";

            parameter.clear();
            parameter.add(menuName);
            parameter.add(price);
            parameter.add(catName);
            parameter.add(price);
            parameter.add(catName);
            return conn.insert(query, parameter);            
        }
        return false;
    }
    
    public boolean deleteMenu (String menuName) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        String query = null;
        if (conn != null){
            query = "DELETE FROM menu WHERE nama='"+menuName+"'";
        } 
        return query!=null ? conn.updateOrDelete(query) : false;
    } 
    
    public ResultSet getAllMenu () {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            String query = "SELECT m.nama as Nama, m.harga as Harga, mk.nama as Kategori FROM menu m left join menu_kategori mk \n" +
"on m.kategori_id = mk.kategori_id order by m.nama;";
            try {
                ResultSet rset = conn.query(query, null);
                return rset;
            } catch (SQLException ex) {
                Logger.getLogger(MyMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
