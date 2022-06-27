/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.util.List;
import mx.com.rotwsservice.model.Vivienda;

/**
 *
 * @author mvillavicencio
 */
public interface ViviendaService {
    
    List<Vivienda> getAllViviendas();
    List<Vivienda> obtenerViviendasActivas();
    boolean createVivienda(Vivienda vivienda);
    Vivienda getViviendaPorId(int idVivienda);
    boolean updateVivienda(Vivienda vivienda);
    List<Vivienda> getAllViviendasByUsuarioResponsable(int idUsuario);
    List<Vivienda> getAllViviendasById(List<String> listaViviendas);
    boolean quitarVivienda(int idVivienda);
}
