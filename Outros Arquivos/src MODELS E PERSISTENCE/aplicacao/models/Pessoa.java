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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
    , @NamedQuery(name = "Pessoa.findByIdPessoa", query = "SELECT p FROM Pessoa p WHERE p.idPessoa = :idPessoa")
    , @NamedQuery(name = "Pessoa.findByTipoPessoa", query = "SELECT p FROM Pessoa p WHERE p.tipoPessoa = :tipoPessoa")
    , @NamedQuery(name = "Pessoa.findByFuncao", query = "SELECT p FROM Pessoa p WHERE p.funcao = :funcao")
    , @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome")
    , @NamedQuery(name = "Pessoa.findBySobrenome", query = "SELECT p FROM Pessoa p WHERE p.sobrenome = :sobrenome")
    , @NamedQuery(name = "Pessoa.findBySexo", query = "SELECT p FROM Pessoa p WHERE p.sexo = :sexo")
    , @NamedQuery(name = "Pessoa.findByEstadoCivil", query = "SELECT p FROM Pessoa p WHERE p.estadoCivil = :estadoCivil")
    , @NamedQuery(name = "Pessoa.findByDataNascimento", query = "SELECT p FROM Pessoa p WHERE p.dataNascimento = :dataNascimento")
    , @NamedQuery(name = "Pessoa.findByCidadeNatal", query = "SELECT p FROM Pessoa p WHERE p.cidadeNatal = :cidadeNatal")
    , @NamedQuery(name = "Pessoa.findByUfNatal", query = "SELECT p FROM Pessoa p WHERE p.ufNatal = :ufNatal")
    , @NamedQuery(name = "Pessoa.findByEndereco", query = "SELECT p FROM Pessoa p WHERE p.endereco = :endereco")
    , @NamedQuery(name = "Pessoa.findByCidade", query = "SELECT p FROM Pessoa p WHERE p.cidade = :cidade")
    , @NamedQuery(name = "Pessoa.findByUf", query = "SELECT p FROM Pessoa p WHERE p.uf = :uf")
    , @NamedQuery(name = "Pessoa.findByCep", query = "SELECT p FROM Pessoa p WHERE p.cep = :cep")
    , @NamedQuery(name = "Pessoa.findByBairro", query = "SELECT p FROM Pessoa p WHERE p.bairro = :bairro")
    , @NamedQuery(name = "Pessoa.findByTelefoneFixo", query = "SELECT p FROM Pessoa p WHERE p.telefoneFixo = :telefoneFixo")
    , @NamedQuery(name = "Pessoa.findByTelefoneCelular", query = "SELECT p FROM Pessoa p WHERE p.telefoneCelular = :telefoneCelular")
    , @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email")
    , @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf")
    , @NamedQuery(name = "Pessoa.findByRg", query = "SELECT p FROM Pessoa p WHERE p.rg = :rg")
    , @NamedQuery(name = "Pessoa.findByIsDizimista", query = "SELECT p FROM Pessoa p WHERE p.isDizimista = :isDizimista")
    , @NamedQuery(name = "Pessoa.findByIsDizimistaAtivo", query = "SELECT p FROM Pessoa p WHERE p.isDizimistaAtivo = :isDizimistaAtivo")
    , @NamedQuery(name = "Pessoa.findByObservacao", query = "SELECT p FROM Pessoa p WHERE p.observacao = :observacao")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPessoa")
    private Long idPessoa;
    @Basic(optional = false)
    @Column(name = "tipoPessoa")
    private String tipoPessoa;
    @Basic(optional = false)
    @Column(name = "funcao")
    private String funcao;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "sobrenome")
    private String sobrenome;
    @Basic(optional = false)
    @Column(name = "sexo")
    private Character sexo;
    @Basic(optional = false)
    @Column(name = "estadoCivil")
    private Character estadoCivil;
    @Basic(optional = false)
    @Column(name = "dataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "cidadeNatal")
    private String cidadeNatal;
    @Column(name = "ufNatal")
    private String ufNatal;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @Column(name = "uf")
    private String uf;
    @Column(name = "cep")
    private String cep;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "telefoneFixo")
    private String telefoneFixo;
    @Column(name = "telefoneCelular")
    private String telefoneCelular;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "rg")
    private String rg;
    @Basic(optional = false)
    @Column(name = "isDizimista")
    private boolean isDizimista;
    @Basic(optional = false)
    @Column(name = "isDizimistaAtivo")
    private boolean isDizimistaAtivo;
    @Column(name = "observacao")
    private String observacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Collection<Lancamento> lancamentoCollection;
    @JoinColumn(name = "idfuncao", referencedColumnName = "idfuncao")
    @ManyToOne(optional = false)
    private Funcao idfuncao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Collection<Membro> membroCollection;
    @OneToMany(mappedBy = "idPessoaContribuinte")
    private Collection<Contribuicao> contribuicaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPessoaRegistrouContribuicao")
    private Collection<Contribuicao> contribuicaoCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Collection<Participante> participanteCollection;

    public Pessoa() {
    }

    public Pessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoa(Long idPessoa, String tipoPessoa, String funcao, String nome, String sobrenome, Character sexo, Character estadoCivil, Date dataNascimento, String endereco, String cidade, String uf, String cpf, String rg, boolean isDizimista, boolean isDizimistaAtivo) {
        this.idPessoa = idPessoa;
        this.tipoPessoa = tipoPessoa;
        this.funcao = funcao;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.cpf = cpf;
        this.rg = rg;
        this.isDizimista = isDizimista;
        this.isDizimistaAtivo = isDizimistaAtivo;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Character getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Character estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidadeNatal() {
        return cidadeNatal;
    }

    public void setCidadeNatal(String cidadeNatal) {
        this.cidadeNatal = cidadeNatal;
    }

    public String getUfNatal() {
        return ufNatal;
    }

    public void setUfNatal(String ufNatal) {
        this.ufNatal = ufNatal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public boolean getIsDizimista() {
        return isDizimista;
    }

    public void setIsDizimista(boolean isDizimista) {
        this.isDizimista = isDizimista;
    }

    public boolean getIsDizimistaAtivo() {
        return isDizimistaAtivo;
    }

    public void setIsDizimistaAtivo(boolean isDizimistaAtivo) {
        this.isDizimistaAtivo = isDizimistaAtivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @XmlTransient
    public Collection<Lancamento> getLancamentoCollection() {
        return lancamentoCollection;
    }

    public void setLancamentoCollection(Collection<Lancamento> lancamentoCollection) {
        this.lancamentoCollection = lancamentoCollection;
    }

    public Funcao getIdfuncao() {
        return idfuncao;
    }

    public void setIdfuncao(Funcao idfuncao) {
        this.idfuncao = idfuncao;
    }

    @XmlTransient
    public Collection<Membro> getMembroCollection() {
        return membroCollection;
    }

    public void setMembroCollection(Collection<Membro> membroCollection) {
        this.membroCollection = membroCollection;
    }

    @XmlTransient
    public Collection<Contribuicao> getContribuicaoCollection() {
        return contribuicaoCollection;
    }

    public void setContribuicaoCollection(Collection<Contribuicao> contribuicaoCollection) {
        this.contribuicaoCollection = contribuicaoCollection;
    }

    @XmlTransient
    public Collection<Contribuicao> getContribuicaoCollection1() {
        return contribuicaoCollection1;
    }

    public void setContribuicaoCollection1(Collection<Contribuicao> contribuicaoCollection1) {
        this.contribuicaoCollection1 = contribuicaoCollection1;
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
        hash += (idPessoa != null ? idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacao.models.Pessoa[ idPessoa=" + idPessoa + " ]";
    }
    
}
