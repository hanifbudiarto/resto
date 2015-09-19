/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import windu.pos.Dao.IPenjualan;
import windu.pos.database.DataConnection;

/**
 *
 * @author WinduPurnomo
 */
public class Penjualan implements IPenjualan{
    private String qSelect = "SELECT * FROM penjualan";
    private String qAdd = "INSERT INTO penjualan (tanggal, idPelanggan) values (?, ?)";
    
    @Override
    public List<windu.pos.entity.Penjualan> selectAll() {
        Connection con = new DataConnection().buatKoneksi();
        ResultSet rs = null;
        List<windu.pos.entity.Penjualan> listPenjualan = new ArrayList<windu.pos.entity.Penjualan>();
        try {
            PreparedStatement ps = con.prepareStatement(qSelect);
            rs = ps.executeQuery();
            while (rs.next()) {                
                windu.pos.entity.Penjualan p = new windu.pos.entity.Penjualan();
                p.setIdPenjualan(rs.getInt(1));
                p.setTanggal(rs.getDate(2));
                p.setPelanggan(new Pelanggan().selectById(rs.getInt(3)));
                listPenjualan.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Penjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPenjualan;
    }

    @Override
    public windu.pos.entity.Penjualan selectById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int add(windu.pos.entity.Penjualan penjualan) {
        Connection con = new DataConnection().buatKoneksi();
        ResultSet rs = null;
        int key = 0;
        try {
            long time = penjualan.getTanggal().getTime();
            Timestamp ts = new Timestamp(time);
            PreparedStatement ps = con.prepareStatement(qAdd, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, ts);
            ps.setInt(2, penjualan.getPelanggan().getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            key = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Penjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    @Override
    public void update(windu.pos.entity.Penjualan penjualan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void main(String[] args) {
        windu.pos.entity.Penjualan p = new windu.pos.entity.Penjualan();
        windu.pos.entity.Pelanggan pe = new Pelanggan().selectById(1);
        p.setPelanggan(pe);
        p.setTanggal(new Date());
        new Penjualan().add(p);
    }
    
}
