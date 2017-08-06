/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

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
    
    //@Max(value=?)
    //@Min(value=?)
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

    public Contribuicao(Long numeroContribuicao, String tipoContribuicao, String descricao, Date dataRegistro, Date horarioRegistro, Pessoa idPessoaRegistrouContribuicao) {
        this.numeroContribuicao = numeroContribuicao;
        this.tipoContribuicao = tipoContribuicao;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.horarioRegistro = horarioRegistro;
        this.idPessoaRegistrouContribuicao = idPessoaRegistrouContribuicao;
    }
    
    
    //GETTES AND SETTERS
    
    
    //Métodos sobrescritos do java.lang.Object
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
