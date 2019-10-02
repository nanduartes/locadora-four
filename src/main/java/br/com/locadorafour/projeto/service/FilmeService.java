package br.com.locadorafour.projeto.service;

import br.com.locadorafour.projeto.entity.Filme;
import br.com.locadorafour.projeto.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    // buscar todos filmes da locadora
    public List<Filme> buscaLista() {
        List<Filme> filme = this.filmeRepository.findAll();
        return filme;
    }

    // buscar filmes disponiveis
    public List<Filme> buscaListaDisponivel() {
        List<Filme> lista = this.filmeRepository.buscaListaDisponivel();
        return lista;
    }

    // buscar filmes disponiveis por nome
    public List<Filme> buscaPorNome(String nome) {
        List<Filme> lista = this.filmeRepository.buscaPorNome(nome);
        return lista;
    }

}
