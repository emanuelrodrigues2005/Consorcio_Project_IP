package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Boleto;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class PopUpPagoController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private Stage popupStage;
    private Boleto boletoAtual;

    @FXML
    private Label lbValorParcela;

    @FXML
    private Label lbDataPagamento;

    @FXML
    private Label lbSaldoDevedor;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    public void setBoletoAtual(Boleto boletoAtual) {
        this.boletoAtual = boletoAtual;
    }

    @FXML
    public void initialize() {
        this.limparCampos();
        lbValorParcela.setText(String.valueOf(this.boletoAtual.getValorBoleto()));
        lbDataPagamento.setText(this.boletoAtual.getDataPagamento().toString());
        lbSaldoDevedor.setText(String.valueOf(this.boletoAtual.getContratoBoleto().getSaldoDevedor()));
    }

    private void limparCampos() {
        lbValorParcela.setText("");
        lbDataPagamento.setText("");
        lbSaldoDevedor.setText("");
    }
}
