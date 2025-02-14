package br.ufrpe.ip.projeto.exceptions;

public class GrupoConsorcioDuplicadoException extends RuntimeException {
        private final String idGrupoConsorcio;

        public GrupoConsorcioDuplicadoException(String idGrupoConsorcio) {
            super(String.format("Já existe um Grupo Consorcio cadastrado com o id \"%s\"", idGrupoConsorcio));
            this.idGrupoConsorcio = idGrupoConsorcio;
        }

        public String getIdGrupoConsorcio() {
            return this.idGrupoConsorcio;
        }
}
