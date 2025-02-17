package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Administrador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TelaCriacaoGrupoController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private Administrador administrador;

    @FXML
    private Label lbHomeAdm;

    @FXML
    private Label lbPerfilAdm;

    @FXML
    private Label lbSair;

    @FXML
    private TextField txtNomeGrupo;

    @FXML
    private TextField txtTaxaAdmin;

    @FXML
    private TextField txtValorTotalGrupo;

    @FXML
    private TextField txtNumeroTotalGrupo;

    @FXML
    private Button btCriarGrupo;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    @FXML
    private void handleTelaPerfilAdmin(MouseEvent event) {
        System.out.println("Redirecionando para a tela de perfil do cliente...");
        gerenciador.abrirTelaPerfilAdmin(this.sistema.getClienteLogado());
    }

    @FXML
    private void handleTelaPrincipalADM(MouseEvent event) {
        System.out.println("Redirecionando para a tela de grupos...");
        gerenciador.abrirTelaPrincipalADM();
    }

    @FXML
    private void handleTelaLogin(MouseEvent event) {
        System.out.println("Redirecionando para a tela de login...");
        gerenciador.abrirTelaEscolhaLogin();
    }

    @FXML
    public void initialize() {
        txtNomeGrupo.setText("");
        txtTaxaAdmin.setText("");
        txtValorTotalGrupo.setText("");
        txtNumeroTotalGrupo.setText("");
    }

    @FXML
    private void criarGrupo(MouseEvent event) {
        String nomeGrupo = txtNomeGrupo.getText();
        String taxaAdmin = txtTaxaAdmin.getText();
        String valorTotal = txtValorTotalGrupo.getText();
        String numeroTotal = txtNumeroTotalGrupo.getText();

        if (nomeGrupo.isEmpty() || taxaAdmin.isEmpty() || valorTotal.isEmpty() || numeroTotal.isEmpty()) {
            System.out.println("Preencha todos os campos antes de criar o grupo.");
            return;
        }

        try {
            double taxa = Double.parseDouble(taxaAdmin);
            double valor = Double.parseDouble(valorTotal);
            int totalParticipantes = Integer.parseInt(numeroTotal);

            System.out.println("Grupo criado com sucesso!");
            System.out.println("Nome: " + nomeGrupo);
            System.out.println("Taxa de Administração: " + taxa);
            System.out.println("Valor Total: " + valor);
            System.out.println("Número Total de Participantes: " + totalParticipantes);

            sistema.createGrupoConsorcio(nomeGrupo, totalParticipantes, valor, taxa);
            clearCampos();
        } catch (Exception e) {
            System.out.println("Erro: Certifique-se de inserir valores numéricos válidos para taxa, valor total e número de participantes.");
        }
    }

    private void clearCampos() {
        this.txtNomeGrupo.clear();
        this.txtTaxaAdmin.clear();
        this.txtValorTotalGrupo.clear();
        this.txtNumeroTotalGrupo.clear();
    }
}
