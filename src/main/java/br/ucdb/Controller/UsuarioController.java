package br.ucdb.Controller;

import br.ucdb.Repository.UsuarioRepository;
import br.ucdb.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;


@RestController
//@RequestMapping("/admin")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PalavraController.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) throws ServletException {

        if(usuario == null){
            LOGGER.error("Erro - Dados de usuário inexistentes");
            throw new ServletException("Dados de usuário inexistentes ");
        }

        if(usuarioRepository.buscaCpf(usuario.getCpf()) != null){
            LOGGER.error("Erro - Usuário já cadastrado");
            throw new ServletException("Usuário já cadastrado");

        }

        return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);

    }

    @GetMapping("/usuario")
    public List<Usuario> buscarTodos() throws ServletException{

        if(usuarioRepository.findAll().isEmpty()){
            throw new ServletException("Não ha usuários cadastrados");
        }

        return usuarioRepository.findAll();

    }

    @GetMapping("/usuario/ {cpf} ")
    public ResponseEntity<Usuario> buscaCpf(@PathVariable String cpf) throws ServletException{

        if(usuarioRepository.buscaCpf(cpf) == null){
            throw new  ServletException("Usuário nao encontrado");
        }

        return new ResponseEntity<Usuario>(usuarioRepository.buscaCpf(cpf), HttpStatus.OK);
    }

    @DeleteMapping("/usuario")
    public void excluirUsuario(@RequestBody Usuario usuario){

        usuarioRepository.delete(usuario);
    }

    @PutMapping("/usuario")
    public ResponseEntity<Usuario> atualizarCadastro(@RequestBody Usuario usuario) throws ServletException{

        if(usuario == null){
            throw new ServletException("Dados de Usuário inexistentes");
        }
        return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.OK);

    }

}
