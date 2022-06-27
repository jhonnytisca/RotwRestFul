/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mx.com.rotwsservice.authUtils.AuthUtils;
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.dto.InfoFacebookDto;
import mx.com.rotwsservice.dto.LoggedUserDto;
import mx.com.rotwsservice.model.TipoUsuario;
import mx.com.rotwsservice.model.Token;
import mx.com.rotwsservice.model.Usuario;
import mx.com.rotwsservice.service.MailService;
import mx.com.rotwsservice.service.UsuarioService;
import mx.com.rotwsservice.util.AES;
import mx.com.rotwsservice.util.CorreosTemplates;
import mx.com.rotwsservice.util.HistorialUtil;
import org.apache.velocity.VelocityContext;
import org.hibernate.HibernateException;
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
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Marco Villa
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {    
    
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MailService mailService;
    
    @GetMapping("/")
    public List getUsuarios() {
        return usuarioService.getUsuarios();
    }
    
    @PostMapping("/roomie/")
    public ResponseEntity createRoomie(@RequestBody Usuario usuario) throws Exception{
        ErrorDto errorDto = new ErrorDto();
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(3);
        usuario.setEstatus(true);
        usuario.setTipoUsuario(tipoUsuario);
        if(usuarioService.validaUsuario(usuario.getNombreUsuario()) > 0) {
            return new ResponseEntity("Usuario ya registrado con el nombre de usuario ingresado", HttpStatus.NOT_ACCEPTABLE);
        }
        if(usuarioService.validaCorreo(usuario.getCorreoElectronico(), usuario.getCorreoRed()) > 0) {
            return new ResponseEntity("Usuario ya registrado con correo electrónico", HttpStatus.CONFLICT);
        } 
        errorDto = usuarioService.createUsuario(usuario);
        if(errorDto.getNumeroError() != -1){
            HistorialUtil.salvarHistorial(1, "Se ha dado de alta al roomie "+usuario.getNombreUsuario(),HistorialUtil.INSERT);
            VelocityContext context = new VelocityContext();
            context.put("nombreRoomie",usuario.getNombre());
            mailService.sendEmail(new String[]{usuario.getCorreoElectronico()}, CorreosTemplates.BIENVENIDA_ROOMIE_SUBJECT, CorreosTemplates.BIENVENIDA_ROOMIE, context);
            return new ResponseEntity(errorDto.getNumeroError(), HttpStatus.OK);
        }else{
            return new ResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/roomie/")
    public ResponseEntity updateRoomie(@RequestBody Usuario usuario){
        
        ErrorDto errorDto = new ErrorDto();
        errorDto = usuarioService.updateRoomie(usuario);
        System.out.println("updateRoomie");
        if(errorDto.getNumeroError() != -1){
            System.out.println("Actualizo roomie");
            HistorialUtil.salvarHistorial(1, "Se ha modificado al roomie "+usuario.getNombreUsuario(),HistorialUtil.INSERT);
            return new ResponseEntity(errorDto.getNumeroError(), HttpStatus.OK);
        }else{
            System.out.println("Hubo error al actualizar roomie");
            return new ResponseEntity(errorDto, HttpStatus.NOT_MODIFIED);
        }
    }
      
    @PutMapping("/usuarioFb/")
    public ResponseEntity updateUsuariofb(@RequestBody Usuario usuario){
        
        ErrorDto errorDto = new ErrorDto();
        errorDto = usuarioService.updateUsuario(usuario);
        System.out.println("updateRoomie");
        if(errorDto.getNumeroError() != -1){
            System.out.println("Actualizo roomie");
            HistorialUtil.salvarHistorial(1, "Se ha modificado al roomie "+usuario.getNombreUsuario(),HistorialUtil.INSERT);
            return new ResponseEntity(errorDto.getNumeroError(), HttpStatus.OK);
        }else{
            System.out.println("Hubo error al actualizar roomie");
            return new ResponseEntity(errorDto, HttpStatus.NOT_MODIFIED);
        }
    }
    @PutMapping("/responsableVivienda/")
    public ResponseEntity updateResponsableVivienda(HttpServletRequest request,@RequestBody Usuario usuario){
        System.out.println("mx.com.rotwsservice.controller.UsuarioController.updateResponsableVivienda()");
        ErrorDto errorDto = new ErrorDto();
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(4);
        usuario.setEstatus(true);
        usuario.setTipoUsuario(tipoUsuario);
        try{
            errorDto = usuarioService.updateResponsable(usuario);
            if(errorDto.getNumeroError() != -1){
                HistorialUtil.salvarHistorial(1, "Se ha modificado responsable de vivienda "+usuario.getNombreUsuario(),HistorialUtil.INSERT);
                request.getSession().invalidate();
                return new ResponseEntity(errorDto.getNumeroError(), HttpStatus.OK);
            }else{
                request.getSession().invalidate();
                return new ResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(HibernateException ex){
            ex.printStackTrace();
            request.getSession().invalidate();
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception ex){
            ex.printStackTrace();
            request.getSession().invalidate();
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
            
    }
    
    @PostMapping("/responsableVivienda/")
    public ResponseEntity createResponsableVivienda(@RequestBody Usuario usuario) throws Exception{
        ErrorDto errorDto = new ErrorDto();
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(4);
         usuario.setEstatus(true);
        usuario.setTipoUsuario(tipoUsuario);
        if(usuarioService.validaUsuario(usuario.getNombreUsuario()) > 0) {
            return new ResponseEntity("Usuario ya registrado con el nombre de usuario ingresado", HttpStatus.NOT_ACCEPTABLE);
        }
        if(usuarioService.validaCorreo(usuario.getCorreoElectronico(), usuario.getCorreoRed()) > 0) {
            return new ResponseEntity("Usuario ya registrado con correo electrónico", HttpStatus.CONFLICT);
        } 
        errorDto = usuarioService.createUsuario(usuario);
        if(errorDto.getNumeroError() != -1){
            HistorialUtil.salvarHistorial(1, "Se ha dado de alta al responsable de vivienda "+usuario.getNombreUsuario(),HistorialUtil.INSERT);
            VelocityContext context = new VelocityContext();
            context.put("nombreAnfitrion",usuario.getNombre());
            mailService.sendEmail(new String[]{usuario.getCorreoElectronico(), "support@roomiesoftheworld.com"}
                    , CorreosTemplates.BIENVENIDA_ANFITRION_SUBJECT, CorreosTemplates.BIENVENIDA_ANFITRION, context);
            return new ResponseEntity(errorDto.getNumeroError(), HttpStatus.OK);
        }else{
            return new ResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{idUsuario}")
    public ResponseEntity getUsuarioById(@PathVariable("idUsuario") int idUsuario){
        System.out.println("PEticiÃ³n de usuario");
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if(usuario!=null){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }else{
            return new ResponseEntity(usuario, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/vivienda/{listaUsuarios}")
    public ResponseEntity getUsuariosById(@PathVariable("listaUsuarios") String idUsuario){
        List<String> idsUsuarios = Arrays.asList(idUsuario.split("-"));
        List<Usuario> usuarios = usuarioService.getUsuariosById(idsUsuarios);
        if(usuarios!=null){
            usuarios.forEach(usuario -> usuario.setPassword(null));
            return new ResponseEntity(usuarios, HttpStatus.OK);
        }else{
            return new ResponseEntity(usuarios, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("altaFB/")
    public ResponseEntity altaFB(final HttpServletRequest request, final @RequestBody String json) {
        AES aes = new AES();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            InfoFacebookDto infoFb = objectMapper.readValue(aes.decryptV2(json), InfoFacebookDto.class);
            Usuario usuario = usuarioService.validateUsuarioRed(infoFb.getId(), infoFb.getEmail());
            if(usuario != null){
                System.out.println("El usuario esta activo ::::::::::::::::::::::::::::::::::::: " + (usuario.isEstatus()));
                if(usuario.isEstatus()){
                    LoggedUserDto loggedUserDto = new LoggedUserDto();
                    ObjectMapper mapper = new ObjectMapper();
                    loggedUserDto.setNombreUsuario(usuario.getNombre());
                    loggedUserDto.setIdUsuario(usuario.getIdUsuario());
                    loggedUserDto.setAvatar(usuario.getAvatar());
                    loggedUserDto.setTipoUsuario(usuario.getTipoUsuario().getIdTipoUsuario());
                    mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
                    mapper.writeValue(System.out, loggedUserDto);
                    String jsonString = mapper.writeValueAsString(loggedUserDto);
                    final Token token = AuthUtils.createToken(request.getRemoteHost(),jsonString);
                    return new ResponseEntity(token,HttpStatus.OK);
                }else{
                    return new ResponseEntity(usuario.getIdUsuario(),HttpStatus.OK);
                }
            }
            usuario = new Usuario();
            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setIdTipoUsuario(3);
            usuario.setNombre(infoFb.getFirstName());
            usuario.setPrimerApellido(infoFb.getLastName());
            usuario.setTipoUsuario(tipoUsuario);
            usuario.setPerfilRed(infoFb.getId());
            usuario.setCorreoRed(infoFb.getEmail());
            usuario.setNombreUsuario(infoFb.getId());
            usuario.setCorreoElectronico(infoFb.getEmail());
            usuario.setSexo('M');
            ErrorDto error = usuarioService.createUsuario(usuario);
            return new ResponseEntity(error.getNumeroError(),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity(ex,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}