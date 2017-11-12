/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Lancamento;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;

/**
 *
 * @author pedrohensanches
 */
public class Lancamentos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Lancamento guardar(Lancamento lancamento) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        lancamento = manager.merge(lancamento);
        et.commit();
        return lancamento;
    }

    public void remover(Lancamento lancamento) {
        EntityTransaction et = manager.getTransaction();
        et.begin();
        lancamento = manager.find(Lancamento.class, lancamento.getNumeroLancamento());
        manager.remove(lancamento);
        et.commit();
    }

    public Object retornaPorNumeroLancamento(Long numeroLancamento) {
        return manager.find(Lancamento.class, numeroLancamento);
    }

    public List<Lancamento> retornaTodosLancamentos() {
        return manager.createQuery("from Lancamento order by descricao asc").getResultList();
    }

    public List<Lancamento> retornaLancamentos(String descricaoPesquisada, Character tipoLancamentoSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Lancamento.class);

        adicionarRestricoesPorNomeETipo(c, descricaoPesquisada, tipoLancamentoSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    public List<Lancamento> retornaLancamentosPorPeriodo(int periodoSelecionado, String descricaoPesquisada, Character tipoLancamentoSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Lancamento.class);

        Calendar calendar = Calendar.getInstance();
        switch (periodoSelecionado) {
            case 2:
                calendar.add(Calendar.DAY_OF_YEAR, -30);
                c.add(Restrictions.ge("dataReferente", calendar.getTime()));
                break;
            case 3:
                calendar.add(Calendar.DAY_OF_YEAR, -7);
                c.add(Restrictions.ge("dataReferente", calendar.getTime()));
        }

        adicionarRestricoesPorNomeETipo(c, descricaoPesquisada, tipoLancamentoSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    public List<Lancamento> retornaLancamentosPorMesEAno(int mesSelecionado, int anoSelecionado, String descricaoPesquisada, Character tipoLancamentoSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Lancamento.class);

        if (mesSelecionado != 0) {
            c.add(Restrictions.sqlRestriction("MONTH(dataReferente) = ? ", mesSelecionado, IntegerType.INSTANCE));
        }

        if (anoSelecionado != 0) {
            c.add(Restrictions.sqlRestriction("YEAR(dataReferente) = ? ", anoSelecionado, IntegerType.INSTANCE));
        }

        adicionarRestricoesPorNomeETipo(c, descricaoPesquisada, tipoLancamentoSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    public List<Lancamento> retornaLancamentosPorIntervalo(Date dataInicio, Date dataFim, String descricaoPesquisada, Character tipoLancamentoSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Lancamento.class);

        if (dataInicio != null) {
            c.add(Restrictions.ge("dataReferente", dataInicio));
        }

        if (dataFim != null) {
            c.add(Restrictions.le("dataReferente", dataFim));
        }

        adicionarRestricoesPorNomeETipo(c, descricaoPesquisada, tipoLancamentoSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    private void adicionarRestricoesPorNomeETipo(Criteria c, String descricaoPesquisada, Character tipoLancamentoSelecionado) {

        if (descricaoPesquisada != null && !descricaoPesquisada.equals("")) {
            c.add(Restrictions.like("descricao", descricaoPesquisada, MatchMode.ANYWHERE));
        }

        if (tipoLancamentoSelecionado != null) {
            c.add(Restrictions.eq("isDespesa", tipoLancamentoSelecionado));
        }
    }

}
