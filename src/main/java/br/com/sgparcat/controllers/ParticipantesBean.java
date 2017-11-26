/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Evento;
import br.com.sgparcat.models.Participante;
import br.com.sgparcat.models.Pessoa;
import br.com.sgparcat.repositories.Participantes;
import br.com.sgparcat.repositories.Pessoas;
import br.com.sgparcat.services.EventoService;
import br.com.sgparcat.services.ParticipanteService;
import br.com.sgparcat.util.jsf.FacesUtil;
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
public class ParticipantesBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private EventoService eventoService;
    
    @Inject
    private ParticipanteService participanteService;
    
    @Inject
    Pessoas repositorioPessoas;
    
    @Inject
    Participantes repositorioParticipantes;

    @Inject
    private Evento evento;
    
    private List<Pessoa> pessoas;

    private String inputPesquisaPessoa;

    private String inputPesquisaParticipante;

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public String getInputPesquisaPessoa() {
        return inputPesquisaPessoa;
    }

    public void setInputPesquisaPessoa(String inputPesquisaPessoa) {
        this.inputPesquisaPessoa = inputPesquisaPessoa;
    }

    public String getInputPesquisaParticipante() {
        return inputPesquisaParticipante;
    }

    public void setInputPesquisaParticipante(String inputPesquisaParticipante) {
        this.inputPesquisaParticipante = inputPesquisaParticipante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public void filtrarPessoasQueNaoParticipamDesseEvento() {
        //Se o evento não tem participantes, retornar todas as pessoas
        if (evento.getParticipantes() == null || evento.getParticipantes().isEmpty()) {
            pessoas = repositorioPessoas.retornaTodasAsPessoas();
        } else {
            pessoas = repositorioPessoas.retornaPessoasQueNaoSaoParticipantes(evento, inputPesquisaPessoa);
        }
    }
    
    public void filtrarParticipantes(){
        evento.setParticipantes(repositorioParticipantes.pesquisarParticipante(evento, inputPesquisaParticipante));
    }

    public void adicionarParticipante(Pessoa pessoa) {
        if (evento.getIdEvento() == null) {
            FacesUtil.addWarMessage(null,"Primeiro crie e salve o evento para poder adicionar participantes");
        } else {
            Participante participante = new Participante(evento, pessoa);
            participanteService.salvar(participante);
            pessoas.remove(pessoa);
            filtrarParticipantes();
        }
    }

    public void removerParticipante(Participante participante) {
        pessoas.add(participante.getPessoa());
        evento.getParticipantes().remove(participante);
        if (participante.getIdParticipante() != null) {
            participanteService.excluir(participante);
        }
    }
    
    public void salvar() {
        Boolean estaEditando = evento.getIdEvento() != null;

        evento = eventoService.salvar(evento);

        if (estaEditando) {
            FacesUtil.addInfoMessage(null, "Evento editado com sucesso!");
        } else {
            FacesUtil.addInfoMessage(null, "O evento foi adicionado com sucesso!");
        }
    }
    
}
