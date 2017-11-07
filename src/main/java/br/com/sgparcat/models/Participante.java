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
@Table(name = "participante")
public class Participante implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipante;
    
    @JoinColumn(nullable = false, name = "idFuncao")
    @ManyToOne
    private Funcao funcao;
    
    @JoinColumn(nullable = false, name = "idEvento")
    @ManyToOne
    private Evento evento;

    @JoinColumn(nullable = false, name = "idPessoa")
    @ManyToOne
    private Pessoa pessoa;

    public Participante() {
    }

    public Participante(Long idParticipante, Funcao funcao, Evento evento, Pessoa pessoa) {
        this.idParticipante = idParticipante;
        this.funcao = funcao;
        this.evento = evento;
        this.pessoa = pessoa;
    }

    public Long getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Long idParticipante) {
        this.idParticipante = idParticipante;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.idParticipante);
        hash = 11 * hash + Objects.hashCode(this.funcao);
        hash = 11 * hash + Objects.hashCode(this.evento);
        hash = 11 * hash + Objects.hashCode(this.pessoa);
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
        final Participante other = (Participante) obj;
        if (!Objects.equals(this.idParticipante, other.idParticipante)) {
            return false;
        }
        if (!Objects.equals(this.funcao, other.funcao)) {
            return false;
        }
        if (!Objects.equals(this.evento, other.evento)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Participante{" + "idParticipante=" + idParticipante + ", funcao=" + funcao + ", evento=" + evento + ", pessoa=" + pessoa + '}';
    }
    
}
