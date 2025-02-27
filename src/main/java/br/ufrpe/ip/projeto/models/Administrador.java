package br.ufrpe.ip.projeto.models;

public class Administrador extends Cliente{
    private boolean acessoEspecial;

    public Administrador(String nome, String cpf, String telefone, String email, String senha) {
        super(nome, cpf, telefone, email, senha);
        this.acessoEspecial = true;
    }

    public boolean temAcessoEspecial() {
        return acessoEspecial;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTipo: Administrador";
    }
}
