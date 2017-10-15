/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.repositories.Funcoes;
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
public class FuncaoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    Funcoes repositorioFuncoes;
        
    public List<Funcao> filtrarFuncoesApenasParaClerigos(String input){
        return repositorioFuncoes.retornaFuncoesApenasParaClerigos(input);
    }
    
    public List<Funcao> filtrarFuncoesQueNaoSaoApenasParaClerigos(String input){
        return repositorioFuncoes.retornaFuncoesQueNaoSaoApenasParaClerigos(input);
    }
    
}