/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Contribuicao;
import br.com.sgparcat.models.Pessoa;
import java.io.Serializable;
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
@ManagedBean(name = "ContribuicaoController")
@ViewScoped
public class ContribuicaoController implements Serializable {

    private List<Contribuicao> contribuicoes;
    private List<String> anos;

    @PostConstruct
    public void init() {
        geraAnos();
        contribuicoes = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            Contribuicao c = new Contribuicao(1219L, Contribuicao.TipoContribuicao.DOACAO, "Descrição da contribuição", new Date(), new Pessoa());
            contribuicoes.add(c);
        }
    }

    private void geraAnos(){
        anos = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1990; i < year + 1; i++) {
            anos.add(""+i);
        }
    }
    
    public List<Contribuicao> getContribuicoes() {
        return contribuicoes;
    }

    public List<String> getAnos() {
        return anos;
    }

}
