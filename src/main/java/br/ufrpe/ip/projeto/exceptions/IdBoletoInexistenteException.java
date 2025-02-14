package br.ufrpe.ip.projeto.exceptions;

public class IdBoletoInexistenteException extends Exception {
    private final String idBoleto;

    public IdBoletoInexistenteException(String idBoleto) {
        super("O ID do boleto '" + idBoleto + "' não foi encontrado.");
        this.idBoleto = idBoleto;
    }

    public String getIdBoleto() {
        return idBoleto;
    }
}