package br.com.locadorafour.projeto.security;

import br.com.locadorafour.projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Service
@Transactional(readOnly = false)
public class Security {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // valida token
    public Boolean validaToken(Integer token) {
        Integer t = this.usuarioRepository.validaToken(token);
        if (t == 1) {
            return true;
        } else return false;
    }

    // faz login
    public String fazLogin(String email, String senha) {
        Integer id = this.usuarioRepository.fazLogin(email, senha);
        if (id != null) {
            Random random = new Random();
            this.usuarioRepository.geraToken(random.nextInt(999999), id);
            Date data = Date.from(LocalDateTime.now().plusMinutes(20).atZone(ZoneId.systemDefault()).toInstant());
            this.usuarioRepository.geraSessao(data, id);
            return this.usuarioRepository.buscaToken(id);
        } else {
            return null;
        }
    }

    // sair da sessao
    public void sair(Integer token) {
        Date data = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        this.usuarioRepository.sair(data, token);
    }

}

