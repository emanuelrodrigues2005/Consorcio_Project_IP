package br.ufrpe.ip.projeto.exceptions;

public class ClienteDuplicadoException extends Exception {
    private final String cpfCliente;

    public ClienteDuplicadoException(String cpfCliente) {
        super(String.format("Já existe um usuário cadastrado com login \"%s\"", cpfCliente));
        this.cpfCliente = cpfCliente;
    }

    public String getCpfCliente() {
        return this.cpfCliente;
    }
}
