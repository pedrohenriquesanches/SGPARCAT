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
import java.util.Calendar;
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

    private int mesReferente;

    private int anoReferente;

    @PostConstruct
    public void RegistrarLancamentoBean() {
        preecherDadosPadroes();
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public int getMesReferente() {
        return mesReferente;
    }

    public void setMesReferente(int mesReferente) {
        this.mesReferente = mesReferente;
    }

    public int getAnoReferente() {
        return anoReferente;
    }

    public void setAnoReferente(int anoReferente) {
        this.anoReferente = anoReferente;
    }

    public void salvar() {
        Boolean estaEditando = estaEditando();

        lancamento = lancamentoService.salvar(lancamento, mesReferente, anoReferente);

        if (estaEditando) {
            FacesUtil.addInfoMessage("dialogMessages", "Lançamento editado com sucesso!");
        } else {
            FacesUtil.addInfoMessage("dialogMessages", "Lançamento registrado com sucesso!");
        }
    }

    public Boolean estaEditando() {
        return lancamento.getNumeroLancamento() != null;
    }

    public void limpar(CloseEvent event) {
        lancamento = new Lancamento();
        preecherDadosPadroes();
    }

    private void preecherDadosPadroes() {
        lancamento.setIsDespesa('N');
        lancamento.setIsPago('N');
        Date hoje = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        lancamento.setDataRegistro(hoje);
        setMesReferente(cal.get(Calendar.MONTH));
        setAnoReferente(cal.get(Calendar.YEAR));
    }

    public String getLabelBotaoNaoPago() {
        if (lancamento.getIsDespesa() == 'S') {
            return "A pagar";
        }
        return "A receber";
    }

    public String getLabelBotaoPago() {
        if (lancamento.getIsDespesa() == 'S') {
            return "Pago";
        }
        return "Recebido";
    }

    public void alterarStatus(Lancamento lancamento) {
        if (lancamento.getIsPago() == 'S') {
            lancamento.setIsPago('N');
        } else {
            lancamento.setIsPago('S');
        }
        lancamentoService.salvar(lancamento);
    }

}
