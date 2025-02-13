package br.ufrpe.ip.projeto.exceptions;

public class ClienteInexistenteException extends Exception {
    private final String cpfCliente;

    public ClienteInexistenteException(String cpfCliente) {
        super("Não existe usuário cadastrado com o cpf: " + cpfCliente + "\n");
        this.cpfCliente = cpfCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }
}
