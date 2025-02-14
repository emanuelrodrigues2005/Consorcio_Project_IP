package br.ufrpe.ip.projeto.exceptions;

public class GrupoConsorcioInexistenteException extends Exception {
    private final String idGrupo;

    public GrupoConsorcioInexistenteException(String idGrupo) {
        super("O grupo consorcio vinculado ao id" + idGrupo + "inexistente");
        this.idGrupo = idGrupo;
    }

    public String getIdGrupo() {
        return idGrupo;
    }
}
