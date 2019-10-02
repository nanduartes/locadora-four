package br.com.locadorafour.projeto.controller;

import br.com.locadorafour.projeto.entity.Usuario;
import br.com.locadorafour.projeto.security.Security;
import br.com.locadorafour.projeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Security security;

    // buscar usuarios cadastrados
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> buscarUsuarios(@RequestHeader(name = "token", defaultValue = "0") Integer token) {
        if (this.security.validaToken(token)) {
            return new ResponseEntity<>(this.usuarioService.buscarUsuarios(), HttpStatus.OK);
        } else {
            String msg = "{\"mensagem\": \"Falha de autenticação\"}";
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
        }
    }

    // buscar usuario por id ou nome
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(value = "/{find}", produces = "application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable(value = "find", required = false) String find, @RequestHeader(name = "token", defaultValue = "0") Integer token) {
        if (this.security.validaToken(token)) {
            if (find.matches("^[0-9]*$")) {
                int id = Integer.parseInt(find);
                return new ResponseEntity<>(this.usuarioService.buscaPorId(id), HttpStatus.OK);
            } else if (find.matches("^[a-zA-Z]*$")) {
                return new ResponseEntity<>(this.usuarioService.buscaPorNome(find), HttpStatus.OK);
            } else {
                String msg = "{\"mensagem\": \"Caracteres especiais não são permitidos\"}";
                return new ResponseEntity<>(msg, HttpStatus.FAILED_DEPENDENCY);
            }
        } else {
            String msg = "{\"mensagem\": \"Falha de autenticação\"}";
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
        }
    }

    // criar novo usuario
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(path = "criarUsuario", produces = "application/json")
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {
        String token = this.usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    // alterar usuario
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PutMapping(path = "alterarUsuario", produces = "application/json")
    public ResponseEntity<?> alterarUsuario(@RequestHeader(name = "token", defaultValue = "0") Integer token, @Valid @RequestBody Usuario usuario) {
        if (this.security.validaToken(token)) {
            String newtoken = this.usuarioService.alterarUsuario(usuario);
            return new ResponseEntity<>(newtoken, HttpStatus.OK);
        } else {
            String msg = "{\"mensagem\": \"Falha de autenticação\"}";
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
        }
    }
}
