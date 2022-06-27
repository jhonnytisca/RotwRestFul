/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.util.List;
import mx.com.rotwsservice.dao.TipoCuartoDao;
import mx.com.rotwsservice.model.TipoCuarto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mvillavicencio
 */

@Service
@Transactional(readOnly = true)
public class TipoCuartoServiceImpl implements TipoCuartoService{

    @Autowired
    private TipoCuartoDao tipoCuartoDao;
    
    @Override
    public List<TipoCuarto> getAllTipoCuarto() {
        return tipoCuartoDao.getAllTipoCuarto();
    }
    
}
