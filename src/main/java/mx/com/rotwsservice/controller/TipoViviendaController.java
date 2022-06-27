/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.controller;

import java.util.List;
import mx.com.rotwsservice.dao.TipoViviendaDaoImpl;
import mx.com.rotwsservice.service.TipoViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marco Villa
 */
@RestController
@RequestMapping("/tiposVivienda")
public class TipoViviendaController {
    
    @Autowired
    private TipoViviendaService tipoViviendaService;
    
    @GetMapping("/")
    public ResponseEntity getTiposVivienda() {
        return new ResponseEntity(tipoViviendaService.getAllTipoVivienda(), HttpStatus.OK);
    }
}
