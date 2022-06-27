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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marco Villa
 */
@Entity
@Table(name="historial")
public class Historial implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idHistorial;
    private Usuario usuario;
    private String descripcion;
    private TipoEntradaHistorial tipoEntradaHistorial;
    private Date fechaMovimiento;
    
    /** Constructores */
    public Historial() {
    }

    public Historial(int idHistorial, Usuario usuario, String descripcion, TipoEntradaHistorial tipoEntradaHistorial) {
        this.idHistorial = idHistorial;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.tipoEntradaHistorial = tipoEntradaHistorial;
    }

    /** Getters y Setters
     * @return  */
    @Id
    @Column(name="id_historial")
    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    @OneToOne
    @JoinColumn(name="id_usuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name="desripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @OneToOne
    @JoinColumn(name="id_tipo_entrada_historial")
    public TipoEntradaHistorial getTipoEntradaHistorial() {
        return tipoEntradaHistorial;
    }

    public void setTipoEntradaHistorial(TipoEntradaHistorial tipoEntradaHistorial) {
        this.tipoEntradaHistorial = tipoEntradaHistorial;
    }

    @Column(name = "fecha_movimiento", nullable = true)
    @Temporal(TemporalType.DATE)
    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
    
}