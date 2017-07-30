/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.models;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "participante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participante.findAll", query = "SELECT p FROM Participante p")
    , @NamedQuery(name = "Participante.findByIdPessoa", query = "SELECT p FROM Participante p WHERE p.participantePK.idPessoa = :idPessoa")
    , @NamedQuery(name = "Participante.findByIdEvento", query = "SELECT p FROM Participante p WHERE p.participantePK.idEvento = :idEvento")})
public class Participante implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipantePK participantePK;
    @JoinColumn(name = "idfuncao", referencedColumnName = "idfuncao")
    @ManyToOne(optional = false)
    private Funcao idfuncao;
    @JoinColumn(name = "idEvento", referencedColumnName = "idEvento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evento evento;
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public Participante() {
    }

    public Participante(ParticipantePK participantePK) {
        this.participantePK = participantePK;
    }

    public Participante(long idPessoa, int idEvento) {
        this.participantePK = new ParticipantePK(idPessoa, idEvento);
    }

    public ParticipantePK getParticipantePK() {
        return participantePK;
    }

    public void setParticipantePK(ParticipantePK participantePK) {
        this.participantePK = participantePK;
    }

    public Funcao getIdfuncao() {
        return idfuncao;
    }

    public void setIdfuncao(Funcao idfuncao) {
        this.idfuncao = idfuncao;
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
        int hash = 0;
        hash += (participantePK != null ? participantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participante)) {
            return false;
        }
        Participante other = (Participante) object;
        if ((this.participantePK == null && other.participantePK != null) || (this.participantePK != null && !this.participantePK.equals(other.participantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.Participante[ participantePK=" + participantePK + " ]";
    }
    
}
