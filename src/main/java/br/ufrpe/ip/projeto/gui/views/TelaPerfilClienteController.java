package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contrato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TelaPerfilClienteController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador screenManager;
    private Cliente cliente;

    @FXML
    private Label lbHomeCliente;

    @FXML
    private Label lbPerfilCliente;

    @FXML
    private Label lbGruposCliente;

    @FXML
    private Label lbSair;

    @FXML
    private Label lbNomeUser;

    @FXML
    private Label lbCPF;

    @FXML
    private Label lbTelefone;

    @FXML
    private Label lbEmail;

    @FXML
    private TableView<Contrato> tbvContratos;

    @FXML
    private TableColumn<Contrato, String> tbvcolumnAutomovel;

    @FXML
    private TableColumn<Contrato, String> tbvcolumnStatus;

    @FXML
    public void initialize() {
        if (cliente != null) {
            lbNomeUser.setText(cliente.getNome());
            lbCPF.setText(cliente.getCpf());
            lbTelefone.setText(cliente.getTelefone());
            lbEmail.setText(cliente.getEmail());
            tbvContratos.getItems().clear();
            if (!(sistema.getAllContratosByCPF(cliente).isEmpty())) {
                configurarTabela();
                carregarDados();
                tbvContratos.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                        handleContratoSelecionado();
                    }
                });
            }
        }
    }

    private void carregarDados() {
        ObservableList<Contrato> contratos = FXCollections.observableArrayList(sistema.getAllContratosByCPF(cliente));
        tbvContratos.setItems(contratos);
    }

    private void configurarTabela() {
        tbvcolumnAutomovel.setCellValueFactory(new PropertyValueFactory<>("nomeGrupoConsorcio"));
        tbvcolumnStatus.setCellValueFactory(new PropertyValueFactory<>("statusContratoString"));
    }

    public void setGerenciador(Gerenciador screenManager) {
        this.screenManager = screenManager;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    public void handleTelaPrincipalCliente(MouseEvent event) {
        System.out.println("Redirecionando tela principal do cliente...");
        this.screenManager.abrirTelaPrincipalCliente();
    }

    @FXML
    public void handleTelaGruposCliente(MouseEvent event) {
        System.out.println("Redirecionando tela grupos do cliente...");
        //this.screenManager.abriTelaGruposCliente;
    }

    @FXML
    public void handleTelaEscolherLogin(MouseEvent event) {
        System.out.println("Redirecionando tela escolher forma de login...");
        this.screenManager.abrirTelaEscolhaLogin();
    }

    @FXML
    public void handleContratoSelecionado() {
        Contrato contratoSelecionado = tbvContratos.getSelectionModel().getSelectedItem();

        if (contratoSelecionado != null) {
            screenManager.abrirTelaDadosContrato(contratoSelecionado);
            System.out.println("Redirecionando contrato selecionado...");
        } else {
            System.out.println("Nenhum grupo foi selecionado.");
        }
    }
}