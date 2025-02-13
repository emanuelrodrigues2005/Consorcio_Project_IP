package br.ufrpe.ip.projeto.gui;

public enum TelasEnum {
    TELA_VISUALIZACAO_GRUPO("/TelaVisualizacaoGrupo.fxml"),
    TELA_LOGIN("/TelaLogin.fxml"),
    TELA_CADASTRO("/TelaCadastro.fxml");

    private final String caminho;

    TelasEnum(String caminho) {
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }
}
