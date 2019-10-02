package br.com.locadorafour.projeto.controller;

import br.com.locadorafour.projeto.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/filme", produces = "application/json")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    // buscar todos filmes da locadora
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping()
    public ResponseEntity<?> buscaLista() {
        return new ResponseEntity<>(filmeService.buscaLista(), HttpStatus.OK);
    }

    // buscar filmes disponiveis
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(value = "/disponiveis", produces = "application/json")
    public ResponseEntity<?> buscaListaDisponivel() {
        return new ResponseEntity<>(filmeService.buscaListaDisponivel(), HttpStatus.OK);
    }

    // buscar filmes disponiveis por nome
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(value = "/{nome}", produces = "application/json")
    public ResponseEntity<?> buscaPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(filmeService.buscaPorNome(nome), HttpStatus.OK);
    }

}
