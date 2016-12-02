package br.ucdb.Controller;


import br.ucdb.Repository.DicionarioRepository;
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
public class PalavraController {


    private static final Logger LOGGER = LoggerFactory.getLogger(PalavraController.class);

    @Autowired
    DicionarioRepository dicionarioRepository;

   @PostMapping("/dicionario")
    public ResponseEntity<Dicionario> cadastrarPalavra(@RequestBody Dicionario palavra) throws ServletException{

        if(dicionarioRepository.buscaPalavra(palavra.getPalavra()) == null){
            return new ResponseEntity<Dicionario>(dicionarioRepository.save(palavra), HttpStatus.OK);
        }else {
            LOGGER.error("Erro - Palavra ja Cadastrada");
            throw  new ServletException("Palavra ja Cadastrada");
        }
    }

    @GetMapping ("/dicionario")
    public List<Dicionario> buscarTodasPalavras() throws ServletException{
        if(! dicionarioRepository.findAll().isEmpty()){
            return dicionarioRepository.findAll();
        }else
            LOGGER.error("Erro - Erro ao Buscar Palavras");
            throw new ServletException("Erro ao Buscar Palavras");

    }

    @DeleteMapping("/dicionario/{id}")
    public void excluirPalavra(@PathVariable Integer id) throws ServletException{
        try {
            dicionarioRepository.delete(id);
        }catch (Exception ex){
            LOGGER.error("Erro - Erro ao deletar palavra");
            throw new ServletException("Erro ao deletar palavra");
        }
    }

    @GetMapping("/dicionario/{palavra}")
    private ResponseEntity<Dicionario> buscaPalvra(@PathVariable String palavra) throws ServletException {
        if(dicionarioRepository.buscaPalavra(palavra) != null) {
            return new ResponseEntity<Dicionario>(dicionarioRepository.buscaPalavra(palavra), HttpStatus.OK);
        }else
            throw new ServletException("Palavra não encontrada !");
    }

    @PutMapping("/dicionario")
    public ResponseEntity<Dicionario> atualizaPalavra(@RequestBody Dicionario palavra) throws ServletException{
        try {
            return new ResponseEntity<Dicionario>(dicionarioRepository.save(palavra), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("Erro - Erro ao atualizar cadastro da palavra");
            throw new ServletException("Erro ao atualizar palavra");
        }
    }
}
