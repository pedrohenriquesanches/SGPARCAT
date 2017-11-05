/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.services;

import br.com.sgparcat.models.Contribuicao;
import br.com.sgparcat.repositories.Contribuicoes;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author pedrohensanches
 */
public class ContribuicaoService implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Contribuicoes repositorioContribuicoes;
    
    public Contribuicao salvar(Contribuicao contribuicao){
        contribuicao.setDataRegistro(new Date());
        return repositorioContribuicoes.guardar(contribuicao);
    }
    
    public void excluir(Contribuicao contribuicao){
        repositorioContribuicoes.remover(contribuicao);
    }
    
}
