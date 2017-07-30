/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
    , @NamedQuery(name = "Evento.findByIdEvento", query = "SELECT e FROM Evento e WHERE e.idEvento = :idEvento")
    , @NamedQuery(name = "Evento.findByTitulo", query = "SELECT e FROM Evento e WHERE e.titulo = :titulo")
    , @NamedQuery(name = "Evento.findByDataEvento", query = "SELECT e FROM Evento e WHERE e.dataEvento = :dataEvento")
    , @NamedQuery(name = "Evento.findByHorarioInicio", query = "SELECT e FROM Evento e WHERE e.horarioInicio = :horarioInicio")
    , @NamedQuery(name = "Evento.findByHorarioFim", query = "SELECT e FROM Evento e WHERE e.horarioFim = :horarioFim")
    , @NamedQuery(name = "Evento.findByLocal", query = "SELECT e FROM Evento e WHERE e.local = :local")
    , @NamedQuery(name = "Evento.findByDataRegistro", query = "SELECT e FROM Evento e WHERE e.dataRegistro = :dataRegistro")
    , @NamedQuery(name = "Evento.findByHorarioRegistro", query = "SELECT e FROM Evento e WHERE e.horarioRegistro = :horarioRegistro")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEvento")
    private Integer idEvento;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "dataEvento")
    @Temporal(TemporalType.DATE)
    private Date dataEvento;
    @Basic(optional = false)
    @Column(name = "horarioInicio")
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;
    @Column(name = "horarioFim")
    @Temporal(TemporalType.TIME)
    private Date horarioFim;
    @Basic(optional = false)
    @Column(name = "local")
    private String local;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @Column(name = "dataRegistro")
    @Temporal(TemporalType.DATE)
    private Date dataRegistro;
    @Basic(optional = false)
    @Column(name = "horarioRegistro")
    @Temporal(TemporalType.TIME)
    private Date horarioRegistro;
    @OneToMany(mappedBy = "idEvento")
    private Collection<Lancamento> lancamentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private Collection<Participante> participanteCollection;

    public Evento() {
    }

    public Evento(Integer idEvento) {
        this.idEvento = idEvento;
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

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(Date horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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

    public Date getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(Date horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }

    @XmlTransient
    public Collection<Lancamento> getLancamentoCollection() {
        return lancamentoCollection;
    }

    public void setLancamentoCollection(Collection<Lancamento> lancamentoCollection) {
        this.lancamentoCollection = lancamentoCollection;
    }

    @XmlTransient
    public Collection<Participante> getParticipanteCollection() {
        return participanteCollection;
    }

    public void setParticipanteCollection(Collection<Participante> participanteCollection) {
        this.participanteCollection = participanteCollection;
    }

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
