package com.spring.codeblog.model;

import com.spring.codeblog.utils.Authorization;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String perfil;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String token;

    public User(@NotBlank String nome, @NotBlank String perfil, @NotBlank String email, @NotBlank String password) {
        this.nome = nome;
        this.perfil = perfil;
        this.email = email;
        this.password =  Authorization.bcryptEncode(password);
        this.token = "";
    }

    public User() {

    }

    public User getUserLogin(User user, String token){
        User newUser = new User();

        newUser.nome = user.getNome();
        newUser.email = user.getEmail();
        user.token = token;

        return newUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
