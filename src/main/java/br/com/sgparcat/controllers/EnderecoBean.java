/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Endereco;
import br.com.sgparcat.models.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author pedrohensanches
 */
@ManagedBean(name = "EnderecoBean")
@ViewScoped
public class EnderecoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Endereco> enderecos;

    @PostConstruct
    public void init() {
        enderecos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Endereco e = new Endereco(2132l, "Rua Brasil, 132", "Centro", "Londrina", "PR", "86290000", new Pessoa());
            enderecos.add(e);
        }
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

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
