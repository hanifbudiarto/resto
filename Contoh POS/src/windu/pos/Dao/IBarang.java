/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.Dao;

import java.util.List;
import windu.pos.entity.Barang;

/**
 *
 * @author WinduPurnomo
 */
public interface IBarang {
    public List<Barang> selectAll();
    public Barang selectById(int id);
    public int add(Barang barang);
    public void update(Barang barang);
    public void delete(int id);
    public List<Barang> selectByPartNama(String nama);
}
