/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dto;

import java.io.Serializable;

/**
 *
 * @author Marco Villa
 */
public class UsuarioAutenticaDto implements Serializable{
    
    public UsuarioAutenticaDto(){}
    
    private static final long serialVersionUID = 1L;
    
    private String  data0;
    private String  data1;
    private String  data2;
    private String  ftoken;

    public String getData0() {
        return data0;
    }

    public void setData0(String data0) {
        this.data0 = data0;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getFtoken() {
        return ftoken;
    }

    public void setFtoken(String ftoken) {
        this.ftoken = ftoken;
    }
}