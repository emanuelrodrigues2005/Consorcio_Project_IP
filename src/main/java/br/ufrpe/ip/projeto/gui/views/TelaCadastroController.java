package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TelaCadastroController {
    private IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtConfirmarSenha;

    @FXML
    private Text btConecte;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    @FXML
    private void handleCadastro() {
        String cpf = txtCpf.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        String confirmarSenha = txtConfirmarSenha.getText();

        if (cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            System.out.println("As senhas não coincidem.");
            return;
        }

        System.out.println("Cadastro realizado com sucesso! CPF: " + cpf + ", Email: " + email);
        this.openLogin();
    }

    @FXML
    private void openLogin() {
        if (gerenciador != null) {
            gerenciador.abrirTelaLogin();
        }
    }
}
