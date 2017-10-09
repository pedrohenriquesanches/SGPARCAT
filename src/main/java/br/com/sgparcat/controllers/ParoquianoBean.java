/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.models.Pessoa;
import br.com.sgparcat.repositories.Pessoas;
import br.com.sgparcat.services.CadastroPessoaService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author pedrohensanches
 */

@Named
@SessionScoped
public class ParoquianoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    CadastroPessoaService cadastroPessoaService;
    
    @Inject
    Pessoas repositorioPessoas;
    
    @Inject
    private Pessoa paroquiano;
    
    private List<Pessoa> paroquianos;
    
    @Inject
    private Funcao funcao;
    
    public void salvar(){
        paroquiano.setTipoPessoa(Pessoa.TipoPessoa.PAROQUIANO);
        paroquiano.setFuncao(funcao);
        paroquiano = cadastroPessoaService.salvar(paroquiano);
        limpar();
    }
    
    public void listar(){
        paroquianos = repositorioPessoas.retornaParoquianos();
    }            

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

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
    
    public void limpar(){
        paroquiano = new Pessoa();
    }

}