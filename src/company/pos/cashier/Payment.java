package company.pos.cashier;

import company.pos.database.MysqlConnect;
import company.pos.util.Session;
import java.math.BigInteger;
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
    
    private final ArrayList<String> parameter;
    
    public Payment () {
        this.parameter = new ArrayList<>();
    }
    
    public ResultSet getAllPaymentByDate (String date, String tableNum) {
        MysqlConnect conn = MysqlConnect.getDbCon();        
        if (conn != null) { 
            String query = "select a.penjualan_id as ID, p.penjualan_tanggal as Tanggal, p.meja as 'Nomor Meja', sum(a.total) as Total from (" +
            "select pd.*, m.harga, pd.jumlah*m.harga as total from penjualan_detail pd " +
            "left join menu m " +
            "on pd.menu = m.nama ) as a left join penjualan p " +
            "on a.penjualan_id = p.penjualan_id " +
            "where p.penjualan_tanggal = ? and p.ispaid = 0";
            parameter.clear();
            parameter.add(date);            
            
            if (tableNum.length() != 0) { 
                query += " and p.meja = ?"; 
                parameter.add(tableNum);
            }
            query += " group by p.meja";

            try {
                ResultSet rset = conn.query(query, parameter);
                return rset;
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }
    
    public ResultSet getPaymentDetail (String date, String tableNum) {
        MysqlConnect conn = MysqlConnect.getDbCon();        
        if (conn != null) { 
            String query = "select pd.menu as Menu, m.Harga, sum(pd.jumlah) as Jumlah, sum(pd.jumlah*m.harga) as Total from penjualan_detail pd\n" +
            "left join menu m\n" +
            "on pd.menu = m.nama left join penjualan p on p.penjualan_id = pd.penjualan_id where p.penjualan_tanggal=? and p.meja=? and p.ispaid = 0 group by menu";
            parameter.clear();
            parameter.add(date);
            parameter.add(tableNum);
            try {
                ResultSet rset = conn.query(query, parameter);
                return rset;
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }
    
    public boolean pay (String tableNum, BigInteger total, BigInteger paid) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            BigInteger ret = paid.subtract(total);
            String query = "update penjualan set total ="+total+" ,ispaid=1 ,operator='"+Session.getUserLoggedIn()+"', dibayar="+paid+", kembalian="+ret+" where meja="+tableNum;             
            return conn.updateOrDelete(query);
        }
        return false;
    }
}
