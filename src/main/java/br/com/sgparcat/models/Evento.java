/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvento", nullable = false)
    private Integer idEvento;
    
    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @Column(name = "dataInicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Column(name = "horarioInicio", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;
    
    @Column(name = "dataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    
    @Column(name = "horarioFim")
    @Temporal(TemporalType.TIME)
    private Date horarioFim;
    
    @Column(name = "localEvento", nullable = false)
    private String localEvento;
    
    @Lob
    @Column(name = "observacao")
    private String observacao;
    
    @Column(name = "dataRegistro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;
    
    @OneToMany(mappedBy = "evento")
    private List<Lancamento> lancamentos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Participante> participantes;

    public Evento() {
    }

    public Evento(Integer idEvento, String titulo, Date dataInicio, Date horarioInicio, String localEvento, Date dataRegistro) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.horarioInicio = horarioInicio;
        this.localEvento = localEvento;
        this.dataRegistro = dataRegistro;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(Date horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idEvento);
        hash = 89 * hash + Objects.hashCode(this.titulo);
        hash = 89 * hash + Objects.hashCode(this.dataInicio);
        hash = 89 * hash + Objects.hashCode(this.horarioInicio);
        hash = 89 * hash + Objects.hashCode(this.dataFim);
        hash = 89 * hash + Objects.hashCode(this.horarioFim);
        hash = 89 * hash + Objects.hashCode(this.localEvento);
        hash = 89 * hash + Objects.hashCode(this.observacao);
        hash = 89 * hash + Objects.hashCode(this.dataRegistro);
        hash = 89 * hash + Objects.hashCode(this.participantes);
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
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.localEvento, other.localEvento)) {
            return false;
        }
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        if (!Objects.equals(this.idEvento, other.idEvento)) {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        if (!Objects.equals(this.horarioInicio, other.horarioInicio)) {
            return false;
        }
        if (!Objects.equals(this.dataFim, other.dataFim)) {
            return false;
        }
        if (!Objects.equals(this.horarioFim, other.horarioFim)) {
            return false;
        }
        if (!Objects.equals(this.dataRegistro, other.dataRegistro)) {
            return false;
        }
        if (!Objects.equals(this.participantes, other.participantes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evento{" + "idEvento=" + idEvento + ", titulo=" + titulo + ", dataInicio=" + dataInicio + ", horarioInicio=" + horarioInicio + ", dataFim=" + dataFim + ", horarioFim=" + horarioFim + ", localEvento=" + localEvento + ", observacao=" + observacao + ", dataRegistro=" + dataRegistro + '}';
    }
}
