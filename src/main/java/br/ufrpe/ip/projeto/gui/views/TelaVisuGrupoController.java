package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.IdGrupoConsorcioInexistenteException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaVisuGrupoController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador screenManager;
    private GrupoConsorcio grupoAtual;

    @FXML
    private Label lbHomeCliente;

    @FXML
    private Label lbPerfilCliente;

    @FXML
    private Label lbGruposCliente;

    @FXML
    private Label lbSair;

    @FXML
    private Label lbAutoConsor;

    @FXML
    private Label lbValorTotal;

    @FXML
    private Label lbTaxaAdmin;

    @FXML
    private Label lbValorParcela;

    @FXML
    private Label lbTotalParticipantes;

    @FXML
    private Button btAcessarContrato;

    @FXML
    public void initialize() {
        limparDadosGrupo();
    }

    public void setGrupoAtual(GrupoConsorcio grupoAtual) {
        limparDadosGrupo();
        this.grupoAtual = grupoAtual;
        if (grupoAtual != null) {
            carregarDadosGrupo(grupoAtual.getIdGrupo());
        }
    }

    public void setGerenciador(Gerenciador screenManager) {
        this.screenManager = screenManager;
    }

    @FXML
    private void handleTelaHomeCliente() {
        System.out.println("Botão Home clicado");
        screenManager.abrirTelaPrincipalCliente();
    }

    @FXML
    private void handleTelaPerfilCliente() {
        System.out.println("Botão Perfil clicado");
        screenManager.abrirPerfilCliente(this.sistema.getClienteLogado());
    }

    @FXML
    private void handleTelaEscolherLogin() {
        System.out.println("Saindo...");
        screenManager.abrirTelaEscolhaLogin();
    }

    @FXML
    private void handleTelaVisualizacaoContrato() {
        System.out.println("Redirecionado para contrato ...");
        screenManager.abriTelaVisualizacaoContrato(grupoAtual);
    }

    private void carregarDadosGrupo(String idGrupo) {
        try {
            grupoAtual = sistema.getGrupoById(idGrupo);

            if (lbAutoConsor != null) {
                lbAutoConsor.setText(grupoAtual.getNomeGrupo());
            } else {
                System.out.println("lbAutoConsor está nulo!");
            }

            lbValorTotal.setText(String.format("%.2f", grupoAtual.getValorTotal()));
            lbTaxaAdmin.setText(String.format("%.2f", grupoAtual.getTaxaAdmin()));
            lbValorParcela.setText(String.format("%.2f", grupoAtual.getValorParcela()));
            lbTotalParticipantes.setText(String.valueOf(grupoAtual.getNumeroParticipantes()));

        } catch (IdGrupoConsorcioInexistenteException e) {
            exibirAlertaErro("Grupo não encontrado", "Nenhum grupo foi encontrado com o ID fornecido: " + idGrupo);
            limparDadosGrupo();
        }
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparDadosGrupo() {
        lbAutoConsor.setText("-");
        lbValorTotal.setText("-");
        lbTaxaAdmin.setText("-");
        lbValorParcela.setText("-");
        lbTotalParticipantes.setText("0");
    }
}
