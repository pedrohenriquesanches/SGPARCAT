/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

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
public class Organismos implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    public Organismo guardar(Organismo organismo) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        organismo = manager.merge(organismo);
        et.commit();
        return organismo;
    }

    public void remover(Organismo organismo) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        organismo = manager.find(Organismo.class, organismo.getIdOrganismo());
        manager.remove(organismo);
        et.commit();
    }
    
    public List<Organismo> retornaOrganismos(Organismo.TipoOrganismo tipoOrganismo, String nomePesquisado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Organismo.class);
        
        if(tipoOrganismo != null){
            c.add(Restrictions.eq("tipoOrganismo", tipoOrganismo));
        }
        
        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("nome", nomePesquisado, MatchMode.ANYWHERE));
        }

        c.addOrder(Order.asc("nome"));
        return c.list();
    }

    public Object retornaPorId(Integer id) {
        Object ob = manager.find(Organismo.class, id);
        return manager.find(Organismo.class, id);
    }
    
}
