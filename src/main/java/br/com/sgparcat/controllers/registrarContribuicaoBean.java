/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Contribuicao;
import br.com.sgparcat.services.ContribuicaoService;
import br.com.sgparcat.util.jsf.FacesUtil;
import java.io.Serializable;
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
public class registrarContribuicaoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Contribuicao contribuicao;

    @Inject
    private ContribuicaoService contribuicaoService;

    public Contribuicao getContribuicao() {
        return contribuicao;
    }

    public void setContribuicao(Contribuicao contribuicao) {
        this.contribuicao = contribuicao;
    }

    public void salvar() {
        Boolean estaEditando = estaEditando();
        
        contribuicao = contribuicaoService.salvar(contribuicao);
        
        if (estaEditando) {
            FacesUtil.addInfoMessage("dialogMessages", "Contribuição editada com sucesso!");
        } else {
            FacesUtil.addInfoMessage("dialogMessages", "Contribuição registrada com sucesso!");
        }
    }

    public Contribuicao.TipoContribuicao[] tiposDeContribuicoes() {
        return Contribuicao.TipoContribuicao.values();
    }

    public Boolean estaEditando() {
        return contribuicao.getNumeroContribuicao() != null;
    }

    public void limpar(CloseEvent event) {
        contribuicao = new Contribuicao();
    }

}
