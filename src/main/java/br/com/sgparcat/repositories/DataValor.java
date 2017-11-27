/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.repositories;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author pedrohensanches
 */
public class DataValor implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Date data;
    private BigDecimal valor;

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
}