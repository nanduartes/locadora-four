package br.com.locadorafour.projeto.entity;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "tb_locacao")
public class Locacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_filme", nullable = false)
    private String idFilme;

    @Column(name = "id_usuario", nullable = false)
    private String idUsuario;

    @Column(name = "dt_atu", nullable = false)
    private Calendar dtAtu;

    @Column(name = "id_acao", nullable = false)
    private String idAcao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Calendar getDtAtu() {
        return dtAtu;
    }

    public void setDtAtu(Calendar dtAtu) {
        this.dtAtu = dtAtu;
    }

    public String getIdAcao() {
        return idAcao;
    }

    public void setIdAcao(String idAcao) {
        this.idAcao = idAcao;
    }
}
