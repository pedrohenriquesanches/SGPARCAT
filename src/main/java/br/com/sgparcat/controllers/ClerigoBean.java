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
public class ClerigoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    CadastroPessoaService cadastroPessoaService;
    
    @Inject
    Pessoas repositorioPessoas;
    
    @Inject
    private Pessoa clerigo;
    
    private List<Pessoa> clerigos;
    
    @Inject
    private Funcao funcao;
    
    public void salvar(){
        clerigo.setTipoPessoa(Pessoa.TipoPessoa.CLERIGO);
        clerigo.setFuncao(funcao);
        clerigo = cadastroPessoaService.salvar(clerigo);
        limpar();
    }
    
    public void listar(){
        clerigos = repositorioPessoas.retornaClerigos();
    }            

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

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
    
    public void limpar(){
        clerigo = new Pessoa();
    }
    
}