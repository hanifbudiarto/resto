package company.pos.admin;

import company.pos.database.MysqlConnect;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@Table(name = "pelayan", catalog = "resto", schema = "")
@NamedQueries({
    @NamedQuery(name = "Pelayan.findAll", query = "SELECT p FROM Pelayan p"),
    @NamedQuery(name = "Pelayan.findByPelayanId", query = "SELECT p FROM Pelayan p WHERE p.pelayanId = :pelayanId"),
    @NamedQuery(name = "Pelayan.findByNama", query = "SELECT p FROM Pelayan p WHERE p.nama = :nama"),
    @NamedQuery(name = "Pelayan.findByPanggilan", query = "SELECT p FROM Pelayan p WHERE p.panggilan = :panggilan")})
public class Pelayan implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pelayan_id")
    private Integer pelayanId;
    @Column(name = "nama")
    private String nama;
    @Column(name = "panggilan")
    private String panggilan;

    public Pelayan() {
    }

    public Pelayan(Integer pelayanId) {
        this.pelayanId = pelayanId;
    }

    public Integer getPelayanId() {
        return pelayanId;
    }

    public void setPelayanId(Integer pelayanId) {
        Integer oldPelayanId = this.pelayanId;
        this.pelayanId = pelayanId;
        changeSupport.firePropertyChange("pelayanId", oldPelayanId, pelayanId);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    public String getPanggilan() {
        return panggilan;
    }

    public void setPanggilan(String panggilan) {
        String oldPanggilan = this.panggilan;
        this.panggilan = panggilan;
        changeSupport.firePropertyChange("panggilan", oldPanggilan, panggilan);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pelayanId != null ? pelayanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelayan)) {
            return false;
        }
        Pelayan other = (Pelayan) object;
        if ((this.pelayanId == null && other.pelayanId != null) || (this.pelayanId != null && !this.pelayanId.equals(other.pelayanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "company.pos.admin.Pelayan[ pelayanId=" + pelayanId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
    public ResultSet getAllPelayan () {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            String query = "SELECT m.nama as Nama, m.panggilan as Panggilan FROM pelayan m";
            try {
                ResultSet rset = conn.query(query, null);
                return rset;
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}
