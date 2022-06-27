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
public class ViviendaTipoServicioId implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int idVivienda;
    private int idTipoServicio;
    
    public ViviendaTipoServicioId(){}

    public ViviendaTipoServicioId(int idVivienda, int idTipoServicio) {
        this.idVivienda = idVivienda;
        this.idTipoServicio = idTipoServicio;
    }

    @Column(name="id_vivienda")
    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    @Column(name="id_tipo_servicio")
    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }
    
    
}
