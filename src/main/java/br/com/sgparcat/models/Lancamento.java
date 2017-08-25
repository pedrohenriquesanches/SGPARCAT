/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "lancamento")
public class Lancamento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLancamento", nullable = false)
    private Long idLancamento;
    
    @Column(name = "numeroLancamento", nullable = false)
    private Long numeroLancamento;
    
    @Column(name = "isDespesa", nullable = false)
    private boolean isDespesa;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Column(name = "valorJuros", precision = 10, scale = 2)
    private BigDecimal valorJuros;
    
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    
    @Column(name = "emitente")
    private String emitente;
    
    @Column(name = "isParcelado", nullable = false)
    private boolean isParcelado;
    
    @Lob
    @Column(name = "observacao")
    private String observacao;
    
    @Column(name = "isPago", nullable = false)
    private boolean isPago;
    
    @Column(name = "dataRegistro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;
        
    @JoinColumn(name = "idEvento", referencedColumnName = "idEvento")
    @ManyToOne
    private Evento evento;
    
    @JoinColumn(name = "idPessoaRegistrouLancamento", referencedColumnName = "idPessoa",nullable = true)
    @ManyToOne()
    private Pessoa pessoaRegistrouLancamento;

    public Lancamento() {
    }

    public Lancamento(Long idLancamento, Long numeroLancamento, boolean isDespesa, String descricao, BigDecimal valor, boolean isParcelado, boolean isPago, Date dataRegistro) {
        this.idLancamento = idLancamento;
        this.numeroLancamento = numeroLancamento;
        this.isDespesa = isDespesa;
        this.descricao = descricao;
        this.valor = valor;
        this.isParcelado = isParcelado;
        this.isPago = isPago;
        this.dataRegistro = dataRegistro;
    }

    public Long getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Long idLancamento) {
        this.idLancamento = idLancamento;
    }

    public Long getNumeroLancamento() {
        return numeroLancamento;
    }

    public void setNumeroLancamento(Long numeroLancamento) {
        this.numeroLancamento = numeroLancamento;
    }

    public boolean isIsDespesa() {
        return isDespesa;
    }

    public void setIsDespesa(boolean isDespesa) {
        this.isDespesa = isDespesa;
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

    public BigDecimal getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getEmitente() {
        return emitente;
    }

    public void setEmitente(String emitente) {
        this.emitente = emitente;
    }

    public boolean isIsParcelado() {
        return isParcelado;
    }

    public void setIsParcelado(boolean isParcelado) {
        this.isParcelado = isParcelado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isIsPago() {
        return isPago;
    }

    public void setIsPago(boolean isPago) {
        this.isPago = isPago;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Pessoa getPessoaRegistrouLancamento() {
        return pessoaRegistrouLancamento;
    }

    public void setPessoaRegistrouLancamento(Pessoa pessoaRegistrouLancamento) {
        this.pessoaRegistrouLancamento = pessoaRegistrouLancamento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.idLancamento);
        hash = 19 * hash + Objects.hashCode(this.numeroLancamento);
        hash = 19 * hash + (this.isDespesa ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.descricao);
        hash = 19 * hash + Objects.hashCode(this.valor);
        hash = 19 * hash + (this.isParcelado ? 1 : 0);
        hash = 19 * hash + (this.isPago ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.dataRegistro);
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
        final Lancamento other = (Lancamento) obj;
        if (this.isDespesa != other.isDespesa) {
            return false;
        }
        if (this.isParcelado != other.isParcelado) {
            return false;
        }
        if (this.isPago != other.isPago) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.idLancamento, other.idLancamento)) {
            return false;
        }
        if (!Objects.equals(this.numeroLancamento, other.numeroLancamento)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.dataRegistro, other.dataRegistro)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lancamento{" + "idLancamento=" + idLancamento + ", numeroLancamento=" + numeroLancamento + ", isDespesa=" + isDespesa + ", descricao=" + descricao + ", valor=" + valor + ", isParcelado=" + isParcelado + ", isPago=" + isPago + ", dataRegistro=" + dataRegistro + '}';
    }

}