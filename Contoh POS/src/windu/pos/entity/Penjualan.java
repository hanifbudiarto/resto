/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.entity;

import java.util.Date;

/**
 *
 * @author WinduPurnomo
 */
public class Penjualan {
    private int idPenjualan;
    private Date tanggal;
    private Pelanggan pelanggan;

    public int getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(int idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
