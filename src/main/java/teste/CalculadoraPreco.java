/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.Serializable;
import javax.enterprise.context.Dependent;

/**
 *
 * @author pedrohensanches
 */
@Dependent
public class CalculadoraPreco implements Serializable{

    private static final long serialVersionUID = 1L;
    
    public double CalcularPreco(int qtd, double precouni){
        return qtd * precouni;
    }
    
}
