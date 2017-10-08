/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import br.com.sgparcat.models.Funcao;
import br.com.sgparcat.models.Pessoa;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pedrohensanches
 */
public class TestandoBanco {
    
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sgparcat");
        EntityManager manager = factory.createEntityManager();
        
        EntityTransaction transa = manager.getTransaction();
        transa.begin();
        
        System.out.println("Chegou aqui");
        
        
        Funcao f = new Funcao();
        f.setTitulo("Secretário");
        manager.persist(f);
        
        Pessoa paroquiano = new Pessoa();
        paroquiano.setFuncao(f);
        paroquiano.setTipoPessoa(Pessoa.TipoPessoa.PAROQUIANO);
        paroquiano.setCpf("09404551961");
        paroquiano.setDataNascimento(new Date());
        paroquiano.setEmail("pedrohenrique@hotmail.com");
        paroquiano.setEstadoCivil('C');
        paroquiano.setSexo('M');
        paroquiano.setIsDizimista(1);
        paroquiano.setIsDizimistaAtivo(0);
        paroquiano.setRg("83323884");
        paroquiano.setNomeCompleto("Pedro Sanches");
        
        manager.persist(paroquiano);
        transa.commit();


//    public Pessoa(Long idPessoa, TipoPessoa tipoPessoa, String nomeCompleto, Character sexo, Character estadoCivil, Date dataNascimento, String cpf, boolean isDizimista, boolean isDizimistaAtivo, Funcao funcao) {
//        this.idPessoa = idPessoa;
//        this.tipoPessoa = tipoPessoa;
//        this.nomeCompleto = nomeCompleto;
//        this.sexo = sexo;
//        this.estadoCivil = estadoCivil;
//        this.dataNascimento = dataNascimento;
//        this.cpf = cpf;
//        this.isDizimista = isDizimista;
//        this.isDizimistaAtivo = isDizimistaAtivo;
//        this.funcao = funcao;
//    }
        
    }
}
