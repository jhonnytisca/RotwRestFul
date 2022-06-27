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
@Table(name="tipo_entrada_historial")
public class TipoEntradaHistorial implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idTipoEntradaHistorial;
    private String tipoEntradaHistorial;
    
    /** Constructores */
    public TipoEntradaHistorial (){}

    public TipoEntradaHistorial(int idTipoEntradaHistorial, String tipoEntradaHistorial) {
        this.idTipoEntradaHistorial = idTipoEntradaHistorial;
        this.tipoEntradaHistorial = tipoEntradaHistorial;
    }

    /** Getters y Setters */
    @Id
    @Column(name="id_tipo_entrada_historial")
    public int getIdTipoEntradaHistorial() {
        return idTipoEntradaHistorial;
    }

    public void setIdTipoEntradaHistorial(int idTipoEntradaHistorial) {
        this.idTipoEntradaHistorial = idTipoEntradaHistorial;
    }

    @Column(name="tipo_entrada_historial")
    public String getTipoEntradaHistorial() {
        return tipoEntradaHistorial;
    }

    public void setTipoEntradaHistorial(String tipoEntradaHistorial) {
        this.tipoEntradaHistorial = tipoEntradaHistorial;
    }
    
}
