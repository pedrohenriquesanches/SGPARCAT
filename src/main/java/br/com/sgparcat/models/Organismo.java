/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "organismo")
public class Organismo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idOrganismo", nullable = false)
    private Integer idOrganismo;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "tipo", nullable = false)
    private String tipo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organismo")
    private List<Membro> membros;

    public Organismo() {
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
        return "br.com.sgparcat.models.Organismo[ idOrganismo=" + idOrganismo + " ]";
    }
}
