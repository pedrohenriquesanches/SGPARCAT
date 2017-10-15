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
public class ParoquianoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    PessoaService pessoaService;

    @Inject
    Pessoas repositorioPessoas;

    @Inject
    private Pessoa paroquiano;

    private List<Pessoa> paroquianos;
    
    private String inputPesquisa;
    
    @Inject
    private Funcao funcaoSelecionada;

    public Pessoa getParoquiano() {
        return paroquiano;
    }

    public void setParoquiano(Pessoa paroquiano) {
        this.paroquiano = paroquiano;
    }

    public List<Pessoa> getParoquianos() {
        return paroquianos;
    }

    public void setParoquianos(List<Pessoa> paroquianos) {
        this.paroquianos = paroquianos;
    }

    public String getInputPesquisa() {
        return inputPesquisa;
    }

    public void setInputPesquisa(String inputPesquisa) {
        this.inputPesquisa = inputPesquisa;
    }
    
    public Funcao getFuncaoSelecionada() {
        return funcaoSelecionada;
    }

    public void setFuncaoSelecionada(Funcao funcaoSelecionada) {
        this.funcaoSelecionada = funcaoSelecionada;
    }
    
    public void limpar() {
        paroquiano = new Pessoa();
        funcaoSelecionada = new Funcao();
        inputPesquisa = "";        
    }
    
    public void salvar() {
        paroquiano.setTipoPessoa(Pessoa.TipoPessoa.PAROQUIANO);
        pessoaService.salvar(paroquiano);        
        FacesUtil.addInfoMessage("O cadastro de " + paroquiano.getNomeCompleto() + " foi realizado com sucesso");
        limpar();
    }

    public void excluir(Pessoa paroquiano) {
        pessoaService.excluir(paroquiano);
        paroquianos.remove(paroquiano);
        FacesUtil.addInfoMessage("O cadastro de" + paroquiano.getNomeCompleto() + " foi excluido com sucesso!");
        limpar();
    }

    public void filtrarParoquianos() {
        paroquianos = repositorioPessoas.retornaParoquianoso(funcaoSelecionada, inputPesquisa);
    }
}