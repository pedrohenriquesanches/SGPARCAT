/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Contribuicao;
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
public class Contribuicoes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Contribuicao guardar(Contribuicao contribuicao) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        contribuicao = manager.merge(contribuicao);
        et.commit();
        return contribuicao;
    }

    public void remover(Contribuicao contribuicao) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        contribuicao = manager.find(Contribuicao.class, contribuicao.getNumeroContribuicao());
        manager.remove(contribuicao);
        et.commit();
    }

    public Object retornaPorNumeroContribuicao(Long numeroContribuicao) {
        return manager.find(Contribuicao.class, numeroContribuicao);
    }

    public List<Contribuicao> retornaTodasContribuicoes() {
//        Session session = manager.unwrap(Session.class);
//        Criteria c = session.createCriteria(Contribuicao.class);
//        return c.list();

        return manager.createQuery("from Contribuicao order by descricao asc").getResultList();
    }

    public List<Contribuicao> retornaContribuicoes(String descricaoPesquisada, Contribuicao.TipoContribuicao tipoContribuicaoSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Contribuicao.class);
        
        if (descricaoPesquisada != null && !descricaoPesquisada.equals("")) {
            c.add(Restrictions.like("descricao", descricaoPesquisada, MatchMode.ANYWHERE));
        }
        
        if(tipoContribuicaoSelecionado != null){
            c.add(Restrictions.eq("tipoContribuicao", tipoContribuicaoSelecionado));
        }
        
        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

}
