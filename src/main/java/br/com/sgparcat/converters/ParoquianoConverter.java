/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.converters;

import br.com.sgparcat.models.Pessoa;
import br.com.sgparcat.repositories.Pessoas;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author pedrohensanches
 */

@FacesConverter(forClass = Pessoa.class)
public class ParoquianoConverter implements Converter{
    
    private Pessoas repositorioPessoas = new Pessoas();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Pessoa retorno = null;
        if(string != null){
            Long id = new Long(string);
            retorno = repositorioPessoas.retornaPorId(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o != null){
            return ((Pessoa) o).getIdPessoa().toString();
        }
        return "";
    }
}
