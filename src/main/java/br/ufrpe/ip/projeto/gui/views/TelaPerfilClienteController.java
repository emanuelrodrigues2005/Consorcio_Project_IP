package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private Label lbNomeGrupo;

    @FXML
    private Label lbParcelasPagas;

    @FXML
    private Label lbValorPago;

    @FXML
    private Label lbSaldoDevedor;

    @FXML
    private Label lbStatusContrato;

    @FXML
    private Label lbDataInicio;

    @FXML
    private ListView<Contrato> ltvContratos;

    @FXML
    public void initialize() {
        if (cliente != null) {
            lbNomeUser.setText(cliente.getNome());
            lbCPF.setText(cliente.getCpf());
            lbTelefone.setText(cliente.getTelefone());
            lbEmail.setText(cliente.getEmail());
            ltvContratos.getItems().clear();
            ltvContratos.getItems().setAll(sistema.getAllContratosByCPF(cliente));
            lbNomeGrupo.setVisible(false);
            lbParcelasPagas.setVisible(false);
            lbValorPago.setVisible(false);
            lbSaldoDevedor.setVisible(false);
            lbStatusContrato.setVisible(false);
            lbDataInicio.setVisible(false);
            ltvContratos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    setInfoContrato(newSelection);
                }
            });
        }
    }

    public void setGerenciador(Gerenciador screenManager) {
        this.screenManager = screenManager;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setLtvContratos() {
        ltvContratos.getItems().setAll(sistema.getAllContratosByCPF(cliente));
    }

    public void setInfoContrato(Contrato contrato) {
        lbNomeGrupo.setVisible(true);
        lbParcelasPagas.setVisible(true);
        lbValorPago.setVisible(true);
        lbSaldoDevedor.setVisible(true);
        lbStatusContrato.setVisible(true);
        lbDataInicio.setVisible(true);

        lbNomeGrupo.setText(contrato.getGrupoAssociado() != null ? contrato.getGrupoAssociado().getNomeGrupo() : "Não informado");
        lbParcelasPagas.setText(String.valueOf(contrato.getParcelasPagas()));
        lbValorPago.setText(String.format("R$ %.2f", contrato.getValorPago()));
        lbSaldoDevedor.setText(String.format("R$ %.2f", contrato.getSaldoDevedor()));
        lbStatusContrato.setText(contrato.getStatusContrato() != null ? contrato.getStatusContrato().toString() : "Não informado");
        lbDataInicio.setText(contrato.getDataInicio() != null ? contrato.getDataInicio().toString() : "Não informado");
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
}