/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.converters;

import br.com.sgparcat.cdi.CDIServiceLocator;
import br.com.sgparcat.models.Contribuicao;
import br.com.sgparcat.repositories.Contribuicoes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author pedrohensanches
 */
@FacesConverter(forClass = Contribuicao.class)
public class ContribuicaoConverter implements Converter {

    private Contribuicoes repositorioContribuicoes;

    public ContribuicaoConverter() {
        this.repositorioContribuicoes = CDIServiceLocator.getBean(Contribuicoes.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        
        if (!value.matches("\\d+")) {
            throw new ConverterException("O valor não é um número de contribuição válido: " + value);
        }        
        
        Long numeroContribuicao = new Long(value);
        return repositorioContribuicoes.retornaPorNumeroContribuicao(numeroContribuicao);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }
        
        if (!(value instanceof Contribuicao)) {
            throw new ConverterException("Esse valor não é uma Cotnribuicão Válida: " + value);
        }     

        Long numeroContribuicao = ((Contribuicao) value).getNumeroContribuicao();
        return (numeroContribuicao != null) ? String.valueOf(numeroContribuicao) : null;
    }
}
