/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "evento")
public class Evento implements Serializable {
    
    @Id
    @Column(name = "idEvento", nullable = false)
    private Integer idEvento;
    
    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @Column(name = "dataEvento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEvento;
    
    @Column(name = "horarioInicio", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;
    
    @Column(name = "horarioFim")
    @Temporal(TemporalType.TIME)
    private Date horarioFim;
    
    @Column(name = "localEvento", nullable = false)
    private String local;
    
    @Lob
    @Column(name = "observacao")
    private String observacao;
    
    @Column(name = "dataRegistro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataRegistro;
    
    @Column(name = "horarioRegistro", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioRegistro;
    
    @OneToMany(mappedBy = "idEvento")
    private Collection<Lancamento> lancamentoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private Collection<Participante> participanteCollection;

    public Evento() {
    }

    public Evento(Integer idEvento, String titulo, Date dataEvento, Date horarioInicio, String local, Date dataRegistro, Date horarioRegistro) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.dataEvento = dataEvento;
        this.horarioInicio = horarioInicio;
        this.local = local;
        this.dataRegistro = dataRegistro;
        this.horarioRegistro = horarioRegistro;
    }
    
        
    //GETTES AND SETTERS
    
    
    //Métodos sobrescritos do java.lang.Object
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.Evento[ idEvento=" + idEvento + " ]";
    }
}
