package br.ucdb.Service;
import br.ucdb.Repository.DicionarioRepository;
import br.ucdb.model.Dicionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicionarioService {

    @Autowired
    DicionarioRepository dicionarioRepository;


    public Dicionario cadastrar(Dicionario dicionario){

        return dicionarioRepository.save(dicionario);
    }

    public List<Dicionario> buscaTodos(){
        return dicionarioRepository.findAll();
    }

    public void excluirPalavra(Dicionario palavra){
      dicionarioRepository.delete(palavra);
    }
}
