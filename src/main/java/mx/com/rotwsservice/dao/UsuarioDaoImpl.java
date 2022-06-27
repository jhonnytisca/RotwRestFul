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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.model.Usuario;
import mx.com.rotwsservice.dto.UsuarioAutenticaDto;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco Villa
 */
@Repository
public class UsuarioDaoImpl implements UsuarioDao {

    private static final Logger LOG = Logger.getLogger(UsuarioDaoImpl.class.getName());
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Usuario identifica(UsuarioAutenticaDto usuarioAutentica) {
        LOG.info("mx.com.rotwsservice.rotwsservice.daoImpl.UsuarioDaoImpl.identifica()");
        Session session = sessionFactory.getCurrentSession();
        Usuario usr = null;
        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            if (Integer.parseInt(usuarioAutentica.getData2()) == 0) {
                criteria.add(Restrictions.eq("nombreUsuario", usuarioAutentica.getData0()));
            } else {
                criteria.add(Restrictions.eq("correoElectronico", usuarioAutentica.getData0()));
            }
            criteria.add(Restrictions.eq("password", usuarioAutentica.getData1()));
            criteria.add(Restrictions.eq("estatus", true));
            usr = (Usuario) criteria.uniqueResult();
            return usr;
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Error al identificar al usuario,", e);
            return usr;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al identificar al usuario,", e);
            return usr;
        }
    }

    @Override
    public Usuario identificaCorreo(UsuarioAutenticaDto usuarioAutentica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<Usuario> getUsuarios() throws HibernateException {
        System.out.println("mx.com.rotwsservice.rotwsservice.daoImpl.UsuarioDaoImpl.getUsuarios()");
        List<Usuario> usuarios = new ArrayList<>();
        Session session = null;
        session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Usuario.class, "usr");
        criteria.createAlias("usr.tipoUsuario", "tipoUsuario");
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("idUsuario"), "idUsuario")
                .add(Projections.property("nombre"), "nombre")
                .add(Projections.property("primerApellido"), "primerApellido")
                .add(Projections.property("segundoApellido"), "segundoApellido")
                .add(Projections.property("perfilRed"), "perfilRed")
                .add(Projections.property("correoElectronico"), "correoElectronico")
                .add(Projections.property("telefono"), "telefono")
                .add(Projections.property("nombreUsuario"), "nombreUsuario")
                .add(Projections.property("tipoUsuario"), "tipoUsuario")
                .add(Projections.property("altaUsuario"), "altaUsuario")
                .add(Projections.property("avatar"), "avatar")
                .add(Projections.property("descripcion"), "descripcion")
                .add(Projections.property("mensaje"), "mensaje")
                .add(Projections.property("fechaNac"), "fechaNac")
                .add(Projections.property("nacionalidad"), "nacionalidad")
                .add(Projections.property("correoRed"), "correoRed")
                //                .add(Projections.property("tipoUsuario.tipoUsuario"))
                .add(Projections.property("sexo"), "sexo")
        )
                .setResultTransformer(Transformers.aliasToBean(Usuario.class));
        usuarios = criteria.list();
        return usuarios;
    }

    @Override
    public ErrorDto createUsuario(Usuario usuario) throws HibernateException {
        System.out.println("mx.com.rotwsservice.rotwsservice.daoImpl.UsuarioDaoImpl.createUsuario() : " + (usuario.getFechaNac() == null));
        Session session;
        session = sessionFactory.getCurrentSession();
        ErrorDto errorDto = new ErrorDto();
        usuario.setAltaUsuario(new Date());
        session.save(usuario);
        errorDto.setNumeroError(usuario.getIdUsuario());
        return errorDto;
    }

    @Override
    public Usuario getUsuarioById(int idUsuario) {
        System.out.println("mx.com.rotwsservice.rotwsservice.daoImpl.UsuarioDaoImpl.getUsuarioById()");
        Usuario usuario = null;
        Session session = null;
        session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Usuario.class, "usr");
            criteria.createAlias("usr.tipoUsuario", "tipoUsuario");
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("idUsuario"), "idUsuario")
                    .add(Projections.property("nombre"), "nombre")
                    .add(Projections.property("primerApellido"), "primerApellido")
                    .add(Projections.property("segundoApellido"), "segundoApellido")
                    .add(Projections.property("perfilRed"), "perfilRed")
                    .add(Projections.property("correoElectronico"), "correoElectronico")
                    .add(Projections.property("telefono"), "telefono")
                    .add(Projections.property("nombreUsuario"), "nombreUsuario")
                    .add(Projections.property("tipoUsuario"), "tipoUsuario")
                    .add(Projections.property("altaUsuario"), "altaUsuario")
                    .add(Projections.property("avatar"), "avatar")
                    .add(Projections.property("descripcion"), "descripcion")
                    .add(Projections.property("mensaje"), "mensaje")
                    .add(Projections.property("fechaNac"), "fechaNac")
                    .add(Projections.property("nacionalidad"), "nacionalidad")
                    .add(Projections.property("correoRed"), "correoRed")
                    //                .add(Projections.property("tipoUsuario.tipoUsuario"))
                    .add(Projections.property("sexo"), "sexo")
            )
                    .setResultTransformer(Transformers.aliasToBean(Usuario.class));
            criteria.add(Restrictions.eq("idUsuario", idUsuario));
            usuario = (Usuario) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> getUsuariosById(List<String> idsUsuarios) {
        List<Integer> usuariosId = new ArrayList<>();
        for (String id : idsUsuarios) {
            usuariosId.add(Integer.parseInt(id));
        }
        List<Usuario> usuarios = null;
        Session session = null;
        session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria.add(Restrictions.in("idUsuario", usuariosId));
            usuarios = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public ErrorDto updateUsuario(Usuario usuario) {
        Session session;
        session = sessionFactory.getCurrentSession();
        ErrorDto errorDto = new ErrorDto();
        try {
            session.saveOrUpdate(usuario);
            errorDto.setNumeroError(usuario.getIdUsuario());
            return errorDto;
        } catch (HibernateException e) {
            e.printStackTrace();
            errorDto.setNumeroError(-1);
            errorDto.setError("Error al insertar usuario");
            errorDto.setDescripcionError(e.getMessage());
            return errorDto;
        }
    }

    @Override
    public ErrorDto updateResponsable(Usuario usuario) {
        Session session;
        session = sessionFactory.getCurrentSession();
        ErrorDto errorDto = new ErrorDto();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Usuario> criteria = builder.createCriteriaUpdate(Usuario.class);
            criteria.from(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.set(root.get("descripcion"),usuario.getDescripcion() );
            criteria.where(builder.equal(root.get("idUsuario"), Integer.toString(usuario.getIdUsuario())));
            session.createQuery(criteria).executeUpdate();
            errorDto.setNumeroError(usuario.getIdUsuario());
            return errorDto;
        } catch (HibernateException e) {
            e.printStackTrace();
            errorDto.setNumeroError(-1);
            errorDto.setError("Error al insertar usuario");
            errorDto.setDescripcionError(e.getMessage());
            return errorDto;
        }
    }

    @Override
    public ErrorDto updateRoomie(Usuario usuario) {
        Session session;
        session = sessionFactory.getCurrentSession();
        ErrorDto errorDto = new ErrorDto();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Usuario> criteria = builder.createCriteriaUpdate(Usuario.class);
            criteria.from(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.set(root.get("descripcion"),usuario.getDescripcion() );
            criteria.set(root.get("mensaje"),usuario.getMensaje());
            criteria.where(builder.equal(root.get("idUsuario"), Integer.toString(usuario.getIdUsuario())));
            session.createQuery(criteria).executeUpdate();
            errorDto.setNumeroError(usuario.getIdUsuario());
            return errorDto;
        } catch (HibernateException e) {
            e.printStackTrace();
            errorDto.setNumeroError(-1);
            errorDto.setError("Error al insertar usuario");
            errorDto.setDescripcionError(e.getMessage());
            return errorDto;
        }
    }
    @Override
    public Usuario validateUsuarioRed(String perfilRed, String correoRed) {
        System.out.println("mx.com.rotwsservice.rotwsservice.daoImpl.UsuarioDaoImpl.validateUsuarioRed()");
        Usuario usuario = null;
        Session session = null;
        session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria.add(Restrictions.eq("perfilRed", perfilRed));
            criteria.add(Restrictions.eq("correoRed", correoRed));
            usuario = (Usuario) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuario;
    }

    @Override
    public ErrorDto insertInfoComplementaria(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer validaCorreo(String correo, String correoRed) {
        int count = 0;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("select count(*) FROM Usuario u where u.correoElectronico = :correoElectronico or correoRed = :correoRed");
            query.setParameter("correoElectronico", correo);
            query.setParameter("correoRed", correoRed);
            List<Long> countList = query.list();
            count = countList.get(0).intValue();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public Integer validaUsuario(String nombreUsuario) {
        int count = 0;
        System.out.println("Consultando usuario ::::::::::::::::::: " + nombreUsuario);
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("select count(*) FROM Usuario u where u.nombreUsuario = :nombreUsuario");
            query.setParameter("nombreUsuario", nombreUsuario);
            List<Long> countList = query.list();
            count = countList.get(0).intValue();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public Usuario getUsuarioByCorreo(String correoUsuario) {
        Session session = null;
        session = sessionFactory.getCurrentSession();
        Usuario usr = null;
        
        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria.add(Restrictions.eq("correoElectronico", correoUsuario));
            usr = (Usuario) criteria.uniqueResult();
            return usr;
        }catch (HibernateException e) {
            e.printStackTrace();
            return usr;
        } catch (Exception ex) {
            ex.printStackTrace();
            return usr;
        }
    }

}
