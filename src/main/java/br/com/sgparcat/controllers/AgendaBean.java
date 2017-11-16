/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Evento;
import br.com.sgparcat.repositories.Eventos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    private ScheduleModel scheduleModelEventos;

    private List<Evento> eventos;

    @PostConstruct
    public void AgendaBean() {
        listarEventos();
        carregarScheduleModelEventos();
    }

    public ScheduleModel getScheduleModelEventos() {
        return scheduleModelEventos;
    }

    public void setScheduleModelEventos(ScheduleModel scheduleModelEventos) {
        this.scheduleModelEventos = scheduleModelEventos;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    public void listarEventos() {
        eventos = repositorioEventos.retornaTodosEventos();
    }

    private void carregarScheduleModelEventos() {
        scheduleModelEventos = new DefaultScheduleModel();
        for (Evento evento : eventos) {
            scheduleModelEventos.addEvent(new DefaultScheduleEvent(evento.getTitulo(), evento.getDataInicio(), evento.getDataFim()));
        }
    }

}
