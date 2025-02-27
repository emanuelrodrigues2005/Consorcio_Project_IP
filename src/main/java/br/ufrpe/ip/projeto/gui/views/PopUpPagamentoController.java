package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.BoletoInexistenteException;
import br.ufrpe.ip.projeto.exceptions.CampoInvalidoException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        try {
            boletoAtual = sistema.getBoletoById(idBoleto);

            if (boletoAtual != null) {
                lbValorParcela.setText(String.valueOf(boletoAtual.getValorBoleto()));
                lbSaldoDevedor.setText(String.valueOf(boletoAtual.getContratoBoleto().getSaldoDevedor()));
            }
        } catch (BoletoInexistenteException | CampoInvalidoException e) {
            exibirAlertaErro(e);
        }
    }

    private void exibirAlertaErro(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");

        if (e instanceof BoletoInexistenteException) {
            alert.setHeaderText("Boleto Não Encontrado!");
        } else if (e instanceof CampoInvalidoException) {
            alert.setHeaderText("Campo Inválido!");
        }

        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }


    private void limparDadosGrupo() {
        lbValorParcela.setText("0");
        lbSaldoDevedor.setText("0");
    }
}
