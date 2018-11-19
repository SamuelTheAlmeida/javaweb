/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SAMUEL
 */
public class Cliente implements Serializable {
    	private int id;
	private String cpf;
	private String nome;
	private String email;
	private Date data;
	private String rua;
	private int numero;
	private String cep;
	private Cidade cidade;

    public Cliente() {
    }

    public Cliente(int id, String cpf, String nome, String email, Date data, String rua, int numero, String cep, Cidade cidade) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.data = data;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
    }
    
        public Cliente(String cpf, String nome, String email, String rua, int numero, String cep, Cidade cidade) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }  
}
