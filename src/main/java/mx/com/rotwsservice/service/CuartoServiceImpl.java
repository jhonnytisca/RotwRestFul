/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import mx.com.rotwsservice.dao.CuartoDao;
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.model.Cuarto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mvillavicencio
 */

@Service
@Transactional(readOnly = true)
public class CuartoServiceImpl implements CuartoService{
    
    @Autowired
    private CuartoDao cuartoDao;

    @Transactional
    @Override
    public int addCuarto(Cuarto cuarto) {
        return cuartoDao.addCuarto(cuarto);
    }

    @Override
    public Cuarto getCuartoById(Integer idCuarto) {
        return cuartoDao.getCuartoById(idCuarto);
    }

    @Transactional
    @Override
    public ErrorDto updateCuarto(Cuarto cuarto) {
        return cuartoDao.updateCuarto(cuarto);
    }
    
}
