/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Contribuicao;
import br.com.sgparcat.repositories.Contribuicoes;
import br.com.sgparcat.services.ContribuicaoService;
import br.com.sgparcat.util.jsf.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author pedrohensanches
 */
@Named
@ViewScoped
public class GerenciarContribuicaoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Contribuicoes repositorioContribuicoes;

    @Inject
    private ContribuicaoService contribuicaoService;

    private List<String> anos;

    private BigDecimal valorTotal;

    private List<Contribuicao> contribuicoes;

    private Contribuicao.TipoContribuicao tipoContribuicaoSelecionado;
    
    private String inputPesquisa;

    @PostConstruct
    public void GerenciarContribuicaoBean() {
        listarContribuicoes();
        geraAnos();
    }

    private void geraAnos() {
        anos = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1990; i < year + 1; i++) {
            anos.add("" + i);
        }
    }

    public List<String> getAnos() {
        return anos;
    }

    public List<Contribuicao> getContribuicoes() {
        return contribuicoes;
    }

    public void setContribuicoes(List<Contribuicao> contribuicoes) {
        this.contribuicoes = contribuicoes;
    }

    public Contribuicao.TipoContribuicao getTipoContribuicaoSelecionado() {
        return tipoContribuicaoSelecionado;
    }

    public void setTipoContribuicaoSelecionado(Contribuicao.TipoContribuicao tipoContribuicaoSelecionado) {
        this.tipoContribuicaoSelecionado = tipoContribuicaoSelecionado;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getInputPesquisa() {
        return inputPesquisa;
    }

    public void setInputPesquisa(String inputPesquisa) {
        this.inputPesquisa = inputPesquisa;
    }
    
    public Contribuicao.TipoContribuicao[] tiposDeContribuicoes() {
        return Contribuicao.TipoContribuicao.values();
    }

    public void listarContribuicoes() {
        contribuicoes = repositorioContribuicoes.retornaTodasContribuicoes();
        calculaValorTotal();
    }

    public void filtrarContribuicoes() {
        contribuicoes = repositorioContribuicoes.retornaContribuicoes(inputPesquisa);
        calculaValorTotal();
    }

    public void excluir(Contribuicao contribuicao) {
        contribuicaoService.excluir(contribuicao);
        contribuicoes.remove(contribuicao);
        FacesUtil.addInfoMessage("messages", "Contribuição excluida com sucesso!");
        limpar();
        filtrarContribuicoes();
        calculaValorTotal();
    }

    public void limpar() {
        inputPesquisa = null;
    }

    private void calculaValorTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (Contribuicao contribuicao : contribuicoes) {
            if (contribuicao.getValor() != null) {
                total = total.add(contribuicao.getValor());
            }
        }
        setValorTotal(total);
    }

}
