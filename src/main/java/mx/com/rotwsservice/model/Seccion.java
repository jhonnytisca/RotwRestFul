/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name="seccion")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@idSeccion")
public class Seccion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idSeccion;
    private String seccion;
    
    /** Constructores */
    public Seccion(){}

    public Seccion(int idSeccion, String seccion) {
        this.idSeccion = idSeccion;
        this.seccion = seccion;
    }

    /** Getters y Setters */
    @Id
    @Column(name="id_seccion")
    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    @Column(name="seccion")
    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    
}
