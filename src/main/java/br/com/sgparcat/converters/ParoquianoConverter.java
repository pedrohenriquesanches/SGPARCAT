/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.converters;

import br.com.sgparcat.cdi.CDIServiceLocator;
import br.com.sgparcat.models.Pessoa;
import br.com.sgparcat.repositories.Pessoas;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author pedrohensanches
 */
@FacesConverter(forClass = Pessoa.class)
public class ParoquianoConverter implements Converter {

    private Pessoas repositorioPessoas;

    public ParoquianoConverter() {
        this.repositorioPessoas = CDIServiceLocator.getBean(Pessoas.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        if (!value.matches("\\d+")) {
            throw new ConverterException("O valor não é ID válido: " + value);
        }

        Long id = new Long(value);
        return repositorioPessoas.retornaPorId(id);
    }
    
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }

        if (!(value instanceof Pessoa)) {
            throw new ConverterException("Esse valor não é uma Pessoa Válida: " + value);
        }

        Long id = ((Pessoa) value).getIdPessoa();
        return (id != null) ? String.valueOf(id) : null;
    }
}
