/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.controller;

import java.util.List;
import mx.com.rotwsservice.dao.TipoCuartoDaoImpl;
import mx.com.rotwsservice.service.TipoCuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marco Villa
 */
@RestController
@RequestMapping("/tipoCuarto")
public class TipoCuartoController {
    
    @Autowired
    private TipoCuartoService tipoCuartoService;
    
    @GetMapping("/")
    public List getTiposVivienda() {
        return tipoCuartoService.getAllTipoCuarto();
    }
}
