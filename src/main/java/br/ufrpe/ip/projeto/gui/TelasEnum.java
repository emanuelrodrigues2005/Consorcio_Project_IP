package br.ufrpe.ip.projeto.gui;

public enum TelasEnum {
    TELA_VISUALIZACAO_GRUPO("/TelaVisualizacaoGrupo.fxml"),
    TELA_LOGIN_CLIENTE("/TelaLoginCliente.fxml"),
    TELA_CADASTRO("/TelaCadastro.fxml"),
    TELA_PERFIL_CLIENTE("/TelaPerfilCliente.fxml"),
    TELA_PRINCIPAL_CLIENTE("/TelaPrincipalCliente.fxml"),
    TELA_CRIACAO_GRUPO("/TelaCriacaoGrupo.fxml"),
    TELA_PRINCIPAL_ADM("/TelaPrincipalAdm.fxml"),
    TELA_VISUALIZACAO_CONTRATO("/TelaVisualizacaoContrato.fxml"),
    TELA_ESCOLHA_LOGIN("/TelaEscolhaLogin.fxml"),
    TELA_LOGIN_ADM("/TelaLoginAdm.fxml"),
    TELA_EDICAO_GRUPO("/TelaEdicaoGrupo.fxml"),
    TELA_DADOS_CONTRATO("/TelaDadosContrato.fxml"),
    POP_UP_PAGAMENTO("/PopupPagamento.fxml"),
    POP_UP_SORTEIO("/PopupSorteio.fxml"),
    TELA_PERFIL_ADM("/TelaPerfilADM.fxml"),
    POP_UP_TAXA("/PopupTaxa.fxml"),;

    private final String caminho;

    TelasEnum(String caminho) {
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }
}
