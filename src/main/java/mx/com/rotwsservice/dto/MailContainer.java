/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dto;

import java.util.Map;

/**
 *
 * @author Marco Villa
 */
public class MailContainer {
    private String asunto;
    private String[] destinatarios;
    private String plantilla;
    private Map parametros;

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String[] getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String[] destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    public Map getParametros() {
        return parametros;
    }

    public void setParametros(Map parametros) {
        this.parametros = parametros;
    }
    
    
}
