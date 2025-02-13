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

public class TelaPerfilClienteController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador screenManager;
    private Cliente cliente = sistema.getClienteLogado();

    @FXML
    private Button btHome;

    @FXML
    private Button btGrupos;

    @FXML
    private Button btPerfil;

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
    private ListView<Contrato> listaContratos;

    @FXML
    public void initialize() {
        lbNomeUser.setText(cliente.getNome());
        lbCPF.setText(cliente.getCpf());
        lbTelefone.setText(cliente.getTelefone());
        lbEmail.setText(cliente.getEmail());
        listaContratos.getItems().setAll(sistema.getAllContratosByCPF(cliente));
        lbNomeGrupo.setText("");
        lbParcelasPagas.setText("");
        lbValorPago.setText("");
        lbSaldoDevedor.setText("");
        lbStatusContrato.setText("");
        lbDataInicio.setText("");
        listaContratos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setInfoContrato(newSelection);
            }
        });
    }

    public void setGerenciador(Gerenciador screenManager) {
        this.screenManager = screenManager;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setInfoContrato(Contrato contrato) {
        lbNomeGrupo.setText(contrato.getGrupoAssociado() != null ? contrato.getGrupoAssociado().getNomeGrupo() : "Não informado");
        lbParcelasPagas.setText(String.valueOf(contrato.getParcelasPagas()));
        lbValorPago.setText(String.format("R$ %.2f", contrato.getValorPago()));
        lbSaldoDevedor.setText(String.format("R$ %.2f", contrato.getSaldoDevedor()));
        lbStatusContrato.setText(contrato.getStatusContrato() != null ? contrato.getStatusContrato().toString() : "Não informado");
        lbDataInicio.setText(contrato.getDataInicio() != null ? contrato.getDataInicio().toString() : "Não informado");
    }
    @FXML
    private void openHome(ActionEvent actionEvent) {
    }

    @FXML
    public void openAllGrupos(ActionEvent actionEvent) {
    }

    public void openPerfil(ActionEvent actionEvent) {
    }
}
