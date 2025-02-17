package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class TelaEscolhaLoginController {
    IConsorcio sistema = ConsorcioFachada.getInstance();
    Gerenciador gerenciador;

    @FXML
    private Text btRegistro;

    @FXML
    private Button btLoginAdmin;

    @FXML
    private Button btLoginCliente;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void handleTelaCadastro() {
        System.out.println("Redirecionado para o cadastro...");
        gerenciador.abrirCadastro();
    }

    public void handleTelaLoginAdmin() {
        System.out.println("Redirecionado para a tela de login do administrador...");
        gerenciador.abrirTelaLoginAdm();
    }

    public void handleTelaLoginCliente() {
        System.out.println("Redirecionado para a tela de login do cliente...");
        gerenciador.abrirTelaLoginCliente();
    }
}
