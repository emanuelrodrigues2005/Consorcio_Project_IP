package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaPrincipalClienteController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador screenManager;

    @FXML
    private Label lbHomeCliente;

    @FXML
    private Label lbPerfilCliente;

    @FXML
    private TableView<GrupoConsorcio> tbvGrupos;

    @FXML
    private TableColumn<GrupoConsorcio, String> tbcGruposDisponiveis;

    @FXML
    private TableColumn<GrupoConsorcio, Integer> tbcParticipantes;

    @FXML
    private TableColumn<GrupoConsorcio, Integer> tbcValorTotal;

    @FXML
    private TableColumn<GrupoConsorcio, Integer> tbcTaxaAdmin;

    @FXML
    public void initialize() {
        configurarTabela();
        carregarDados();
    }

    public void setGerenciador(Gerenciador g) {
        this.screenManager = g;
    }

    private void configurarTabela() {
        tbcGruposDisponiveis.setCellValueFactory(new PropertyValueFactory<>("nomeGrupo"));
        tbcParticipantes.setCellValueFactory(new PropertyValueFactory<>("numeroParticipantes"));
        tbcValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tbcTaxaAdmin.setCellValueFactory(new PropertyValueFactory<>("taxaAdmin"));
    }

    private void carregarDados() {
        ObservableList<GrupoConsorcio> grupos = FXCollections.observableArrayList(sistema.getAllGrupos());
        tbvGrupos.setItems(grupos);
    }

    @FXML
    public void handleTelaPerfilCliente() {
        this.screenManager.abrirPerfilCliente(this.sistema.getClienteLogado());
    }

    @FXML
    public void handleTelaGruposCliente() {
        //this.screenManeger.abrirTelaGruposCliente
    }
}
