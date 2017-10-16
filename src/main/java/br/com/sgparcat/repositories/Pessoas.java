/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.models.Organismo;
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
public class Pessoas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public void guardar(Pessoa pessoa) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        manager.merge(pessoa);
        et.commit();
    }

    public void remover(Pessoa pessoa) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        pessoa = manager.find(Pessoa.class, pessoa.getIdPessoa());
        manager.remove(pessoa);
        et.commit();
    }

    public List<Pessoa> retornaParoquianoso(Funcao funcaoFiltro, String nomePesquisado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class);
        c.add(Restrictions.eq("tipoPessoa", Pessoa.TipoPessoa.PAROQUIANO));
        c.add(Restrictions.isNotNull("funcao"));

        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
        }

        if (funcaoFiltro.isFuncaoValida()) {
            c.add(Restrictions.eq("funcao", funcaoFiltro));
        }

        c.addOrder(Order.asc("nomeCompleto"));
        return c.list();
    }

    public List<Pessoa> retornaClerigos(Funcao funcaoFiltro, String nomePesquisado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class);
        c.add(Restrictions.eq("tipoPessoa", Pessoa.TipoPessoa.CLERIGO));

        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
        }

        if (funcaoFiltro.isFuncaoValida()) {
            c.add(Restrictions.eq("funcao", funcaoFiltro));
        }

        c.addOrder(Order.asc("nomeCompleto"));
        return c.list();
    }

    public List<Pessoa> retornaDizimistas(Character statusSelecionado, String nomePesquisado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class);
        c.add(Restrictions.like("isDizimista", 'S'));

        if (statusSelecionado != null) {
            if (statusSelecionado == 'S') {
                c.add(Restrictions.like("isDizimistaAtivo", 'S'));
            } else if (statusSelecionado == 'N') {
                c.add(Restrictions.like("isDizimistaAtivo", 'N'));
            }
        }

        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
        }

        c.addOrder(Order.asc("nomeCompleto"));
        return c.list();
    }

    public Pessoa retornaPorId(Long id) {
        return manager.find(Pessoa.class, id);
    }

    public List<Pessoa> retornaPessoasQueNãoFazemParteDoOrganismo(Organismo organismo) {
        return null;
    }

}

//    public List<Pessoa> retornaPessoas(Funcao funcaoFiltro, String nomePesquisado, Pessoa.TipoPessoa tipoPessoa) {
//        Session session = manager.unwrap(Session.class);
//        Criteria c = session.createCriteria(Pessoa.class);
//        c.add(Restrictions.eq("tipoPessoa", tipoPessoa));
//        
//        if(nomePesquisado != null && !nomePesquisado.equals("")){
//            c.add(Restrictions.like("nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
//        }
//        
//        if(funcaoFiltro.isFuncaoValida()){
//            c.add(Restrictions.eq("funcao", funcaoFiltro));
//        }
//        
//        c.addOrder(Order.asc("nomeCompleto"));
//        return c.list();
//    }
