/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import mx.com.rotwsservice.dao.ContratoDaoImpl;
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.model.Contrato;
import mx.com.rotwsservice.model.Usuario;
import mx.com.rotwsservice.model.Vivienda;
import mx.com.rotwsservice.service.ContratoService;
import mx.com.rotwsservice.service.MailService;
import mx.com.rotwsservice.service.UsuarioService;
import mx.com.rotwsservice.service.ViviendaService;
import mx.com.rotwsservice.util.CorreosTemplates;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mvillavicencio
 */

@RestController
@RequestMapping("/contrato")
public class ContratoController {
    
    @Autowired
    private ContratoService contratoService;
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ViviendaService viviendaService;
    
    @PostMapping("/")
    public ResponseEntity createContrato(@RequestBody Contrato contrato) throws Exception{
        
        int success = contratoService.addContrato(contrato);
        if(success != 0){
            VelocityContext contextRoomie = new VelocityContext();
            VelocityContext contextAnfitrion = new VelocityContext();
            contextAnfitrion.put("nombreAnfitrion", this);
            Vivienda viviendaRoomie = viviendaService.getViviendaPorId(contrato.getVivienda().getIdVivienda());
            Usuario roomie = usuarioService.getUsuarioById(contrato.getUsuario().getIdUsuario());
            Usuario anfirtrion = usuarioService.getUsuarioById(viviendaRoomie.getIdResponsable());
            List<String> listaCorreosRoomie = new ArrayList<>();
            List<String> listaCorreosAnfitrion = new ArrayList<>();
            if(roomie.getCorreoElectronico() != null){
                listaCorreosRoomie.add(roomie.getCorreoElectronico());
            }
            if(roomie.getCorreoRed() != null){
                listaCorreosRoomie.add(roomie.getCorreoRed());
            }
            if(anfirtrion.getCorreoElectronico() != null){
                listaCorreosAnfitrion.add(anfirtrion.getCorreoElectronico());
            }
            if(anfirtrion.getCorreoRed() != null){
                listaCorreosAnfitrion.add(anfirtrion.getCorreoRed());
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(contrato.getFechaInicio());
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            contextAnfitrion.put("nombreAnfitrion", anfirtrion.getNombre());
            contextAnfitrion.put("nombreRoomie", roomie.getNombre());
            contextAnfitrion.put("dia", dayOfMonth);
            contextAnfitrion.put("lugar", contrato.getVivienda().getDireccion());
            contextRoomie.put("nombreRoomie", roomie.getNombre());
            contextRoomie.put("dia", dayOfMonth);
            contextRoomie.put("lugar", contrato.getVivienda().getDireccion());
            String []correosRoomie = new String[listaCorreosRoomie.size()];
            String []correosAnfitrion = new String[listaCorreosAnfitrion.size()];
            mailService.sendEmail(listaCorreosRoomie.toArray(correosRoomie),
                    CorreosTemplates.INICIO_CONTRATO_ROOMIE_SUBJECT
                    ,CorreosTemplates.INICIO_CONTRATO_ROOMIE, contextRoomie);
            mailService.sendEmail(listaCorreosAnfitrion.toArray(correosAnfitrion),
                    CorreosTemplates.INICIO_CONTRATO_ANFITRION_SUBJECT
                    ,CorreosTemplates.INICIO_CONTRATO_ANFITRION, contextAnfitrion);
            return new ResponseEntity(success, HttpStatus.OK);
        }else{
            return new ResponseEntity(success, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{idContrato}")
    public ResponseEntity getContratoById(@PathVariable("idContrato") Integer idContrato){
        Contrato contrato = null;
        contrato = contratoService.getContratoById(idContrato);
        return new ResponseEntity(contrato, HttpStatus.OK);
    }
    
    @PutMapping("/checkIn")
    public ResponseEntity checkIn(@RequestBody Contrato contrato, @RequestParam boolean esRoomie){
        ErrorDto error = new ErrorDto();
        error = contratoService.checkIn(contrato, esRoomie);
        System.out.println("mx.com.rotwsservice.controller.ContratoController.checkIn()");
        if(error.getNumeroError() != 0){
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity(error, HttpStatus.OK);
        }
    }
    
    @GetMapping("/getCheckIn/{idContrato}")
    public ResponseEntity getCheckIn(@PathVariable Integer idContrato, @RequestParam boolean esRoomie){
        Contrato contrato = null;
        contrato = contratoService.getCheckIn(idContrato, esRoomie);
        return new ResponseEntity(contrato, HttpStatus.OK);
    }
}
