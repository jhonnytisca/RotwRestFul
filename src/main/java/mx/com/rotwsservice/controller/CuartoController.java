/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.controller;

/**
 *
 * @author Marco Villa
 */
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.model.Cuarto;
import mx.com.rotwsservice.service.CuartoService;
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

@RestController
@RequestMapping("/cuartos")
public class CuartoController {
    
    @Autowired
    private CuartoService cuartoService;
    
    @GetMapping("/{idCuarto}")
    public ResponseEntity getCuartoById(@PathVariable("idCuarto") Integer idCuarto){
        Cuarto cuarto = cuartoService.getCuartoById(idCuarto);
        if(cuarto == null){
            return new ResponseEntity("Cuarto no encontrado!!!",HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(cuarto,HttpStatus.OK);
        }
    }
    
    @PostMapping("/")
    public ResponseEntity getCuartoById(@RequestBody Cuarto cuarto){
        int res = cuartoService.addCuarto(cuarto);
        if(res == 0){
            return new ResponseEntity("Error al guardar cuarto!!!",HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(cuarto,HttpStatus.OK);
        }
    }
    
    @PutMapping("/")
    public ResponseEntity updateCuarto(@RequestBody Cuarto cuarto){
        ErrorDto errorDto = new ErrorDto();
        errorDto = cuartoService.updateCuarto(cuarto);
        if(errorDto.getNumeroError() == -1){
            return new ResponseEntity(errorDto.getError(),HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity(cuarto,HttpStatus.OK);
        }
    }
}
