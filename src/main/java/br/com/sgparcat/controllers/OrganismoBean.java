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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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

    @PostConstruct
    public void OrganismoBean() {
        listarPessoasQueNaoEstaoNesseOrganismo();
        listarMembros();
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public String getInputPesquisaPessoa() {
        return inputPesquisaPessoa;
    }

    public String getInputPesquisaMembro() {
        return inputPesquisaMembro;
    }

    public void salvar() throws IOException {
        System.out.println("SALVOU");
        //organismoService.salvar(organismo);
        //FacesUtil.addInfoMessage("O organismo " + organismo.getNome() + " foi criado com sucesso");
        //limpar();
    }
    
    public void fecharDialogoERedirecionar() throws IOException {
        RequestContext.getCurrentInstance().closeDialog(null);
        FacesContext.getCurrentInstance().getExternalContext().redirect("adicionar.xhtml");
    }
    
    public String redirecionar(){
        System.out.println("ENTROU NO METODO REDIRECIONAR");
        RequestContext.getCurrentInstance().closeDialog(null);
        return "/organismos/adicionar?faces-redirect=true";
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

    public void PesquisarPessoa() {
    }

    public void PesquisarMembro() {
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

    public void preCadastro() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 320);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        RequestContext.getCurrentInstance().openDialog("pre-cadastro", options, null);
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
//    public void teste(){
//        Pessoa pessoa = pessoas.get(2);
//        Membro membro = new Membro();
//        membro.setPessoa(pessoa);
//        membro.setOrganismo(organismo);
//        membro.setFuncao(null);        
//        pessoas.remove(2);
//        membros.add(membro);
//    }
