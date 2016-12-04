package br.ucdb.Controller;

import br.ucdb.Repository.UsuarioRepository;
import br.ucdb.exception.BaseException;
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
@RequestMapping("/adm")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PalavraController.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {

        if(usuario == null){
            LOGGER.error("Erro - Dados de usuário inexistentes");
            throw new BaseException("Dados de usuário inexistentes ");
        }

        if(usuarioRepository.buscaCpf(usuario.getCpf()) != null){
            LOGGER.error("Erro - Usuário já cadastrado");
            throw new BaseException("Usuário já cadastrado");

        }

        return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);

    }

    @GetMapping("/usuario")
    public List<Usuario> buscarTodos() {

        if(usuarioRepository.findAll().isEmpty()){
            throw new BaseException("Não ha usuários cadastrados");
        }

        return usuarioRepository.findAll();

    }

    @GetMapping("/usuario/{cpf}")
    public ResponseEntity<Usuario> buscaCpf(@PathVariable String cpf) {

        if(usuarioRepository.buscaCpf(cpf) == null){
            throw new  BaseException("Usuário nao encontrado");
        }

        return new ResponseEntity<Usuario>(usuarioRepository.buscaCpf(cpf), HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{id}")
    public void excluirUsuario(@PathVariable Integer id){
        try {
            usuarioRepository.delete(id);
        }catch (Exception ex){
            LOGGER.error("Erro - Erro ao deletar usuario");
            throw new BaseException("Erro ao deletar usuario");
        }
    }

    @PutMapping("/usuario")
    public ResponseEntity<Usuario> atualizarCadastro(@RequestBody Usuario usuario){

        if(usuario == null){
            throw new BaseException("Dados de Usuário inexistentes");
        }
        return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.OK);

    }

}
