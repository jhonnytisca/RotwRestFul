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
public class ViviendaTipoCuartoId implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int idVivienda;
    private int idTipoCuarto;
    
    public ViviendaTipoCuartoId(){}

    public ViviendaTipoCuartoId(int idVivienda, int idTipoCuarto) {
        this.idVivienda = idVivienda;
        this.idTipoCuarto = idTipoCuarto;
    }

    @Column(name="id_vivienda")
    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    @Column(name="id_tipo_cuarto")
    public int getIdTipoCuarto() {
        return idTipoCuarto;
    }

    public void setIdTipoCuarto(int idTipoCuarto) {
        this.idTipoCuarto = idTipoCuarto;
    }
    
    
}
