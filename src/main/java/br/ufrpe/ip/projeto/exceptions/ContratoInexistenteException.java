package br.ufrpe.ip.projeto.exceptions;

public class ContratoInexistenteException extends RuntimeException {
  private final String idContratoInexistente;

  public ContratoInexistenteException(String idContratoInexistente) {
    super("O Contrato vinculado ao id: " + idContratoInexistente + " inexistente");
    this.idContratoInexistente = idContratoInexistente;
  }

  public String getContratoInexistente() {
    return idContratoInexistente;
  }
}
