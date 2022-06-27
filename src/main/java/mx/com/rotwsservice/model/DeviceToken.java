/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mvillavicencio
 */
@Entity
@Table(name="devicetoken")
public class DeviceToken implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String ftoken;
    private Usuario usuario;
    private Date fechaCreacion;
    private Date fechaUltimaConexion;
    private Integer estatus;

    @Id
    @Column(name="ftoken")
    public String getFtoken() {
        return ftoken;
    }

    public void setFtoken(String ftoken) {
        this.ftoken = ftoken;
    }

    @Id
    @ManyToOne
    @JoinColumn(name="id_usuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaUltimaConexion() {
        return fechaUltimaConexion;
    }

    public void setFechaUltimaConexion(Date fechaUltimaConexion) {
        this.fechaUltimaConexion = fechaUltimaConexion;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
    
    
}
