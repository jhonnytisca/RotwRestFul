/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import java.util.List;
import mx.com.rotwsservice.model.Seccion;
import mx.com.rotwsservice.model.Usuario;

/**
 *
 * @author Marco Villa
 */
public interface SeccionDao {
    public List<Seccion> getSeccionesUsuario(Usuario usuario);
}
