/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Evento;
import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.models.Membro;
import br.com.sgparcat.models.Organismo;
import br.com.sgparcat.models.Participante;
import br.com.sgparcat.models.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

/**
 *
 * @author pedrohensanches
 */
public class Pessoas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    private Funcoes repositorioFuncoes;

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

    public List<Pessoa> retornaParoquianos(Funcao funcaoFiltro, String nomePesquisado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class);
        c.add(Restrictions.eq("tipoPessoa", Pessoa.TipoPessoa.PAROQUIANO));
        c.add(Restrictions.isNotNull("funcao"));

        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
        }

        if (funcaoFiltro != null && funcaoFiltro.isFuncaoValida()) {
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

        if (funcaoFiltro != null && funcaoFiltro.isFuncaoValida()) {
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

    public Pessoa retornaPorEmail(String email) {
        Pessoa pessoa = null;
        try {
            pessoa = this.manager.createQuery("from Pessoa where lower(email) =:email", Pessoa.class)
                    .setParameter("email",email.toLowerCase()).getSingleResult();
        } catch (NoResultException e) {
        
        }
        return pessoa;
    }

    public List<Pessoa> retornaPessoasQueNaoSaoMembrosDoOrganismo(Organismo organismo, String nomePesquisado) {

//        return manager.createQuery("from Pessoa where idPessoa not in"
//                + "(select pessoa from Membro where idOrganismo = :idOrganismo)").
//                setParameter("idOrganismo", ""+organismo.getIdOrganismo()).getResultList();
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class)
                .add(Subqueries.propertyNotIn("idPessoa", DetachedCriteria.forClass(Membro.class)
                        .add(Restrictions.eq("organismo", organismo))
                        .setProjection(Property.forName("pessoa"))
                ));

        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
        }

        c.addOrder(Order.asc("nomeCompleto"));
        return c.list();
    }
    

    public List<Pessoa> retornaPessoasQueNaoSaoParticipantes(Evento evento, String nomePesquisado) {

//        return manager.createQuery("from Pessoa where idPessoa not in"
//                + "(select pessoa from Membro where idOrganismo = :idOrganismo)").
//                setParameter("idOrganismo", ""+organismo.getIdOrganismo()).getResultList();
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class)
                .add(Subqueries.propertyNotIn("idPessoa", DetachedCriteria.forClass(Participante.class)
                        .add(Restrictions.eq("evento", evento))
                        .setProjection(Property.forName("pessoa"))
                ));

        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
        }

        c.addOrder(Order.asc("nomeCompleto"));
        return c.list();
    }
        

    public List<Pessoa> retornaTodasAsPessoas() {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class);
        c.addOrder(Order.asc("nomeCompleto"));
        return c.list();
    }

    public List<Pessoa> retornaTodasAsPessoas(String nomePesquisado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Pessoa.class);

        if (nomePesquisado != null && !nomePesquisado.equals("")) {
            c.add(Restrictions.like("nomeCompleto", nomePesquisado, MatchMode.ANYWHERE));
        }

        c.addOrder(Order.asc("nomeCompleto"));
        return c.list();
    }

    public Pessoa retornaCoordenadoresDoOrganismo(Organismo organismo) {
        return (Pessoa) manager.createQuery("from Pessoa where idPessoa in"
                + "(select Pessoa from Membro where idOrganismo = :idOrganismo and idFuncao = 7)").
                setParameter("idOrganismo", "" + organismo.getIdOrganismo()).getSingleResult();
//
//        select nomeCompleto from pessoa where idPessoa in (select idPessoa from membro  where idOrganismo = 4 and idFuncao = 7);
        
//        repositorioFuncoes = new Funcoes();
//        Session session = manager.unwrap(Session.class);
//        Criteria c = session.createCriteria(Pessoa.class)
//                .add(Subqueries.propertyIn("idPessoa", DetachedCriteria.forClass(Membro.class)
//                        .add(Restrictions.eq("organismo", organismo))
//                        .setProjection(Property.forName("pessoa"))
//                        .add(Restrictions.like("funcao", repositorioFuncoes.retornaPorId(7)))
//                ));
//        
//         
//
//        c.addOrder(Order.asc("nomeCompleto"));
//        return c.list();
    }

}
