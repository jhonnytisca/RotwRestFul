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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marco Villa
 */
@Entity
@Table(name="cuarto")
public class Cuarto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer idCuarto;
    private Vivienda idVivienda;
    private Double precio;
    private String titulo;
    private String descripcion;
    private Date fechaInicion;
    private Date fechaFin;
    private Double m2;
    private EstatusVivienda idEstatusVivienda;
    private String portada;
    private Date altaCuarto;
    
    public Cuarto(){}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_cuarto")
    public Integer getIdCuarto() {
        return idCuarto;
    }

    public void setIdCuarto(Integer idCuarto) {
        this.idCuarto = idCuarto;
    }

    @ManyToOne
    @JoinColumn(name="id_vivienda")
    public Vivienda getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(Vivienda idVivienda) {
        this.idVivienda = idVivienda;
    }

    @Column(name="precio")
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Column(name="titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name="descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name="fecha_inicio")
    @Temporal(TemporalType.DATE)
    public Date getFechaInicion() {
        return fechaInicion;
    }

    public void setFechaInicion(Date fechaInicion) {
        this.fechaInicion = fechaInicion;
    }

    @Column(name="fecha_fin")
    @Temporal(TemporalType.DATE)
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Column(name="m2")
    public Double getM2() {
        return m2;
    }

    public void setM2(Double m2) {
        this.m2 = m2;
    }

    @OneToOne
    @JoinColumn(name="id_estatus_vivienda")
    public EstatusVivienda getIdEstatusVivienda() {
        return idEstatusVivienda;
    }

    public void setIdEstatusVivienda(EstatusVivienda idEstatusVivienda) {
        this.idEstatusVivienda = idEstatusVivienda;
    }

    @Column(name="portada")
    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    @Column(name="alta_cuarto", insertable = false)
    @Temporal(TemporalType.DATE)
    public Date getAltaCuarto() {
        return altaCuarto;
    }

    public void setAltaCuarto(Date altaCuarto) {
        this.altaCuarto = altaCuarto;
    }
    
}