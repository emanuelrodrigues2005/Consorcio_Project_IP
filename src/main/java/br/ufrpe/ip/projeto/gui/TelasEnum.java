package br.ufrpe.ip.projeto.gui;

public enum TelasEnum {
    TELA_VISUALIZACAO_GRUPO("src/main/resource/TelaVisualizacaoGrupo.fxml");

    private final String caminho;

    TelasEnum(String caminho) {
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }
}
