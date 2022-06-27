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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mvillavicencio
 */
@Entity
@Table(name="contrato")
public class Contrato implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer idContrato;
    private Date fechaInicio;
    private Date fechaFin;
    private Double precioBase;
    private String moneda;
    private Double comisionRotw;
    private String firma;
    
    private Usuario usuario;
    private Vivienda vivienda;
    private Cuarto cuarto;
    
    private Date    roomieCheckin;
    private Date    responsableCheckin;
    private Date    roomieCheckout;
    private Date    responsableCheckout;
    private Integer roomieCheckinSt;
    private Integer responsableCheckinSt;
    private Integer roomieCheckoutSt;
    private Integer responsableCheckoutSt;
    private String  roomieCheckinComment;
    private String  responsableCheckinComment;
    private String  roomieCheckoutComment;
    private String  responsableCheckoutComment;

    @Column(name="fecha_inicio")
    @Temporal(TemporalType.DATE)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Column(name="fecha_fin")
    @Temporal(TemporalType.DATE)
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Column(name="precio_base")
    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    @Column(name="moneda")
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Column(name="comision_rotw")
    public Double getComisionRotw() {
        return comisionRotw;
    }

    public void setComisionRotw(Double comisionRotw) {
        this.comisionRotw = comisionRotw;
    }

    @Column(name="firma")
    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_contrato")
    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    @OneToOne
    @JoinColumn(name="id_roomie")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @OneToOne
    @JoinColumn(name="id_vivienda")
    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    @OneToOne
    @JoinColumn(name="id_cuarto")
    public Cuarto getCuarto() {
        return cuarto;
    }

    public void setCuarto(Cuarto cuarto) {
        this.cuarto = cuarto;
    }
    
    @Column(name="roomie_checkin")
    public Date getRoomieCheckin() {
        return roomieCheckin;
    }

    public void setRoomieCheckin(Date roomieCheckin) {
        this.roomieCheckin = roomieCheckin;
    }

    @Column(name="responsable_checkin")
    public Date getResponsableCheckin() {
        return responsableCheckin;
    }

    public void setResponsableCheckin(Date responsableCheckin) {
        this.responsableCheckin = responsableCheckin;
    }

    @Column(name="roomie_checkout")
    public Date getRoomieCheckout() {
        return roomieCheckout;
    }

    public void setRoomieCheckout(Date roomieChecout) {
        this.roomieCheckout = roomieChecout;
    }

    @Column(name="responsable_checkout")
    public Date getResponsableCheckout() {
        return responsableCheckout;
    }

    public void setResponsableCheckout(Date responsableCheckout) {
        this.responsableCheckout = responsableCheckout;
    }

    @Column(name="roomie_checkin_st")
    public Integer getRoomieCheckinSt() {
        return roomieCheckinSt;
    }

    public void setRoomieCheckinSt(Integer roomieCheckinSt) {
        this.roomieCheckinSt = roomieCheckinSt;
    }

    @Column(name="responsable_checkin_st")
    public Integer getResponsableCheckinSt() {
        return responsableCheckinSt;
    }

    public void setResponsableCheckinSt(Integer responsableCheckinSt) {
        this.responsableCheckinSt = responsableCheckinSt;
    }

    @Column(name="roomie_checkout_st")
    public Integer getRoomieCheckoutSt() {
        return roomieCheckoutSt;
    }

    public void setRoomieCheckoutSt(Integer roomieChecoutSt) {
        this.roomieCheckoutSt = roomieChecoutSt;
    }

    @Column(name="responsable_checkout_st")
    public Integer getResponsableCheckoutSt() {
        return responsableCheckoutSt;
    }

    public void setResponsableCheckoutSt(Integer responsableCheckoutSt) {
        this.responsableCheckoutSt = responsableCheckoutSt;
    }

    @Column(name="roomie_checkin_comment")
    public String getRoomieCheckinComment() {
        return roomieCheckinComment;
    }

    public void setRoomieCheckinComment(String roomieCheckinComment) {
        this.roomieCheckinComment = roomieCheckinComment;
    }

    @Column(name="responsable_checkin_comment")
    public String getResponsableCheckinComment() {
        return responsableCheckinComment;
    }

    public void setResponsableCheckinComment(String responsableCheckinComment) {
        this.responsableCheckinComment = responsableCheckinComment;
    }

    @Column(name="roomie_checkout_comment")
    public String getRoomieCheckoutComment() {
        return roomieCheckoutComment;
    }

    public void setRoomieCheckoutComment(String roomieChecoutComment) {
        this.roomieCheckoutComment = roomieChecoutComment;
    }

    @Column(name="responsable_checkout_comment")
    public String getResponsableCheckoutComment() {
        return responsableCheckoutComment;
    }

    public void setResponsableCheckoutComment(String responsableCheckoutComment) {
        this.responsableCheckoutComment = responsableCheckoutComment;
    }
}