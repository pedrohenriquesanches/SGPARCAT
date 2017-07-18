/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.models;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "membro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membro.findAll", query = "SELECT m FROM Membro m")
    , @NamedQuery(name = "Membro.findByIdPessoa", query = "SELECT m FROM Membro m WHERE m.membroPK.idPessoa = :idPessoa")
    , @NamedQuery(name = "Membro.findByIdOrganismo", query = "SELECT m FROM Membro m WHERE m.membroPK.idOrganismo = :idOrganismo")})
public class Membro implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MembroPK membroPK;
    @JoinColumn(name = "idfuncao", referencedColumnName = "idfuncao")
    @ManyToOne(optional = false)
    private Funcao idfuncao;
    @JoinColumn(name = "idOrganismo", referencedColumnName = "idOrganismo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Organismo organismo;
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public Membro() {
    }

    public Membro(MembroPK membroPK) {
        this.membroPK = membroPK;
    }

    public Membro(long idPessoa, int idOrganismo) {
        this.membroPK = new MembroPK(idPessoa, idOrganismo);
    }

    public MembroPK getMembroPK() {
        return membroPK;
    }

    public void setMembroPK(MembroPK membroPK) {
        this.membroPK = membroPK;
    }

    public Funcao getIdfuncao() {
        return idfuncao;
    }

    public void setIdfuncao(Funcao idfuncao) {
        this.idfuncao = idfuncao;
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (membroPK != null ? membroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membro)) {
            return false;
        }
        Membro other = (Membro) object;
        if ((this.membroPK == null && other.membroPK != null) || (this.membroPK != null && !this.membroPK.equals(other.membroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.Membro[ membroPK=" + membroPK + " ]";
    }
    
}
