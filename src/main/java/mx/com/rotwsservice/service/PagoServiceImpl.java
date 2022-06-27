/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.util.List;
import mx.com.rotwsservice.dao.PagoDao;
import mx.com.rotwsservice.model.Pago;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mvillavicencio
 */

@Service
@Transactional(readOnly = true)
public class PagoServiceImpl implements PagoService{
    
    @Autowired
    private PagoDao pagoDao;

    @Transactional
    @Override
    public int createPago(Pago pago) throws HibernateException, Exception {
        return pagoDao.createPago(pago);
    }

    @Override
    public List<Pago> getPagosByContrato(Integer idContrato) throws HibernateException, Exception {
        return pagoDao.getPagosByContrato(idContrato);
    }

    @Override
    public Pago getPagoById(Integer idPago) throws HibernateException, Exception {
        return pagoDao.getPagoById(idPago);
    }
    
}
