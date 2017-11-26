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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author pedrohensanches
 */
public class Eventos implements Serializable {

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

    public Evento retornaPorNomeEData(ScheduleEvent scheduleEvent) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Evento.class);
        c.add(Restrictions.eq("titulo", scheduleEvent.getTitle()));
        c.add(Restrictions.eq("dataInicio", scheduleEvent.getStartDate()));
        c.add(Restrictions.eq("dataFim", scheduleEvent.getEndDate()));        
        return (Evento) c.uniqueResult();
    }

}
