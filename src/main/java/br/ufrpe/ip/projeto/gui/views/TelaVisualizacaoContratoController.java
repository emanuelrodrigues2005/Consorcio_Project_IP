package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.GrupoConsorcioController;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class TelaVisualizacaoContratoController {
    private IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private GrupoConsorcio grupoAtual;
    private Cliente clienteLogado = sistema.getClienteLogado();

    @FXML
    private Label lbNomeGrupo;

    @FXML
    private Label lbCpfCliente;

    @FXML
    private Label lbValorParcela;

    @FXML
    private Label lbTaxaAdmin;

    @FXML
    private Label lbDataInicio;

    @FXML
    private Label lbHomeCliente;

    @FXML
    private Label lbPerfilCliente;

    @FXML
    private Label lbGruposCliente;

    @FXML
    private Label lbSair;

    @FXML
    private Button btCriarContrato;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void setGrupoAtual(GrupoConsorcio grupo) {
        limparDadosContrato(); // Limpa os dados antigos antes de definir o novo grupo
        this.grupoAtual = grupo;
        carregarDadosContrato(); // Atualiza os dados com o novo grupo
    }


    @FXML
    public void initialize() {
        System.out.println("Tela de visualização de contrato inicializada.");
        carregarDadosContrato();
    }

    private void carregarDadosContrato() {
        if (grupoAtual != null) {
            lbNomeGrupo.setText(grupoAtual.getNomeGrupo());
            lbValorParcela.setText(String.format("%.2f", grupoAtual.getValorParcela()));
            lbTaxaAdmin.setText(String.format("%.2f", grupoAtual.getTaxaAdmin()));

            if (sistema.getClienteLogado() != null) {
                lbCpfCliente.setText(sistema.getClienteLogado().getCpf());
            } else {
                lbCpfCliente.setText("N/A");
                System.out.println("Erro: Nenhum cliente logado encontrado!");
            }

            lbDataInicio.setText(LocalDate.now().toString());
        } else {
            System.out.println("Nenhum grupo foi definido para exibição do contrato.");
        }
    }

    @FXML
    private void handleCriarContrato(MouseEvent event) {
        if (grupoAtual != null) {
            sistema.createContrato(clienteLogado, grupoAtual);
            System.out.println("Contrato assinado!");

            int novoNumeroParticipantes = grupoAtual.getNumeroParticipantes() + 1;
            sistema.updateParticipantes(grupoAtual, novoNumeroParticipantes);
            grupoAtual.setNumeroParticipantes(novoNumeroParticipantes);

            btCriarContrato.setDisable(true);
            btCriarContrato.setText("Contrato Assinado");

            // Atualizar a TableView na tela principal
            if (gerenciador != null) {
                TelaPrincipalClienteController telaPrincipal = gerenciador.getTelaPrincipalClienteController();
                if (telaPrincipal != null) {
                    telaPrincipal.atualizarTabela();
                }
            }
        } else {
            System.out.println("Erro: Nenhum grupo selecionado para criar contrato.");
        }
    }

    @FXML
    private void handleTelaHome(MouseEvent event) {
        this.gerenciador.abrirTelaPrincipalCliente();
    }

    @FXML
    private void handleTelaPerfilCliente(MouseEvent event) {
        this.gerenciador.abrirPerfilCliente(clienteLogado);
    }

    @FXML
    private void handleTelaLoginCliente(MouseEvent event) {
        this.gerenciador.abrirTelaEscolhaLogin();
    }

    @FXML
    private void handleTelaGruposCliente(MouseEvent event) {
        //this.gerenciador.abrirTelaGruposCliente;
    }

    private void limparDadosContrato() {
        lbNomeGrupo.setText("");
        lbValorParcela.setText("");
        lbTaxaAdmin.setText("");
        lbCpfCliente.setText("");
        lbDataInicio.setText("");
    }
}
