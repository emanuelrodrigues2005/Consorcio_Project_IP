package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.ClienteDuplicadoException;
import br.ufrpe.ip.projeto.exceptions.ClienteInexistenteException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Administrador;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        try {
            String cpf = this.txtCpf.getText();
            String senha = this.pswSenha.getText();
            this.sistema.efutuarLogin(cpf, senha);
            System.out.println("Login realizado com sucesso!");
            this.gerenciador.abrirTelaPrincipalCliente();
        }
        catch (ClienteDuplicadoException | ClienteInexistenteException e){
            exceptAlert(e);
        }
        clearCampos();
    }

    @FXML
    private void handleVoltarEscolhaLogin(MouseEvent event) {
        System.out.println("Voltando para tela de escolha de login...");
        this.gerenciador.abrirTelaEscolhaLogin();
    }

    private void exceptAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (e instanceof ClienteDuplicadoException) {
            alert.setTitle(null);
            alert.setHeaderText("Cliente Duplicado!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        else if (e instanceof ClienteInexistenteException) {
            alert.setTitle(null);
            alert.setHeaderText("Cliente Não Encontrado!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void clearCampos() {
        this.txtCpf.clear();
        this.pswSenha.clear();
    }
}
