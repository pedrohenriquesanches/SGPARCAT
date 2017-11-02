/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Membro;
import br.com.sgparcat.models.Organismo;
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
public class Membros implements Serializable {

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

    public List<Membro> pesquisarMembro(Organismo organismo, String nomePesquisado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Membro.class);
        c.add(Restrictions.eq("organismo", organismo));
        c.createAlias("pessoa", "pessoa");
        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("pessoa.nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
        }
        c.addOrder(Order.asc("pessoa.nomeCompleto"));
        return c.list();
    }

}
