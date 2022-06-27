/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marco Villa
 */
@Entity
@Table(name="usuario")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@idUsuario")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** Atributos */
    private int idUsuario;
    private String nombre;
    private String password;
    private TipoUsuario tipoUsuario;
    private String primerApellido;
    private String segundoApellido;
    private String perfilRed;
    private String correoElectronico;
    private String telefono;
    private Sede sede;
    private Pais pais;
    private Foto foto;
    private String nombreUsuario;
//    private List<Seccion> secciones = new ArrayList<>();
//    private List<Gusto> gustos = new ArrayList<>();
    private Date altaUsuario;
    private String token;
    private boolean estatus;
    private char sexo;
    private String avatar;
    private String descripcion;
    private String mensaje;
    private Date fechaNac;
    private String nacionalidad;
    private String correoRed;
    private String pushToken;

    /** Getters y Setters*/
    @Id
    @Column(name="id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Column(name="nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name="password")
    @Basic(fetch = FetchType.LAZY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne
    @JoinColumn(name="id_tipo_usuario")
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Column(name="primer_apellido")
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @Column(name="segungo_apellido")
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @Column(name="perfil_red")
    public String getPerfilRed() {
        return perfilRed;
    }

    public void setPerfilRed(String perfilRed) {
        this.perfilRed = perfilRed;
    }

    @Column(name="correo_electronico")
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Column(name="telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @OneToOne
    @JoinColumn(name="id_sede")
    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
    
    @OneToOne
    @JoinColumn(name="id_pais")
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @OneToOne
    @JoinColumn(name="id_fotos")
    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    /*@OneToMany()
    @JoinColumn(name="id_seccion")
    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="id_gusto",insertable=false, updatable=false)
    public List<Gusto> getGustos() {
        return gustos;
    }

    public void setGustos(List<Gusto> gustos) {
        this.gustos = gustos;
    }*/

    @Column(name = "alta_usuario", columnDefinition="DATETIME",updatable=false)
    @Temporal(TemporalType.DATE)
    public Date getAltaUsuario() {
        return altaUsuario;
    }

    public void setAltaUsuario(Date altaUsuario) {
        this.altaUsuario = altaUsuario;
    }

    @Column(name="token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "nombre_usuario")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Column(name = "estatus")
    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Column(name = "sexo")
    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "mensaje")
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Column(name = "nacionalidad")
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Column(name = "correo_red")
    public String getCorreoRed() {
        return correoRed;
    }

    public void setCorreoRed(String correoRed) {
        this.correoRed = correoRed;
    }

    @Column(name = "push_token")
    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
    
}