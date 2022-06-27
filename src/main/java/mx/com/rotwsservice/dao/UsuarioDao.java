/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import java.util.List;
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.dto.UsuarioAutenticaDto;
import mx.com.rotwsservice.model.Usuario;
/**
 *
 * @author Marco Villa
 */
public interface UsuarioDao {
    Usuario identifica(UsuarioAutenticaDto usuarioAutentica);
    Usuario identificaCorreo(UsuarioAutenticaDto usuarioAutentica);
    List<Usuario> getUsuarios();
    ErrorDto createUsuario(Usuario usuario);
    ErrorDto updateUsuario(Usuario usuario);
    Usuario getUsuarioById(int idUsuario);
    List<Usuario> getUsuariosById(List <String>idsUsuarios);
    Usuario validateUsuarioRed(String perfilRed, String correoRed);
    ErrorDto updateResponsable(Usuario usuario);
    ErrorDto updateRoomie(Usuario usuario);
    ErrorDto insertInfoComplementaria(Usuario usuario);
    Integer validaCorreo(String correo, String correoRed);
    Integer validaUsuario(String nombreUsuario);
    Usuario getUsuarioByCorreo(String correoUsuario);
}
