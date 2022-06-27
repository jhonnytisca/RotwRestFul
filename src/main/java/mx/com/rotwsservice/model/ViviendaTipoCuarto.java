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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author Marco Villa
 */
@Entity
@Table(name="vivienda_tipo_cuarto")
public class ViviendaTipoCuarto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private ViviendaTipoCuartoId viviendaTipoCuartoId;
    private Vivienda vivienda;
    private TipoCuarto tipoCuarto;
    private int cantidad;
    
    public ViviendaTipoCuarto(){}

    public ViviendaTipoCuarto(ViviendaTipoCuartoId viviendaTipoCuartoId, Vivienda vivienda, TipoCuarto tipoCuarto) {
        this.viviendaTipoCuartoId = viviendaTipoCuartoId;
        this.vivienda = vivienda;
        this.tipoCuarto = tipoCuarto;
    }

    @JsonIgnore
    @EmbeddedId
    public ViviendaTipoCuartoId getViviendaTipoCuartoId() {
        return viviendaTipoCuartoId;
    }

    public void setViviendaTipoCuartoId(ViviendaTipoCuartoId viviendaTipoCuartoId) {
        this.viviendaTipoCuartoId = viviendaTipoCuartoId;
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
    @JoinColumn(name="id_tipo_cuarto",insertable = false,updatable = false)
    public TipoCuarto getTipoCuarto() {
        return tipoCuarto;
    }

    public void setTipoCuarto(TipoCuarto tipoCuarto) {
        this.tipoCuarto = tipoCuarto;
    }

    @Column(name="cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
