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
public class UsuarioGustoId implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idUsuario;
    private int idGusto;
    
    /** Constructores */
    public UsuarioGustoId(){}

    public UsuarioGustoId(int idUsuario, int idGusto) {
        this.idUsuario = idUsuario;
        this.idGusto = idGusto;
    }
    
    /** Getters y Setters */
    @Column(name="id_usuario")
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Column(name="id_gusto")
    public int getIdGusto() {
        return idGusto;
    }

    public void setIdGusto(int idGusto) {
        this.idGusto = idGusto;
    }
    
    
}
