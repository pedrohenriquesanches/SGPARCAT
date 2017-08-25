/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "contribuicao")
public class Contribuicao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public enum TipoContribuicao {
        DIZIMO, OFERTA, DOACAO;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numeroContribuicao", nullable = false)
    private Long numeroContribuicao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoContribuicao", nullable = false)
    private TipoContribuicao tipoContribuicao;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Column(name = "valor", precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Column(name = "dataRegistro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;
    
    @Lob
    @Column(name = "observacao")
    private String observacao;
    
    @JoinColumn(name = "idPessoaContribuinte", referencedColumnName = "idPessoa")
    @ManyToOne
    private Pessoa pessoaContribuinte;
    
    @JoinColumn(name = "idPessoaRegistrouContribuicao", referencedColumnName = "idPessoa")
    @ManyToOne(optional = false)
    private Pessoa pessoaRegistrouContribuicao;

    public Contribuicao() {
    }

    public Contribuicao(Long numeroContribuicao, TipoContribuicao tipoContribuicao, String descricao, Date dataRegistro, Pessoa pessoaRegistrouContribuicao) {
        this.numeroContribuicao = numeroContribuicao;
        this.tipoContribuicao = tipoContribuicao;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.pessoaRegistrouContribuicao = pessoaRegistrouContribuicao;
    }

    public Long getNumeroContribuicao() {
        return numeroContribuicao;
    }

    public void setNumeroContribuicao(Long numeroContribuicao) {
        this.numeroContribuicao = numeroContribuicao;
    }

    public TipoContribuicao getTipoContribuicao() {
        return tipoContribuicao;
    }

    public void setTipoContribuicao(TipoContribuicao tipoContribuicao) {
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pessoa getPessoaContribuinte() {
        return pessoaContribuinte;
    }

    public void setPessoaContribuinte(Pessoa pessoaContribuinte) {
        this.pessoaContribuinte = pessoaContribuinte;
    }

    public Pessoa getPessoaRegistrouContribuicao() {
        return pessoaRegistrouContribuicao;
    }

    public void setPessoaRegistrouContribuicao(Pessoa pessoaRegistrouContribuicao) {
        this.pessoaRegistrouContribuicao = pessoaRegistrouContribuicao;
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
        return "br.com.sgparcat.models.Contribuicao[ numeroContribuicao=" + numeroContribuicao + " ]";
    }
    
}