/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Marco Villa
 */
@Entity
@Table(name="vivienda_tipo_servicio")
public class ViviendaTipoServicio implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private ViviendaTipoServicioId viviendaTipoServicioId;
    private Vivienda vivienda;
    private TipoServicio tipoServicio;
    private boolean incluido;
    private double costoExtra;
    
    public ViviendaTipoServicio(){}

    public ViviendaTipoServicio(ViviendaTipoServicioId viviendaTipoServicioId, Vivienda vivienda, TipoServicio tipoServicio, boolean incluido, double costoExtra) {
        this.viviendaTipoServicioId = viviendaTipoServicioId;
        this.vivienda = vivienda;
        this.tipoServicio = tipoServicio;
        this.incluido = incluido;
        this.costoExtra = costoExtra;
    }

    @EmbeddedId
    public ViviendaTipoServicioId getViviendaTipoServicioId() {
        return viviendaTipoServicioId;
    }

    public void setViviendaTipoServicioId(ViviendaTipoServicioId viviendaTipoServicioId) {
        this.viviendaTipoServicioId = viviendaTipoServicioId;
    }

    @ManyToOne
    @JoinColumn(name="id_vivienda",insertable = false,updatable = false)
    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    @ManyToOne
    @JoinColumn(name="id_tipo_servicio",insertable = false,updatable = false)
    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Column(name="incluido")
    public boolean isIncluido() {
        return incluido;
    }

    public void setIncluido(boolean incluido) {
        this.incluido = incluido;
    }

    @Column(name="costo_extra")
    public double getCostoExtra() {
        return costoExtra;
    }

    public void setCostoExtra(double costoExtra) {
        this.costoExtra = costoExtra;
    }
       
}