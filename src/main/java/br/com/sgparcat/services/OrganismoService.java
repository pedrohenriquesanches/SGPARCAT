/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.services;

import br.com.sgparcat.models.Organismo;
import br.com.sgparcat.repositories.Organismos;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author pedrohensanches
 */
public class OrganismoService implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Organismos repositorioOrganismos;
    
    public void salvar(Organismo organismo){
        repositorioOrganismos.guardar(organismo);
    }
    
    public void excluir(Organismo organismo){
        repositorioOrganismos.remover(organismo);
    }
    
}
