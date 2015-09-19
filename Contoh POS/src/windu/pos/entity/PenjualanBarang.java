/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.entity;

/**
 *
 * @author WinduPurnomo
 */
public class PenjualanBarang {
    private int idPenjBrg;
    private Penjualan penjualan;
    private Barang barang;
    private int jumlah;
    private double diskon;//diskon adalah nilai dalam rupiah

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getIdPenjBrg() {
        return idPenjBrg;
    }

    public void setIdPenjBrg(int idPenjBrg) {
        this.idPenjBrg = idPenjBrg;
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }
    
}
