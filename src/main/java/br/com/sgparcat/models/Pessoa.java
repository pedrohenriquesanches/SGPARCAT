/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

    public enum TipoPessoa {
        PAROQUIANO, CLERIGO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPessoa tipoPessoa;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private Character sexo;

    @Column(nullable = false)
    private Character estadoCivil;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column
    private String telefoneFixo;

    @Column
    private String telefoneCelular;

    @Column
    private String endereco;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String UF;

    @Column
    private String CEP;

    @Column
    private String complemento;

    @Column
    private String email;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true)
    private String rg;

    @Column(nullable = false)
    private byte isDizimista;

    @Column(nullable = false)
    private byte isDizimistaAtivo;

    @JoinColumn(nullable = false, name = "idFuncao")
    @ManyToOne
    private Funcao funcao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaRegistrouLancamento")
    private List<Lancamento> lancamentosQueRegistrou;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaContribuinte")
    private List<Contribuicao> contribuicoesQueRealizou;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaRegistrouContribuicao")
    private List<Contribuicao> contribuicoesQueRegistrou;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Membro> membros;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Participante> participantes;

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public byte getIsDizimista() {
        return isDizimista;
    }

    public void setIsDizimista(byte isDizimista) {
        this.isDizimista = isDizimista;
    }

    public byte getIsDizimistaAtivo() {
        return isDizimistaAtivo;
    }

    public void setIsDizimistaAtivo(byte isDizimistaAtivo) {
        this.isDizimistaAtivo = isDizimistaAtivo;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public List<Lancamento> getLancamentosQueRegistrou() {
        return lancamentosQueRegistrou;
    }

    public void setLancamentosQueRegistrou(List<Lancamento> lancamentosQueRegistrou) {
        this.lancamentosQueRegistrou = lancamentosQueRegistrou;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public List<Contribuicao> getContribuicoesQueRealizou() {
        return contribuicoesQueRealizou;
    }

    public void setContribuicoesQueRealizou(List<Contribuicao> contribuicoesQueRealizou) {
        this.contribuicoesQueRealizou = contribuicoesQueRealizou;
    }

    public List<Contribuicao> getContribuicoesQueRegistrou() {
        return contribuicoesQueRegistrou;
    }

    public void setContribuicoesQueRegistrou(List<Contribuicao> contribuicoesQueRegistrou) {
        this.contribuicoesQueRegistrou = contribuicoesQueRegistrou;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idPessoa);
        hash = 97 * hash + Objects.hashCode(this.tipoPessoa);
        hash = 97 * hash + Objects.hashCode(this.nomeCompleto);
        hash = 97 * hash + Objects.hashCode(this.sexo);
        hash = 97 * hash + Objects.hashCode(this.estadoCivil);
        hash = 97 * hash + Objects.hashCode(this.dataNascimento);
        hash = 97 * hash + Objects.hashCode(this.telefoneFixo);
        hash = 97 * hash + Objects.hashCode(this.telefoneCelular);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.cpf);
        hash = 97 * hash + Objects.hashCode(this.rg);
        hash = 97 * hash + this.isDizimista;
        hash = 97 * hash + this.isDizimistaAtivo;
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
        final Pessoa other = (Pessoa) obj;
        if (this.isDizimista != other.isDizimista) {
            return false;
        }
        if (this.isDizimistaAtivo != other.isDizimistaAtivo) {
            return false;
        }
        if (!Objects.equals(this.nomeCompleto, other.nomeCompleto)) {
            return false;
        }
        if (!Objects.equals(this.telefoneFixo, other.telefoneFixo)) {
            return false;
        }
        if (!Objects.equals(this.telefoneCelular, other.telefoneCelular)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        if (!Objects.equals(this.idPessoa, other.idPessoa)) {
            return false;
        }
        if (this.tipoPessoa != other.tipoPessoa) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.estadoCivil, other.estadoCivil)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimento, other.dataNascimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "idPessoa=" + idPessoa + ", tipoPessoa=" + tipoPessoa + ", nomeCompleto=" + nomeCompleto + ", sexo=" + sexo + ", estadoCivil=" + estadoCivil + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf + ", isDizimista=" + isDizimista + ", isDizimistaAtivo=" + isDizimistaAtivo + '}';
    }

}
