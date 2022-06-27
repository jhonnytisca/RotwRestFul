/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import mx.com.rotwsservice.model.TipoEntradaHistorial;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco Villa
 */
@Repository
public class TipoEntradaHistorialDaoImpl implements TipoEntradaHistorialDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TipoEntradaHistorial getTipoEntradaHistorialById(int id) {

        TipoEntradaHistorial tipoEntradaHistorial = null;
        Session session = null;
        session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(TipoEntradaHistorial.class);
            criteria.add(Restrictions.eq("idTipoEntradaHistorial", id));
            tipoEntradaHistorial = (TipoEntradaHistorial) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return tipoEntradaHistorial;
    }

}
