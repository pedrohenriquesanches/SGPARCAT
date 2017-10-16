/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Membro;
import br.com.sgparcat.models.Organismo;
import br.com.sgparcat.models.Pessoa;
import br.com.sgparcat.repositories.Organismos;
import br.com.sgparcat.repositories.Pessoas;
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
    Pessoas repositorioPessoas;
    
    @Inject
    private Organismo organismo;
    
    private List<Organismo> organismos;
    
    private List<Pessoa> pessoas;
    
    private List<Membro> membros;
    
    private String inputPesquisaPessoa;
    
    private String inputPesquisaMembro;
    
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

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }
    
    public String getInputPesquisaPessoa() {
        return inputPesquisaPessoa;
    }

    public void setInputPesquisaPessoa(String inputPesquisaPessoa) {
        this.inputPesquisaPessoa = inputPesquisaPessoa;
    }

    public String getInputPesquisaMembro() {
        return inputPesquisaMembro;
    }

    public void setInputPesquisaMembro(String inputPesquisaMembro) {
        this.inputPesquisaMembro = inputPesquisaMembro;
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
    
    public void filtrarPessoas() {
        pessoas = repositorioPessoas.retornaPessoasQueNãoFazemParteDoOrganismo(organismo);
    }
    
    public void filtrarMembros() {
        membros = organismo.getMembros();
    }
    
}