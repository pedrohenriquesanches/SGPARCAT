/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.repositories.Contribuicoes;
import br.com.sgparcat.repositories.Lancamentos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author pedrohensanches
 */
@Named
@RequestScoped
public class HomeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat ("MMM yyyy", new Locale ("pt", "BR"));

    private LineChartModel graficoLancamentosFinanceiros;
    
    private LineChartModel graficoContribuicoes;

    @Inject
    private Lancamentos repositorioLancamentos;
    
    @Inject
    private Contribuicoes repositorioContribuicoes;

    @PostConstruct
    public void HomeBean() {
        criarGraficoDeLancamentos();
        criarGraficoDeContribuicoes();
    }

    public LineChartModel getGraficoLancamentosFinanceiros() {
        return graficoLancamentosFinanceiros;
    }

    public void setGraficoLancamentosFinanceiros(LineChartModel graficoLancamentosFinanceiros) {
        this.graficoLancamentosFinanceiros = graficoLancamentosFinanceiros;
    }
    
    public LineChartModel getGraficoContribuicoes() {
        return graficoContribuicoes;
    }

    public void setGraficoContribuicoes(LineChartModel graficoContribuicoes) {
        this.graficoContribuicoes = graficoContribuicoes;
    }

    private void criarGraficoDeLancamentos() {
        graficoLancamentosFinanceiros = new LineChartModel();
        graficoLancamentosFinanceiros.setTitle("LANÇAMENTOS FINANCEIROS");
        graficoLancamentosFinanceiros.setAnimate(true);
        graficoLancamentosFinanceiros.setLegendPosition("se");
        graficoLancamentosFinanceiros.setShowPointLabels(true);
        graficoLancamentosFinanceiros.getAxes().put(AxisType.X, new CategoryAxis());
        graficoLancamentosFinanceiros.getAxis(AxisType.Y).setLabel("R$");
        graficoLancamentosFinanceiros.getAxis(AxisType.Y).setMin(0);
        graficoLancamentosFinanceiros.setSeriesColors("FF0000,32CD32");
        adicionarSeriesDeDespesas();
        adicionarSeriesDeReceitas();
    }

    private void adicionarSeriesDeReceitas() {
        LineChartSeries seriesReceitas = new LineChartSeries();
        seriesReceitas.setLabel("Receitas");
        
        Map<Date, BigDecimal> receitas = repositorioLancamentos.retornaValorTotalDeLancamentosPorMes(365,'N');
        int i = 1;
        for (Date data : receitas.keySet()) {
            seriesReceitas.set(DATE_FORMAT.format(data), receitas.get(data));
            i++;
        }

        graficoLancamentosFinanceiros.addSeries(seriesReceitas);
    }

    private void adicionarSeriesDeDespesas() {
        LineChartSeries seriesReceitas = new LineChartSeries();
        seriesReceitas.setLabel("Despesas");
        
        Map<Date, BigDecimal> receitas = repositorioLancamentos.retornaValorTotalDeLancamentosPorMes(365,'S');
        int i = 1;
        for (Date data : receitas.keySet()) {
            seriesReceitas.set(DATE_FORMAT.format(data), receitas.get(data));
            i++;
        }

        graficoLancamentosFinanceiros.addSeries(seriesReceitas);
    }
    
    private void criarGraficoDeContribuicoes() {
        graficoContribuicoes = new LineChartModel();
        graficoContribuicoes.setTitle("DÍZIMOS");
        graficoContribuicoes.setAnimate(true);
        graficoContribuicoes.setLegendPosition("se");
        graficoContribuicoes.setShowPointLabels(true);
        graficoContribuicoes.getAxes().put(AxisType.X, new CategoryAxis());
        graficoContribuicoes.getAxis(AxisType.Y).setLabel("R$");
        graficoContribuicoes.getAxis(AxisType.Y).setMin(0);
        graficoContribuicoes.setSeriesColors("0a82eb");
        adicionarSeriesDeContribuicoes();
    }
    
    private void adicionarSeriesDeContribuicoes() {
        LineChartSeries seriesContribuicoes = new LineChartSeries();
        seriesContribuicoes.setLabel("Contribuições");
        
        Map<Date, BigDecimal> receitas = repositorioContribuicoes.retornaValorTotalDeDizimoPorMes(365);
        int i = 1;
        for (Date data : receitas.keySet()) {
            seriesContribuicoes.set(DATE_FORMAT.format(data), receitas.get(data));
            i++;
        }

        graficoContribuicoes.addSeries(seriesContribuicoes);
    }

}