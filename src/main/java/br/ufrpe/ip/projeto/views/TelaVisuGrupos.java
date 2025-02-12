package br.ufrpe.ip.projeto.views;

import br.ufrpe.ip.projeto.controllers.ClienteController;
import br.ufrpe.ip.projeto.controllers.GrupoConsorcioController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaVisuGrupos {
    @FXML
    private Button btHome;

    @FXML
    private Button btGrupos;

    @FXML
    private Button btPerfil;

    @FXML
    private Label lbAutoConsor;

    @FXML
    private Label lbValorTotal;

    @FXML
    private TextField txtTotalParticipantes;

    @FXML
    private Label lbDtInicio;

    @FXML
    private Label lbDtTermino;

    @FXML
    private Label lbParcelasAd;

    @FXML
    private Label lbValorPago;

    private ClienteController clienteController;
    private GrupoConsorcioController grupoConsorcioController;

    @FXML
    public void initialize() {
        lbAutoConsor.setText("-");
        lbValorTotal.setText("-");
        lbDtInicio.setText("-");
        lbDtTermino.setText("-");
        lbParcelasAd.setText("-");
        lbValorPago.setText("-");
        txtTotalParticipantes.setText("");
    }

    @FXML
    private void openHome() {
        System.out.println("Botão Home clicado");
    }

    @FXML
    private void openAllGrupos() {
        System.out.println("Botão Grupos clicado");
    }

    @FXML
    private void openPerfil() {
        System.out.println("Botão Perfil clicado");
    }

    public void setAutoConsorciado() {
        lbAutoConsor.setText(grupoConsorcioController.);
    }

    public void setValorTotal(String valorTotal) {
        lbValorTotal.setText(valorTotal);
    }

    public void setDtInicio(String dtInicio) {
        lbDtInicio.setText(dtInicio);
    }

    public void setDtTermino(String dtTermino) {
        lbDtTermino.setText(dtTermino);
    }

    public void setParcelasAdimplentes(String parcelasAd) {
        lbParcelasAd.setText(parcelasAd);
    }

    public void setValorPago(String valorPago) {
        lbValorPago.setText(valorPago);
    }

    public void setTotalParticipantes(String totalParticipantes) {
        txtTotalParticipantes.setText(totalParticipantes);
    }
}
