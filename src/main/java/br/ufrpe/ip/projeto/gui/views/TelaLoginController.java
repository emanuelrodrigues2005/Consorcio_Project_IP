package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class TelaLoginController {
    private IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    @FXML
    private Text btRegistro;

    @FXML
    private Button btLogin;

    @FXML
    private CheckBox cbManterConectado;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtSenha;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    @FXML
    public void initialize() {
        txtCpf.setText("");
        txtSenha.setText("");
        cbManterConectado.setSelected(false);
    }

    @FXML
    private void openCadastro(MouseEvent event) {
        System.out.println("Acessando tela de cadastro...");
        this.gerenciador.abrirCadastro();
        this.clearCampos();
    }

    @FXML
    private void openGerenciamentoAdmin(ActionEvent event) {
        String cpf = txtCpf.getText();
        String senha = txtSenha.getText();
        boolean manterConectado = cbManterConectado.isSelected();

        System.out.println("Tentando login com:");
        System.out.println("CPF: " + cpf);
        System.out.println("Senha: " + senha);
        System.out.println("Manter conectado: " + manterConectado);

        if (cpf.equalsIgnoreCase("admin") && senha.equals("1234")) {
            this.sistema.getClienteByCpf(cpf);
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Login ou Senha inválidos!");
        }
    }

    private void clearCampos() {
        this.txtCpf.clear();
        this.txtSenha.clear();
    }
}
