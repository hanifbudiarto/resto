/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.cashier;

import company.pos.database.MysqlConnect;
import company.pos.util.Session;
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
    
    private final ArrayList<String> parameter;
    private final ArrayList<ArrayList<String>> paramTransaction;
    
    public Kitchen () {
        this.parameter = new ArrayList<>();
        this.paramTransaction = new ArrayList<>();
    }
    
    public boolean insertLogistic (Object [][] logistic, String date, String total) {
        MysqlConnect conn = MysqlConnect.getDbCon();        
        if (conn != null) { 
            String query = "INSERT INTO PEMBELIAN (pembelian_tanggal, total, operator) VALUES (?,?,?)";
            parameter.clear();
            parameter.add(date);
            parameter.add(total);
            parameter.add(Session.getUserLoggedIn());
            try {
                boolean result = conn.insert(query, parameter);
                if (result) {
                    query = "SELECT LAST_INSERT_ID()";
                    ResultSet rset = conn.query(query, null);
                    String lastInsertedId = "";
                    while (rset.next()) {
                        lastInsertedId = Integer.toString(rset.getInt(1));
                        break;
                    }              
                    parameter.clear();
                    paramTransaction.clear();
                    int rowLength = logistic.length;
                    for (int i=0; i<rowLength; i++){
                        parameter.add("INSERT INTO PEMBELIAN_DETAIL (pembelian_id,nama_barang,satuan,harga,jumlah) VALUES (?,?,?,?,?)");
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(lastInsertedId);
                        temp.add(logistic[i][0].toString());
                        temp.add(logistic[i][1].toString());
                        temp.add(logistic[i][2].toString());
                        temp.add(logistic[i][3].toString());
                        
                        paramTransaction.add(temp);
                    }
                    boolean finalResult = conn.insertTransaction(parameter, paramTransaction);
                    if (!finalResult) {                        
                        query = "DELETE from PEMBELIAN WHERE pembelian_id = "+lastInsertedId;
                        return conn.updateOrDelete(query);
                    }
                    return finalResult;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }    
}
