/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import java.util.List;
import mx.com.rotwsservice.dao.PagoDao;
import mx.com.rotwsservice.model.Pago;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mvillavicencio
 */

@Repository
public class PagoDaoImpl implements PagoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int createPago(Pago pago) throws HibernateException, Exception {
        Session session = sessionFactory.getCurrentSession();
        session.save(pago);
        return pago.getIdPago();
    }

    @Override
    public List<Pago> getPagosByContrato(Integer idContrato) throws HibernateException, Exception {
        List<Pago> pagos;
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Pago.class);
        criteria.add(Restrictions.eq("contrato.idContrato", idContrato));
        pagos = criteria.list();
        return pagos;
    }

    @Override
    public Pago getPagoById(Integer idPago) throws HibernateException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
