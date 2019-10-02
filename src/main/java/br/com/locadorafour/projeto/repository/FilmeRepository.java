package br.com.locadorafour.projeto.repository;

import br.com.locadorafour.projeto.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    @Query(value = "select distinct f.* from tb_filme f inner join tb_catalogo c on c.id_filme = f.id where c.id_status = 'D'", nativeQuery = true)
    List<Filme> buscaListaDisponivel();

    @Query(value = "select distinct f.* from tb_filme f inner join tb_catalogo c on c.id_filme = f.id where f.nome like %?% and c.id_status = 'D'", nativeQuery = true)
    List<Filme> buscaPorNome(@Param("nome") String nome);

}

