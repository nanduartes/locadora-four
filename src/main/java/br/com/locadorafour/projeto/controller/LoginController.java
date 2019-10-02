package br.com.locadorafour.projeto.controller;

import br.com.locadorafour.projeto.entity.Usuario;
import br.com.locadorafour.projeto.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api")
public class LoginController {

    @Autowired
    private Security security;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(path = "login",  produces = "application/json")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        String token = this.security.fazLogin(usuario.getEmail(), usuario.getSenha());
        if (token != null){
            String msg = "{\"token_acesso\":\"" + token + "\"}";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else{
            String msg = "{\"mensagem\":\"Falha de autenticação\"}";
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "sair", produces = "application/json")
    public ResponseEntity<?> buscarUsuarios(@RequestHeader(name = "token", defaultValue = "0") Integer token) {
        if (this.security.validaToken(token)) {
            this.security.sair(token);
            String msg = "{\"mensagem\":\"Volte sempre\"}";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            String msg = "{\"mensagem\":\"Falha de autenticação\"}";
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
        }
    }
}
