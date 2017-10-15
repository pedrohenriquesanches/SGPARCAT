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
public class ClerigoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    PessoaService cadastroPessoaService;
    
    @Inject
    Pessoas repositorioPessoas;
    
    @Inject
    private Pessoa clerigo;
    
    private List<Pessoa> clerigos;
    
    private String inputPesquisa;
    
    @Inject
    private Funcao funcaoSelecionada;

    public Pessoa getClerigo() {
        return clerigo;
    }

    public void setClerigo(Pessoa clerigo) {
        this.clerigo = clerigo;
    }

    public List<Pessoa> getClerigos() {
        return clerigos;
    }

    public void setClerigos(List<Pessoa> clerigos) {
        this.clerigos = clerigos;
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
        clerigo = new Pessoa();
        funcaoSelecionada = new Funcao();
        inputPesquisa = "";        
    }
    
    public void salvar() {
        clerigo.setTipoPessoa(Pessoa.TipoPessoa.CLERIGO);
        cadastroPessoaService.salvar(clerigo);        
        FacesUtil.addInfoMessage("O cadastro de " + clerigo.getNomeCompleto() + " foi realizado com sucesso");
        limpar();
    }

    public void excluir(Pessoa clerigo) {
        cadastroPessoaService.excluir(clerigo);
        clerigos.remove(clerigo);
        FacesUtil.addInfoMessage("O cadastro de" + clerigo.getNomeCompleto() + " foi excluido com sucesso!");
        limpar();
    }
    
    public void filtrarClerigos() {
        clerigos = repositorioPessoas.retornaClerigos(funcaoSelecionada, inputPesquisa);
    }
}