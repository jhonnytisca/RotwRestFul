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
@Table(name="tipo_vivienda")
public class TipoVivienda implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idTipoVivienda;
    private String nombre;
    
    /** Constructores */
    public TipoVivienda(){}

    public TipoVivienda(int idTipoVivienda, String nombre) {
        this.idTipoVivienda = idTipoVivienda;
        this.nombre = nombre;
    }

    /** Getters y Setters */
    @Id
    @Column(name="id_tipo_vivienda" , nullable = false)
    public int getIdTipoVivienda() {
        return idTipoVivienda;
    }

    public void setIdTipoVivienda(int idTipoVivienda) {
        this.idTipoVivienda = idTipoVivienda;
    }

    @Column(name="nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
