/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

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

    public Pessoa(Long idPessoa, String tipoPessoa, String funcao, String nome, String sobrenome, Character sexo, Character estadoCivil, Date dataNascimento, String cpf, String rg, boolean isDizimista, boolean isDizimistaAtivo) {
        this.idPessoa = idPessoa;
        this.tipoPessoa = tipoPessoa;
        this.funcao = funcao;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.isDizimista = isDizimista;
        this.isDizimistaAtivo = isDizimistaAtivo;
    }
    
    
    //GETTES AND SETTERS
    
    
    //Métodos sobrescritos do java.lang.Object
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