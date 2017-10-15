/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.services;

import br.com.sgparcat.models.Pessoa;
import br.com.sgparcat.repositories.Pessoas;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author pedrohensanches
 */

public class PessoaService implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Pessoas repositorioPessoas;
    
    public void salvar(Pessoa pessoa){
        
        if(pessoa.getIsDizimista() == 'N'){
            pessoa.setIsDizimistaAtivo('N');
        }
        
        if(pessoa.getIsDizimistaAtivo() == null){
            pessoa.setIsDizimistaAtivo('N');
        }
        
        repositorioPessoas.guardar(pessoa);
    }

    public void excluir(Pessoa pessoa) {
        repositorioPessoas.remover(pessoa);
    }
    
}
