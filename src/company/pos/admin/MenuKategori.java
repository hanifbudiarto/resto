/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.admin;

import company.pos.database.MysqlConnect;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Muhammad Hanif B
 */
@Entity
@Table(name = "menu_kategori", catalog = "resto", schema = "")
@NamedQueries({
    @NamedQuery(name = "MenuKategori.findAll", query = "SELECT m FROM MenuKategori m"),
    @NamedQuery(name = "MenuKategori.findByKategoriId", query = "SELECT m FROM MenuKategori m WHERE m.kategoriId = :kategoriId"),
    @NamedQuery(name = "MenuKategori.findByNama", query = "SELECT m FROM MenuKategori m WHERE m.nama = :nama")})
public class MenuKategori implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kategori_id")
    private Integer kategoriId;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;

    public MenuKategori() {
    }

    public MenuKategori(Integer kategoriId) {
        this.kategoriId = kategoriId;
    }

    public MenuKategori(Integer kategoriId, String nama) {
        this.kategoriId = kategoriId;
        this.nama = nama;
    }

    public Integer getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Integer kategoriId) {
        Integer oldKategoriId = this.kategoriId;
        this.kategoriId = kategoriId;
        changeSupport.firePropertyChange("kategoriId", oldKategoriId, kategoriId);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kategoriId != null ? kategoriId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuKategori)) {
            return false;
        }
        MenuKategori other = (MenuKategori) object;
        if ((this.kategoriId == null && other.kategoriId != null) || (this.kategoriId != null && !this.kategoriId.equals(other.kategoriId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "company.pos.menu.MenuKategori[ kategoriId=" + kategoriId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
    
    
    // by Hanif Budiarto
    public List<String> getAllCategory () {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            try {
                String query = "SELECT nama FROM menu_kategori";
                
                ResultSet rs = conn.query(query);
                List<String> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rs.getString("nama"));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(MenuKategori.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        return null;
    }
}
