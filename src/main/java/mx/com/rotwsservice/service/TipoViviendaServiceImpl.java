/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.util.List;
import mx.com.rotwsservice.dao.TipoViviendaDao;
import mx.com.rotwsservice.model.TipoVivienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mvillavicencio
 */

@Service
@Transactional(readOnly = true)
public class TipoViviendaServiceImpl implements TipoViviendaService{

    @Autowired
    private TipoViviendaDao tipoViviendaDao;
            
    @Override
    public List<TipoVivienda> getAllTipoVivienda() {
        return tipoViviendaDao.getAllTipoVivienda();
    }
    
}
