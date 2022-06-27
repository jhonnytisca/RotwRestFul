/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import mx.com.rotwsservice.model.Cuarto;
import mx.com.rotwsservice.model.Vivienda;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco Villa
 */
@Repository
public class ViviendaDaoImpl implements ViviendaDao {

    private static final Logger LOG = Logger.getLogger(ViviendaDaoImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Vivienda> getAllViviendas() {
        System.out.println("mx.com.rotwsservice.rotwsservice.daoImpl.ViviendaDaoImpl.getAllViviendas()");
        List<Vivienda> viviendas = new ArrayList<>();
        Session session = null;
        session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Vivienda.class);
            criteria.addOrder(Order.desc("idVivienda"));
            viviendas = criteria.list();
            System.out.println("viviendas: " + viviendas.size());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        System.out.println("<<<<< Termino >>>>>");
        return viviendas;
    }

    @Override
    public List<Vivienda> getViviendasActivas() {
        LOG.info("mx.com.rotwsservice.rotwsservice.daoImpl.ViviendaDaoImpl.getViviendasActivas()");
        List<Vivienda> viviendas = new ArrayList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("select new mx.com.rotwsservice.dto.ViviendaActiva(vivienda1, "
                    + "usuario.nombre as nombre, usuario.primerApellido, usuario.segundoApellido, "
                    + "usuario.avatar) from Vivienda as vivienda1 "
                    + "inner join vivienda1.responsable as usuario "
                    + "where vivienda1.activa is true");
            viviendas = (List<Vivienda>)query.getResultList();
            String mensaje = "viviendas: {}" + viviendas.size();
            LOG.info(mensaje);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Error al obtener viviendas activas", e);
        }
        LOG.info("<<<<< Termino >>>>>");
        return viviendas;
    }

    @Override
    public boolean createVivienda(Vivienda vivienda) {
        //System.out.println("mx.com.rotwsservice.rotwsservice.daoImpl.ViviendaDaoImpl.createVivienda() "+vivienda.getRecamaras().get(0).getDescripcion());
        Session session;
        session = sessionFactory.getCurrentSession();
        try {
            vivienda.setAltaVivienda(new Date());
            session.save(vivienda);
            if (vivienda.getRecamaras() != null && vivienda.getRecamaras().size() > 0) {
                for (Cuarto cuarto : vivienda.getRecamaras()) {
                    cuarto.setIdVivienda(vivienda);
                    session.save(cuarto);
                }
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Vivienda getViviendaPorId(int idVivienda) {
        System.out.println("mx.com.rotwsservice.rotwsservice.daoImpl.ViviendaDaoImpl.getViviendaPorId()");
        Vivienda vivienda = null;
        Session session = null;
        session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Vivienda.class);
            criteria.add(Restrictions.eq("idVivienda", idVivienda));
            vivienda = (Vivienda) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return vivienda;
    }

    @Override
    public boolean updateVivienda(Vivienda vivienda) {
        System.out.println("mx.com.rotwsservice.rotwsservice.daoImpl.ViviendaDaoImpl.updateVivienda()");
        Session session;
        session = sessionFactory.getCurrentSession();
        try {
            session.update(vivienda);

            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<Vivienda> getAllViviendasByUsuarioResponsable(int idUsuario) {
        System.out.println("mx.com.rotwsservice.rotwsservice.daoImpl.ViviendaDaoImpl.getAllViviendasByUsuarioResponsable()");
        List<Vivienda> viviendas = new ArrayList<>();
        Session session = null;
        session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Vivienda.class);
            criteria.add(Restrictions.eq("idResponsable", idUsuario));
            criteria.addOrder(Order.desc("idVivienda"));
            viviendas = criteria.list();
            System.out.println("viviendas: " + viviendas.size());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return viviendas;
    }

    @Override
    public List<Vivienda> getAllViviendasById(List<String> listaViviendas) {
        List<Vivienda> viviendas = new ArrayList<Vivienda>();
        List<Integer> listaVivivendasInt = new ArrayList<Integer>();
        for (String id : listaViviendas) {
            listaVivivendasInt.add(Integer.parseInt(id));
        }
        Session session = null;
        session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Vivienda.class);
            criteria.add(Restrictions.in("idVivienda", listaVivivendasInt));
            viviendas = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return viviendas;
    }

    @Override
    public int eliminaVivienda(int idVivienda) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("update Vivienda vivienda set vivienda.activa = false"
                    + " where vivienda.idVivienda = :idVivienda");
            query.setParameter("idVivienda", idVivienda);
            return query.executeUpdate();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al eliminar la vivienda", e);
            return 0;
        }
    }

}
