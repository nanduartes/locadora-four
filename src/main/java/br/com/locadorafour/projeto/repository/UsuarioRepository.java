package br.com.locadorafour.projeto.repository;

import br.com.locadorafour.projeto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "select id, email, '******' senha, nome from tb_usuario", nativeQuery = true)
    List<Usuario> buscaUsuarios();

    @Query(value = "select id, email, '******' senha, nome from tb_usuario where id = ?", nativeQuery = true)
    List<Usuario> buscarPorId(@Param("id") Integer id);

    @Query(value = "select id, email, '******' senha, nome from tb_usuario where nome like %?%", nativeQuery = true)
    List<Usuario> buscaPorNome(@Param("nome") String nome);

    @Query(value = "select count(1) from tb_usuario where email = ?", nativeQuery = true)
    Integer verificaEmail(@Param("email") String email);

    @Query(value = "select token from tb_usuario where id = ?", nativeQuery = true)
    String buscaToken(@Param("id") Integer id);

    @Query(value = "select id from tb_usuario where email = ?", nativeQuery = true)
    Integer buscaPorEmail(@Param("email") String email);

    @Query(value = "select count(1) from tb_usuario where token = ? and sessao > sysdate()", nativeQuery = true)
    Integer validaToken(@Param("token") Integer token);

    @Query(value = "select id from tb_usuario where email = ? and senha = ?", nativeQuery = true)
    Integer fazLogin(@Param("email") String email, @Param("senha") String senha);

    @Query(value = "select id from tb_usuario where token = ?", nativeQuery = true)
    String buscaPorToken(@Param("token") Integer token);

    @Transactional
    @Modifying
    @Query(value = "update tb_usuario set token = ? where id = ?", nativeQuery = true)
    void geraToken(@Param("token") Integer rdm, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update tb_usuario set sessao = ? where id = ?", nativeQuery = true)
    void geraSessao(@Param("sessao") Date data, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update tb_usuario set senha = ?, nome = ? where email = ?", nativeQuery = true)
    void alterarUsuario(@Param("senha") String senha, @Param("nome") String nome, @Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "update tb_usuario set sessao = ? where token = ?", nativeQuery = true)
    void sair(@Param("token") Date data, @Param("token") Integer token);

}
