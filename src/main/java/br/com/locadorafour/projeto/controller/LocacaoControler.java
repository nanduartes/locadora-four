package br.com.locadorafour.projeto.controller;

import br.com.locadorafour.projeto.security.Security;
import br.com.locadorafour.projeto.service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api")
public class LocacaoControler {

    @Autowired
    private LocacaoService locacaoService;

    @Autowired
    private Security security;

    // aluga filme
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(value = "/aluga/{id}", produces = "application/json")
    public ResponseEntity<?> alugaFilme(@PathVariable Integer id, @RequestHeader(name = "token", defaultValue = "0") Integer token) {
        if (this.security.validaToken(token)) {
            String result = this.locacaoService.alugaFilme(id, token);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            String msg = "{\"mensagem\":\"Falha de autenticação\"}";
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
        }
    }

    // devolve filme
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(value = "/devolve/{id}", produces = "application/json")
    public ResponseEntity<?> devolveFilme(@PathVariable Integer id, @RequestHeader(name = "token", defaultValue = "0") Integer token) {
        if (this.security.validaToken(token)) {
            String result = this.locacaoService.devolveFilme(id, token);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            String msg = "{\"mensagem\":\"Falha de autenticação\"}";
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
        }
    }
}
