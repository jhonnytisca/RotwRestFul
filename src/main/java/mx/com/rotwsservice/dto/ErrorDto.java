/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dto;

/**
 *
 * @author mvillavicencio
 */
public class ErrorDto {
    
    private int numeroError;
    private String error;
    private String descripcionError;

    public ErrorDto(){}
    public ErrorDto(int numeroError, String error, String descripcionError) {
        this.numeroError = numeroError;
        this.error = error;
        this.descripcionError = descripcionError;
    }

    public int getNumeroError() {
        return numeroError;
    }

    public void setNumeroError(int numeroError) {
        this.numeroError = numeroError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }
}