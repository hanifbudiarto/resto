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
    
    
    private final ArrayList<String> parameter;
    private final ArrayList<ArrayList<String>> paramTransaction;
    
    public Order () {
        this.parameter = new ArrayList<>();
        this.paramTransaction = new ArrayList<>();
    }
    
    public int insertOrder (Object [][] order, String date, String table, String note) {
        MysqlConnect conn = MysqlConnect.getDbCon();        
        if (conn != null) { 
            String query = "INSERT INTO PENJUALAN (penjualan_tanggal, meja, catatan) VALUES (?,?,?)";
            parameter.clear();
            parameter.add(date);
            parameter.add(table);
            parameter.add(note);
            try {
                boolean result = conn.insert(query, parameter);
                if (result) {
                    query = "SELECT LAST_INSERT_ID()";
                    ResultSet rset = conn.query(query, null);
                    int lastInsertedId = 0;
                    while (rset.next()) {
                        lastInsertedId = rset.getInt(1);
                        break;
                    }              
                    parameter.clear();
                    int rowLength = order.length;
                    for (int i=0; i<rowLength; i++){
                        parameter.add("INSERT INTO PENJUALAN_DETAIL (penjualan_id,menu,jumlah) VALUES (?,?,?)");                        
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(Integer.toString(lastInsertedId));
                        temp.add((String) order[i][0]);
                        temp.add((String) order[i][1]);
                        paramTransaction.add(temp);
                    }
                    boolean finalResult = conn.insertTransaction(parameter, paramTransaction);
                    if (!finalResult) {
                        
                        query = "DELETE from PENJUALAN WHERE penjualan_id = "+lastInsertedId;
                        conn.updateOrDelete(query);
//                        return conn.updateOrDelete(query);
                    }                  
                    if (finalResult) return lastInsertedId;
//                    return finalResult;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
        return -1;
    }
    
    public ResultSet showOrderByTable (String table) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            String query = "select menu, jumlah from penjualan_detail where penjualan_id = (select penjualan_id from penjualan where meja = ? and ispaid = 0 order by penjualan_id desc limit 1)";
            parameter.clear();
            parameter.add(table);
            try {
                return conn.query(query, parameter);
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }        
        return null;
    }
}
