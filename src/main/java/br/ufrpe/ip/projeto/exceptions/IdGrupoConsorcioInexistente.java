package br.ufrpe.ip.projeto.exceptions;

public class IdGrupoConsorcioInexistente extends Exception {
    private final String idGrupoConsorcio;

    public IdGrupoConsorcioInexistente(String idGrupoConsorcio) {
        super("O Id " + idGrupoConsorcio + " do grupo consorcio inexistente");
        this.idGrupoConsorcio = idGrupoConsorcio;
    }

    public String getIdGrupoConsorcio() {
        return idGrupoConsorcio;
    }
}
