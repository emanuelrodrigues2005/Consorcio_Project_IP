package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TelaDadosContratoController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private Contrato contratoAtual;

    @FXML
    private Label lbHomeCliente;

    @FXML
    private Label lbPerfilCliente;

    @FXML
    private Label lbGruposCliente;

    @FXML
    private Label lbSair;

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
    private Button btEncerrarContrato;

    @FXML
    private Button btPagarBoleto;

    @FXML
    private TableView<Boleto> tbvBoletos;

    @FXML
    private TableColumn<Contrato, String> tbvcolumnDataVencimento;

    @FXML
    private TableColumn<Contrato, String> tbvcolumnStatusBoleto;

    @FXML
    public void initialize() {
        if (contratoAtual != null) {
            carregarDados();
            configurarTabela();
        }
    }

    public void setContratoAtual(Contrato contratoAtual) {
        limparDadosGrupo();
        this.contratoAtual = contratoAtual;
        if (contratoAtual != null) {
            carregarDadosContrato();
        }
    }

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    private void carregarDados() {
        for (Boleto boleto : sistema.getAllBoletos()) {
            System.out.println(boleto.getContratoBoleto().getNomeGrupoConsorcio());
        }
        System.out.println(contratoAtual.getNomeGrupoConsorcio());
        ObservableList<Boleto> boletos = FXCollections.observableArrayList(sistema.getAllBoletosByContrato(contratoAtual.getIdContrato()));
        tbvBoletos.setItems(boletos);
    }

    private void configurarTabela() {
        tbvcolumnDataVencimento.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        tbvcolumnStatusBoleto.setCellValueFactory(new PropertyValueFactory<>("statusBoletoString"));
    }

    @FXML
    private void handleTelaPrincipalCliente() {
        System.out.println("Botão Home clicado");
        gerenciador.abrirTelaPrincipalCliente();
    }

    @FXML
    private void handleTelaPerfilCliente() {
        System.out.println("Botão Perfil clicado");
        gerenciador.abrirPerfilCliente(this.sistema.getClienteLogado());
    }

    @FXML
    private void handleTelaEscolherLogin() {
        System.out.println("Saindo...");
        gerenciador.abrirTelaEscolhaLogin();
    }

    @FXML
    private void handleTelaGruposCliente() {
        System.out.println("Redirecionado para grupos ...");
        //screenManager.abrirTelaGruposCliente;
    }

    @FXML
    private void handleMudarStatusContrato(MouseEvent event) {
        if (contratoAtual != null) {
            this.sistema.updateStatusContrato(contratoAtual, StatusContratoEnum.ENCERRADO);
            System.out.println("Grupo Encerrado com sucesso!");
            carregarDadosContrato();
            btEncerrarContrato.setText("Contrato Encerrado");
            btEncerrarContrato.setDisable(true);
        }
    }

    @FXML
    private void handlePopPagamento(MouseEvent event) {
        Boleto boleto = tbvBoletos.getSelectionModel().getSelectedItem();

        if (boleto != null) {
            this.gerenciador.abrirPopUpPagamento(boleto);
        } else {
            System.out.println("Nenhum boleto selecionado!");
        }
    }

    private void carregarDadosContrato() {
        if (contratoAtual != null) {

            lbNomeGrupo.setText(contratoAtual.getGrupoAssociado().getNomeGrupo());
            lbParcelasPagas.setText(String.valueOf(contratoAtual.getParcelasPagas()));
            lbValorPago.setText(String.format("R$ %.2f", contratoAtual.getValorPago()));
            lbSaldoDevedor.setText(String.format("R$ %.2f", contratoAtual.getSaldoDevedor()));
            lbStatusContrato.setText(contratoAtual.getStatusContrato() != null ? contratoAtual.getStatusContrato().toString() : "Não informado");
            lbDataInicio.setText(contratoAtual.getDataInicio() != null ? contratoAtual.getDataInicio().toString() : "Não informado");
        }
    }

    private void limparDadosGrupo() {
        lbNomeGrupo.setText("-");
        lbValorPago.setText("0");
        lbSaldoDevedor.setText("0");
        lbStatusContrato.setText("-");
        lbDataInicio.setText("-");
    }
}
