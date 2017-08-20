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
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFuncao", nullable = false)
    private Integer idFuncao;
    
    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcao")
    private List<Pessoa> pessoas;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcao")
    private List<Membro> membros;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcao")
    private List<Participante> participantes;

    public Funcao() {
    }

    public Funcao(Integer idFuncao, String titulo) {
        this.idFuncao = idFuncao;
        this.titulo = titulo;
    }

    public Integer getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(Integer idFuncao) {
        this.idFuncao = idFuncao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncao != null ? idFuncao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcao)) {
            return false;
        }
        Funcao other = (Funcao) object;
        if ((this.idFuncao == null && other.idFuncao != null) || (this.idFuncao != null && !this.idFuncao.equals(other.idFuncao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgparcat.models.Funcao[ idFuncao=" + idFuncao + " ]";
    }
}
