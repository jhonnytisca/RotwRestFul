/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.model.Contrato;

/**
 *
 * @author mvillavicencio
 */
public interface ContratoService {
    public int addContrato(Contrato contrato);
    public Contrato getContratoById(Integer contratoId);
    public ErrorDto checkIn(Contrato contrato, boolean esRoomie);
    public Contrato getCheckIn(Integer contratoId, boolean esRoomie);
}
