/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.model.Cuarto;
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
public class CuartoDaoImpl implements CuartoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int addCuarto(Cuarto cuarto) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.save(cuarto);
            return cuarto.getIdCuarto();
        } catch (HibernateException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public Cuarto getCuartoById(Integer idCuarto) {
        Cuarto cuarto = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Cuarto.class);
            criteria.add(Restrictions.eq("idCuarto", idCuarto));
            cuarto = (Cuarto) criteria.uniqueResult();
            return cuarto;
        } catch (HibernateException e) {
            e.printStackTrace();
            return cuarto;
        } catch (Exception ex) {
            ex.printStackTrace();
            return cuarto;
        }
    }

    @Override
    public ErrorDto updateCuarto(Cuarto cuarto) {
        Session session;
        session = sessionFactory.getCurrentSession();
        ErrorDto errorDto = new ErrorDto();
        try {
            System.out.println("CuartoDaoImpl.updateCuarto.save.Cuarto.id "+cuarto.getIdCuarto());
            System.out.println("CuartoDaoImpl.updateCuarto.save.Cuarto.titulo "+cuarto.getTitulo());
            session.saveOrUpdate(cuarto);
            System.out.println("CuartoDaoImpl.updateCuarto.save");
            errorDto.setNumeroError(cuarto.getIdCuarto());
            return errorDto;
        } catch (HibernateException e) {
            System.out.println("CuartoDaoImpl.updateCuarto.error");
            e.printStackTrace();
            errorDto.setNumeroError(-1);
            errorDto.setError("Error al actualizar cuarto");
            errorDto.setDescripcionError(e.getMessage());
            return errorDto;
        }
    }
}
