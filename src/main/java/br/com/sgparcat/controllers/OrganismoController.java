/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Organismo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author pedrohensanches
 */

@ManagedBean(name="OrganismoController")
@ViewScoped
public class OrganismoController implements Serializable {
    
    private List<Organismo> organismos;
    
    @PostConstruct
    public void init() {
        organismos = new ArrayList<>();
        for(int i = 0 ; i < 35 ; i++) {
            Organismo o = new Organismo(3432, "Nome Organismo", Organismo.TipoOrganismo.MOVIMENTO);
            organismos.add(o);
        }
    }
 
    public List<Organismo> getOrganismos() {
        return organismos;
    }
}
