/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.converters;

import br.com.sgparcat.cdi.CDIServiceLocator;
import br.com.sgparcat.models.Evento;
import br.com.sgparcat.repositories.Eventos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author pedrohensanches
 */
@FacesConverter(forClass = Evento.class)
public class EventoConverter implements Converter {

    private Eventos repositorioEventos;

    public EventoConverter() {
        this.repositorioEventos = CDIServiceLocator.getBean(Eventos.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        if (!value.matches("\\d+")) {
            throw new ConverterException("O valor não é um ID válido: " + value);
        }

        Integer id = new Integer(value);
        return repositorioEventos.retornaPorId(id);
    }
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }

        if (!(value instanceof Evento)) {
            throw new ConverterException("Esse valor não é um Evento Válido: " + value);
        }

        Integer id = ((Evento) value).getIdEvento();
        return (id != null) ? String.valueOf(id) : null;
    }
}
