/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "funcao")
public class Funcao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfuncao")
    private Integer idfuncao;
    
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfuncao")
    private Collection<Pessoa> pessoaCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfuncao")
    private Collection<Membro> membroCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfuncao")
    private Collection<Participante> participanteCollection;

    public Funcao() {
    }

    public Funcao(Integer idfuncao, String titulo) {
        this.idfuncao = idfuncao;
        this.titulo = titulo;
    }
    
    //GETTES AND SETTERS
    
    
    //Métodos sobrescritos do java.lang.Object
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfuncao != null ? idfuncao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcao)) {
            return false;
        }
        Funcao other = (Funcao) object;
        if ((this.idfuncao == null && other.idfuncao != null) || (this.idfuncao != null && !this.idfuncao.equals(other.idfuncao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.Funcao[ idfuncao=" + idfuncao + " ]";
    }
}
