/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.util.List;
import mx.com.rotwsservice.model.Pago;
import org.hibernate.HibernateException;

/**
 *
 * @author mvillavicencio
 */
public interface PagoService {
    public int createPago(Pago pago) throws HibernateException, Exception;
    public List<Pago> getPagosByContrato(Integer idContrato) throws HibernateException, Exception;
    public Pago getPagoById(Integer idPago) throws HibernateException, Exception;
}
