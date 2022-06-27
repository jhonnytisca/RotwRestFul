/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.util;

import mx.com.rotwsservice.dao.HistorialDaoImpl;
import mx.com.rotwsservice.dao.TipoEntradaHistorialDaoImpl;
import mx.com.rotwsservice.dao.UsuarioDaoImpl;
import mx.com.rotwsservice.model.Historial;

/**
 *
 * @author Marco Villa
 */
public class HistorialUtil {
    
    /** Tipos de entrada */
    public static int INSERT = 1;
    public static int UPDATE = 2;
    public static int DELETE = 3;
    
    public static void salvarHistorial(int idUsuario, String descripcion, int tipoEntrada){
        
//        UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
//        TipoEntradaHistorialDaoImpl tipoEntradaHistorialDaoImpl = new TipoEntradaHistorialDaoImpl();
//        HistorialDaoImpl historialDaoImpl = new HistorialDaoImpl();
//        
//        Historial historial = new Historial();
//        historial.setDescripcion(descripcion);
//        historial.setUsuario(usuarioDaoImpl.getUsuarioById(1));
//        historial.setTipoEntradaHistorial(tipoEntradaHistorialDaoImpl.getTipoEntradaHistorialById(tipoEntrada));
//        
//        historialDaoImpl.generarHistorico(historial);
    }
}