/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

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
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

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

    @Inject
    private Lancamentos repositorioLancamentos;

    @PostConstruct
    public void HomeBean() {
        criarGraficoDeLancamentos();
    }

    public LineChartModel getGraficoLancamentosFinanceiros() {
        return graficoLancamentosFinanceiros;
    }

    public void setGraficoLancamentosFinanceiros(LineChartModel graficoLancamentosFinanceiros) {
        this.graficoLancamentosFinanceiros = graficoLancamentosFinanceiros;
    }

    private void criarGraficoDeLancamentos() {
        graficoLancamentosFinanceiros = adicionarSeriesDeReceitas();
        graficoLancamentosFinanceiros.setTitle("LANÇAMENTOS FINANCEIROS");
        graficoLancamentosFinanceiros.setAnimate(true);
        graficoLancamentosFinanceiros.setLegendPosition("ne");
        graficoLancamentosFinanceiros.setShowPointLabels(true);
        graficoLancamentosFinanceiros.getAxes().put(AxisType.X, new CategoryAxis());
        graficoLancamentosFinanceiros.getAxis(AxisType.Y).setLabel("R$");
    }

    private LineChartModel adicionarSeriesDeReceitas() {
        LineChartModel model = new LineChartModel();
        ChartSeries seriesReceitas = new ChartSeries();
        seriesReceitas.setLabel("Receitas");
        
        Map<Date, BigDecimal> receitas = repositorioLancamentos.retornaValorTotalDeReceitasPorData(365);
        int i = 1;
        for (Date data : receitas.keySet()) {
            seriesReceitas.set(DATE_FORMAT.format(data), receitas.get(data));
            i++;
        }

        model.addSeries(seriesReceitas);
        return model;
    }

    private void adicionarSeriesDeDespesas() {

    }

}

//
//            System.out.println("data " + data);
//            System.out.println("valor " + receitas.get(data) + "\n");
