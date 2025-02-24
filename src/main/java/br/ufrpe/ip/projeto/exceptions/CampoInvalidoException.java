package br.ufrpe.ip.projeto.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampoInvalidoException extends Exception {
    private final List<String> camposInvalidos;

    public CampoInvalidoException(String campoInvalido) {
        super("O seguinte campo é inválido: " + campoInvalido);
        this.camposInvalidos = new ArrayList<>();
        this.camposInvalidos.add(campoInvalido);
    }

    public CampoInvalidoException(List<String> camposInvalidos) {
        super("Os seguintes campos são inválidos: " + String.join("; ", camposInvalidos));
        this.camposInvalidos = camposInvalidos;
    }

    public List<String> getImmutableCamposInvalidos() {
        return Collections.unmodifiableList(this.camposInvalidos);
    }
}
