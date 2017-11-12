/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.security;

import br.com.sgparcat.models.Pessoa;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author pedrohensanches
 */
public class UsuarioSistema extends User{

    private static final long serialVersionUID = 1L;
    
    public UsuarioSistema(Pessoa pessoa, Collection<? extends GrantedAuthority> authorities) {
        super(pessoa.getEmail(), pessoa.getSenha(), authorities);
        this.pessoa = pessoa;
    }
    
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }
    
    
}
