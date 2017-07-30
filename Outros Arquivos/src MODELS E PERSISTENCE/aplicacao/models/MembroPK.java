/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author pedrohensanches
 */
@Embeddable
public class MembroPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idPessoa")
    private long idPessoa;
    @Basic(optional = false)
    @Column(name = "idOrganismo")
    private int idOrganismo;

    public MembroPK() {
    }

    public MembroPK(long idPessoa, int idOrganismo) {
        this.idPessoa = idPessoa;
        this.idOrganismo = idOrganismo;
    }

    public long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(int idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPessoa;
        hash += (int) idOrganismo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MembroPK)) {
            return false;
        }
        MembroPK other = (MembroPK) object;
        if (this.idPessoa != other.idPessoa) {
            return false;
        }
        if (this.idOrganismo != other.idOrganismo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.MembroPK[ idPessoa=" + idPessoa + ", idOrganismo=" + idOrganismo + " ]";
    }
    
}
