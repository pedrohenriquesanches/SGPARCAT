/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.models.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author pedrohensanches
 */

@ManagedBean(name="PessoaController")
@ViewScoped
public class PessoaController implements Serializable {
    
    private List<Pessoa> pessoas;
       
    @PostConstruct
    public void init() {
        pessoas = new ArrayList<>();
        for(int i = 0 ; i < 35 ; i++) {
            Pessoa p = new Pessoa(93291L, Pessoa.TipoPessoa.PAROQUIANO, "Nome", 'M', 'C', new Date(), "09404551961", true, true, new Funcao());
            p.setTelefoneFixo("43 99999-9999");
            pessoas.add(p);
        }
    }
 
    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    
}


//    public void onRowSelect(SelectEvent event) {
//        FacesMessage msg = new FacesMessage("Pessoa selecionada", ""+((Pessoa) event.getObject()).getIdPessoa());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
// 
//    public void onRowUnselect(UnselectEvent event) {
//        FacesMessage msg = new FacesMessage("Pessoa não selecionada", ""+((Pessoa) event.getObject()).getIdPessoa());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }