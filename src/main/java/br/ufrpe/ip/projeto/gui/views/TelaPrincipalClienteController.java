package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TableColumn<GrupoConsorcio, String> tbcStatusGrupo;

    @FXML
    public void initialize() {
        for (GrupoConsorcio grupo : sistema.getAllGrupos()) {
            grupo.setNumeroParticipantes(sistema.getAllClientesByGrupo(grupo).size());
        }
        configurarTabela();
        carregarDados();

        tbvGrupos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleGrupoSelecionado();
            }
        });
    }

    public void setGerenciador(Gerenciador g) {
        this.screenManager = g;
    }

    private void configurarTabela() {
        tbcGruposDisponiveis.setCellValueFactory(new PropertyValueFactory<>("nomeGrupo"));
        tbcParticipantes.setCellValueFactory(new PropertyValueFactory<>("numeroParticipantes"));
        tbcValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tbcTaxaAdmin.setCellValueFactory(new PropertyValueFactory<>("taxaAdmin"));
        tbcStatusGrupo.setCellValueFactory(new PropertyValueFactory<>("statusGrupoConsorcioString"));
    }

    private void carregarDados() {
        try {
            ObservableList<GrupoConsorcio> grupos = FXCollections.observableArrayList(sistema.getAllGrupos());
            tbvGrupos.setItems(grupos);
        } catch (Exception e) {
            exibirAlertaErro("Erro ao Carregar Dados", e.getMessage());
            e.printStackTrace();
        }
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void handleTelaPerfilCliente() {
        this.screenManager.abrirPerfilCliente(this.sistema.getClienteLogado());
    }

    @FXML
    public void handleTelaLoginCliente() {
        this.screenManager.abrirTelaEscolhaLogin();
    }

    @FXML
    private void handleGrupoSelecionado() {
        GrupoConsorcio grupoSelecionado = tbvGrupos.getSelectionModel().getSelectedItem();

        if (grupoSelecionado != null) {
            screenManager.abrirTelaVisuGrupo(grupoSelecionado);
        } else {
            System.out.println("Nenhum grupo foi selecionado.");
        }
    }

    public void atualizarTabela() {
        tbvGrupos.refresh();
    }
}
