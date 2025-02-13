package br.ufrpe.ip.projeto.gui;

import br.ufrpe.ip.projeto.gui.views.TelaCadastroController;
import br.ufrpe.ip.projeto.gui.views.TelaLoginController;
import br.ufrpe.ip.projeto.gui.views.TelaPerfilClienteController;
import br.ufrpe.ip.projeto.gui.views.TelaVisuGrupoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gerenciador {
    private static Gerenciador instance;

    private Parent telaVisuGrupo;
    private Parent telaLogin;
    private Parent telaCadastro;
    private Parent telaPerfilCliente;

    private Stage stagePrincipal;
    private Scene scenePrincipal;

    private TelaVisuGrupoController telaVisuGrupoController;
    private TelaLoginController telaLoginController;
    private TelaCadastroController telaCadastroController;
    private TelaPerfilClienteController telaPerfilClienteController;

    private Gerenciador() {
        try{
                FXMLLoader loaderTelaVisuGrupo = new FXMLLoader(getClass().getResource(TelasEnum.TELA_VISUALIZACAO_GRUPO.getCaminho()));
                telaVisuGrupo = loaderTelaVisuGrupo.load();
                telaVisuGrupoController = loaderTelaVisuGrupo.getController();
                telaVisuGrupoController.setGerenciador(this);

                FXMLLoader loaderTelaLogin = new FXMLLoader(getClass().getResource(TelasEnum.TELA_LOGIN.getCaminho()));
                telaLogin = loaderTelaLogin.load();
                telaLoginController = loaderTelaLogin.getController();
                telaLoginController.setGerenciador(this);

                FXMLLoader loaderTelaCadastro = new FXMLLoader(getClass().getResource(TelasEnum.TELA_CADASTRO.getCaminho()));
                telaCadastro = loaderTelaCadastro.load();
                telaCadastroController = loaderTelaCadastro.getController();
                telaCadastroController.setGerenciador(this);

                FXMLLoader loaderTelaPerfilCliente = new FXMLLoader(getClass().getResource(TelasEnum.TELA_PERFIL_CLIENTE.getCaminho()));
                telaPerfilCliente = loaderTelaPerfilCliente.load();
                telaPerfilClienteController = loaderTelaPerfilCliente.getController();
                telaPerfilClienteController.setGerenciador(this);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static Gerenciador getInstance() {
        if (instance == null) {
            instance = new Gerenciador();
        }
        return instance;
    }

    public void setStagePrincipal(Stage stage) {
        stagePrincipal = stage;
    }

    public void iniciarTelas() {
        stagePrincipal.setTitle("ConsorX");
        stagePrincipal.setResizable(false);
        scenePrincipal = new Scene(telaPerfilCliente, 1280, 720);
        stagePrincipal.setScene(scenePrincipal);
        stagePrincipal.show();
    }

    public void abrirTelaLogin() {
        scenePrincipal.setRoot(telaLogin);
    }

    public void abrirCadastro() {
        scenePrincipal.setRoot(telaCadastro);
    }

    public void abrirPerfilCliente() {
        scenePrincipal.setRoot(telaPerfilCliente);
    }
}
