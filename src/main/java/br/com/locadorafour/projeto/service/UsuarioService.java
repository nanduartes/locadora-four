package br.com.locadorafour.projeto.service;

import br.com.locadorafour.projeto.entity.Usuario;
import br.com.locadorafour.projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = false)
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // busca lista com todos usuarios
    public List<Usuario> buscarUsuarios() {
        List<Usuario> usuarios = this.usuarioRepository.buscaUsuarios();
        return usuarios;
    }

    // busca usuario por id
    public List<Usuario> buscaPorId(Integer id) {
        List<Usuario> usuario = this.usuarioRepository.buscarPorId(id);
        return usuario;
    }

    // busca usuario por nome
    public List<Usuario> buscaPorNome(String nome) {
        List<Usuario> usuarios = this.usuarioRepository.buscaPorNome(nome);
        return usuarios;
    }

    // criar novo usuario
    @Transactional
    public String criarUsuario(Usuario usuario) throws IllegalArgumentException {
        int existe = this.usuarioRepository.verificaEmail(usuario.getEmail());
        if (existe == 0) {
            Usuario user = this.usuarioRepository.save(usuario);
            Random random = new Random();
            this.usuarioRepository.geraToken(random.nextInt(999999), usuario.getId());
            String token = "{\"token_acesso\":\"" + this.usuarioRepository.buscaToken(usuario.getId()) + "\"}";
            Date data = Date.from(LocalDateTime.now().plusMinutes(20).atZone(ZoneId.systemDefault()).toInstant());
            this.usuarioRepository.geraSessao(data, usuario.getId());
            return token;
        } else {
            String msg = "{\"mensagem\":\"Usuario já cadastrado\"}";
            return msg;
        }
    }

    // alterar usuario
    @Transactional
    public String alterarUsuario(Usuario usuario) throws IllegalArgumentException {
        this.usuarioRepository.alterarUsuario(usuario.getSenha(), usuario.getNome(), usuario.getEmail());
        Integer id = this.usuarioRepository.buscaPorEmail(usuario.getEmail());
        Random random = new Random();
        this.usuarioRepository.geraToken(random.nextInt(999999), id);
        String token = "{\"mensagem\":\"Usuario atualizado\",\"novo_token_acesso\":\"" + this.usuarioRepository.buscaToken(id) + "\"}";
        Date data = Date.from(LocalDateTime.now().plusMinutes(20).atZone(ZoneId.systemDefault()).toInstant());
        this.usuarioRepository.geraSessao(data, id);
        return token;
    }
}
