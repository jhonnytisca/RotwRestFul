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
@Table(name = "estatus_vivienda")
public class EstatusVivienda implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int idEstatusVivienda;
    private String nombre;
    private String descripcion;

    public EstatusVivienda() {
    }

    public EstatusVivienda(int idEstatusVivienda, String nombre, String descripcion) {
        this.idEstatusVivienda = idEstatusVivienda;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Id
    @Column(name = "id_estatus_vivienda" , nullable = false)
    public int getIdEstatusVivienda() {
        return idEstatusVivienda;
    }

    public void setIdEstatusVivienda(int idEstatusVivienda) {
        this.idEstatusVivienda = idEstatusVivienda;
    }

    @Column(name = "nombre" , nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "descripcion" , nullable = true)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
     
    
}
