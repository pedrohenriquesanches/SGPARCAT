/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Funcao;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author pedrohensanches
 */

public class Funcoes implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager manager;
    
    public List<Funcao> retornaFuncoesPorNome(String nomeFuncao){
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Funcao.class);
        c.add(Restrictions.like("titulo", nomeFuncao, MatchMode.ANYWHERE));
        return c.list();
    }

    public List<Funcao> retornaFuncoes(){
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Funcao.class);
        return c.list();
    }
    
    public Funcao retornaPorId(Integer id) {
        return manager.find(Funcao.class, id);
    }
    
}
