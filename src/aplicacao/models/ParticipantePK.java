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
public class ParticipantePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idPessoa")
    private long idPessoa;
    @Basic(optional = false)
    @Column(name = "idEvento")
    private int idEvento;

    public ParticipantePK() {
    }

    public ParticipantePK(long idPessoa, int idEvento) {
        this.idPessoa = idPessoa;
        this.idEvento = idEvento;
    }

    public long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPessoa;
        hash += (int) idEvento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipantePK)) {
            return false;
        }
        ParticipantePK other = (ParticipantePK) object;
        if (this.idPessoa != other.idPessoa) {
            return false;
        }
        if (this.idEvento != other.idEvento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.ParticipantePK[ idPessoa=" + idPessoa + ", idEvento=" + idEvento + " ]";
    }
    
}
