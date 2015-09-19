/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.DaoImpl;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import windu.pos.Dao.IBarang;
import windu.pos.database.DataConnection;

/**
 *
 * @author WinduPurnomo
 */
public class Barang implements IBarang{

    private String qSelectAll = "SELECT * FROM barang ORDER BY nama ASC";
    private String qSelectById = "SELECT * FROM barang WHERE IdBarang = ?";
    private String qSelectPart = "SELECT * FROM barang WHERE nama LIKE ?";
    private String qUpdate = "UPDATE barang SET nama = ?, jumlah = ?, hargaBeli = ?, hargaJual = ? WHERE IdBarang = ?";
    private String qAdd = "INSERT INTO barang (nama, jumlah, hargaBeli, hargaJual) VALUES(?, ?, ?, ?)";
    
    @Override
    public List<windu.pos.entity.Barang> selectAll() {
        List<windu.pos.entity.Barang> lb = new ArrayList<windu.pos.entity.Barang>();
        Connection con = new DataConnection().buatKoneksi();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(qSelectAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {     
                windu.pos.entity.Barang b = new windu.pos.entity.Barang();
                b.setId(rs.getInt(1));
                b.setNama(rs.getString(2));
                b.setHargaBeli(rs.getDouble(4));
                b.setHargaJual(rs.getDouble(5));
                b.setJumlah(rs.getInt(3));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public windu.pos.entity.Barang selectById(int id) {
        Connection con = new DataConnection().buatKoneksi();
        ResultSet rs = null;
        windu.pos.entity.Barang barang = new windu.pos.entity.Barang();
        try {
            PreparedStatement ps = con.prepareStatement(qSelectById);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            String nama = rs.getString(2);
            int jumlah = rs.getInt(3);
            double hargaBeli = rs.getDouble(4);
            double hargaJual = rs.getDouble(5);
            
            barang.setId(id);
            barang.setNama(nama);
            barang.setJumlah(jumlah);
            barang.setHargaBeli(hargaBeli);
            barang.setHargaJual(hargaJual);
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return barang;
    }

    @Override
    public int add(windu.pos.entity.Barang barang) {
        Connection con = new DataConnection().buatKoneksi();
        ResultSet rs = null;
        int key = 0;
        try {
            PreparedStatement ps = con.prepareStatement(qAdd, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, barang.getNama());
            ps.setInt(2, barang.getJumlah());
            ps.setDouble(3, barang.getHargaBeli());
            ps.setDouble(4, barang.getHargaJual());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            key = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    @Override
    public void update(windu.pos.entity.Barang barang) {
        Connection con = new DataConnection().buatKoneksi();
        try {
            PreparedStatement ps = con.prepareStatement(qUpdate);
            ps.setString(1, barang.getNama());
            ps.setInt(2, barang.getJumlah());
            ps.setDouble(3, barang.getHargaBeli());
            ps.setDouble(4, barang.getHargaJual());
            ps.setInt(5, barang.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void updateByBatch(List<windu.pos.entity.Barang> listBarang){
        Connection con = new DataConnection().buatKoneksi();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(qUpdate);
            for (int i = 0; i < listBarang.size(); i++) {
                ps.setString(1, listBarang.get(i).getNama());
                ps.setInt(2, listBarang.get(i).getJumlah());
                ps.setDouble(3, listBarang.get(i).getHargaBeli());
                ps.setDouble(4, listBarang.get(i).getHargaJual());
                ps.setInt(5, listBarang.get(i).getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<windu.pos.entity.Barang> selectByPartNama(String nama) {
        List<windu.pos.entity.Barang> lb = new ArrayList<windu.pos.entity.Barang>();
        Connection con = new DataConnection().buatKoneksi();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(qSelectPart);
            ps.setString(1, nama+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {     
                windu.pos.entity.Barang b = new windu.pos.entity.Barang();
                b.setId(rs.getInt(1));
                b.setNama(rs.getString(2));
                b.setHargaBeli(rs.getDouble(4));
                b.setHargaJual(rs.getDouble(5));
                b.setJumlah(rs.getInt(3));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
    
}
