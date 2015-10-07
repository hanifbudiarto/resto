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
public class Kitchen {
    public Kitchen () {}
    
    public boolean insertLogistic (Object [][] logistic, String date) {
        MysqlConnect conn = MysqlConnect.getDbCon();        
        if (conn != null) { 
            String query = "INSERT INTO PEMBELIAN (pembelian_tanggal) VALUES ('"+date+"')";
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
                    int rowLength = logistic.length;
                    for (int i=0; i<rowLength; i++){
                        alist.add("INSERT INTO PEMBELIAN_DETAIL (pembelian_id,nama_barang,satuan,harga,jumlah) VALUES" + 
                                "("+lastInsertedId+",'"+logistic[i][0]+"','"+logistic[i][1]+"',"+logistic[i][2]+","+logistic[i][3]+")");
                    }
                    int finalResult = conn.insertTransaction(alist);
                    if (finalResult<=0) {
                        query = "DELETE from PEMBELIAN WHERE pembelian_id = "+lastInsertedId;
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
}
