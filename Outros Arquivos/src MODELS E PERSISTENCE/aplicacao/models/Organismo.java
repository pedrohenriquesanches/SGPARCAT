/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "organismo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organismo.findAll", query = "SELECT o FROM Organismo o")
    , @NamedQuery(name = "Organismo.findByIdOrganismo", query = "SELECT o FROM Organismo o WHERE o.idOrganismo = :idOrganismo")
    , @NamedQuery(name = "Organismo.findByNome", query = "SELECT o FROM Organismo o WHERE o.nome = :nome")
    , @NamedQuery(name = "Organismo.findByTipo", query = "SELECT o FROM Organismo o WHERE o.tipo = :tipo")})
public class Organismo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idOrganismo")
    private Integer idOrganismo;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organismo")
    private Collection<Membro> membroCollection;

    public Organismo() {
    }

    public Organismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public Organismo(Integer idOrganismo, String nome, String tipo) {
        this.idOrganismo = idOrganismo;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Integer getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Membro> getMembroCollection() {
        return membroCollection;
    }

    public void setMembroCollection(Collection<Membro> membroCollection) {
        this.membroCollection = membroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrganismo != null ? idOrganismo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organismo)) {
            return false;
        }
        Organismo other = (Organismo) object;
        if ((this.idOrganismo == null && other.idOrganismo != null) || (this.idOrganismo != null && !this.idOrganismo.equals(other.idOrganismo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.Organismo[ idOrganismo=" + idOrganismo + " ]";
    }
    
}
