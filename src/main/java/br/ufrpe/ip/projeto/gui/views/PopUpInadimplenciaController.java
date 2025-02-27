package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.ArrayVazioException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PopUpInadimplenciaController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private Stage popupStage;
    private GrupoConsorcio grupoAtual;

    @FXML
    private Button btNotificarParticipantes;

    @FXML
    private Label lbInadimplentes;

    @FXML
    private TableView<Boleto> tbvInadimplencia;

    @FXML
    private TableColumn<Boleto, String> tbvcolumnCPFInadimplencia;

    @FXML
    private TableColumn<Boleto, String> tbvcolumnStatusInadimplencia;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    public void setGrupoAtual(GrupoConsorcio grupoAtual) {
        this.grupoAtual = grupoAtual;
    }

    @FXML
    public void initialize() {
        try {
            limparCampos();
            configurarTabela();
            carregarDados();
        } catch (ArrayVazioException e) {
            e.getMessage();
        }
    }

    private void carregarDados() throws ArrayVazioException {
        ObservableList<Boleto> boletos = FXCollections.observableArrayList(this.getAllBoletosByGrupo(grupoAtual));
        tbvInadimplencia.setItems(boletos);
    }

    private void configurarTabela() {
        tbvcolumnCPFInadimplencia.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
        tbvcolumnStatusInadimplencia.setCellValueFactory(new PropertyValueFactory<>("statusBoletoString"));
    }

    private List<Boleto> getAllBoletosByGrupo(GrupoConsorcio grupoConsorcio) throws ArrayVazioException {
        List<Boleto> boletosGrupo = new ArrayList<>();
        for (Boleto boleto : this.sistema.getAllBoletos()) {
            if (sistema.getContratosByIdGrupo(grupoConsorcio).contains(boleto.getContratoBoleto())) {
                boletosGrupo.add(boleto);
            }
        }
        return boletosGrupo;
    }

    private void limparCampos() {
        lbInadimplentes.setText("");
        tbvInadimplencia.getItems().clear();
    }
}
