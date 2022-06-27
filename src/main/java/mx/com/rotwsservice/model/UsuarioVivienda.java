/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marco Villa
 */
@Entity
@Table(name="usuario_vivienda")
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property = "@idUsuarioVivienda")
public class UsuarioVivienda implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private UsuarioViviendaId usuarioViviendaId;
    private Usuario usuario;
    private Vivienda vivienda;
    private Date fechaAlta;
    private Date fechaInicio;
    private Date fechaFin;
    
    /** Constructores */
    public UsuarioVivienda(){}

    public UsuarioVivienda(UsuarioViviendaId usuarioViviendaId, Usuario usuario, Vivienda vivienda, Date fechaAlta, Date fechaInicio, Date fechaFin) {
        this.usuarioViviendaId = usuarioViviendaId;
        this.usuario = usuario;
        this.vivienda = vivienda;
        this.fechaAlta = fechaAlta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    /** Getters y Setters */
    @EmbeddedId
    public UsuarioViviendaId getUsuarioViviendaId() {
        return usuarioViviendaId;
    }

    public void setUsuarioViviendaId(UsuarioViviendaId usuarioViviendaId) {
        this.usuarioViviendaId = usuarioViviendaId;
    }
    
    @ManyToOne
    @JoinColumn(name="id_usuario", insertable=false, updatable=false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name="id_vivienda", insertable=false, updatable=false)
    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    @Column(name = "fecha_alta", columnDefinition="DATETIME",updatable=false)
    @Temporal(TemporalType.DATE)
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Column(name = "fecha_inicio", columnDefinition="DATETIME",updatable=false)
    @Temporal(TemporalType.DATE)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Column(name = "fecha_fin", columnDefinition="DATETIME",updatable=false)
    @Temporal(TemporalType.DATE)
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}
