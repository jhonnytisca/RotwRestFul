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
public class UsuarioViviendaId implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idUsuario;
    private int idVivienda;
    
    /** Constructores */
    public UsuarioViviendaId(){}

    public UsuarioViviendaId(int idUsuario, int idVivienda) {
        this.idUsuario = idUsuario;
        this.idVivienda = idVivienda;
    }

    /** Getters y Setters */
    @Column(name="id_usuario")
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Column(name="id_vivienda")
    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }
    
}
