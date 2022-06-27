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
@Table(name="foto_vivienda")
public class FotoVivienda implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int idFotoVivienda;
    private String path;
    private Date fechaGuardado;
    private Vivienda vivienda;
    
    public FotoVivienda(){}

    public FotoVivienda(int idFotoVivienda, String path, Date fechaGuardado, Vivienda vivienda) {
        this.idFotoVivienda = idFotoVivienda;
        this.path = path;
        this.fechaGuardado = fechaGuardado;
        this.vivienda = vivienda;
    }

    @Id
    @Column(name="id_foto_vivienda")
    public int getIdFotoVivienda() {
        return idFotoVivienda;
    }

    public void setIdFotoVivienda(int idFotoVivienda) {
        this.idFotoVivienda = idFotoVivienda;
    }

    @Column(name="path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name="fecha_guardado")
    @Temporal(TemporalType.DATE)
    public Date getFechaGuardado() {
        return fechaGuardado;
    }

    public void setFechaGuardado(Date fechaGuardado) {
        this.fechaGuardado = fechaGuardado;
    }

    @OneToOne
    @JoinColumn(name = "id_vivienda")
    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }
}
