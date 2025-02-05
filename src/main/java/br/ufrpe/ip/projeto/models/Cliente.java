package br.ufrpe.ip.projeto.models;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Cliente(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
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

    private boolean verificarDigito(String cpf, int posicao) {
        int soma = 0;
        int peso = posicao + 1;

        for(int i = 0; i < posicao; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso;
            peso--;
        }

        int resto = soma % 11;
        int digitoVerificador;

        if(resto < 2) {
            digitoVerificador = 0;
        } else {
            digitoVerificador = 11 - resto;
        }
        return digitoVerificador == Character.getNumericValue(cpf.charAt(posicao));
    }

    public boolean validarCpf(String cpf) {
        if(cpf == null || cpf.length() != 11) {
            return false;
        }

        return verificarDigito(cpf, 9) && verificarDigito(cpf, 10);
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