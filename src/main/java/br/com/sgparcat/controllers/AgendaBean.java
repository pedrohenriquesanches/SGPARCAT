/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Evento;
import br.com.sgparcat.repositories.Eventos;
import br.com.sgparcat.services.EventoService;
import br.com.sgparcat.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
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

    @Inject
    private EventoService eventoService;
    
    @Inject
    private Evento evento;

    private ScheduleModel eventos;


    @PostConstruct
    public void AgendaBean() {
        evento = new Evento();
        listarEventos();
    }

    public ScheduleModel getEventos() {
        return eventos;
    }

    public void setEventos(ScheduleModel eventos) {
        this.eventos = eventos;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void salvar() {
        //Boolean estaEditando = evento.getIdEvento() != null;        
        evento = eventoService.salvar(evento);
        listarEventos();
//        if (estaEditando) {
//            FacesUtil.addInfoMessage("dialogMessages", "Evento editado com sucesso!");
//        } else {
//            FacesUtil.addInfoMessage("dialogMessages", "O evento foi adicionado com sucesso!");
//        }
    }

    public void remover() {
        eventoService.excluir(evento);
        //FacesUtil.addInfoMessage("messages", "O evento foi removido com sucesso!");
        limpar(null);
        listarEventos();
    }

    public void listarEventos() {
        eventos = new DefaultScheduleModel();
        for (Evento tmp : repositorioEventos.retornaTodosEventos()) {
            eventos.addEvent(new DefaultScheduleEvent(tmp.getTitulo(), tmp.getDataInicio(), tmp.getDataFim()));
        }
    }

    public void limpar(CloseEvent event) {
        evento = new Evento();
    }

    public void onDateSelect(SelectEvent dataSelecionada) {
        evento.setDataInicio((Date) dataSelecionada.getObject());
        evento.setDataFim((Date) dataSelecionada.getObject());
    }

    public void onEventSelect(SelectEvent selectEvent) {
        evento = repositorioEventos.retornaPorNomeEData((ScheduleEvent) selectEvent.getObject());
    }

    public String textoBotaoParticipantes(){
        if(evento.getParticipantes() == null || evento.getParticipantes().isEmpty()){
            return "Adicionar Participantes";
        } else {
            return "Gerenciar Participantes";
        }
    }
    
    public Boolean disponibilidadeBotaoParticipantes(){
        return evento.getIdEvento() == null;
    }
    
    public String gerenciarParticipantes(){
        evento = eventoService.salvar(evento);
        FacesUtil.addInfoMessage("dialogMessages","O evento foi adicionado com sucesso!");
        //Thread.sleep(2000);
        return "/calendario/participantes?evento=" + evento.getIdEvento() + "&faces-redirect=true";
    }
    
    public List<Evento> pesquisarEvento(String input){
        return repositorioEventos.retornaTodosOsEventos(input);
    }

}
