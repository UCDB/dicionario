package br.ucdb.Controller;


import br.ucdb.Service.UsuarioService;
import br.ucdb.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UsuarioService usuarioService;


    @RequestMapping(value = "/autenticar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Usuario> autenticar(@RequestBody Usuario usuario){

        return new ResponseEntity<Usuario>(usuarioService.buscaUsuario(usuario), HttpStatus.OK);
    }

}
