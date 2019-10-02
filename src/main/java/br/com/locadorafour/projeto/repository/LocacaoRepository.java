package br.com.locadorafour.projeto.repository;

import br.com.locadorafour.projeto.entity.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    @Query(value = "select count(1) existe from tb_catalogo c where c.id_filme = ? and c.id_status = 'D'", nativeQuery = true)
    Integer existeFilmeDisponivel(@Param("id") Integer id);

    @Query(value = "select count(1)" +
                   "  from tb_filme f" +
                   " inner join tb_catalogo c" +
                   "    on c.id_filme = f.id" +
                   "   and c.id_usuario = (select id from tb_usuario where token = ?)" +
                   "   and c.id_status = 'I'" +
                   "   and c.id_filme = ?", nativeQuery = true)
    Integer existeFilmeAlugado(@Param("token") Integer token, @Param("id") Integer id);

    @Query(value = "select c.id_filme, f.nome, c.dt_devolucao, c.id_usuario" +
                   "  from tb_filme f" +
                   " inner join tb_catalogo c" +
                   "    on c.id_filme = f.id" +
                   "   and c.id_usuario = (select id from tb_usuario where token = ?)" +
                   "   and c.id_status = 'I'" +
                   " order by c.dt_aluguel desc limit 1", nativeQuery = true)
    List<String> ultimoAlugado(@Param("token") Integer token);

    @Transactional
    @Modifying
    @Query(value = "update tb_catalogo" +
                   "   set id_status = 'I'," +
                   "       dt_aluguel = sysdate()," +
                   "       dt_devolucao = sysdate() + INTERVAL 15 DAY," +
                   "       id_usuario = (select id from tb_usuario where token = ?)" +
                   " where id_filme = ?" +
                   "   and id_status = 'D'" +
                   "   and dt_aluguel is null" +
                  " limit 1", nativeQuery = true)
    void alugaFilme(@Param("token") Integer token, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update tb_catalogo" +
                   "   set id_status = 'D'," +
                   "       dt_aluguel = null," +
                   "       dt_devolucao = null," +
                   "       id_usuario = null" +
                   " where id_filme = ?" +
                   "   and id_status = 'I'" +
                   "   and id_usuario = (select id from tb_usuario where token = ?)" +
                   "   and dt_aluguel is not null limit 1", nativeQuery = true)
    void devolveFilme(@Param("id") Integer id, @Param("token") Integer token);

    @Transactional
    @Modifying
    @Query(value = "insert into tb_locacao (dt_atu, id_acao, id_filme, id_usuario) value (?, ?, ?, ?)", nativeQuery = true)
    void salvaHistorico(@Param("data") Date data, @Param("acao") String acao, @Param("id_filme") String idFilme, @Param("id_cliente") String idCliente);

}
