package br.ufrpe.ip.projeto.exceptions;

public class ContemplacaoInexistenteException extends Exception {
    private final String idContemplacao;

    public ContemplacaoInexistenteException(String idContemplacao) {
        super("Não existe contemplação com o id: " + idContemplacao + "\n");
        this.idContemplacao = idContemplacao;
    }

    public String getIdContemplacao() {
        return idContemplacao;
    }
}
