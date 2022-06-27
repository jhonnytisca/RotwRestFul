/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import mx.com.rotwsservice.model.UsuarioUrl;
import mx.com.rotwsservice.model.Usuario;
import mx.com.rotwsservice.model.request.RecuperarPasswordRequest;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marco Villavicencio
 */
@Repository
public class UsuarioUrlDaoImpl implements UsuarioUrlDao {

    private static final Logger LOG = Logger.getLogger(UsuarioUrlDaoImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Integer save(final UsuarioUrl usuarioUrl) {
        Session session;
        int res = 0;
        try {
            session = sessionFactory.getCurrentSession();
            session.save(usuarioUrl);
            res = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public String verificarUrlActiva(String url) {
        String mensaje = "UsuarioUrlDaoImpl.verificarUrlActiva " + url;
        LOG.info(mensaje);
        Session session = null;
        session = sessionFactory.getCurrentSession();
        UsuarioUrl usuarioUrl = null;
        try {
            Criteria criteria = session.createCriteria(UsuarioUrl.class);
            criteria.add(Restrictions.eq("url", url));
            criteria.add(Restrictions.eq("activa", 1)); 
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
            Date fecha = new Date();
            System.out.println("Fecha : " + format.format(fecha));
            criteria.add(Restrictions.gt("fechaCaducidad", fecha));
            usuarioUrl = (UsuarioUrl) criteria.uniqueResult();
            return usuarioUrl.getUrl();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer consumirUrl(String nombreUsuario) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("UsuarioUrl url set url.activa=0 "
                    + "where url.nombreUsuario = :nombreUsuario");
            query.setParameter("nombreUsuario", nombreUsuario);
            return query.executeUpdate();
        } catch(Exception e){
            LOG.log(Level.SEVERE, "Error al cosnumir la url", e);
            return 0;
        }
    }

    @Override
    public Integer updatePassword(RecuperarPasswordRequest request) {
        String nombreUsuario = "";
        Session session = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("select usuarioUrl.nombreUsuario from UsuarioUrl usuarioUrl"
                    + " where usuarioUrl.url = :url");
            query.setParameter("url", request.getUrl());
            nombreUsuario = (String)query.getResultList().get(0);
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria.add(Restrictions.eq("nombreUsuario", nombreUsuario));
            LOG.info("nombreUsuario -----------------> " + nombreUsuario);
            query = session.createQuery("update Usuario usuario set usuario.password=:password where "
                    + "usuario.nombreUsuario=:nombreUsuario");
            query.setParameter("password", request.getPasswordNueva());
            query.setParameter("nombreUsuario", nombreUsuario);
            this.consumirUrl(nombreUsuario);
            return query.executeUpdate();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al actualizar la password", e);
            return 0;
        }
    }
    
}
