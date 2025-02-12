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
    private Label lbTaxaAdmin;

    @FXML
    private Label lbValorPago;

    private ClienteController clienteController;
    private GrupoConsorcioController grupoConsorcioController;

    @FXML
    public void initialize() {
        lbAutoConsor.setText("-");
        lbValorTotal.setText("-");
        lbTaxaAdmin.setText("-");
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

    private void setLbAutoConsor(String idGrupo) {
        lbAutoConsor.setText(grupoConsorcioController.getGrupoById(idGrupo).getNomeGrupo());
    }

    private void setLbValorTotal(String idGrupo) {
        lbValorTotal.setText(String.valueOf(grupoConsorcioController.getGrupoById(idGrupo).getValorTotal()));
    }

    private void setLbTaxaAdmin(String idGrupo) {
        lbTaxaAdmin.setText(String.valueOf(grupoConsorcioController.getGrupoById(idGrupo).getTaxaAdmin()));
    }

    private void setLbValorPago(String idGrupo) {
        lbValorPago.setText(String.valueOf(grupoConsorcioController.getValorPago(idGrupo)));
    }
}
