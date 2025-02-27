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

    @FXML
    private void handleCadastro() throws CampoInvalidoException {
        String nome = txtNome.getText();
        String telefone = txtTelefone.getText();
        String cpf = txtCpf.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        String confirmarSenha = txtConfirmarSenha.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        try {
            if (cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty() || nome.isEmpty() || telefone.isEmpty()) {
                alert.setHeaderText("Todos os Campos Devem Ser Preenchidos");
                throw new CampoInvalidoException("Campos Vazios!");
            }

            if (!senha.equals(confirmarSenha)) {
                alert.setHeaderText("As Senhas Não Coincidem!");
                throw new CampoInvalidoException(senha);
            }

            this.sistema.createCliente(nome, cpf, telefone, email, senha);
            System.out.println("Cadastro realizado com sucesso! CPF: " + cpf + ", Email: " + email);
            this.openLogin();
        }
        catch (CampoInvalidoException e) {
            alert.setTitle("Campo Invalido!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void openLogin() {
        if (gerenciador != null) {
            gerenciador.abrirTelaEscolhaLogin();
        }
    }
}
