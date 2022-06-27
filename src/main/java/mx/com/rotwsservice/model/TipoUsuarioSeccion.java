/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
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
@Table(name="tipo_usuario_seccion")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@tipoUsuarioSeccionId")
public class TipoUsuarioSeccion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private TipoUsuarioSeccionId tipoUsuarioSeccionId;
    private TipoUsuario tipoUsuario;
    private Seccion seccion;
    
    /** Constructores */
    public TipoUsuarioSeccion(){}

    public TipoUsuarioSeccion(TipoUsuario tipoUsuario, Seccion seccion, TipoUsuarioSeccionId tipoUsuarioSeccionId) {
        this.tipoUsuario = tipoUsuario;
        this.seccion = seccion;
        this.tipoUsuarioSeccionId = tipoUsuarioSeccionId;
    }

    /** Getters y Setters
     * @return  */

    @EmbeddedId
    public TipoUsuarioSeccionId getTipoUsuarioSeccionId() {
        return tipoUsuarioSeccionId;
    }

    public void setTipoUsuarioSeccionId(TipoUsuarioSeccionId tipoUsuarioSeccionId) {
        this.tipoUsuarioSeccionId = tipoUsuarioSeccionId;
    }
    
    @ManyToOne
    @JoinColumn(name="id_tipo_usuario", insertable=false, updatable=false)
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @ManyToOne
    @JoinColumn(name="id_seccion", insertable=false, updatable=false)
    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
        
}
