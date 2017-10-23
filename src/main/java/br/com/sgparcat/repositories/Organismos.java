/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Organismo;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author pedrohensanches
 */
public class Organismos implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    public void guardar(Organismo organismo) {
    
    }

    public void remover(Organismo organismo) {
        
    }

    public Object retornaPorId(Integer id) {
        return manager.find(Organismo.class, id);
    }
    
}
