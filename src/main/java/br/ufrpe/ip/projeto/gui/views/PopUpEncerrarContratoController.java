package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpEncerrarContratoController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private Stage popupStage;
    private Contrato contratoAtual;

    @FXML
    private Label lbDataEncerramento;

    @FXML
    private Label lbSaldoEstornado;

    @FXML
    private Label lbDataEstorno;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    public void setContratoAtual(Contrato contratoAtual) {
        this.contratoAtual = contratoAtual;
        this.atualizarCampos();
    }

    @FXML
    public void initialize() {
        this.limparCampos();
    }

    private void atualizarCampos() {
            if (this.contratoAtual != null) {
                lbSaldoEstornado.setText(String.valueOf(contratoAtual.getSaldoDevolucao()));

                if (contratoAtual.getDataEncerramento() != null) {
                    lbDataEncerramento.setText(contratoAtual.getDataEncerramento().toString());
                    lbDataEstorno.setText(contratoAtual.getDataEncerramento().plusMonths(1).toString());
                } else {
                    lbDataEncerramento.setText("Não encerrado");
                    lbDataEstorno.setText("Indefinido");
                }
            }
    }

    private void limparCampos() {
        lbDataEncerramento.setText("");
        lbSaldoEstornado.setText("");
        lbDataEstorno.setText("");
    }
}
