/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author pedrohensanches
 */
@Named
@SessionScoped
public class PrecoProdutoBean implements Serializable{

    private static final long serialVersionUID = 1L;
    @Inject
    private CalculadoraPreco calculadora;
    
    public double getPreco(){
        return calculadora.CalcularPreco(10, 2.5);
    }
    
}
