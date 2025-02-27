package br.ufrpe.ip.projeto.gui;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.views.*;
import br.ufrpe.ip.projeto.models.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Gerenciador {
    private static Gerenciador instance;
    private static IConsorcio sistema = ConsorcioFachada.getInstance();

    private Parent telaVisuGrupo;
    private Parent telaLoginCliente;
    private Parent telaLoginAdm;
    private Parent telaEscolhaLogin;
    private Parent telaCadastro;
    private Parent telaPerfilCliente;
    private Parent telaPrincipalCliente;
    private Parent telaCriacaoGrupo;
    private Parent telaPrincipalADM;
    private Parent telaVisualizacaoContrato;
    private Parent telaEdicaoGrupo;
    private Parent telaDadosContrato;
    private Parent telaPerfilADM;

    private Stage stagePrincipal;
    private Scene scenePrincipal;

    private TelaVisuGrupoController telaVisuGrupoController;
    private TelaLoginClienteController telaLoginClienteController;
    private TelaCadastroController telaCadastroController;
    private TelaPerfilClienteController telaPerfilClienteController;
    private TelaPrincipalClienteController telaPrincipalClienteController;
    private TelaCriacaoGrupoController telaCriacaoGrupoController;
    private TelaPrincipalADMController telaPrincipalADMController;
    private TelaVisualizacaoContratoController telaVisualizacaoContratoController;
    private TelaEscolhaLoginController telaEscolhaLoginController;
    private TelaLoginAdmController telaLoginAdmController;
    private TelaEditGrupoController telaEdicaoGrupoController;
    private TelaDadosContratoController telaDadosContratoController;
    private TelaPerfilADMController telaPerfilADMController;

    private Gerenciador() {
        try{
                FXMLLoader loaderTelaVisuGrupo = new FXMLLoader(getClass().getResource(TelasEnum.TELA_VISUALIZACAO_GRUPO.getCaminho()));
                telaVisuGrupo = loaderTelaVisuGrupo.load();
                telaVisuGrupoController = loaderTelaVisuGrupo.getController();
                telaVisuGrupoController.setGerenciador(this);

                FXMLLoader loaderTelaLogin = new FXMLLoader(getClass().getResource(TelasEnum.TELA_LOGIN_CLIENTE.getCaminho()));
                telaLoginCliente = loaderTelaLogin.load();
                telaLoginClienteController = loaderTelaLogin.getController();
                telaLoginClienteController.setGerenciador(this);

                FXMLLoader loaderTelaCadastro = new FXMLLoader(getClass().getResource(TelasEnum.TELA_CADASTRO.getCaminho()));
                telaCadastro = loaderTelaCadastro.load();
                telaCadastroController = loaderTelaCadastro.getController();
                telaCadastroController.setGerenciador(this);

                FXMLLoader loaderTelaPerfilCliente = new FXMLLoader(getClass().getResource(TelasEnum.TELA_PERFIL_CLIENTE.getCaminho()));
                telaPerfilCliente = loaderTelaPerfilCliente.load();
                telaPerfilClienteController = loaderTelaPerfilCliente.getController();
                telaPerfilClienteController.setGerenciador(this);

                FXMLLoader loaderTelaPrincipalCliente = new FXMLLoader(getClass().getResource(TelasEnum.TELA_PRINCIPAL_CLIENTE.getCaminho()));
                telaPrincipalCliente = loaderTelaPrincipalCliente.load();
                telaPrincipalClienteController = loaderTelaPrincipalCliente.getController();
                telaPrincipalClienteController.setGerenciador(this);

                FXMLLoader loaderTelaCriacaoGrupo = new FXMLLoader(getClass().getResource(TelasEnum.TELA_CRIACAO_GRUPO.getCaminho()));
                telaCriacaoGrupo = loaderTelaCriacaoGrupo.load();
                telaCriacaoGrupoController = loaderTelaCriacaoGrupo.getController();
                telaCriacaoGrupoController.setGerenciador(this);

                FXMLLoader loadrerTelaPrincipalADM = new FXMLLoader(getClass().getResource(TelasEnum.TELA_PRINCIPAL_ADM.getCaminho()));
                telaPrincipalADM = loadrerTelaPrincipalADM.load();
                telaPrincipalADMController = loadrerTelaPrincipalADM.getController();
                telaPrincipalADMController.setGerenciador(this);

                FXMLLoader loaderTelaVisualizacaoContrato = new FXMLLoader(getClass().getResource(TelasEnum.TELA_VISUALIZACAO_CONTRATO.getCaminho()));
                telaVisualizacaoContrato = loaderTelaVisualizacaoContrato.load();
                telaVisualizacaoContratoController = loaderTelaVisualizacaoContrato.getController();
                telaVisualizacaoContratoController.setGerenciador(this);

                FXMLLoader loaderTelaEscolhaLogin = new FXMLLoader(getClass().getResource(TelasEnum.TELA_ESCOLHA_LOGIN.getCaminho()));
                telaEscolhaLogin = loaderTelaEscolhaLogin.load();
                telaEscolhaLoginController = loaderTelaEscolhaLogin.getController();
                telaEscolhaLoginController.setGerenciador(this);

                FXMLLoader loaderTelaLoginAdm = new FXMLLoader(getClass().getResource(TelasEnum.TELA_LOGIN_ADM.getCaminho()));
                telaLoginAdm = loaderTelaLoginAdm.load();
                telaLoginAdmController = loaderTelaLoginAdm.getController();
                telaLoginAdmController.setGerenciador(this);

                FXMLLoader loaderTelaEdicaoGrupo = new FXMLLoader(getClass().getResource(TelasEnum.TELA_EDICAO_GRUPO.getCaminho()));
                telaEdicaoGrupo = loaderTelaEdicaoGrupo.load();
                telaEdicaoGrupoController = loaderTelaEdicaoGrupo.getController();
                telaEdicaoGrupoController.setGerenciador(this);

                FXMLLoader loaderTelaDadosContrato = new FXMLLoader(getClass().getResource(TelasEnum.TELA_DADOS_CONTRATO.getCaminho()));
                telaDadosContrato = loaderTelaDadosContrato.load();
                telaDadosContratoController = loaderTelaDadosContrato.getController();
                telaDadosContratoController.setGerenciador(this);

                FXMLLoader loaderTelaPerfilADM = new FXMLLoader(getClass().getResource(TelasEnum.TELA_PERFIL_ADM.getCaminho()));
                telaPerfilADM = loaderTelaPerfilADM.load();
                telaPerfilADMController = loaderTelaPerfilADM.getController();
                telaPerfilADMController.setGerenciador(this);
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
        scenePrincipal = new Scene(telaEscolhaLogin, 1280, 720);
        stagePrincipal.setScene(scenePrincipal);
        stagePrincipal.show();
    }

    public void abrirTelaLoginCliente() {
        telaLoginClienteController.initialize();
        scenePrincipal.setRoot(telaLoginCliente);
    }

    public void abrirTelaLoginAdm() {
        telaLoginAdmController.initialize();
        scenePrincipal.setRoot(telaLoginAdm);
    }

    public void abrirCadastro() {
        telaCadastroController.initialize();
        scenePrincipal.setRoot(telaCadastro);
    }

    public void abrirTelaPrincipalCliente() {
        telaPrincipalClienteController.initialize();
        scenePrincipal.setRoot(telaPrincipalCliente);
    }

    public void abrirPerfilCliente(Cliente cliente) {
        telaPerfilClienteController.setCliente(cliente);
        telaPerfilClienteController.initialize();
        scenePrincipal.setRoot(telaPerfilCliente);
    }

    public void abrirTelaCriacaoGrupo() {
        telaCriacaoGrupoController.initialize();
        scenePrincipal.setRoot(telaCriacaoGrupo);
    }

    public void abrirTelaPerfilAdmin(Cliente admin) {
        telaPerfilADMController.setCliente(admin);
        telaPerfilADMController.initialize();
        scenePrincipal.setRoot(telaPerfilADM);
    }

    public void abrirTelaPrincipalADM() {
        telaPrincipalADMController.initialize();
        scenePrincipal.setRoot(telaPrincipalADM);
    }

    public void abriTelaVisualizacaoContrato(GrupoConsorcio grupo) {
        telaVisualizacaoContratoController.setClienteLogado(sistema.getClienteLogado());
        telaVisualizacaoContratoController.setGrupoAtual(grupo);
        telaVisualizacaoContratoController.initialize();
        scenePrincipal.setRoot(telaVisualizacaoContrato);
    }

    public void abrirTelaVisuGrupo(GrupoConsorcio grupo) {
        telaVisuGrupoController.setGrupoAtual(grupo);
        scenePrincipal.setRoot(telaVisuGrupo);
    }

    public void abrirTelaEscolhaLogin() {
        scenePrincipal.setRoot(telaEscolhaLogin);
    }

    public TelaPrincipalClienteController getTelaPrincipalClienteController() {
        return telaPrincipalClienteController;
    }

    public void abrirTelaEdicaoGrupo(GrupoConsorcio grupo) {
        telaEdicaoGrupoController.initialize();
        telaEdicaoGrupoController.setGrupoAtual(grupo);
        scenePrincipal.setRoot(telaEdicaoGrupo);
    }

    public void abrirTelaDadosContrato(Contrato contrato) {
        telaDadosContratoController.initialize();
        telaDadosContratoController.setContratoAtual(contrato);
        scenePrincipal.setRoot(telaDadosContrato);
    }

    public void abrirPopUpPagamento(Boleto boleto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(TelasEnum.POP_UP_PAGAMENTO.getCaminho()));
            Pane popupRoot = loader.load();

            PopUpPagamentoController controller = loader.getController();
            controller.setBoletoAtual(boleto);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Pagamento do Boleto");
            popupStage.setScene(new Scene(popupRoot));
            popupStage.setResizable(false);

            controller.setPopupStage(popupStage);

            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirPopUpSorteio(Contemplacao contemplacao) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(TelasEnum.POP_UP_SORTEIO.getCaminho()));
            Pane popupRoot = loader.load();

            PopUpSorteioController controller = loader.getController();
            controller.setContemplacaoAtual(contemplacao);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Contemplaçao de Contrato");
            popupStage.setScene(new Scene(popupRoot));
            popupStage.setResizable(false);

            controller.setPopupStage(popupStage);

            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
