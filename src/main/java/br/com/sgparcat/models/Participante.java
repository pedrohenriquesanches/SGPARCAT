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
@Table(name = "participante")
public class Participante implements Serializable {
    
    @JoinColumn(name = "idFuncao", referencedColumnName = "idFuncao")
    @ManyToOne(optional = false)
    private Funcao idFuncao;
    
    @JoinColumn(name = "idEvento", referencedColumnName = "idEvento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evento evento;
    
    @Id //??
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public Participante() {
    }

    public Participante(Funcao idFuncao, Evento evento, Pessoa pessoa) {
        this.idFuncao = idFuncao;
        this.evento = evento;
        this.pessoa = pessoa;
    }
    
    //GETTES AND SETTERS
    
    
    //Métodos sobrescritos do java.lang.Object
    
}
