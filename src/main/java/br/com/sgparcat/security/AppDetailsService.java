/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.security;

import br.com.sgparcat.cdi.CDIServiceLocator;
import br.com.sgparcat.models.Pessoa;
import br.com.sgparcat.repositories.Pessoas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author pedrohensanches
 */
public class AppDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoas repositorioPessoas = CDIServiceLocator.getBean(Pessoas.class);
        Pessoa pessoa = repositorioPessoas.retornaPorEmail(email);

        UsuarioSistema user = null;

        if (pessoa != null) {
            user = new UsuarioSistema(pessoa, getGrupo(pessoa));
        }
        
        return user;        
    }
    
    private Collection<? extends GrantedAuthority> getGrupo(Pessoa pessoa){
        List<SimpleGrantedAuthority> grupo = new ArrayList<>();
        grupo.add(new SimpleGrantedAuthority(pessoa.getFuncao().getTitulo()));
        return grupo;
    }

}
