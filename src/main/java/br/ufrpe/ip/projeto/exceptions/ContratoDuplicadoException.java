package br.ufrpe.ip.projeto.exceptions;

public class ContratoDuplicadoException extends RuntimeException {
    private final String idContrato;

    public ContratoDuplicadoException(String idContrato) {
        super(String.format("Já existe um usuário cadastrado com login \"%s\"", idContrato));
        this.idContrato = idContrato;
    }

    public String getIdContrato() {
        return this.idContrato;
    }
}
