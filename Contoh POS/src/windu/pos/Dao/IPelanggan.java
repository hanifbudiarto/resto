/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.Dao;

import java.util.List;
import windu.pos.entity.Pelanggan;

/**
 *
 * @author WinduPurnomo
 */
public interface IPelanggan {
    public List<Pelanggan> selectAll();
    public Pelanggan selectById(int id);
    public Pelanggan add(Pelanggan pelanggan);
    public void update(Pelanggan pelanggan);
    public void delete(int id);
}
