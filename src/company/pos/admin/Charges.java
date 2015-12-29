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
@Table(name = "charges", catalog = "resto", schema = "")
@NamedQueries({
    @NamedQuery(name = "Charges.findAll", query = "SELECT c FROM Charges c"),
    @NamedQuery(name = "Charges.findByChargesId", query = "SELECT c FROM Charges c WHERE c.chargesId = :chargesId"),
    @NamedQuery(name = "Charges.findByNama", query = "SELECT c FROM Charges c WHERE c.nama = :nama"),
    @NamedQuery(name = "Charges.findByBiaya", query = "SELECT c FROM Charges c WHERE c.biaya = :biaya")})
public class Charges implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "charges_id")
    private Integer chargesId;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Column(name = "biaya")
    private String biaya;

    public Charges() {
    }

    public Charges(Integer chargesId) {
        this.chargesId = chargesId;
    }

    public Charges(Integer chargesId, String nama) {
        this.chargesId = chargesId;
        this.nama = nama;
    }

    public Integer getChargesId() {
        return chargesId;
    }

    public void setChargesId(Integer chargesId) {
        Integer oldChargesId = this.chargesId;
        this.chargesId = chargesId;
        changeSupport.firePropertyChange("chargesId", oldChargesId, chargesId);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        String oldBiaya = this.biaya;
        this.biaya = biaya;
        changeSupport.firePropertyChange("biaya", oldBiaya, biaya);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chargesId != null ? chargesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Charges)) {
            return false;
        }
        Charges other = (Charges) object;
        if ((this.chargesId == null && other.chargesId != null) || (this.chargesId != null && !this.chargesId.equals(other.chargesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "company.pos.admin.Charges[ chargesId=" + chargesId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
    
        
    public ResultSet getAllCharges () {
        MysqlConnect conn = MysqlConnect.getDbCon();
        if (conn != null) {
            String query = "SELECT * FROM charges";
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
