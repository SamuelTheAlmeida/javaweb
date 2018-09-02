package com.ufpr.tads.web2.beans;
import java.io.Serializable;

public class Usuario implements Serializable {
    // atributos do usuario
    private int id;
    private String login;
    private String senha;
    private String nome;

    // construtor vazio, usado para cadastro de novo usuário
    public Usuario() {
        
    }
    
    // construtor completo, usado para listagem e consulta de usuários
    public Usuario(int id, String login, String senha, String nome) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }
    
    // getters e setters padrão
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
