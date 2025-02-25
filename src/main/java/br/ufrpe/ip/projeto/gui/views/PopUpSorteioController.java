package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpSorteioController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private Contrato contratoSorteado;
    private Stage popupStage;

    @FXML
    private Button btEnviarMensagem;

    @FXML
    private Label lbNomeUserContemplado;

    @FXML
    private Label lbCPFContemplado;

    @FXML
    private Label lbTelefoneContemplado;

    @FXML
    private Label lbNomeGrupoContemplado;

    @FXML
    private Label lbDataContemplacao;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    @FXML
    public void initialize() {
        limparDadosGrupo();

    }

    public void setContratoSorteado(String idContrato) {
        this.contratoSorteado = this.sistema.getContratoByIdContrato(idContrato);
    }

    private void carregarDadosContemplacao() {

    }

    private void limparDadosGrupo() {
        lbNomeUserContemplado.setText("");
        lbCPFContemplado.setText("");
        lbTelefoneContemplado.setText("");
        lbNomeGrupoContemplado.setText("");
        lbDataContemplacao.setText("");
    }
}
