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

@RestController
public class PalavraController {

    @Autowired
    DicionarioService dicionarioService;

    @RequestMapping(value = "/dicionario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Dicionario> cadastrarPalavra(@RequestBody Dicionario dicionario){

        Dicionario paavraCad = dicionarioService.cadastrar(dicionario);

        return new ResponseEntity<Dicionario>(paavraCad, HttpStatus.OK);
    }
}
