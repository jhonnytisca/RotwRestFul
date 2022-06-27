/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import mx.com.rotwsservice.dao.HistorialDao;
import mx.com.rotwsservice.model.Historial;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco Villa
 */
@Repository
public class HistorialDaoImpl implements HistorialDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean generarHistorico(Historial historial) {
        Session session;
        Transaction transaction = null;
        session = sessionFactory.getCurrentSession();
        try {
            transaction = session.beginTransaction();
            session.save(historial);
            transaction.commit();

            return true;
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();

            return false;
        }
    }

}
