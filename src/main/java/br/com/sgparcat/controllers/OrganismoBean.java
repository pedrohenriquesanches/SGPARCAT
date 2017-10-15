/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Organismo;
import br.com.sgparcat.repositories.Organismos;
import br.com.sgparcat.services.OrganismoService;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author pedrohensanches
 */

@Named
@ViewScoped
public class OrganismoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    OrganismoService organismoService;
    
    @Inject
    Organismos repositorioOrganismos;
    
    @Inject
    private Organismo organismo;
    
    private List<Organismo> organismos;
    
    private String inputPesquisa;
    
    private Organismo.TipoOrganismo tipoOrganismoSelecionado;

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public List<Organismo> getOrganismos() {
        return organismos;
    }

    public void setOrganismos(List<Organismo> organismos) {
        this.organismos = organismos;
    }

    public String getInputPesquisa() {
        return inputPesquisa;
    }

    public void setInputPesquisa(String inputPesquisa) {
        this.inputPesquisa = inputPesquisa;
    }

    public Organismo.TipoOrganismo getTipoOrganismoSelecionado() {
        return tipoOrganismoSelecionado;
    }

    public void setTipoOrganismoSelecionado(Organismo.TipoOrganismo tipoOrganismoSelecionado) {
        this.tipoOrganismoSelecionado = tipoOrganismoSelecionado;
    }
    
    public void salvar(){
        organismoService.salvar(organismo);
    }
    
}