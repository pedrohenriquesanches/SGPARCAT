/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.services;

import br.com.sgparcat.models.Membro;
import br.com.sgparcat.repositories.Membros;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author pedrohensanches
 */
public class MembroService implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Membros repositorioMembros;
    
    public Membro salvar(Membro membro){        
        return repositorioMembros.guardar(membro);
    }

    public void excluir(Membro membro) {
        repositorioMembros.remover(membro);
    }
    
}
