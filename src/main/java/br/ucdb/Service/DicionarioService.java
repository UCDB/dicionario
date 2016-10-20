package br.ucdb.Service;

import br.ucdb.Controller.Repository.DicionarioRepository;
import br.ucdb.model.Dicionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicionarioService {

    @Autowired
    DicionarioRepository dicionarioRepository;

    public Dicionario cadastrar(Dicionario dicionario){

        return dicionarioRepository.save(dicionario);
    }
}
