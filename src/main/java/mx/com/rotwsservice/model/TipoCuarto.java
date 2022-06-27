/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "tipo_cuarto")
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@idTipoCuarto")
public class TipoCuarto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int idTipoCuarto;
    private String nombreTipoCuarto;
    private String descripcion;
    private String path;
    
    public TipoCuarto(){}

    public TipoCuarto(int idTipoCuarto, String nombreTipoCuarto, String descripcion) {
        this.idTipoCuarto = idTipoCuarto;
        this.nombreTipoCuarto = nombreTipoCuarto;
        this.descripcion = descripcion;
    }

    @Id
    @Column(name = "id_tipo_cuarto")
    public int getIdTipoCuarto() {
        return idTipoCuarto;
    }

    public void setIdTipoCuarto(int idTipoCuarto) {
        this.idTipoCuarto = idTipoCuarto;
    }

    @Column(name = "nombre_tipo_cuarto")
    public String getNombreTipoCuarto() {
        return nombreTipoCuarto;
    }

    public void setNombreTipoCuarto(String nombreTipoCuarto) {
        this.nombreTipoCuarto = nombreTipoCuarto;
    }

    @Column(name = "descripcion")
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
