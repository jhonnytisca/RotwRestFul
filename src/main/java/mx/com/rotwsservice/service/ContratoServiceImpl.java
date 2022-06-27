/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import mx.com.rotwsservice.dao.ContratoDao;
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.model.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mvillavicencio
 */

@Service
@Transactional(readOnly = true)
public class ContratoServiceImpl implements ContratoService{
    
    @Autowired
    private ContratoDao contratoDao;

    @Transactional
    @Override
    public int addContrato(Contrato contrato) {
        return contratoDao.addContrato(contrato);
    }

    @Override
    public Contrato getContratoById(Integer contratoId) {
        return contratoDao.getContratoById(contratoId);
    }

    @Override
    @Transactional
    public ErrorDto checkIn(Contrato contrato, boolean esRoomie) {
        return contratoDao.checkIn(contrato, esRoomie);
    }

    @Override
    public Contrato getCheckIn(Integer contratoId, boolean esRoomie) {
        return contratoDao.getCheckIn(contratoId, esRoomie);
    }
}
