/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.cashier;

import company.pos.database.MysqlConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muhammad Hanif B
 */
public class Order {
    public Order () {
    }
    
    public boolean insertOrder (Object [][] order, String date, String table, String note) {
        MysqlConnect conn = MysqlConnect.getDbCon();        
        if (conn != null) { 
            String query = "INSERT INTO PENJUALAN (penjualan_tanggal, meja, catatan) VALUES ('"+date+"','"+table+"','"+note+"')";
            try {
                int result = conn.insert(query);
                if (result>0) {
                    query = "SELECT LAST_INSERT_ID()";
                    ResultSet rset = conn.query(query);
                    int lastInsertedId = 0;
                    while (rset.next()) {
                        lastInsertedId = rset.getInt(1);
                        break;
                    }              
                    ArrayList<String> alist = new ArrayList<>();  
                    int rowLength = order.length;
                    for (int i=0; i<rowLength; i++){
                        alist.add("INSERT INTO PENJUALAN_DETAIL (penjualan_id,menu,jumlah) VALUES ("+lastInsertedId+",'"+order[i][0]+"',"+order[i][1]+")");
                    }
                    int finalResult = conn.insertTransaction(alist);
                    if (finalResult<=0) {
                        query = "DELETE from PENJUALAN WHERE penjualan_id = "+lastInsertedId;
                        return conn.insert(query)>0;
                    }
                    return finalResult>0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    public ResultSet showOrderByTable (String table) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            String query = "select menu, jumlah from penjualan_detail where penjualan_id = (select penjualan_id from penjualan where meja = "+table+" and ispaid = 0 order by penjualan_id desc limit 1)";
            try {
                return conn.query(query);
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }        
        return null;
    }
}
