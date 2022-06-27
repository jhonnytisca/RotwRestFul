/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.controller;

import java.util.List;
import mx.com.rotwsservice.dao.PagoDaoImpl;
import mx.com.rotwsservice.model.Pago;
import mx.com.rotwsservice.service.PagoService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mvillavicencio
 */
@RestController
@RequestMapping("/pago")
public class PagoController {
    
    @Autowired
    private PagoService pagoService;
    
    @PostMapping("/")
    public ResponseEntity createPago(@RequestBody Pago pago){
        try{
            int res = pagoService.createPago(pago);
            return new ResponseEntity(res,HttpStatus.OK);
        }catch(HibernateException ex){
            ex.printStackTrace();
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{idContrato}")
    public ResponseEntity getPagos(@PathVariable("idContrato") Integer idContrato){
        List<Pago> pagos;
        try{
            pagos = pagoService.getPagosByContrato(idContrato);
            return new ResponseEntity(pagos,HttpStatus.OK);
        }catch(HibernateException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
