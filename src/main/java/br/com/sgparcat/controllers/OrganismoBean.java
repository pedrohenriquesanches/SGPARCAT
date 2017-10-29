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
import br.com.sgparcat.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
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

    private String inputPesquisaOrganismo;

    private String inputPesquisaPessoa;

    private String inputPesquisaMembro;

    private Organismo.TipoOrganismo tipoOrganismoSelecionado;

    @PostConstruct
    public void OrganismoBean() {
        filtrarOrganismos();
        listarPessoasQueNaoEstaoNesseOrganismo();
        listarMembros();
    }

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

    public Organismo.TipoOrganismo getTipoOrganismoSelecionado() {
        return tipoOrganismoSelecionado;
    }

    public void setTipoOrganismoSelecionado(Organismo.TipoOrganismo tipoOrganismoSelecionado) {
        this.tipoOrganismoSelecionado = tipoOrganismoSelecionado;
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

    public String getInputPesquisaOrganismo() {
        return inputPesquisaOrganismo;
    }

    public void setInputPesquisaOrganismo(String inputPesquisaOrganismo) {
        this.inputPesquisaOrganismo = inputPesquisaOrganismo;
    }

    public Organismo.TipoOrganismo[] tiposDeOrganismo() {
        return Organismo.TipoOrganismo.values();
    }

    public String salvarPreCadastro() {
        organismoService.salvar(organismo);
        //FacesUtil.addInfoMessage("O organismo " + organismo.getNome() + " foi criado com sucesso");
        //esperar 2 segundos para redirecionar
        return "/organismos/membros?faces-redirect=true";
    }
    
    public void salvar() {
        organismoService.salvar(organismo);
        //FacesUtil.addInfoMessage("O organismo " + organismo.getNome() + " foi criado com sucesso");
        //limpar();
    }
    
    public void excluir(Organismo organismo) {
        organismoService.excluir(organismo);
        organismos.remove(organismo);
        FacesUtil.addInfoMessage("O organismo " + organismo.getNome() + " foi excluido com sucesso!");
        limpar();
    }
    
    public void limpar() {
        organismo = new Organismo();
        tipoOrganismoSelecionado = null;
        inputPesquisaOrganismo = null;        
    }

    public void filtrarOrganismos() {
        organismos = repositorioOrganismos.retornaOrganismos(tipoOrganismoSelecionado, inputPesquisaOrganismo);
    }

    public void listarPessoasQueNaoEstaoNesseOrganismo() {
        //Se o organismo esta sendo criado agora, retornar todas as pessoas
        if (organismo.getIdOrganismo() == null) {
            pessoas = repositorioPessoas.retornaTodasAsPessoas();
        } else {
            pessoas = repositorioPessoas.retornaPessoasQueNãoMembrosDoOrganismo(organismo);
        }
    }

    public void listarMembros() {
        membros = organismo.getMembros();
        if (membros == null) {
            membros = new ArrayList<>();
        }
    }

    public void adicionarMembro(Pessoa pessoa) {
        Membro membro = new Membro();
        membro.setPessoa(pessoa);
        membro.setOrganismo(organismo);
        membro.setFuncao(null);
        membros.add(membro);
        pessoas.remove(pessoa);
    }

    public void removerMembro(Membro membro) {
        membros.remove(membro);

        //atualizar a lista de Pessoas
    }
    
    public void teste(){
        System.out.println("OLHA: "+ organismo.getTipoOrganismo());
    }

}

//    public void onDrop(DragDropEvent ddEvent) {
//        Pessoa pessoa = ((Pessoa) ddEvent.getData());
//        Membro membro = new Membro();
//        membro.setPessoa(pessoa);
//        membro.setOrganismo(organismo);
//        membro.setFuncao(null);
//        membros.add(membro);
//        pessoas.remove(pessoa);
//    }
//    
