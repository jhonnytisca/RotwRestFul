/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dto;

import java.io.Serializable;

/**
 *
 * @author mvillavicencio
 */
public class FaceRequestDto implements Serializable {
    public FaceRequestDto(){}
        
    private static final long serialVersionUID = 1L;
    private String idFacebook;
    private String correoFacebook;

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public String getCorreoFacebook() {
        return correoFacebook;
    }

    public void setCorreoFacebook(String correoFacebook) {
        this.correoFacebook = correoFacebook;
    }
}
