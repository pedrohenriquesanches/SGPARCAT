/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Evento;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author pedrohensanches
 */
public class Eventos implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    public Evento guardar(Evento evento) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        evento = manager.merge(evento);
        et.commit();
        return evento;
    }

    public void remover(Evento evento) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        evento = manager.find(Evento.class, evento.getIdEvento());
        manager.remove(evento);
        et.commit();
    }
    
    public List<Evento> retornaTodosEventos() {
        return manager.createQuery("from Evento order by titulo asc").getResultList();
    }
    
    public Evento retornaPorId(Integer id) {
        return manager.find(Evento.class, id);
    }
    
}
