/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Lancamento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

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

    public Lancamento retornaPorNumeroLancamento(Long numeroLancamento) {
        return manager.find(Lancamento.class, numeroLancamento);
    }

    public List<Lancamento> retornaTodosLancamentos() {
        return manager.createQuery("from Lancamento order by descricao asc").getResultList();
    }

    public List<Lancamento> retornaLancamentos(String descricaoPesquisada, Character tipoLancamentoSelecionado, Character statusSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Lancamento.class);

        adicionarRestricoesPorNomeTipoEStatus(c, descricaoPesquisada, tipoLancamentoSelecionado, statusSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    public List<Lancamento> retornaLancamentosPorPeriodo(int periodoSelecionado, String descricaoPesquisada, Character tipoLancamentoSelecionado, Character statusSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Lancamento.class);

        Calendar calendar = Calendar.getInstance();
        switch (periodoSelecionado) {
            case 2:
                calendar.add(Calendar.DAY_OF_YEAR, -30);
                c.add(Restrictions.ge("dataRegistro", calendar.getTime()));
                break;
            case 3:
                calendar.add(Calendar.DAY_OF_YEAR, -7);
                c.add(Restrictions.ge("dataRegistro", calendar.getTime()));
        }

        adicionarRestricoesPorNomeTipoEStatus(c, descricaoPesquisada, tipoLancamentoSelecionado, statusSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    public List<Lancamento> retornaLancamentosPorMesEAno(int mesSelecionado, int anoSelecionado, String descricaoPesquisada, Character tipoLancamentoSelecionado, Character statusSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Lancamento.class);

        if (mesSelecionado != 0) {
            c.add(Restrictions.sqlRestriction("MONTH(dataReferente) = ? ", mesSelecionado, IntegerType.INSTANCE));
        }

        if (anoSelecionado != 0) {
            c.add(Restrictions.sqlRestriction("YEAR(dataReferente) = ? ", anoSelecionado, IntegerType.INSTANCE));
        }

        adicionarRestricoesPorNomeTipoEStatus(c, descricaoPesquisada, tipoLancamentoSelecionado, statusSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    public List<Lancamento> retornaLancamentosPorIntervalo(Date dataInicio, Date dataFim, String descricaoPesquisada, Character tipoLancamentoSelecionado, Character statusSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Lancamento.class);

        if (dataInicio != null) {
            c.add(Restrictions.ge("dataRegistro", dataInicio));
        }

        if (dataFim != null) {
            c.add(Restrictions.le("dataRegistro", dataFim));
        }

        adicionarRestricoesPorNomeTipoEStatus(c, descricaoPesquisada, tipoLancamentoSelecionado, statusSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    private void adicionarRestricoesPorNomeTipoEStatus(Criteria c, String descricaoPesquisada, Character tipoLancamentoSelecionado, Character statusSelecionado) {
        if (descricaoPesquisada != null && !descricaoPesquisada.equals("")) {
            c.add(Restrictions.like("descricao", descricaoPesquisada, MatchMode.ANYWHERE));
        }

        if (tipoLancamentoSelecionado != null) {
            c.add(Restrictions.eq("isDespesa", tipoLancamentoSelecionado));
        }

        if (statusSelecionado != null) {
            c.add(Restrictions.eq("isPago", statusSelecionado));
        }
    }

    public Map<Date, BigDecimal> retornaValorTotalDeLancamentosPorMes(Integer numeroDeDias, Character isDespesa) {

        Session session = manager.unwrap(Session.class);
        numeroDeDias -= 1;

        Calendar dataInicial = Calendar.getInstance();
        dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
        dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);

        Map<Date, BigDecimal> resultado = new TreeMap<>();

        Criteria criteria = session.createCriteria(Lancamento.class);

        criteria.setProjection(Projections.projectionList()
                .add(Projections.sqlGroupProjection("date(dataReferente) as data",
                        "date(dataReferente)", new String[]{"data"},
                        new Type[]{StandardBasicTypes.DATE}))
                .add(Projections.sum("valor").as("valor")))
                .add(Restrictions.ge("dataReferente", dataInicial.getTime()));

        criteria.add(Restrictions.eq("isDespesa", isDespesa));

        List<DataValor> valoresPorData = criteria.setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();

        for (DataValor dataValor : valoresPorData) {
            resultado.put(dataValor.getData(), dataValor.getValor());
        }
        
        return resultado;
    }

}
