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
public class Payment {
    /*
select a.penjualan_id, p.penjualan_tanggal, p.meja, sum(a.total) as total, p.ispaid from (
select pd.*, m.harga, pd.jumlah*m.harga as total from penjualan_detail pd
left join menu m
on pd.menu = m.nama ) as a left join penjualan p
on a.penjualan_id = p.penjualan_id
where p.penjualan_tanggal = "2015-10-05"
group by a.penjualan_id*/
    
    public Payment () {}
    
    public ResultSet getAllPaymentByDate (String date, String tableNum) {
        MysqlConnect conn = MysqlConnect.getDbCon();        
        if (conn != null) { 
            String query = "select a.penjualan_id, p.penjualan_tanggal, p.meja, sum(a.total) as total from (" +
            "select pd.*, m.harga, pd.jumlah*m.harga as total from penjualan_detail pd " +
            "left join menu m " +
            "on pd.menu = m.nama ) as a left join penjualan p " +
            "on a.penjualan_id = p.penjualan_id " +
            "where p.penjualan_tanggal = '"+date+"' and p.ispaid = 0";
            if (tableNum.length() != 0) { query += " and p.meja = "+ tableNum; }
            query += " group by a.penjualan_id";

            try {
                ResultSet rset = conn.query(query);
                return rset;
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }
    
    public ResultSet getPaymentDetail (int saleId) {
        MysqlConnect conn = MysqlConnect.getDbCon();        
        if (conn != null) { 
            String query = "select pd.menu, m.harga, pd.jumlah, pd.jumlah*m.harga as total from penjualan_detail pd\n" +
            "left join menu m\n" +
            "on pd.menu = m.nama where pd.penjualan_id = "+saleId;
            try {
                ResultSet rset = conn.query(query);
                return rset;
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        /*select pd.menu, m.harga, pd.jumlah, pd.jumlah*m.harga as total from penjualan_detail pd
left join menu m
on pd.menu = m.nama */
        return null;
    }
}
