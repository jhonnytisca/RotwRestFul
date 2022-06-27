/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import mx.com.rotwsservice.model.Historial;
/**
 *
 * @author Marco Villa
 */
public interface HistorialDao {
    public boolean generarHistorico(Historial historial);
}
