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
    private Long idLancamento;
    
    @Column(nullable = false)
    private Long numeroLancamento;
    
    @Column(nullable = false)
    private byte isDespesa;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Column(name = "valorJuros", precision = 10, scale = 2)
    private BigDecimal valorJuros;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    
    @Column(name = "emitente")
    private String emitente;
    
    @Column(nullable = false)
    private byte isParcelado;
    
    @Lob
    @Column
    private String observacao;
    
    @Column(nullable = false)
    private byte isPago;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;
        
    @JoinColumn(nullable = true, name = "idEvento")
    @ManyToOne
    private Evento evento;
    
    @JoinColumn(nullable = true, name = "idPessoaRegistrouLancamento")
    @ManyToOne
    private Pessoa pessoaRegistrouLancamento;

    public Lancamento() {
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

    public byte getIsDespesa() {
        return isDespesa;
    }

    public void setIsDespesa(byte isDespesa) {
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

    public byte getIsParcelado() {
        return isParcelado;
    }

    public void setIsParcelado(byte isParcelado) {
        this.isParcelado = isParcelado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public byte getIsPago() {
        return isPago;
    }

    public void setIsPago(byte isPago) {
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
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.idLancamento);
        hash = 17 * hash + Objects.hashCode(this.numeroLancamento);
        hash = 17 * hash + this.isDespesa;
        hash = 17 * hash + Objects.hashCode(this.descricao);
        hash = 17 * hash + Objects.hashCode(this.valor);
        hash = 17 * hash + Objects.hashCode(this.dataVencimento);
        hash = 17 * hash + this.isParcelado;
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
        if (!Objects.equals(this.dataVencimento, other.dataVencimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lancamento{" + "idLancamento=" + idLancamento + ", numeroLancamento=" + numeroLancamento + ", isDespesa=" + isDespesa + ", descricao=" + descricao + ", valor=" + valor + ", isParcelado=" + isParcelado + ", isPago=" + isPago + ", dataRegistro=" + dataRegistro + '}';
    }

}