package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.CampoInvalidoException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TelaCadastroController {
    private IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtConfirmarSenha;

    @FXML
    private Button btCadastro;

    @FXML
    private Text btConecte;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    @FXML
    public void initialize() {
        txtCpf.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtConfirmarSenha.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
    }

    private void exibirAlertaErro(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");

        if (e instanceof CampoInvalidoException) {
            alert.setHeaderText("Campo Inválido!");
        }

        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void handleCadastro() {
        String nome = null;
        String telefone = null;
        String cpf = null;
        String email = null;
        String senha = null;
        try {
            nome = txtNome.getText();
            telefone = txtTelefone.getText();
            cpf = txtCpf.getText();
            email = txtEmail.getText();
            senha = txtSenha.getText();
            String confirmarSenha = txtConfirmarSenha.getText();

            if (cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty() || nome.isEmpty() || telefone.isEmpty()) {
                throw new CampoInvalidoException("Há um campo inválido");
            }

            if (!senha.equals(confirmarSenha)) {
                System.out.println("As senhas não coincidem.");
                return;
            }
        } catch (Exception e) {
            exibirAlertaErro(e);
        }

        System.out.println("Cadastro realizado com sucesso! CPF: " + cpf + ", Email: " + email);

        this.sistema.createCliente(nome, cpf, telefone, email, senha);
        this.openLogin();
    }

    @FXML
    private void openLogin() {
        if (gerenciador != null) {
            gerenciador.abrirTelaEscolhaLogin();
        }
    }
}
