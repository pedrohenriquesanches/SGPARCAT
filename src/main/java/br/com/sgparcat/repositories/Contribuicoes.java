/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;

import br.com.sgparcat.models.Contribuicao;
import br.com.sgparcat.models.Lancamento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
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

    public Contribuicao retornaPorNumeroContribuicao(Long numeroContribuicao) {
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

        adicionarRestricoesPorNomeETipo(c, descricaoPesquisada, tipoContribuicaoSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    public List<Contribuicao> retornaContribuicoesPorPeriodo(int periodoSelecionado, String descricaoPesquisada, Contribuicao.TipoContribuicao tipoContribuicaoSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Contribuicao.class);

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

        adicionarRestricoesPorNomeETipo(c, descricaoPesquisada, tipoContribuicaoSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    public List<Contribuicao> retornaContribuicoesPorMesEAno(int mesSelecionado, int anoSelecionado, String descricaoPesquisada, Contribuicao.TipoContribuicao tipoContribuicaoSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Contribuicao.class);

        if (mesSelecionado != 0) {
            c.add(Restrictions.sqlRestriction("MONTH(dataReferente) = ? ", mesSelecionado, IntegerType.INSTANCE));
        }

        if (anoSelecionado != 0) {
            c.add(Restrictions.sqlRestriction("YEAR(dataReferente) = ? ", anoSelecionado, IntegerType.INSTANCE));
        }

        adicionarRestricoesPorNomeETipo(c, descricaoPesquisada, tipoContribuicaoSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    public List<Contribuicao> retornaContribuicoesPorIntervalo(Date dataInicio, Date dataFim, String descricaoPesquisada, Contribuicao.TipoContribuicao tipoContribuicaoSelecionado) {
        Session session = manager.unwrap(Session.class);
        Criteria c = session.createCriteria(Contribuicao.class);

        if (dataInicio != null) {
            c.add(Restrictions.ge("dataReferente", dataInicio));
        }

        if (dataFim != null) {
            c.add(Restrictions.le("dataReferente", dataFim));
        }

        adicionarRestricoesPorNomeETipo(c, descricaoPesquisada, tipoContribuicaoSelecionado);

        c.addOrder(Order.asc("descricao"));
        return c.list();
    }

    private void adicionarRestricoesPorNomeETipo(Criteria c, String descricaoPesquisada, Contribuicao.TipoContribuicao tipoContribuicaoSelecionado) {
        if (descricaoPesquisada != null && !descricaoPesquisada.equals("")) {
            c.createAlias("pessoaContribuinte", "contribuinte");
            c.add(Restrictions.or(Restrictions.like("descricao", descricaoPesquisada, MatchMode.ANYWHERE), Restrictions.like("contribuinte.nomeCompleto", descricaoPesquisada, MatchMode.ANYWHERE)));
        }

        if (tipoContribuicaoSelecionado != null) {
            c.add(Restrictions.eq("tipoContribuicao", tipoContribuicaoSelecionado));
        }
    }
    
    public Map<Date, BigDecimal> retornaValorTotalDeDizimoPorMes(Integer numeroDeDias) {
        Session session = manager.unwrap(Session.class);
        numeroDeDias -= 1;

        Calendar dataInicial = Calendar.getInstance();
        dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
        dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);

        Map<Date, BigDecimal> resultado = new TreeMap<>();

        Criteria criteria = session.createCriteria(Contribuicao.class);

        criteria.setProjection(Projections.projectionList()
                .add(Projections.sqlGroupProjection("date(dataReferente) as data",
                        "date(dataReferente)", new String[]{"data"},
                        new Type[]{StandardBasicTypes.DATE}))
                .add(Projections.sum("valor").as("valor")))
                .add(Restrictions.ge("dataReferente", dataInicial.getTime()));

        criteria.add(Restrictions.eq("tipoContribuicao", Contribuicao.TipoContribuicao.DIZIMO));

        List<DataValor> valoresPorData = criteria.setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();

        for (DataValor dataValor : valoresPorData) {
            resultado.put(dataValor.getData(), dataValor.getValor());
        }

        return resultado;
    }
}
