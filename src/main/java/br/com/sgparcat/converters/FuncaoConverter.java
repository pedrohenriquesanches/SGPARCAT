/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.converters;

import br.com.sgparcat.cdi.CDIServiceLocator;
import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.repositories.Funcoes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author pedrohensanches
 */
@FacesConverter(forClass = Funcao.class,value="funcaoConverter")
public class FuncaoConverter implements Converter {

    private Funcoes repositorioFuncoes;

    public FuncaoConverter() {
        this.repositorioFuncoes = CDIServiceLocator.getBean(Funcoes.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        
        if(value.equals("-1")){
            return new Funcao();
        }
        
//        if(value.equals("-2")){
//            return repositorioFuncoes.retornaFuncaoPadrãoParaMembro();
//        }
        
        if (!value.matches("\\d+")) {
            throw new ConverterException("O valor não é um ID válido: " + value);
        }        
        
        Integer id = new Integer(value);
        return repositorioFuncoes.retornaPorId(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }
        
        if (value.equals("-1")){
            return "-1";
        }
        
        if (!(value instanceof Funcao)) {
            throw new ConverterException("Esse valor não é uma Função Válida: " + value);
        }     

        Integer id = ((Funcao) value).getIdFuncao();
        return (id != null) ? String.valueOf(id) : null;
    }
}
