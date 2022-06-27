/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Marco Villa
 */
@Entity 
@Table(name="gusto")
public class Gusto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idGusto;
    private String gusto;
    
    /** Constructores */
    public Gusto(){}
    
    public Gusto(int idGusto, String gusto) {
        this.idGusto = idGusto;
        this.gusto = gusto;
    }

    /** Getters y Setters */
    @Id
    @Column(name="id_gusto")
    public int getIdGusto() {
        return idGusto;
    }

    public void setIdGusto(int idGusto) {
        this.idGusto = idGusto;
    }

    @Column (name="gusto")
    public String getGusto() {
        return gusto;
    }

    public void setGusto(String gusto) {
        this.gusto = gusto;
    }
}
