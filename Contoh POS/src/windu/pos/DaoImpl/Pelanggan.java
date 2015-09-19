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
import windu.pos.Dao.IPelanggan;
import windu.pos.database.DataConnection;

/**
 *
 * @author WinduPurnomo
 */
public class Pelanggan implements IPelanggan{

    private String qSelectAll = "Select * from pelanggan order by nama";
    private String qSelectById = "select * from pelanggan where idPelanggan = ?";
    
    @Override
    public List<windu.pos.entity.Pelanggan> selectAll() {
        Connection con = new DataConnection().buatKoneksi();
        List<windu.pos.entity.Pelanggan> listPelanggan = new ArrayList<windu.pos.entity.Pelanggan>();
        try {
            PreparedStatement ps = con.prepareStatement(qSelectAll);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                windu.pos.entity.Pelanggan p = new windu.pos.entity.Pelanggan();
                p.setId(rs.getInt(1));
                p.setNama(rs.getString(2));
                p.setNoHP(rs.getString(3));
                listPelanggan.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listPelanggan;
    }

    @Override
    public windu.pos.entity.Pelanggan selectById(int id) {
        Connection con = new DataConnection().buatKoneksi();
        ResultSet rs = null;
        windu.pos.entity.Pelanggan pelanggan = new windu.pos.entity.Pelanggan();
        try {
            PreparedStatement ps = con.prepareStatement(qSelectById);
            ps.setInt(1, id);
            System.out.println(ps.toString());
            rs = ps.executeQuery();
            rs.next();
            pelanggan.setId(id);
            pelanggan.setNama(rs.getString(2));
            pelanggan.setNoHP(rs.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(Pelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pelanggan;
    }

    @Override
    public windu.pos.entity.Pelanggan add(windu.pos.entity.Pelanggan pelanggan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(windu.pos.entity.Pelanggan pelanggan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
