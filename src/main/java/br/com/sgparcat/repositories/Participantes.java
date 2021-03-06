/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Evento;
import br.com.sgparcat.models.Participante;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author pedrohensanches
 */
public class Participantes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public void remover(Participante participante) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        participante = manager.find(Participante.class, participante.getIdParticipante());
        manager.remove(participante);
        et.commit();
    }

    public Participante guardar(Participante participante) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        participante = manager.merge(participante);
        et.commit();
        return participante;
    }

    public List<Participante> pesquisarParticipante(Evento evento, String nomePesquisado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Participante.class);
        c.add(Restrictions.eq("evento", evento));
        c.createAlias("pessoa", "pessoa");
        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("pessoa.nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
        }
        c.addOrder(Order.asc("pessoa.nomeCompleto"));
        return c.list();
    }

}
