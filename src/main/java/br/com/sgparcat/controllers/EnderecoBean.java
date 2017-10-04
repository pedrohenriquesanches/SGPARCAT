/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author pedrohensanches
 */
@Named
@SessionScoped
public class EnderecoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public void cadastrarEndereco() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("width",780);
        options.put("height",340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        RequestContext.getCurrentInstance().openDialog("/enderecos/cadastrar", options, null);
    }
}