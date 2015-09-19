/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.Dao;

import java.util.List;
import windu.pos.entity.Penjualan;


/**
 *
 * @author WinduPurnomo
 */
public interface IPenjualan {
    public List<Penjualan> selectAll();
    public Penjualan selectById(int id);
    public int add(Penjualan penjualan);
    public void update(Penjualan penjualan);
    public void delete(int id);
}
