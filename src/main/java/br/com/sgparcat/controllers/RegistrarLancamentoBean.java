/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Lancamento;
import br.com.sgparcat.services.LancamentoService;
import br.com.sgparcat.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author pedrohensanches
 */
@Named
@ViewScoped
public class RegistrarLancamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Lancamento lancamento;

    @Inject
    private LancamentoService lancamentoService;

    @PostConstruct
    public void RegistrarLancamentoBean() {
        lancamento.setIsDespesa('N');
        lancamento.setIsPago('N');
        lancamento.setDataRegistro(new Date());
    }
    
    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public void salvar() {
        Boolean estaEditando = estaEditando();
        
        lancamento = lancamentoService.salvar(lancamento);
        
        if (estaEditando) {
            FacesUtil.addInfoMessage("dialogMessages", "Lançamento editada com sucesso!");
        } else {
            FacesUtil.addInfoMessage("dialogMessages", "Lançamento registrada com sucesso!");
        }
    }

    public Boolean estaEditando() {
        return lancamento.getNumeroLancamento() != null;
    }

    public void limpar(CloseEvent event) {
        lancamento = new Lancamento();
    }

}
