/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Marco Villa
 */
@Embeddable
public class TipoUsuarioSeccionId implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idTipoUsuario;
    private int idSeccion;
    
    /** Constructores */
    public TipoUsuarioSeccionId(){}

    public TipoUsuarioSeccionId(int idTipoUsuario, int idSeccion) {
        this.idTipoUsuario = idTipoUsuario;
        this.idSeccion = idSeccion;
    }

    /** Getters y Setters */
    @Column(name="id_tipo_usuario")
    public int getTipoUsuario() {
        return idTipoUsuario;
    }

    public void setTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    @Column(name="id_seccion")
    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }
    
}
