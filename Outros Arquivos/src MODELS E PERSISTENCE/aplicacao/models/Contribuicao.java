/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "contribuicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contribuicao.findAll", query = "SELECT c FROM Contribuicao c")
    , @NamedQuery(name = "Contribuicao.findByNumeroContribuicao", query = "SELECT c FROM Contribuicao c WHERE c.numeroContribuicao = :numeroContribuicao")
    , @NamedQuery(name = "Contribuicao.findByTipoContribuicao", query = "SELECT c FROM Contribuicao c WHERE c.tipoContribuicao = :tipoContribuicao")
    , @NamedQuery(name = "Contribuicao.findByDescricao", query = "SELECT c FROM Contribuicao c WHERE c.descricao = :descricao")
    , @NamedQuery(name = "Contribuicao.findByValor", query = "SELECT c FROM Contribuicao c WHERE c.valor = :valor")
    , @NamedQuery(name = "Contribuicao.findByDataRegistro", query = "SELECT c FROM Contribuicao c WHERE c.dataRegistro = :dataRegistro")
    , @NamedQuery(name = "Contribuicao.findByHorarioRegistro", query = "SELECT c FROM Contribuicao c WHERE c.horarioRegistro = :horarioRegistro")
    , @NamedQuery(name = "Contribuicao.findByObservacao", query = "SELECT c FROM Contribuicao c WHERE c.observacao = :observacao")})
public class Contribuicao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numeroContribuicao")
    private Long numeroContribuicao;
    @Basic(optional = false)
    @Column(name = "tipoContribuicao")
    private String tipoContribuicao;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "dataRegistro")
    @Temporal(TemporalType.DATE)
    private Date dataRegistro;
    @Basic(optional = false)
    @Column(name = "horarioRegistro")
    @Temporal(TemporalType.TIME)
    private Date horarioRegistro;
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "idPessoaContribuinte", referencedColumnName = "idPessoa")
    @ManyToOne
    private Pessoa idPessoaContribuinte;
    @JoinColumn(name = "idPessoaRegistrouContribuicao", referencedColumnName = "idPessoa")
    @ManyToOne(optional = false)
    private Pessoa idPessoaRegistrouContribuicao;

    public Contribuicao() {
    }

    public Contribuicao(Long numeroContribuicao) {
        this.numeroContribuicao = numeroContribuicao;
    }

    public Contribuicao(Long numeroContribuicao, String tipoContribuicao, String descricao, Date dataRegistro, Date horarioRegistro) {
        this.numeroContribuicao = numeroContribuicao;
        this.tipoContribuicao = tipoContribuicao;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.horarioRegistro = horarioRegistro;
    }

    public Long getNumeroContribuicao() {
        return numeroContribuicao;
    }

    public void setNumeroContribuicao(Long numeroContribuicao) {
        this.numeroContribuicao = numeroContribuicao;
    }

    public String getTipoContribuicao() {
        return tipoContribuicao;
    }

    public void setTipoContribuicao(String tipoContribuicao) {
        this.tipoContribuicao = tipoContribuicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pessoa getIdPessoaContribuinte() {
        return idPessoaContribuinte;
    }

    public void setIdPessoaContribuinte(Pessoa idPessoaContribuinte) {
        this.idPessoaContribuinte = idPessoaContribuinte;
    }

    public Pessoa getIdPessoaRegistrouContribuicao() {
        return idPessoaRegistrouContribuicao;
    }

    public void setIdPessoaRegistrouContribuicao(Pessoa idPessoaRegistrouContribuicao) {
        this.idPessoaRegistrouContribuicao = idPessoaRegistrouContribuicao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroContribuicao != null ? numeroContribuicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contribuicao)) {
            return false;
        }
        Contribuicao other = (Contribuicao) object;
        if ((this.numeroContribuicao == null && other.numeroContribuicao != null) || (this.numeroContribuicao != null && !this.numeroContribuicao.equals(other.numeroContribuicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.Contribuicao[ numeroContribuicao=" + numeroContribuicao + " ]";
    }
    
}
