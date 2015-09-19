/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.Dao;

import java.util.List;
import windu.pos.entity.PenjualanBarang;

/**
 *
 * @author WinduPurnomo
 */
public interface IPenjualanBarang{
    public List<PenjualanBarang> selectAll();
    public PenjualanBarang selectById(int id);
    public int add(PenjualanBarang penjualanBarang);
    public void addByBatch (List<PenjualanBarang> listPenjBrg);
    public void update(PenjualanBarang penjualanBarang);
    public void delete(int id);
}
