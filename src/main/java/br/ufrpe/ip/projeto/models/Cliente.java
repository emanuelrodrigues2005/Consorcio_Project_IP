package br.ufrpe.ip.projeto.models;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;

    public Cliente(String nome, String cpf, String telefone, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String toString() {
        return "Cliente: " + "{ "
                + "\nNome: " + getNome() + " | "
                + "\nCpf: " + getCpf() + " | "
                + "\nTelefone: " + getTelefone() + " | "
                + "\nEmail: " + getEmail() + " | "
                + "\n}";
    }
}