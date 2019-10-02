package br.com.locadorafour.projeto.service;

import br.com.locadorafour.projeto.repository.LocacaoRepository;
import br.com.locadorafour.projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // aluga o filme
    public String alugaFilme(Integer id, Integer token) {
        int existe = this.locacaoRepository.existeFilmeDisponivel(id);
        if (existe > 0) {
            this.locacaoRepository.alugaFilme(token, id);
            List ultimoAlugado = this.locacaoRepository.ultimoAlugado(token);
            List<String> lista = Arrays.asList(ultimoAlugado.get(0).toString().split(","));
            Date data = Date.from(LocalDateTime.now().plusMinutes(20).atZone(ZoneId.systemDefault()).toInstant());
            String acao = "A";
            this.locacaoRepository.salvaHistorico(data, acao, lista.get(0), lista.get(3));
            String filme = "{\"mensagem\":\"Sucesso\", \"id\":\"" + lista.get(0) + "\",\"filme\":\"" + lista.get(1) + "\",\"data_devolucao\":\"" + lista.get(2) + "\"}";
            return filme;
        } else {
            String msg = "{\"mensagem\":\"Filme indisponivel\"}";
            return msg;
        }
    }

    // devolve o filme
    public String devolveFilme(Integer id, Integer token) {
        int existe = this.locacaoRepository.existeFilmeAlugado(token, id);
        System.out.println(existe);
        if (existe > 0) {
            this.locacaoRepository.devolveFilme(id, token);
            String idUsuario = this.usuarioRepository.buscaPorToken(token);
            Date data = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            String acao = "D";
            String idFilme = id.toString(id);
            System.out.println(idFilme);
            this.locacaoRepository.salvaHistorico(data, acao, idFilme, idUsuario);
            String filme = "{\"mensagem\":\"Filme devolvido\"}";
            return filme;
        } else {
            String msg = "{\"mensagem\":\"Você não alugou esse filme\"}";
            return msg;
        }
    }
}
