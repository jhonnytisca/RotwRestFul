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
@Table(name="sede")
public class Sede implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idSede;
    private String nombreSede;
    private String direcion;
    
    /** Constructores */
    public Sede(){}

    public Sede(int idSede, String nombreSede, String direcion) {
        this.idSede = idSede;
        this.nombreSede = nombreSede;
        this.direcion = direcion;
    }


    /** Getters y Setters */
    @Id
    @Column(name="id_sede")
    public int getIdSede() {
        return idSede;
    }
    
    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    @Column(name="nombre_sede")
    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    @Column(name="direccion")
    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }
    
    
    
}
