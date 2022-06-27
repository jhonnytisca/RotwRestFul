/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import mx.com.rotwsservice.model.Vivienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.com.rotwsservice.service.ViviendaService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Marco Villa
 */
@RestController
@RequestMapping("/viviendas")
public class ViviendaController {

    private static final Logger LOG = Logger.getLogger(ViviendaController.class.getName());
    
    @Autowired
    private ViviendaService viviendaService;
    
    @GetMapping("/")
    public ResponseEntity getViviendas() {
        System.out.println("mx.com.rotwsservice.rotwsservice.controller.ViviendaController.getViviendas() :: P1");
        List<Vivienda> viviendas = viviendaService.getAllViviendas();
        System.out.println("mx.com.rotwsservice.rotwsservice.controller.ViviendaController.getViviendas() :: P2");
        if(viviendas != null && viviendas.size()>0){
            return new ResponseEntity(viviendas, HttpStatus.OK);
        }else{
            return new ResponseEntity("error", HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/activas")
    public ResponseEntity getViviendasActivas() {
        LOG.info("mx.com.rotwsservice.rotwsservice.controller.ViviendaController.getViviendasActivas()");
        List<Vivienda> viviendas = viviendaService.obtenerViviendasActivas();
        if(viviendas != null && viviendas.size()>0){
            return new ResponseEntity(viviendas, HttpStatus.OK);
        }else{
            return new ResponseEntity("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    public ResponseEntity createVivienda(@RequestBody Vivienda vivienda){
        boolean success = viviendaService.createVivienda(vivienda);
        if(success){
            //HistorialUtil.salvarHistorial(1, "Se ha dado de alta la vivienda "+vivienda.getTitulo(), HistorialUtil.INSERT);
            return new ResponseEntity(vivienda, HttpStatus.OK);
        }else{
            return new ResponseEntity(vivienda, HttpStatus.NOT_MODIFIED);
        }
    }
    
    @GetMapping("/{idVivienda}")
    public ResponseEntity getViviendaById(@PathVariable("idVivienda") int idVivienda){
        Vivienda vivienda = viviendaService.getViviendaPorId(idVivienda);
        if(vivienda!=null){
            return new ResponseEntity(vivienda, HttpStatus.OK);
        }else{
            return new ResponseEntity(vivienda, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/lista/{viviendasId}")
    public ResponseEntity getAllViviendasById(@PathVariable("viviendasId") String viviendasId){
        List<String> listaViviendas = Arrays.asList(viviendasId.split("-"));
        List<Vivienda> viviendas = viviendaService.getAllViviendasById(listaViviendas);
        if(viviendas!=null && viviendas.size() > 0){
            return new ResponseEntity(viviendas, HttpStatus.OK);
        }else{
            return new ResponseEntity(viviendas, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/")
    public ResponseEntity updateVviienda(@RequestBody Vivienda vivienda){
        boolean success = viviendaService.updateVivienda(vivienda);
        if(success){
            //HistorialUtil.salvarHistorial(1, "Se ha modificado la vivienda "+vivienda.getTitulo(), HistorialUtil.UPDATE);
            return new ResponseEntity(vivienda, HttpStatus.OK);
        }else{
            return new ResponseEntity(vivienda, HttpStatus.NOT_MODIFIED);
        }
    }
    
    @GetMapping("/Responsable/{idResponsable}")
    public ResponseEntity getAllViviendasByUsuarioResponsable(@PathVariable("idResponsable") int idResponsable){
        List<Vivienda> viviendas = new ArrayList<>();
        viviendas = viviendaService.getAllViviendasByUsuarioResponsable(idResponsable);
        if(viviendas.size() > 0){
            return new ResponseEntity(viviendas, HttpStatus.OK);
        }else{
            return new ResponseEntity("Sin Viviendas", HttpStatus.OK);
        }
    }
    
    @PutMapping("/bajaVivienda")
    public ResponseEntity<String> darDeBajaVivienda(@RequestParam Integer idVivenda){
        if(viviendaService.quitarVivienda(idVivenda)) {
            return new ResponseEntity<>("Vivienda eliminada.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al eliminar vivienda.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
