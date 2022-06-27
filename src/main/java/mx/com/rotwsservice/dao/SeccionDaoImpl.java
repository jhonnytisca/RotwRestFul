/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import java.util.List;
import mx.com.rotwsservice.dao.SeccionDao;
import mx.com.rotwsservice.model.Seccion;
import mx.com.rotwsservice.model.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco Villa
 */
@Repository
public class SeccionDaoImpl implements SeccionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Seccion> getSeccionesUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
