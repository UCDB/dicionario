package br.ucdb.Controller;


import br.ucdb.Service.DicionarioService;
import br.ucdb.model.Dicionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PalavraController {

    @Autowired
    DicionarioService dicionarioService;

    @RequestMapping(value = "/dicionario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Dicionario> cadastrarPalavra(@RequestBody Dicionario palavra){

        Dicionario paavraCad = dicionarioService.cadastrar(palavra);

        return new ResponseEntity<Dicionario>(paavraCad, HttpStatus.OK);
    }

    @RequestMapping(value = "/dicionario", method = RequestMethod.GET)
    public List<Dicionario> buscarTodasPalavras(){
        return dicionarioService.buscaTodos();
    }

    @RequestMapping(value = "/dicionario", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void excluirPalavra(@RequestBody Dicionario palavra){
        dicionarioService.excluirPalavra(palavra);
    }///Melhorar metodo, pois nao esta dando erro ao passar um cadastro inexistente
}
