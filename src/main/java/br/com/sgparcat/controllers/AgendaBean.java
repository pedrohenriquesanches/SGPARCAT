/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Evento;
import br.com.sgparcat.repositories.Eventos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author pedrohensanches
 */
@Named
@ViewScoped
public class AgendaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Eventos repositorioEventos;

    private ScheduleModel listagem;

    private List<Evento> agendasPesquisados;
    
    @Inject
    private Evento evento;
  
    public AgendaBean() {
        agendasPesquisados = new ArrayList<>();
        evento = new Evento();
        listagem = new DefaultScheduleModel();
    }

    public ScheduleModel getListagem() {
        return listagem;
    }

    public void setListagem(ScheduleModel listagem) {
        this.listagem = listagem;
    }

    public List<Evento> getAgendasPesquisados() {
        return agendasPesquisados;
    }

    public void setAgendasPesquisados(List<Evento> agendasPesquisados) {
        this.agendasPesquisados = agendasPesquisados;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public void buscar() {
        agendasPesquisados = repositorioEventos.retornaTodosEventos();
        eventos();
    }

    private void eventos() {
        listagem = new DefaultScheduleModel();
        for (Evento tmp : agendasPesquisados) {
            listagem.addEvent(new DefaultScheduleEvent(tmp.getTitulo(), tmp.getDataInicio(), tmp.getDataFim()));
        }
    }
    
    public void limpar(CloseEvent event) {
        evento = new Evento();
    } 

}
