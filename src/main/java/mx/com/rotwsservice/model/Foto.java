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
@Table (name = "foto")
public class Foto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idFotos;
    private String path;
    
    /** Constructores */
    public Foto(){}

    public Foto(int idFotos, String path) {
        this.idFotos = idFotos;
        this.path = path;
    }
    
    /** Getters y setters */
    @Id
    @Column(name="id_fotos")
    public int getIdFotos() {
        return idFotos;
    }
    
    public void setIdFotos(int idFotos) {
        this.idFotos = idFotos;
    }

    @Column(name="path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
