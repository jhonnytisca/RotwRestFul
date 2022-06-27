/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.util.List;
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.dto.UsuarioAutenticaDto;
import mx.com.rotwsservice.model.Usuario;
import mx.com.rotwsservice.model.request.RecuperarPasswordRequest;

/**
 *
 * @author mvillavicencio
 */
public interface UsuarioService {
    Usuario identifica(UsuarioAutenticaDto usuarioAutentica);
    List<Usuario> getUsuarios();
    ErrorDto createUsuario(Usuario usuario);
    ErrorDto updateUsuario(Usuario usuario);
    Usuario getUsuarioById(int idUsuario);
    List<Usuario> getUsuariosById(List <String>idsUsuarios);
    Usuario validateUsuarioRed(String perfilRed, String correoRed);
    ErrorDto updateResponsable(Usuario usuario);
    ErrorDto updateRoomie(Usuario usuario);
    Integer validaCorreo(String correo, String correoRed);
    Integer validaUsuario(String nombreUsuario);
    String generarLinkRecuperarPassword(String correoUsuario);
    String validaUrl(String url);
    String updatePassword(RecuperarPasswordRequest request);
}
