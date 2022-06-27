/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mvillavicencio
 */
@Entity
@Table(name="pago")
public class Pago implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer idPago;
    private String conekOrder;
    private Date fechaPago;
    private Contrato contrato;
    
    public Pago(){}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_pago")
    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    @Column(name="conek_order")
    public String getConekOrder() {
        return conekOrder;
    }

    public void setConekOrder(String conekOrder) {
        this.conekOrder = conekOrder;
    }

    @Column(name="fecha_pago")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @ManyToOne
    @JoinColumn(name="id_contrato")
    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
    
}
