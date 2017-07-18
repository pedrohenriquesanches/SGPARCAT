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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "lancamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lancamento.findAll", query = "SELECT l FROM Lancamento l")
    , @NamedQuery(name = "Lancamento.findByNumeroLancamento", query = "SELECT l FROM Lancamento l WHERE l.lancamentoPK.numeroLancamento = :numeroLancamento")
    , @NamedQuery(name = "Lancamento.findByIdPessoaRegistrouLancamento", query = "SELECT l FROM Lancamento l WHERE l.lancamentoPK.idPessoaRegistrouLancamento = :idPessoaRegistrouLancamento")
    , @NamedQuery(name = "Lancamento.findByIsDespesa", query = "SELECT l FROM Lancamento l WHERE l.isDespesa = :isDespesa")
    , @NamedQuery(name = "Lancamento.findByDescricao", query = "SELECT l FROM Lancamento l WHERE l.descricao = :descricao")
    , @NamedQuery(name = "Lancamento.findByValor", query = "SELECT l FROM Lancamento l WHERE l.valor = :valor")
    , @NamedQuery(name = "Lancamento.findByValorJuros", query = "SELECT l FROM Lancamento l WHERE l.valorJuros = :valorJuros")
    , @NamedQuery(name = "Lancamento.findByDataVencimento", query = "SELECT l FROM Lancamento l WHERE l.dataVencimento = :dataVencimento")
    , @NamedQuery(name = "Lancamento.findByDataPagamento", query = "SELECT l FROM Lancamento l WHERE l.dataPagamento = :dataPagamento")
    , @NamedQuery(name = "Lancamento.findByEmitente", query = "SELECT l FROM Lancamento l WHERE l.emitente = :emitente")
    , @NamedQuery(name = "Lancamento.findByIsParcelado", query = "SELECT l FROM Lancamento l WHERE l.isParcelado = :isParcelado")
    , @NamedQuery(name = "Lancamento.findByIsPago", query = "SELECT l FROM Lancamento l WHERE l.isPago = :isPago")
    , @NamedQuery(name = "Lancamento.findByDataRegistro", query = "SELECT l FROM Lancamento l WHERE l.dataRegistro = :dataRegistro")
    , @NamedQuery(name = "Lancamento.findByHorarioRegistro", query = "SELECT l FROM Lancamento l WHERE l.horarioRegistro = :horarioRegistro")})
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LancamentoPK lancamentoPK;
    @Basic(optional = false)
    @Column(name = "isDespesa")
    private boolean isDespesa;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "valorJuros")
    private BigDecimal valorJuros;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Column(name = "emitente")
    private String emitente;
    @Basic(optional = false)
    @Column(name = "isParcelado")
    private boolean isParcelado;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @Column(name = "isPago")
    private boolean isPago;
    @Basic(optional = false)
    @Column(name = "dataRegistro")
    @Temporal(TemporalType.DATE)
    private Date dataRegistro;
    @Basic(optional = false)
    @Column(name = "horarioRegistro")
    @Temporal(TemporalType.TIME)
    private Date horarioRegistro;
    @JoinColumn(name = "idEvento", referencedColumnName = "idEvento")
    @ManyToOne
    private Evento idEvento;
    @JoinColumn(name = "idPessoaRegistrouLancamento", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public Lancamento() {
    }

    public Lancamento(LancamentoPK lancamentoPK) {
        this.lancamentoPK = lancamentoPK;
    }

    public Lancamento(LancamentoPK lancamentoPK, boolean isDespesa, String descricao, boolean isParcelado, boolean isPago, Date dataRegistro, Date horarioRegistro) {
        this.lancamentoPK = lancamentoPK;
        this.isDespesa = isDespesa;
        this.descricao = descricao;
        this.isParcelado = isParcelado;
        this.isPago = isPago;
        this.dataRegistro = dataRegistro;
        this.horarioRegistro = horarioRegistro;
    }

    public Lancamento(long numeroLancamento, long idPessoaRegistrouLancamento) {
        this.lancamentoPK = new LancamentoPK(numeroLancamento, idPessoaRegistrouLancamento);
    }

    public LancamentoPK getLancamentoPK() {
        return lancamentoPK;
    }

    public void setLancamentoPK(LancamentoPK lancamentoPK) {
        this.lancamentoPK = lancamentoPK;
    }

    public boolean getIsDespesa() {
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

    public boolean getIsParcelado() {
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

    public boolean getIsPago() {
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

    public Date getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(Date horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
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
        hash += (lancamentoPK != null ? lancamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lancamento)) {
            return false;
        }
        Lancamento other = (Lancamento) object;
        if ((this.lancamentoPK == null && other.lancamentoPK != null) || (this.lancamentoPK != null && !this.lancamentoPK.equals(other.lancamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.Lancamento[ lancamentoPK=" + lancamentoPK + " ]";
    }
    
}
