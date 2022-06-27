/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import mx.com.rotwsservice.model.UsuarioUrl;
import mx.com.rotwsservice.model.request.RecuperarPasswordRequest;

/**
 *
 * @author Marco Villavicencio
 */
public interface UsuarioUrlDao {
    Integer save(UsuarioUrl usuarioUrl);
    String verificarUrlActiva(String url);
    Integer consumirUrl(String nombreUsuario);
    Integer updatePassword(RecuperarPasswordRequest request);
}
