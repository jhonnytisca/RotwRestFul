/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mx.com.rotwsservice.dao.UsuarioDao;
import mx.com.rotwsservice.dao.UsuarioUrlDao;
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.dto.UsuarioAutenticaDto;
import mx.com.rotwsservice.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.com.rotwsservice.exception.ServiceExeption;
import mx.com.rotwsservice.model.UsuarioUrl;
import mx.com.rotwsservice.util.CorreosTemplates;
import org.apache.velocity.VelocityContext;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.rotwsservice.model.request.RecuperarPasswordRequest;

/**
 *
 * @author mvillavicencio
 */
@Service
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {
   
    private static final Logger LOG = Logger.getLogger(UsuarioServiceImpl.class.getName());
    
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    private UsuarioUrlDao usuarioUrlDao;

    /** The hex 1. */
    private final int hex1 = 0xff;

    /** The hex 2. */
    private final int hex2 = 0x100;

    /** The gcm tag length. */
    private final int gcmTagLength = 16;

    @Override
    public Usuario identifica(UsuarioAutenticaDto usuarioAutentica) {
        return usuarioDao.identifica(usuarioAutentica);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }

    @Transactional
    @Override
    public ErrorDto createUsuario(Usuario usuario) {
        return usuarioDao.createUsuario(usuario);
    }

    @Transactional
    @Override
    public ErrorDto updateUsuario(Usuario usuario) {
        return usuarioDao.updateUsuario(usuario);
    }

    @Override
    public Usuario getUsuarioById(int idUsuario) {
        return usuarioDao.getUsuarioById(idUsuario);
    }

    @Override
    public List<Usuario> getUsuariosById(List<String> idsUsuarios) {
        return usuarioDao.getUsuariosById(idsUsuarios);
    }

    @Override
    public Usuario validateUsuarioRed(String perfilRed, String correoRed) {
        return usuarioDao.validateUsuarioRed(perfilRed, correoRed);
    }
    
    @Transactional
    @Override
    public ErrorDto updateResponsable(Usuario usuario) {
        return usuarioDao.updateResponsable(usuario);
    }
    
    @Transactional
    @Override
    public ErrorDto updateRoomie(Usuario usuario) {
        return usuarioDao.updateRoomie(usuario);
    }

    @Override
    public Integer validaCorreo(String correo, String correoRed) {
        return usuarioDao.validaCorreo(correo, correoRed);
    }

    @Override
    public Integer validaUsuario(String nombreUsuario) {
        return usuarioDao.validaUsuario(nombreUsuario);
    }

    @Transactional
    @Override
    public String generarLinkRecuperarPassword(final String correoUsuario) {
        Integer usuarioValido = usuarioDao.validaCorreo(correoUsuario, correoUsuario);
        if(usuarioValido == 0) {
            throw new ServiceExeption("No se encuentra ningun usuario asociado al correo ingresado!");
        }
        Usuario usuario = usuarioDao.getUsuarioByCorreo(correoUsuario);
        String url = generarUrl(usuario);
        String linkGenerado = "https://www.roomiesoftheworld.com/#/recuperarPwd/" + url;
        VelocityContext contextLink = new VelocityContext();
        contextLink.put("nombreRoomie", usuario.getNombre());
        contextLink.put("linkGenerado", linkGenerado);
        String []correos = {correoUsuario};
        try {
            mailService.sendEmail(correos,
                    CorreosTemplates.RECUPERAR_PASSWORD_SUBJECT
                    ,CorreosTemplates.RECUPERAR_PASSWORD, contextLink);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al generar el link.", e);
            throw new ServiceExeption("Error al enviar al correo de recuperacion." + e.getMessage());
        }
        return "Link generado y enviado al correo";
    }
    
    protected String generarUrl(final Usuario usuario){
        UsuarioUrl usuarioUrl = new UsuarioUrl();
        usuarioUrl.setActiva(1);
        Date fechaAlta = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(fechaAlta); 
        c.add(Calendar.DATE, 1);
        usuarioUrl.setFechaAlta(fechaAlta);
        usuarioUrl.setFechaCaducidad(c.getTime());
        usuarioUrl.setNombreUsuario(usuario.getNombreUsuario());
        String urlSinHash = usuario.getNombreUsuario() + "_" + Integer.toString(usuario.getIdUsuario()) 
                + "_" + usuario.getCorreoElectronico() + new Date().toString();
        String urlHash = hashUrl(urlSinHash);
        usuarioUrl.setUrl(urlHash);
        System.out.println("Nombre usuario: " + usuarioUrl.getNombreUsuario());
        int res = usuarioUrlDao.save(usuarioUrl);
        if(res == 0) {
            return null;
        }
        return urlHash;
    }
   
    protected String hashUrl(final String url) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] cuentabytes = url.getBytes();
            byte[] cuentaHash = sha256.digest(cuentabytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cuentaHash.length; i++) {
                sb.append(Integer.toString((cuentaHash[i] & hex1) + hex2, gcmTagLength).substring(1)
                        .toUpperCase());
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public String validaUrl(final String url) { 
        String urlEncontrada = usuarioUrlDao.verificarUrlActiva(url);
        if (urlEncontrada != null 
                && !("").equals(urlEncontrada)) {
            return urlEncontrada;
        } else {
            throw new ServiceExeption("La url {" + url + "} ya no se encuentra activa.");
        }
    }

    @Transactional
    @Override
    public String updatePassword(RecuperarPasswordRequest request) {
        String mensaje = "updatePassword" + request.getPasswordNueva() + " , " + request.getUrl();
        LOG.info(mensaje);
        if (usuarioUrlDao.updatePassword(request) == 0) {
            LOG.info("Error URL no valida.");
            return "Error URL no valida.";
        } else {
            LOG.info("Contraseña actualizada.");
            return "Contraseña actualizada.";
        }
    }
}
