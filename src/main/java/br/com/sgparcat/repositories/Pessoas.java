/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Pessoa;
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

public class Pessoas implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager manager;
    
    public Pessoa guardar(Pessoa paroquiano){
        EntityTransaction et = manager.getTransaction();
        et.begin();
        paroquiano = manager.merge(paroquiano);
        et.commit();
        return paroquiano;
    }
    
    public List<Pessoa> retornaPessoas(){
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class);
        c.add(Restrictions.eq("tipoPessoa", Pessoa.TipoPessoa.PAROQUIANO));
        c.addOrder(Order.asc("idPessoa"));
        return c.list();
    }
    
    
}
