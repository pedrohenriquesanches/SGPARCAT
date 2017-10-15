/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.models.Pessoa;
import br.com.sgparcat.repositories.Pessoas;
import br.com.sgparcat.services.PessoaService;
import br.com.sgparcat.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author pedrohensanches
 */
@Named
@ViewScoped
public class DizimistaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    PessoaService pessoaService;

    @Inject
    Pessoas repositorioPessoas;

    @Inject
    private Pessoa dizimista;

    private Character statusSelecionado;

    private String inputPesquisa;

    private List<Pessoa> dizimistas;

    public Pessoa getDizimista() {
        return dizimista;
    }

    public void setDizimista(Pessoa dizimista) {
        this.dizimista = dizimista;
    }

    public Character getStatusSelecionado() {
        return statusSelecionado;
    }

    public void setStatusSelecionado(Character statusSelecionado) {
        this.statusSelecionado = statusSelecionado;
    }

    public String getInputPesquisa() {
        return inputPesquisa;
    }

    public void setInputPesquisa(String inputPesquisa) {
        this.inputPesquisa = inputPesquisa;
    }

    public List<Pessoa> getDizimistas() {
        return dizimistas;
    }

    public void setDizimistas(List<Pessoa> dizimistas) {
        this.dizimistas = dizimistas;
    }

    public void limpar() {
        dizimista = new Pessoa();
        statusSelecionado = null;
        inputPesquisa = "";
    }

    public String getStatusDoDizimista(Pessoa dizimista) {
        if (dizimista.getIsDizimistaAtivo() != null) {
            if (dizimista.getIsDizimistaAtivo() == 'S') {
                return "Ativo";
            } else {
                return "Inativo";
            }
        }else{
            return "-";
        }
    }

    public String getUltimaContribuicao() {
        return "-";
    }

    public void filtrarDizimistas() {
        dizimistas = repositorioPessoas.retornaDizimistas(statusSelecionado, inputPesquisa);
    }

    public void excluir(Pessoa dizimista) {
        pessoaService.excluir(dizimista);
        dizimistas.remove(dizimista);
        FacesUtil.addInfoMessage("O cadastro de" + dizimista.getNomeCompleto() + " foi excluido com sucesso!");
        limpar();
    }
}
