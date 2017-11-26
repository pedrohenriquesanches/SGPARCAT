/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.services;

import br.com.sgparcat.models.Participante;
import br.com.sgparcat.repositories.Participantes;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author pedrohensanches
 */
public class ParticipanteService implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Participantes repositorioMembros;
    
    public Participante salvar(Participante participante){        
        return repositorioMembros.guardar(participante);
    }

    public void excluir(Participante participante) {
        repositorioMembros.remover(participante);
    }
    
}
