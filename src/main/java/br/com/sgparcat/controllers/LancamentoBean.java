/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Lancamento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author pedrohensanches
 */
@ManagedBean(name = "LancamentoBean")
@ViewScoped
public class LancamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Lancamento> lancamentos;
    private List<String> anos;

    @PostConstruct
    public void init() {
        geraAnos();
        lancamentos = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            Lancamento l = new Lancamento(23L, Long.MIN_VALUE, true, "Descrição do lançamento", new BigDecimal("1.3"), true, true, new Date());
            l.setEmitente("Nome emitente");
            lancamentos.add(l);
        }
    }

    private void geraAnos(){
        anos = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1990; i < year + 1; i++) {
            anos.add(""+i);
        }
    }
    
    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public List<String> getAnos() {
        return anos;
    }

}