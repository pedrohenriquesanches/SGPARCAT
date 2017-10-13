/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.models.Pessoa;
import br.com.sgparcat.repositories.Funcoes;
import br.com.sgparcat.repositories.Pessoas;
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
public class FuncaoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    Funcoes repositorioFuncoes;
        
    public List<Funcao> listarFuncoesPorNome(String input){
        return repositorioFuncoes.retornaFuncoesPorNome(input);
    }
    
    public List<Funcao> listarFuncoes(){
        return repositorioFuncoes.retornaFuncoes();
    }

}