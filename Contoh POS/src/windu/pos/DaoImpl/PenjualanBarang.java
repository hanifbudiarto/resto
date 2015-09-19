/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import windu.pos.Dao.IPenjualanBarang;
import windu.pos.database.DataConnection;

/**
 *
 * @author WinduPurnomo
 */
public class PenjualanBarang implements  IPenjualanBarang{
    private String qSelect = "SELECT * FROM penjualanbrg";
    private String qAdd = "INSERT INTO penjualanbrg (IdPenjualan, IdBarang, Jumlah, Diskon) VALUES (?, ?, ?, ?)";
    
    @Override
    public List<windu.pos.entity.PenjualanBarang> selectAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public windu.pos.entity.PenjualanBarang selectById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int add(windu.pos.entity.PenjualanBarang penjualanBarang) {
        Connection con = new DataConnection().buatKoneksi();
        ResultSet rs = null;
        int key = 0;
        try {
            PreparedStatement ps = con.prepareStatement(qAdd);
            ps.setInt(1, penjualanBarang.getPenjualan().getIdPenjualan());
            ps.setInt(2, penjualanBarang.getBarang().getId());
            ps.setInt(3, penjualanBarang.getJumlah());
            ps.setDouble(4, penjualanBarang.getDiskon());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            key = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }
    
    @Override
    public void addByBatch(List<windu.pos.entity.PenjualanBarang> listPenjBrg) {
        Connection con = new DataConnection().buatKoneksi();
        try {
            PreparedStatement ps = con.prepareStatement(qAdd);
            for (int i = 0; i < listPenjBrg.size(); i++) {
                ps.setInt(1, listPenjBrg.get(i).getPenjualan().getIdPenjualan());
                ps.setInt(2, listPenjBrg.get(i).getBarang().getId());
                ps.setInt(3, listPenjBrg.get(i).getJumlah());
                ps.setDouble(4, listPenjBrg.get(i).getDiskon());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(windu.pos.entity.PenjualanBarang penjualanBarang) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
