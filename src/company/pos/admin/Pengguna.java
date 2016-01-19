package company.pos.admin;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
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
@Table(name = "pengguna", catalog = "resto", schema = "")
@NamedQueries({
    @NamedQuery(name = "Pengguna.findAll", query = "SELECT p FROM Pengguna p"),
    @NamedQuery(name = "Pengguna.findByPenggunaId", query = "SELECT p FROM Pengguna p WHERE p.penggunaId = :penggunaId"),
    @NamedQuery(name = "Pengguna.findByNama", query = "SELECT p FROM Pengguna p WHERE p.nama = :nama"),
    @NamedQuery(name = "Pengguna.findByUsername", query = "SELECT p FROM Pengguna p WHERE p.username = :username"),
    @NamedQuery(name = "Pengguna.findByPassword", query = "SELECT p FROM Pengguna p WHERE p.password = :password"),
    @NamedQuery(name = "Pengguna.findByKategori", query = "SELECT p FROM Pengguna p WHERE p.kategori = :kategori")})
public class Pengguna implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pengguna_id")
    private Integer penggunaId;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "kategori")
    private String kategori;

    public Pengguna() {
    }

    public Pengguna(Integer penggunaId) {
        this.penggunaId = penggunaId;
    }

    public Pengguna(Integer penggunaId, String nama, String username, String password, String kategori) {
        this.penggunaId = penggunaId;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.kategori = kategori;
    }

    public Integer getPenggunaId() {
        return penggunaId;
    }

    public void setPenggunaId(Integer penggunaId) {
        Integer oldPenggunaId = this.penggunaId;
        this.penggunaId = penggunaId;
        changeSupport.firePropertyChange("penggunaId", oldPenggunaId, penggunaId);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        changeSupport.firePropertyChange("username", oldUsername, username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        String oldKategori = this.kategori;
        this.kategori = kategori;
        changeSupport.firePropertyChange("kategori", oldKategori, kategori);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (penggunaId != null ? penggunaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pengguna)) {
            return false;
        }
        Pengguna other = (Pengguna) object;
        if ((this.penggunaId == null && other.penggunaId != null) || (this.penggunaId != null && !this.penggunaId.equals(other.penggunaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "company.pos.user.Pengguna[ penggunaId=" + penggunaId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
