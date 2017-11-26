/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
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

    private LineChartModel graficoLancamentosFinanceiros;

    @PostConstruct
    public void HomeBean() {
        criarModelos();
    }

    public LineChartModel getGraficoLancamentosFinanceiros() {
        return graficoLancamentosFinanceiros;
    }

    public void setGraficoLancamentosFinanceiros(LineChartModel graficoLancamentosFinanceiros) {
        this.graficoLancamentosFinanceiros = graficoLancamentosFinanceiros;
    }

    private void criarModelos() {
        graficoLancamentosFinanceiros = initLinearModel();
        graficoLancamentosFinanceiros.setTitle("Line Chart");
        graficoLancamentosFinanceiros.setAnimate(true);
        graficoLancamentosFinanceiros.setLegendPosition("se");
        Axis yAxis = graficoLancamentosFinanceiros.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
    
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
    
}
