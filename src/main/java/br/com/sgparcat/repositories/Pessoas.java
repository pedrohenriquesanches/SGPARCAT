/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.models.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
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
    
    public Pessoa guardar(Pessoa pessoa){
        EntityTransaction et = manager.getTransaction();
        et.begin();
        
        Funcao f = new Funcao();
        f.setTitulo("Coordenador");
        manager.persist(f);
        pessoa.setFuncao(f);
        
        pessoa = manager.merge(pessoa);
        et.commit();
        return pessoa;
    }

    public void remover(Pessoa pessoa) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        
        pessoa =  manager.find(Pessoa.class, pessoa.getIdPessoa());
        manager.remove(pessoa);
        
        et.commit();
    }
    
    public List<Pessoa> retornaParoquianos(){
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class);
        c.add(Restrictions.eq("tipoPessoa", Pessoa.TipoPessoa.PAROQUIANO));
        c.addOrder(Order.asc("idPessoa"));
        return c.list();
    }
    
    public List<Pessoa> retornaClerigos(){
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class);
        c.add(Restrictions.eq("tipoPessoa", Pessoa.TipoPessoa.CLERIGO));
        c.addOrder(Order.asc("idPessoa"));
        return c.list();
    }
    
    
}
