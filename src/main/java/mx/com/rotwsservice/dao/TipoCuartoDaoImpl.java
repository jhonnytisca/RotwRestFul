/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import java.util.ArrayList;
import java.util.List;
import mx.com.rotwsservice.model.TipoCuarto;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco Villa
 */
@Repository
public class TipoCuartoDaoImpl implements TipoCuartoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TipoCuarto> getAllTipoCuarto() {
        List<TipoCuarto> tipos = new ArrayList<>();
        Session session = null;
        session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(TipoCuarto.class);
            tipos = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return tipos;
    }

}
