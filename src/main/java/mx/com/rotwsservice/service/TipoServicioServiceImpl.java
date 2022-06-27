/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.util.List;
import mx.com.rotwsservice.dao.TipoServicioDao;
import mx.com.rotwsservice.model.TipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mvillavicencio
 */

@Service
@Transactional(readOnly = true)
public class TipoServicioServiceImpl implements TipoServicioService{
    
    @Autowired
    private TipoServicioDao tipoServicioDao;

    @Override
    public List<TipoServicio> getAllTipoServicio() {
        return tipoServicioDao.getAllTipoServicio();
    }
    
}
