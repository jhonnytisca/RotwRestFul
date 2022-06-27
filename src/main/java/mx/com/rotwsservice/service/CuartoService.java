/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.model.Cuarto;

/**
 *
 * @author mvillavicencio
 */
public interface CuartoService {
    public int addCuarto(Cuarto cuarto);
    public ErrorDto updateCuarto(Cuarto cuarto);
    public Cuarto getCuartoById(Integer idCuarto);
}
