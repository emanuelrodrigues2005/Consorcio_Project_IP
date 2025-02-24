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

public class PopUpPagamentoController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private Boleto boletoAtual;
    private Stage popupStage;

    @FXML
    private Button btPix;

    @FXML
    private Button btCartaoCredito;

    @FXML
    private Button btCartaoDebito;

    @FXML
    private Label lbValorParcela;

    @FXML
    private Label lbSaldoDevedor;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    @FXML
    public void initialize() {
        limparDadosGrupo();
        this.setBoletoAtual(boletoAtual);
    }
    public void setBoletoAtual(Boleto boletoAtual) {
        limparDadosGrupo();
        this.boletoAtual = boletoAtual;
        if (boletoAtual != null) {
            carregarDadosBoleto(boletoAtual.getIdBoleto());
        }
    }

    private void carregarDadosBoleto(String idBoleto) {
        boletoAtual = sistema.getBoletoById(idBoleto);

        if (boletoAtual != null) {
            lbValorParcela.setText(String.valueOf(boletoAtual.getValorBoleto()));
            lbSaldoDevedor.setText(String.valueOf(boletoAtual.getContratoBoleto().getSaldoDevedor()));
        } else {
            System.out.println("Nenhum boleto encontrado");
        }
    }

    private void limparDadosGrupo() {
        lbValorParcela.setText("0");
        lbSaldoDevedor.setText("0");
    }
}
