/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author pedrohensanches
 */
public class FacesUtil {
    
    /* metodo que verifica se a requisicao é postBack (se ja foi renderizada) */
    public static boolean isPostback(){
        return FacesContext.getCurrentInstance().isPostback();
    }
    
    public static boolean isNotPostback(){
        return !isPostback();
    }
    
    public static void addErrorMessage(String id, String message){
        FacesContext.getCurrentInstance().addMessage
        (id, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
    
    public static void addInfoMessage(String id, String message){
        FacesContext.getCurrentInstance().addMessage
        (id, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
    
    public static void addWarMessage(String id, String message){
        FacesContext.getCurrentInstance().addMessage
        (id, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
    }
}
