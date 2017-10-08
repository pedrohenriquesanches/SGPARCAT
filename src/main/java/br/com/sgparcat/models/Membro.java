/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "membro")
public class Membro implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMembro;
    
    @JoinColumn(nullable = false, name = "idFuncao")
    @ManyToOne
    private Funcao funcao;
    
    @JoinColumn(nullable = false, name = "idOrganismo") //unique=true
    @ManyToOne
    private Organismo organismo;
    
    @JoinColumn(nullable = false, name = "idPessoa") //unique=true
    @ManyToOne
    private Pessoa pessoa;

    public Membro() {
    }

    public Membro(Long idMembro, Funcao funcao, Organismo organismo, Pessoa pessoa) {
        this.idMembro = idMembro;
        this.funcao = funcao;
        this.organismo = organismo;
        this.pessoa = pessoa;
    }

    public Long getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(Long idMembro) {
        this.idMembro = idMembro;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
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
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idMembro);
        hash = 97 * hash + Objects.hashCode(this.funcao);
        hash = 97 * hash + Objects.hashCode(this.organismo);
        hash = 97 * hash + Objects.hashCode(this.pessoa);
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
        final Membro other = (Membro) obj;
        if (!Objects.equals(this.idMembro, other.idMembro)) {
            return false;
        }
        if (!Objects.equals(this.funcao, other.funcao)) {
            return false;
        }
        if (!Objects.equals(this.organismo, other.organismo)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Membro{" + "idMembro=" + idMembro + ", funcao=" + funcao + ", organismo=" + organismo + ", pessoa=" + pessoa + '}';
    }
}
