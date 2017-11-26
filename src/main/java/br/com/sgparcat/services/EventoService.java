/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.services;

import br.com.sgparcat.models.Evento;
import br.com.sgparcat.repositories.Eventos;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author pedrohensanches
 */
public class EventoService implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Eventos repositorioEventos;
    
    public Evento salvar(Evento evento){
        evento.setDataRegistro(new Date());
        return repositorioEventos.guardar(evento);
    }
    
    public void excluir(Evento evento){
        repositorioEventos.remover(evento);
    }
    
}
