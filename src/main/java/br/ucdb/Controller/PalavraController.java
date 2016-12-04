package br.ucdb.Controller;


import br.ucdb.Repository.DicionarioRepository;
import br.ucdb.exception.BaseException;
import br.ucdb.model.Dicionario;
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
public class PalavraController {


    private static final Logger LOGGER = LoggerFactory.getLogger(PalavraController.class);

    @Autowired
    DicionarioRepository dicionarioRepository;

   @PostMapping("/dicionario")
    public ResponseEntity<Dicionario> cadastrarPalavra(@RequestBody Dicionario palavra) {

        if(dicionarioRepository.buscaPalavra(palavra.getPalavra()) == null){
            return new ResponseEntity<Dicionario>(dicionarioRepository.save(palavra), HttpStatus.OK);
        }else {
            LOGGER.error("Erro - Palavra ja Cadastrada");
            throw  new BaseException("Palavra já Cadastrada !");
        }
    }

    @GetMapping ("/dicionario")
    public List<Dicionario> buscarTodasPalavras() {
        if(! dicionarioRepository.findAll().isEmpty()){
            return dicionarioRepository.findAll();
        }else
            LOGGER.error("Erro - Erro ao Buscar Palavras");
            throw new BaseException("Nenhuma palavra cadastrada !");

    }

    @DeleteMapping("/dicionario/{id}")
    public void excluirPalavra(@PathVariable Integer id) {
        try {
            dicionarioRepository.delete(id);
        }catch (Exception ex){
            LOGGER.error("Erro - Erro ao deletar palavra");
            throw new BaseException("Erro ao deletar palavra !");
        }
    }

    @GetMapping("/dicionario/{palavra}")
    private ResponseEntity<Dicionario> buscaPalvra(@PathVariable String palavra) {
        if(dicionarioRepository.buscaPalavra(palavra) != null) {
            return new ResponseEntity<Dicionario>(dicionarioRepository.buscaPalavra(palavra), HttpStatus.OK);
        }else
            throw new BaseException("Palavra não encontrada !");
    }

    @PutMapping("/dicionario")
    public ResponseEntity<Dicionario> atualizaPalavra(@RequestBody Dicionario palavra){
        try {
            return new ResponseEntity<Dicionario>(dicionarioRepository.save(palavra), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("Erro - Erro ao atualizar cadastro da palavra");
            throw new BaseException("Erro ao atualizar palavra !");
        }
    }
}
