package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.ArrayVazioException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaPrincipalADMController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;

    @FXML
    private Label lbHomeADM;

    @FXML
    private Label lbPerfilADM;

    @FXML
    private Label lbSair;

    @FXML
    private TableView<GrupoConsorcio> tbvGrupos;

    @FXML
    private TableColumn<GrupoConsorcio, String> tbcAllGrupos;

    @FXML
    private TableColumn<GrupoConsorcio, Integer> tbcParticipantes;

    @FXML
    private TableColumn<GrupoConsorcio, Double> tbcValorTotal;

    @FXML
    private TableColumn<GrupoConsorcio, Double> tbcTaxaAdmin;

    @FXML
    private TableColumn<GrupoConsorcio, String> tbcStatusGrupo;

    @FXML
    private Button btAdd;

    @FXML
    private TextField tfSearchADM;

    @FXML
    public void initialize() {
        configurarTabela();
        carregarDados();

        tbvGrupos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.gerenciador.abrirTelaEdicaoGrupo(newSelection);
            }
        });
    }

    public void setGerenciador(Gerenciador g) {
        this.gerenciador = g;
    }

    private void configurarTabela() {
        tbcAllGrupos.setCellValueFactory(new PropertyValueFactory<>("nomeGrupo"));
        tbcParticipantes.setCellValueFactory(new PropertyValueFactory<>("numeroParticipantes"));
        tbcValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tbcTaxaAdmin.setCellValueFactory(new PropertyValueFactory<>("taxaAdmin"));
        tbcStatusGrupo.setCellValueFactory(new PropertyValueFactory<>("statusGrupoConsorcioString"));
    }

    private void carregarDados() {
        try {
            ObservableList<GrupoConsorcio> grupos = FXCollections.observableArrayList(sistema.getAllGrupos());
            tbvGrupos.setItems(grupos);
        } catch (ArrayVazioException e) {
            exibirAlertaErro("Nenhum grupo encontrado", "Não há grupos de consórcio cadastrados.");
            tbvGrupos.setItems(FXCollections.observableArrayList());
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
    public void handleTelaPerfilADM() {
        System.out.println("Redirecionando para tela de perfil do Administrador...");
        this.gerenciador.abrirTelaPerfilAdmin(this.sistema.getClienteLogado());
    }

    @FXML
    public void handleTelaLogin() {
        System.out.println("Redirecionando para a tela de login...");
        this.gerenciador.abrirTelaEscolhaLogin();
    }

    @FXML
    public void openTelaCriarGrupo() {
        System.out.println("Redirecionando para a tela de criar grupo...");
        this.gerenciador.abrirTelaCriacaoGrupo();
    }
}
