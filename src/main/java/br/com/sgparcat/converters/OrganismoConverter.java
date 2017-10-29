/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.converters;

import br.com.sgparcat.cdi.CDIServiceLocator;
import br.com.sgparcat.models.Organismo;
import br.com.sgparcat.repositories.Organismos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author pedrohensanches
 */
@FacesConverter(forClass = Organismo.class)
public class OrganismoConverter implements Converter {

    private Organismos repositorioOrganismos;

    public OrganismoConverter() {
        this.repositorioOrganismos = CDIServiceLocator.getBean(Organismos.class);
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
        return repositorioOrganismos.retornaPorId(id);
    }
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }

        if (!(value instanceof Organismo)) {
            throw new ConverterException("Esse valor não é um Organismo Válido: " + value);
        }

        Integer id = ((Organismo) value).getIdOrganismo();
        return (id != null) ? String.valueOf(id) : null;
    }
}
