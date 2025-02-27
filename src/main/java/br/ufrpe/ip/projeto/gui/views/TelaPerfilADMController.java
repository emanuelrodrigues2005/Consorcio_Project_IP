package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TelaPerfilADMController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador screenManager;
    private Cliente cliente;

    @FXML
    private Label lbHomeADM;

    @FXML
    private Label lbPerfilADM;

    @FXML
    private Label lbSair;

    @FXML
    private Label lbNomeADM;

    @FXML
    private Label lbCPFADM;

    @FXML
    private Label lbTelefoneADM;

    @FXML
    private Label lbEmailADM;

    @FXML
    private Label lbCodigoADM;

    @FXML
    public void initialize() {
        limparCampos();

        if (this.cliente != null) {
            lbCodigoADM.setText("2702");
            lbNomeADM.setText(cliente.getNome());
            lbCPFADM.setText(cliente.getCpf());
            lbTelefoneADM.setText(cliente.getTelefone());
            lbEmailADM.setText(cliente.getEmail());
        }
    }

    public void setGerenciador(Gerenciador screenManager) {
        this.screenManager = screenManager;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    public void handleTelaPrincipalADM(MouseEvent event) {
        System.out.println("Redirecionando tela principal do cliente...");
        this.screenManager.abrirTelaPrincipalADM();
    }

    @FXML
    public void handleTelaEscolherLogin(MouseEvent event) {
        System.out.println("Redirecionando tela escolher forma de login...");
        this.screenManager.abrirTelaEscolhaLogin();
    }

    private void limparCampos() {
        lbCodigoADM.setText("");
        lbNomeADM.setText("");
        lbCPFADM.setText("");
        lbTelefoneADM.setText("");
        lbEmailADM.setText("");
    }
}
