/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idPessoa", nullable = false)
    private Long idPessoa;
    
    @Column(name = "tipoPessoa", nullable = false)
    private String tipoPessoa;

    @Column(name = "nomeCompleto", nullable = false)
    private String nomeCompleto;
    
    @Column(name = "sexo", nullable = false)
    private Character sexo;
    
    @Column(name = "estadoCivil")
    private Character estadoCivil;
    
    @Column(name = "dataNascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    
    @Column(name = "telefoneFixo")
    private String telefoneFixo;
    
    @Column(name = "telefoneCelular")
    private String telefoneCelular;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "cpf", nullable = false)
    private String cpf;
    
    @Column(name = "rg")
    private String rg;
    
    @Column(name = "isDizimista", nullable = false)
    private boolean isDizimista;
    
    @Column(name = "isDizimistaAtivo", nullable = false)
    private boolean isDizimistaAtivo;
    
    @JoinColumn(name = "idFuncao", referencedColumnName = "idFuncao")
    @ManyToOne(optional = false)
    private Funcao funcao;
    
    @OneToMany(mappedBy = "pessoaRegistrouLancamento")
    private List<Lancamento> lancamentosQueRegistrou;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Endereco> enderecos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Membro> membros;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Participante> participantes;
    
    @OneToMany(mappedBy = "pessoaContribuinte")
    private List<Contribuicao> contribuicoesQueRealizou;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaRegistrouContribuicao")
    private List<Contribuicao> contribuicoesQueRegistrou;
    
    public Pessoa() {
    }

    public Pessoa(Long idPessoa, String tipoPessoa, String nomeCompleto, Character sexo, Character estadoCivil, Date dataNascimento, String cpf, boolean isDizimista, boolean isDizimistaAtivo, Funcao funcao) {
        this.idPessoa = idPessoa;
        this.tipoPessoa = tipoPessoa;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.isDizimista = isDizimista;
        this.isDizimistaAtivo = isDizimistaAtivo;
        this.funcao = funcao;
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

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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

    public boolean isIsDizimista() {
        return isDizimista;
    }

    public void setIsDizimista(boolean isDizimista) {
        this.isDizimista = isDizimista;
    }

    public boolean isIsDizimistaAtivo() {
        return isDizimistaAtivo;
    }

    public void setIsDizimistaAtivo(boolean isDizimistaAtivo) {
        this.isDizimistaAtivo = isDizimistaAtivo;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
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
        return "br.com.sgparcat.models.Pessoa[ idPessoa=" + idPessoa + " ]";
    }
}