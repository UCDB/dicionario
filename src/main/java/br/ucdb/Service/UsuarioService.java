package br.ucdb.Service;

import br.ucdb.Repository.UsuarioRepository;
import br.ucdb.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario buscaUsuario(Usuario usuario){


        Usuario usuarioBuscado = usuarioRepository.buscaLogin(usuario.getLogin());

        return usuarioBuscado;

//        if(usuarioBuscado == null){
//            // lança exeçao
//            return null;
//        }
//        else if(!usuarioBuscado.getSenha().equals(usuario.getSenha())){
//
//            // lança exeçao
//                return null;
//            }
//        else
//            return usuarioRepository.buscaPorId(usuario.getLogin());

    }
}
