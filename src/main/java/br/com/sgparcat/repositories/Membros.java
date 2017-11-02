/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Membro;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author pedrohensanches
 */
public class Membros implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager manager;

    public void remover(Membro membro) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        membro = manager.find(Membro.class, membro.getIdMembro());
        manager.remove(membro);
        et.commit();
    }

    public Membro guardar(Membro membro) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        membro = manager.merge(membro);
        et.commit();
        return membro;
    }
    
}
