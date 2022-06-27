/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.util.List;
import java.util.logging.Logger;
import mx.com.rotwsservice.dao.ViviendaDao;
import mx.com.rotwsservice.model.Vivienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mvillavicencio
 */

@Service
@Transactional(readOnly = true)
public class ViviendaServiceImpl implements ViviendaService{

    private static final Logger LOG = Logger.getLogger(ViviendaServiceImpl.class.getName());

    @Autowired
    private ViviendaDao viviendaDao;
    
    @Override
    public List<Vivienda> getAllViviendas() {
        return viviendaDao.getAllViviendas();
    }

    @Override
    public List<Vivienda> obtenerViviendasActivas() {
        LOG.info("ViviendaServiceImpl.obtenerViviendasActivas");
        return viviendaDao.getViviendasActivas();
    }

    @Transactional
    @Override
    public boolean createVivienda(Vivienda vivienda) {
        return viviendaDao.createVivienda(vivienda);
    }

    @Override
    public Vivienda getViviendaPorId(int idVivienda) {
        return viviendaDao.getViviendaPorId(idVivienda);
    }

    @Transactional
    @Override
    public boolean updateVivienda(Vivienda vivienda) {
        return viviendaDao.updateVivienda(vivienda);
    }

    @Override
    public List<Vivienda> getAllViviendasByUsuarioResponsable(int idUsuario) {
        return viviendaDao.getAllViviendasByUsuarioResponsable(idUsuario);
    }

    @Override
    public List<Vivienda> getAllViviendasById(List<String> listaViviendas) {
        return viviendaDao.getAllViviendasById(listaViviendas);
    }

    @Transactional
    @Override
    public boolean quitarVivienda(int idVivienda) {
        if(viviendaDao.eliminaVivienda(idVivienda) > 0) {
            return true;
        } else {
            return false;
        }
    }
    
}