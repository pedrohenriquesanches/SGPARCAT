/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.repositories.Funcoes;
import java.io.Serializable;
import java.util.ArrayList;
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
public class FuncaoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    Funcoes repositorioFuncoes;
    
    @Inject
    private Funcao funcao;
    
    private List<Funcao> funcoes;
    
    public void listar(){
        funcoes = repositorioFuncoes.retornaFuncoes();
    }
    
    public List<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(List<Funcao> funcoes) {
        this.funcoes = funcoes;
    }
    
    public void limpar(){
        funcoes = new ArrayList<>();
    }

}