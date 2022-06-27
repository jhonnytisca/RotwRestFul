/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.controller;

import javax.servlet.http.HttpServletRequest;
import mx.com.rotwsservice.dto.MailContainer;
import mx.com.rotwsservice.service.MailService;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mvillavicencio
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    
    @Autowired
    private MailService mailService;
    
    @GetMapping("/sendMailGet/")
    public ResponseEntity sendMailGet(HttpServletRequest request){
        try{
            VelocityContext context = new VelocityContext();
            context.put("name", "Marco Villavicencio");
            context.put("email", "marco.villa.rdz@gmail.com");
            context.put("message", "Contenido");
            mailService.sendEmail(new String[]{"marco.villa.rdz@gmail.com"},"Prueba"
                    ,"templates/templateExample.vm", context);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println("Enviando correo...");
        return new ResponseEntity(0, HttpStatus.OK);
    }
    
    @PostMapping("/sendMail")
    public ResponseEntity sendMail(final @RequestBody MailContainer container){
        try {
            VelocityContext context = new VelocityContext(container.getParametros());
            mailService.sendEmail(container.getDestinatarios(),
                    container.getAsunto()
                    ,container.getPlantilla(), context);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(0, HttpStatus.OK);
    }
    
}
