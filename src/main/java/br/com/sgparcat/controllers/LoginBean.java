package br.com.sgparcat.controllers;

import br.com.sgparcat.util.jsf.FacesUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String email;
    
    @Inject
    private FacesContext facesContext;
    
    @Inject
    private HttpServletRequest request;
    
    @Inject
    private HttpServletResponse response;

    /* metodo para efetuar login */
    public void login() throws ServletException, IOException{
        // formulario de login do spring security
        RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward(request, response); // delegando as responsabilidades
        
        // encerrando o contexto do JSF
        facesContext.responseComplete();
    
    }
    
    // verificando parametros 
    public void preRender(){
        if("true".equals(request.getParameter("invalid"))){
            FacesUtil.addErrorMessage("Usuário ou senha inválido!", "Usuário ou senha inválido!");
        }
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
