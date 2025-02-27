package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Administrador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class TelaLoginClienteController {
    private IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;

    @FXML
    private Text btRegistro;

    @FXML
    private Button btLogin;

    @FXML
    private Button btVoltarEscolhaLogin;

    @FXML
    private CheckBox cbManterConectado;

    @FXML
    private TextField txtCpf;

    @FXML
    private PasswordField pswSenha;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    @FXML
    public void initialize() {
        txtCpf.setText("");
        pswSenha.setText("");
        cbManterConectado.setSelected(false);
    }

    @FXML
    private void handleTelaCadastro(MouseEvent event) {
        System.out.println("Acessando tela de cadastro...");
        this.gerenciador.abrirCadastro();
        this.clearCampos();
    }

    @FXML
    private void handleTelaPrincipalCliente(MouseEvent event) {
        System.out.println("Login realizado com sucesso!");
        String cpf = this.txtCpf.getText();
        String senha = this.pswSenha.getText();
        this.sistema.efutuarLogin(cpf, senha);
        this.gerenciador.abrirTelaPrincipalCliente();
        clearCampos();
    }

    @FXML
    private void handleVoltarEscolhaLogin(MouseEvent event) {
        System.out.println("Voltando para tela de escolha de login...");
        this.gerenciador.abrirTelaEscolhaLogin();
    }

    private void clearCampos() {
        this.txtCpf.clear();
        this.pswSenha.clear();
    }
}
