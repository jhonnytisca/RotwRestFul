/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Marco Villa
 */
@Entity
@Table(name="vivienda")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@idViviendaSerial")
public class Vivienda implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idViviendaSerial;
    private int idVivienda;
    private TipoVivienda tipoVivienda;
    private String direccionGenerica;
    private String direccion;
    private String direccionC;
    private String detalle;
    private Double precio;
    private String titulo;
    private EstatusVivienda estatusVivienda;
    private String motivoSuspension;
    private Date altaVivienda;
    private Date bajaVivienda;
    private int capaciad;
    //private List<ViviendaTipoCuarto> cuartos;
    private Integer idResponsable;
    private Double m2;
    private Date fechaInicio;
    private Date fechaFin;
    private String latitud;
    private String longitud;
    private String delimitador;
    private boolean rentaCuarto;
    private String portada;
    private List<Cuarto> recamaras;
    private boolean activa;
    
    @JsonIgnore
    private Usuario responsable;
//    private List<Foto> fotos;
    
    /** Constructores */
    public Vivienda(){}    

    public Vivienda(int idVivienda, TipoVivienda tipoVivienda, String direccionGenerica, String direccion, String direccionC, String detalle, Double precio, String titulo, EstatusVivienda estatusVivienda, String motivoSuspension, Date altaVivienda, Date bajaVivienda, int capaciad) {
        this.idVivienda = idVivienda;
        this.tipoVivienda = tipoVivienda;
        this.direccionGenerica = direccionGenerica;
        this.direccion = direccion;
        this.direccionC = direccionC;
        this.detalle = detalle;
        this.precio = precio;
        this.titulo = titulo;
        this.estatusVivienda = estatusVivienda;
        this.motivoSuspension = motivoSuspension;
        this.altaVivienda = altaVivienda;
        this.bajaVivienda = bajaVivienda;
        this.capaciad = capaciad;
    }

    /** Getters y Setters
     * @return  */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_vivienda")
    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    @OneToOne
    @JoinColumn(name="id_tipo_vivienda")
    public TipoVivienda getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(TipoVivienda tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    @Column(name="direccion_generica")
    public String getDireccionGenerica() {
        return direccionGenerica;
    }

    public void setDireccionGenerica(String direccionGenerica) {
        this.direccionGenerica = direccionGenerica;
    }

    @Column(name="direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name="direccion_c")
    public String getDireccionC() {
        return direccionC;
    }

    public void setDireccionC(String direccionC) {
        this.direccionC = direccionC;
    }

    @Column(name="detalle")
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

//    @OneToMany
//    @JoinColumn(name="id_vivienda",nullable = true)
//    public List<Foto> getFotos() {
//        return fotos;
//    }
//
//    public void setFotos(List<Foto> fotos) {
//        this.fotos = fotos;
//    }

    @Column(name="precio")
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Column(name="titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @OneToOne
    @JoinColumn(name="id_estatus_vivienda")
    public EstatusVivienda getEstatusVivienda() {
        return estatusVivienda;
    }

    public void setEstatusVivienda(EstatusVivienda estatusVivienda) {
        this.estatusVivienda = estatusVivienda;
    }

    @Column(name = "motivo_suspension")
    public String getMotivoSuspension() {
        return motivoSuspension;
    }

    public void setMotivoSuspension(String motivoSuspension) {
        this.motivoSuspension = motivoSuspension;
    }

    /**
     *
     * @return
     */
    @Column(name = "alta_vivienda", insertable=false)
    @Temporal(TemporalType.DATE)
    public Date getAltaVivienda() {
        return altaVivienda;
    }

    public void setAltaVivienda(Date altaVivienda) {
        this.altaVivienda = altaVivienda;
    }
    @Column(name = "baja_vivienda")
    public Date getBajaVivienda() {
        return bajaVivienda;
    }

    public void setBajaVivienda(Date bajaVivienda) {
        this.bajaVivienda = bajaVivienda;
    }

    @Column(name = "capacidad")
    public int getCapaciad() {
        return capaciad;
    }

    public void setCapaciad(int capaciad) {
        this.capaciad = capaciad;
    }
    
    /*@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumns({
        @JoinColumn(name="id_vivienda",insertable=false, updatable=false)
    })
    public List<ViviendaTipoCuarto> getCuartos() {
        return cuartos;
    }

    public void setCuartos(List<ViviendaTipoCuarto> cuartos) {
        this.cuartos = cuartos;
    }*/

    @Transient
    public int getIdViviendaSerial() {
        return idViviendaSerial;
    }

    public void setIdViviendaSerial(int idViviendaSerial) {
        this.idViviendaSerial = idViviendaSerial;
    }
    
    @Column(name = "id_responsable", nullable = true)
    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    @Column(name = "m2")
    public Double getM2() {
        return m2;
    }

    public void setM2(Double m2) {
        this.m2 = m2;
    }

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Column(name = "latitud")
    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    @Column(name = "delimitador")
    public String getDelimitador() {
        return delimitador;
    }

    public void setDelimitador(String delimitador) {
        this.delimitador = delimitador;
    }

    @Column(name = "renta_cuarto")
    public boolean isRentaCuarto() {
        return rentaCuarto;
    }

    public void setRentaCuarto(boolean rentaCuarto) {
        this.rentaCuarto = rentaCuarto;
    }

    @Column(name = "portada")
    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    @Column(name = "longitud")
    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "idVivienda" )
    public List<Cuarto> getRecamaras() {
        return recamaras;
    }

    public void setRecamaras(List<Cuarto> recamaras) {
        this.recamaras = recamaras;
    }

    @Column(name = "activa")
    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Vivienda{" + "idViviendaSerial=" + idViviendaSerial + ", idVivienda=" + idVivienda + ", tipoVivienda=" + tipoVivienda + ", direccionGenerica=" + direccionGenerica + ", direccion=" + direccion + ", direccionC=" + direccionC + ", detalle=" + detalle + ", precio=" + precio + ", titulo=" + titulo + ", estatusVivienda=" + estatusVivienda + ", motivoSuspension=" + motivoSuspension + ", altaVivienda=" + altaVivienda + ", bajaVivienda=" + bajaVivienda + ", capaciad=" + capaciad + ", idResponsable=" + idResponsable + ", m2=" + m2 + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", latitud=" + latitud + ", longitud=" + longitud + ", delimitador=" + delimitador + ", rentaCuarto=" + rentaCuarto + ", portada=" + portada + ", recamaras=" + recamaras + '}';
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_responsable", nullable=true, insertable = false, updatable = false)
    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }
}