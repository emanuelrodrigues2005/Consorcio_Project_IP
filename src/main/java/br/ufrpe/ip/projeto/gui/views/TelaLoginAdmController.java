package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.CampoInvalidoException;
import br.ufrpe.ip.projeto.exceptions.ClienteDuplicadoException;
import br.ufrpe.ip.projeto.exceptions.ClienteInexistenteException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class TelaLoginAdmController {
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

    @FXML
    private PasswordField pswCodeAdm;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    @FXML
    public void initialize() {
        txtCpf.setText("");
        pswSenha.setText("");
        pswCodeAdm.setText("");
        cbManterConectado.setSelected(false);
    }

    @FXML
    private void handleTelaCadastro(MouseEvent event) {
        System.out.println("Acessando tela de cadastro...");
        this.gerenciador.abrirCadastro();
        this.clearCampos();
    }

    @FXML
    private void handleTelaPrincipalAdm(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        try {
            String cpf = this.txtCpf.getText();
            String senha = this.pswSenha.getText();
            this.sistema.efutuarLogin(cpf, senha);
            VLoginAdm();
        }
        catch (ClienteDuplicadoException e){
            alert.setTitle(null);
            alert.setHeaderText("Usuário Duplicado!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        catch (CampoInvalidoException e){
            alert.setTitle(null);
            alert.setHeaderText("Dados Preenchidos Não Correspondentes!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        catch (ClienteInexistenteException e){
            alert.setTitle(null);
            alert.setHeaderText("Usuário Não Encontrado!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        clearCampos();
    }



    @FXML
    private void handleVoltarEscolhaLogin(MouseEvent event) {
        System.out.println("Voltando tela de escolha de login...");
        this.gerenciador.abrirTelaEscolhaLogin();
    }

    private void VLoginAdm () throws CampoInvalidoException {
        if (pswCodeAdm.getText().equals("2702")) {
            this.gerenciador.abrirTelaPrincipalADM();
            System.out.println("Login realizado com sucesso!");
        } else if (pswSenha.getText().isEmpty() || !pswCodeAdm.getText().equals("2702")) {
            throw new CampoInvalidoException("Campos invalidos!");
        }
    }

    private void clearCampos() {
        this.txtCpf.clear();
        this.pswSenha.clear();
        this.pswCodeAdm.clear();
    }
}
