/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
    private Integer idFuncao;
    
    @Column(nullable = false,unique = true)
    private String titulo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcao")
    private List<Pessoa> pessoas;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcao")
    private List<Membro> membros;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcao")
    private List<Participante> participantes;

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

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
    
    public boolean isFuncaoValida(){
        return (idFuncao != null) && (titulo != null);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idFuncao);
        hash = 41 * hash + Objects.hashCode(this.titulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcao other = (Funcao) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.idFuncao, other.idFuncao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funcao{" + "idFuncao=" + idFuncao + ", titulo=" + titulo + '}';
    }
    
}
