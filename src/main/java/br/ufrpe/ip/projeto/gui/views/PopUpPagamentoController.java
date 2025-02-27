package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Boleto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private void pagarBoletoPix(MouseEvent event) {
        if (boletoAtual.getContratoBoleto().getParcelasPagas() <
                boletoAtual.getContratoBoleto().getGrupoAssociado().getNumeroMaximoParticipantes()) {
            sistema.updateDataPagamento(boletoAtual);
            sistema.updateParcelasPagas(boletoAtual.getContratoBoleto());
            sistema.updateSaldoDevedor(boletoAtual.getContratoBoleto());
            sistema.updateValorPago(boletoAtual.getContratoBoleto());
            sistema.updateStatusBoleto(boletoAtual, StatusBoletoEnum.PAGO);
            if (sistema.getAllBoletosByContrato(boletoAtual.getContratoBoleto().getIdContrato()).size() <
                    boletoAtual.getContratoBoleto().getGrupoAssociado().getNumeroMaximoParticipantes()) {
                sistema.createBoleto(boletoAtual.getContratoBoleto());
            }
            popupStage.close();
            gerenciador.abrirPerfilCliente(boletoAtual.getContratoBoleto().getCliente());
        }
    }
  
    private void limparDadosGrupo() {
        lbValorParcela.setText("0");
        lbSaldoDevedor.setText("0");
    }
}
