/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
    @Column(name = "idPessoa", nullable = false)
    private Long idPessoa;
    
    @Column(name = "tipoPessoa", nullable = false)
    private String tipoPessoa;
    
    @Column(name = "funcao", nullable = false)
    private String funcao;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;
    
    @Column(name = "sexo", nullable = false)
    private Character sexo;
    
    @Column(name = "estadoCivil")
    private Character estadoCivil;
    
    @Column(name = "dataNascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    
    @Column(name = "cidadeNatal")
    private String cidadeNatal;
    
    @Column(name = "ufNatal")
    private String ufNatal;
    
    @Column(name = "endereco")
    private String endereco;
    
    @Column(name = "cidade")
    private String cidade;
    
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
    
    @Column(name = "cpf", nullable = false)
    private String cpf;
    
    @Column(name = "rg", nullable = false)
    private String rg;
    
    @Column(name = "isDizimista", nullable = false)
    private boolean isDizimista;
    
    @Column(name = "isDizimistaAtivo", nullable = false)
    private boolean isDizimistaAtivo;
    
    @Column(name = "observacao")
    private String observacao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Collection<Lancamento> lancamentoCollection;
    
    @JoinColumn(name = "idFuncao", referencedColumnName = "idFuncao")
    @ManyToOne(optional = false)
    private Funcao idFuncao;
    
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

    public Pessoa(Long idPessoa, String tipoPessoa, String funcao, String nome, String sobrenome, Character sexo, Date dataNascimento, String cpf, String rg, boolean isDizimista, boolean isDizimistaAtivo) {
        this.idPessoa = idPessoa;
        this.tipoPessoa = tipoPessoa;
        this.funcao = funcao;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
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