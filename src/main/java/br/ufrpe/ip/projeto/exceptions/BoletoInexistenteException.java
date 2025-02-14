package br.ufrpe.ip.projeto.exceptions;

public class BoletoInexistenteException extends Exception {
    private final String idBoletoInexistente;

    public BoletoInexistenteException(String idBoletoInexistente) {
        super("O Boleto vinculado ao id: " + idBoletoInexistente + " inexistente");
        this.idBoletoInexistente = idBoletoInexistente;
    }

    public String getBoletoInexistente() {
        return idBoletoInexistente;
    }
}
