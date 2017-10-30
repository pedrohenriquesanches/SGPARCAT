/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pedrohensanches
 */
@Entity
@Table(name = "organismo")
public class Organismo implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum TipoOrganismo {
        GRUPO_DE_ORACAO("Grupo de oração"), MOVIMENTO("Movimento"), PASTORAL("Pastoral"), CONSELHO("Conselho");

        private final String label;

        TipoOrganismo(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrganismo;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoOrganismo tipoOrganismo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organismo")
    private List<Membro> membros;

    public Organismo() {
    }

    public Organismo(Integer idOrganismo, String nome, TipoOrganismo tipoOrganismo) {
        this.idOrganismo = idOrganismo;
        this.nome = nome;
        this.tipoOrganismo = tipoOrganismo;
    }

    public Integer getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoOrganismo getTipoOrganismo() {
        return tipoOrganismo;
    }

    public void setTipoOrganismo(TipoOrganismo tipoOrganismo) {
        this.tipoOrganismo = tipoOrganismo;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idOrganismo);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.tipoOrganismo);
        hash = 79 * hash + Objects.hashCode(this.membros);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organismo other = (Organismo) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.idOrganismo, other.idOrganismo)) {
            return false;
        }
        if (this.tipoOrganismo != other.tipoOrganismo) {
            return false;
        }
        if (!Objects.equals(this.membros, other.membros)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Organismo{" + "idOrganismo=" + idOrganismo + ", nome=" + nome + ", tipoOrganismo=" + tipoOrganismo + '}';
    }

}
