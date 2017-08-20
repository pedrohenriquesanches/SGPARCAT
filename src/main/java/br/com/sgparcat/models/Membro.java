/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import javax.persistence.Entity;
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
    
    @JoinColumn(name = "idFuncao", referencedColumnName = "idFuncao")
    @ManyToOne(optional = false)
    private Funcao funcao;
    
    @JoinColumn(name = "idOrganismo", referencedColumnName = "idOrganismo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Organismo organismo;
    
    @Id
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public Membro() {
    }

    public Membro(Funcao funcao, Organismo organismo, Pessoa pessoa) {
        this.funcao = funcao;
        this.organismo = organismo;
        this.pessoa = pessoa;
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

}
