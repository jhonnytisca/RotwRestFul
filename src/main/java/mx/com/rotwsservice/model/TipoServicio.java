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
@Table(name="tipo_servicio")
public class TipoServicio implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int idTipoServicio;
    private String nombreTipoServicio;
    private String descripcion;
    private String path;
    
    public TipoServicio(){}

    public TipoServicio(int idTipoServicio, String nombreTipoServicio, String descripcion) {
        this.idTipoServicio = idTipoServicio;
        this.nombreTipoServicio = nombreTipoServicio;
        this.descripcion = descripcion;
    }

    @Id
    @Column(name="id_tipo_servicio")
    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    @Column(name="nombre_tipo_servicio")
    public String getNombreTipoServicio() {
        return nombreTipoServicio;
    }

    public void setNombreTipoServicio(String nombreTipoServicio) {
        this.nombreTipoServicio = nombreTipoServicio;
    }

    @Column(name="descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name="path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}