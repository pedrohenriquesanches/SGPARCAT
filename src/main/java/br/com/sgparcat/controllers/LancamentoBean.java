/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author pedrohensanches
 */


@Named
@SessionScoped
public class LancamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> anos;

    private void geraAnos(){
        anos = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1990; i < year + 1; i++) {
            anos.add(""+i);
        }
    }

}