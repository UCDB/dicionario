package br.ucdb.Controller;


import br.ucdb.Service.UsuarioService;
import br.ucdb.model.Usuario;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    UsuarioService usuarioService;


    @RequestMapping(value = "/autenticar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException{

        if(usuario.getLogin() == null || usuario.getSenha() == null){
            throw new ServletException("Nome e senha são obrigatorios");
        }

        Usuario usuarioBuscado = usuarioService.buscaUsuario(usuario);

        if(usuarioBuscado == null ){
            throw new ServletException("Usuario não encontrado");
        }
        if(!usuarioBuscado.getSenha().equals(usuario.getSenha())){
            throw new ServletException("Usuario ou senha ivalidos");
        }

        String token = Jwts.builder()
                .setSubject(usuarioBuscado.getLogin())
                .signWith(SignatureAlgorithm.HS512, "banana")
                .setExpiration(new Date(System.currentTimeMillis() +1 *60*1000))
                .compact();

        return new LoginResponse(token);

    }

    private class LoginResponse{
        public String token;

        public LoginResponse(String token){
            this.token = token;
        }

        public String getToken() {
            return token;
        }


    }


}

