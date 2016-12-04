package br.ucdb.Controller;


import br.ucdb.Service.UsuarioService;
import br.ucdb.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PalavraController.class);

    @Autowired
    UsuarioService usuarioService;


    @RequestMapping(value = "/autenticar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException{

        if(usuario.getLogin() == null || usuario.getSenha() == null){
            LOGGER.error("Nome e senha são obrigatorios");
            throw new ServletException("Nome e senha são obrigatorios");
        }

        Usuario usuarioBuscado = usuarioService.buscaUsuario(usuario);

        if(usuarioBuscado == null ){
            LOGGER.error("Usuario não encontrado");
            throw new ServletException("Usuario não encontrado");
        }
        if(!usuarioBuscado.getSenha().equals(usuario.getSenha())){
            LOGGER.error("Usuario ou senha ivalidos");
            throw new ServletException("Usuario ou senha ivalidos");
        }

        String token = Jwts.builder()
                .setSubject(usuarioBuscado.getLogin())
                .signWith(SignatureAlgorithm.HS512, "dicionario")
                .setExpiration(new Date(System.currentTimeMillis() +1 * 60*1000))
                .compact();

        return new LoginResponse(token);

    }

    private class LoginResponse{
        private String token;

        private LoginResponse(String token){
            this.token = token;
        }

        public String getToken() {
            return token;
        }


    }


}

