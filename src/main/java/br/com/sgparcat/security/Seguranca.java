/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.security;

import javax.enterprise.inject.Produces;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author pedrohensanches
 */
@Named
@RequestScoped
public class Seguranca {
    
    @Inject
    private ExternalContext externalContext;
    
    public String getNomeUsuarioLogado(){
        String nome = null;
        UsuarioSistema usuarioSistema = getUsuarioLogado();
        
        if(usuarioSistema != null){
            nome = usuarioSistema.getPessoa().getNomeCompleto();
        }
        
        return nome;
    }
    
    @Produces
    @UsuarioLogado
    public UsuarioSistema getUsuarioLogado(){
        UsuarioSistema usuario = null;
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)
                FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        
        if (auth != null && auth.getPrincipal() != null){
            usuario = (UsuarioSistema) auth.getPrincipal();
        }
        
        return usuario;
    }
    
}
