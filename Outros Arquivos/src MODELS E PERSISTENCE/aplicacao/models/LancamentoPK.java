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
public class LancamentoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "numeroLancamento")
    private long numeroLancamento;
    @Basic(optional = false)
    @Column(name = "idPessoaRegistrouLancamento")
    private long idPessoaRegistrouLancamento;

    public LancamentoPK() {
    }

    public LancamentoPK(long numeroLancamento, long idPessoaRegistrouLancamento) {
        this.numeroLancamento = numeroLancamento;
        this.idPessoaRegistrouLancamento = idPessoaRegistrouLancamento;
    }

    public long getNumeroLancamento() {
        return numeroLancamento;
    }

    public void setNumeroLancamento(long numeroLancamento) {
        this.numeroLancamento = numeroLancamento;
    }

    public long getIdPessoaRegistrouLancamento() {
        return idPessoaRegistrouLancamento;
    }

    public void setIdPessoaRegistrouLancamento(long idPessoaRegistrouLancamento) {
        this.idPessoaRegistrouLancamento = idPessoaRegistrouLancamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numeroLancamento;
        hash += (int) idPessoaRegistrouLancamento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LancamentoPK)) {
            return false;
        }
        LancamentoPK other = (LancamentoPK) object;
        if (this.numeroLancamento != other.numeroLancamento) {
            return false;
        }
        if (this.idPessoaRegistrouLancamento != other.idPessoaRegistrouLancamento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.LancamentoPK[ numeroLancamento=" + numeroLancamento + ", idPessoaRegistrouLancamento=" + idPessoaRegistrouLancamento + " ]";
    }
    
}
