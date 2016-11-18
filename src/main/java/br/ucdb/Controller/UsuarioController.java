package br.ucdb.Controller;

import br.ucdb.Repository.UsuarioRepository;
import br.ucdb.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/admin")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/usuario")
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuario/ {cpf} ")
    public ResponseEntity<Usuario> buscaCpf(@PathVariable String cpf){
        return new ResponseEntity<Usuario>(usuarioRepository.buscaCpf(cpf), HttpStatus.OK);
    }

    @DeleteMapping("/usuario")
    public void excluirUsuario(@RequestBody Usuario usuario){

            usuarioRepository.delete(usuario);
    }

    @PutMapping("/usuario")
    public ResponseEntity<Usuario> atualizarCadastro(@RequestBody Usuario usuario){
        return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.OK);

    }

}
