/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Context;
import mx.com.rotwsservice.authUtils.AuthUtils;
import mx.com.rotwsservice.dto.LoggedUserDto;
import mx.com.rotwsservice.model.Token;
import mx.com.rotwsservice.dto.UsuarioAutenticaDto;
import mx.com.rotwsservice.model.Usuario;
import mx.com.rotwsservice.model.request.RecuperarPasswordRequest;
import mx.com.rotwsservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Marco Villa
 */
@RestController
@RequestMapping("/")
public class LoggController {

    private static final Logger LOG = Logger.getLogger(LoggController.class.getName());
    
    @Autowired
    private UsuarioService usuarioService;
    
    //@Autowired
    //private ;
    
    /*@GetMapping("/{credenciales}")
    public ResponseEntity login(@PathVariable("credenciales") String credenciales){
        String []crd = credenciales.split("-");
        String usr = crd[0];
        String pass = crd[1]; /login/
        if(usuarioDaoImpl.identifica(usr, pass)){
            return new ResponseEntity(true, HttpStatus.OK);
        }else{
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }
    }*/
        
    /**
     *
     * @param request
     * @param usuarioAutentica
     * @return
     * @throws JOSEException
     */
    @PostMapping("login/")
    public ResponseEntity login(final HttpServletRequest request, @RequestBody UsuarioAutenticaDto usuarioAutentica) throws JOSEException, IOException {
        System.out.println("Se ha realizado una peticion ");
        System.out.println("Token: "+usuarioAutentica.getFtoken());
        int tipoInicio = 0;
        try{
            tipoInicio = Integer.parseInt(usuarioAutentica.getData2());
        }catch(NumberFormatException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        try{
            switch (tipoInicio) {
                case 0:
                    Usuario usuario = usuarioService.identifica(usuarioAutentica);
                    if(usuario != null){
                        LoggedUserDto loggedUserDto = new LoggedUserDto();
                        ObjectMapper mapper = new ObjectMapper();
                        loggedUserDto.setNombreUsuario(usuarioAutentica.getData0());
                        /*for(TipoUsuarioSeccion objeto : usuario.getTipoUsuario().getSecciones()){
                            secciones.add(objeto.getSeccion());
                        }*/
                        loggedUserDto.setIdUsuario(usuario.getIdUsuario());
                        loggedUserDto.setAvatar(usuario.getAvatar());
                        loggedUserDto.setTipoUsuario(usuario.getTipoUsuario().getIdTipoUsuario());
                        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
                        mapper.writeValue(System.out, loggedUserDto);

                        String jsonString = mapper.writeValueAsString(loggedUserDto);
                        final Token token = AuthUtils.createToken(request.getRemoteHost(),jsonString);
                        System.out.println("Es correcto");
                        HttpSession session = request.getSession();
                        if (session.getAttribute("usuariosSesion") == null) {
                            Set<String> usuariosSesion = new HashSet<>();
                            usuariosSesion.add(usuarioAutentica.getData0());
                            session.setAttribute("usuariosSesion", usuariosSesion);
                        } else {
                            Set<String> usuariosSesion = (Set<String>)session.getAttribute("usuariosSesion");
                            usuariosSesion.add(usuarioAutentica.getData0());
                            session.setAttribute("usuariosSesion", usuariosSesion);
                        }
                        return new ResponseEntity(token, HttpStatus.OK);
                    }else{
                        System.out.println("Es incorrecot");
                        return new ResponseEntity(false, HttpStatus.UNAUTHORIZED);
                    }
                case 1:
                    if(true){
                        final Token token = AuthUtils.createToken(request.getRemoteHost(),usuarioAutentica.getData0());
                        return new ResponseEntity(token, HttpStatus.OK);
                    }else {
                        return new ResponseEntity(false, HttpStatus.UNAUTHORIZED);
                    }
                default:
                    if(true){
                        final Token token = AuthUtils.createToken(request.getRemoteHost(),usuarioAutentica.getData0());
                        return new ResponseEntity(token, HttpStatus.OK);
                    }else{
                        return new ResponseEntity(false, HttpStatus.UNAUTHORIZED);
                    }
            }
        }catch(Exception ex){
            System.err.println("ERROR ::: "+ex.getMessage());
            ex.printStackTrace();
            return new ResponseEntity(false, HttpStatus.UNAUTHORIZED);
        }
    }
    
    @PostMapping("logout/")
    public ResponseEntity logout(){
        return new ResponseEntity(null,HttpStatus.OK);
    }
    
    @PostMapping("recuperarPassword/")
    public ResponseEntity<String> recuperarPassword(@RequestParam final String correoUsuario) {
        String res = usuarioService.generarLinkRecuperarPassword(correoUsuario);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    @PostMapping("validaURL/")
    public ResponseEntity<String> validaURL(@RequestParam final String urlParam) {
        String res = usuarioService.validaUrl(urlParam);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    @PutMapping("actualizaCtsna/")
    public ResponseEntity<String> actualizaCtsna(@RequestBody final RecuperarPasswordRequest request){
        LOG.info("actualizaCtsna");
        return new ResponseEntity<>(usuarioService.updatePassword(request), HttpStatus.OK);
    }
}
