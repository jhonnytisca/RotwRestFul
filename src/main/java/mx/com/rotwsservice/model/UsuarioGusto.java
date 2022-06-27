/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Marco Villa
 */
@Entity
@Table(name="usuario_gusto")
public class UsuarioGusto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private UsuarioGustoId usuarioGustoId;
    private Usuario usuario;
    private Gusto gusto;
    
    /** Constructors */
    public UsuarioGusto(){}

    public UsuarioGusto(UsuarioGustoId usuarioGustoId, Usuario usuario, Gusto gusto) {
        this.usuarioGustoId = usuarioGustoId;
        this.usuario = usuario;
        this.gusto = gusto;
    }

    /** Atributos */
    @EmbeddedId
    public UsuarioGustoId getUsuarioGustoId() {
        return usuarioGustoId;
    }

    public void setUsuarioGustoId(UsuarioGustoId usuarioGustoId) {
        this.usuarioGustoId = usuarioGustoId;
    }

    @ManyToOne
    @JoinColumn(name="id_usuario", insertable=false, updatable=false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name="id_gusto", insertable=false, updatable=false)
    public Gusto getGusto() {
        return gusto;
    }

    public void setGusto(Gusto gusto) {
        this.gusto = gusto;
    }
    
}
