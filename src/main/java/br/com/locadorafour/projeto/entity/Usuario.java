package br.com.locadorafour.projeto.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false)
    @NotNull(message = "O campo nome não pode ser nulo")
    private String email;

    @Column(name = "senha", nullable = false)
    @NotNull(message = "O campo nome não pode ser nulo")
    private String senha;

    @Column(name = "nome", nullable = false)
    @Pattern(regexp = "[a-zA-ZàèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃçÇ ]+", message = "O nome não deve possuir simbolos ou números")
    @NotNull(message = "O campo nome não pode ser nulo")
    @Size(min = 3, max = 50, message = "Um nome deve possuir entre 4 e 50 caracteres")
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
