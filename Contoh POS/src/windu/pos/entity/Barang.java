/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.entity;

/**
 *
 * @author WinduPurnomo
 */
public class Barang {
    private int id;
    private String nama;
    private int jumlah;
    private double hargaBeli;
    private double hargaJual;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(double hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public double getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(double hargaJual) {
        this.hargaJual = hargaJual;
    }    
    
}
