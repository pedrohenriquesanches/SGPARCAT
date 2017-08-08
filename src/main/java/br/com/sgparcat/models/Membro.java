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
    
    @JoinColumn(name = "idfuncao", referencedColumnName = "idfuncao")
    @ManyToOne(optional = false)
    private Funcao idFuncao;
    
    @JoinColumn(name = "idOrganismo", referencedColumnName = "idOrganismo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Organismo organismo;
    
    @Id // ??
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public Membro() {
    }

    public Membro(Funcao idFuncao, Organismo organismo, Pessoa pessoa) {
        this.idFuncao = idFuncao;
        this.organismo = organismo;
        this.pessoa = pessoa;
    }
    
    //GETTES AND SETTERS
    
    
    //Métodos sobrescritos do java.lang.Object
    
}
