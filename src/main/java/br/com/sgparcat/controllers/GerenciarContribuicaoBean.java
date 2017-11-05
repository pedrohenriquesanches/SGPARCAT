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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author pedrohensanches
 */
@Named
@ViewScoped
public class GerenciarContribuicaoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> anos;

    private List<Contribuicao> contribuicoes;

    private Contribuicao.TipoContribuicao tipoContribuicaoSelecionado;

    @Inject
    private Contribuicoes repositorioContribuicoes;

    @Inject
    private ContribuicaoService contribuicaoService;

    @PostConstruct
    public void GerenciarContribuicaoBean() {
        listarContribuicoes();
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

    public void setAnos(List<String> anos) {
        this.anos = anos;
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

    public void mostrarDialogDeRegistro() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 770);
        options.put("height", 300);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("registrar", options, null);
    }

    public void mostrarDialogDeEdicao(Contribuicao contribuicao) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 770);
        options.put("height", 300);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        options.put("resizable", false);
        options.put("includeViewParams", true);

        Map<String, List<String>> params = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add("1");
        params.put("contribuicao", values);
        RequestContext.getCurrentInstance().openDialog("registrar", options, params);
    }

    public Contribuicao.TipoContribuicao[] tiposDeContribuicoes() {
        return Contribuicao.TipoContribuicao.values();
    }

    public void filtrarContribuicoes() {

    }

    private void listarContribuicoes() {
        contribuicoes = repositorioContribuicoes.retornaTodasContribuicoes();
    }

    public void excluir(Contribuicao contribuicao) {
        contribuicaoService.excluir(contribuicao);
        contribuicoes.remove(contribuicao);
        FacesUtil.addInfoMessage(contribuicao.getDescricao() + " foi excluida com sucesso!");
        limpar();
    }

    public void limpar() {
    }

}
