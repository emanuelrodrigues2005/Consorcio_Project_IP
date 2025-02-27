package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.GrupoConsorcioController;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.CampoInvalidoException;
import br.ufrpe.ip.projeto.exceptions.ContratoDuplicadoException;
import br.ufrpe.ip.projeto.exceptions.GrupoConsorcioInexistenteException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class TelaVisualizacaoContratoController {
    private IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private GrupoConsorcio grupoAtual;
    private Cliente clienteLogado;

    @FXML
    private Label lbNomeGrupo;

    @FXML
    private Label lbNomeCliente;

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
        limparDadosContrato();
        this.grupoAtual = grupo;
        carregarDadosContrato();
    }

    public void setClienteLogado(Cliente clienteLogado) {
        this.clienteLogado = clienteLogado;
    }


    @FXML
    public void initialize() {
        if (clienteLogado != null) {
            System.out.println("Tela de visualização de contrato inicializada.");
            carregarDadosContrato();
        }
    }

    private void carregarDadosContrato() {
        if (grupoAtual != null) {
            lbNomeGrupo.setText(grupoAtual.getNomeGrupo());
            lbValorParcela.setText(String.format("%.2f", grupoAtual.getValorParcela()));
            lbTaxaAdmin.setText(String.format("%.2f", grupoAtual.getTaxaAdmin()));

            if (sistema.getClienteLogado() != null) {
                lbNomeCliente.setText(sistema.getClienteLogado().getNome());
                lbCpfCliente.setText(sistema.getClienteLogado().getCpf());
            } else {
                lbNomeCliente.setText("Nenhum cliente selecionado");
                lbCpfCliente.setText("Cpf não encontrado");
                System.out.println("Erro: Nenhum cliente logado encontrado!");
            }

            lbDataInicio.setText(LocalDate.now().toString());
        } else {
            System.out.println("Nenhum grupo foi definido para exibição do contrato.");
        }
    }

    @FXML
    private void handleCriarContrato(MouseEvent event) {
        if (grupoAtual == null) {
            exibirAlertaErro("Erro ao Criar Contrato", "Nenhum grupo selecionado para criar contrato.");
            return;
        }
        try {
            sistema.createContrato(clienteLogado, grupoAtual);
            System.out.println("Contrato assinado!");

            for (Contrato contrato : sistema.getAllContratosByCPF(clienteLogado)) {
                System.out.println(contrato.getCliente());
            }

            int novoNumeroParticipantes = grupoAtual.getNumeroParticipantes() + 1;
            sistema.updateParticipantes(grupoAtual, novoNumeroParticipantes);
            grupoAtual.setNumeroParticipantes(novoNumeroParticipantes);

            btCriarContrato.setDisable(true);
            btCriarContrato.setText("Contrato Assinado");

            if (gerenciador != null) {
                TelaPrincipalClienteController telaPrincipal = gerenciador.getTelaPrincipalClienteController();
                if (telaPrincipal != null) {
                    telaPrincipal.atualizarTabela();
                }
            }
        } catch (ContratoDuplicadoException e) {
            exibirAlertaErro("Contrato Duplicado", "Você já possui um contrato neste grupo.");
        } catch (CampoInvalidoException e) {
            exibirAlertaErro("Campo Inválido", "Erro no campo: " + e.getMessage());
        } catch (GrupoConsorcioInexistenteException e) {
            exibirAlertaErro("Grupo Não Encontrado", "O grupo selecionado não foi encontrado.");
        } catch (Exception e) {
            exibirAlertaErro("Erro Desconhecido", "Ocorreu um erro inesperado ao criar o contrato.");
            e.printStackTrace();
        }
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void handleTelaHome(MouseEvent event) {
        this.resetarBtCriarContrato();
        this.gerenciador.abrirTelaPrincipalCliente();
    }

    @FXML
    private void handleTelaPerfilCliente(MouseEvent event) {
        this.resetarBtCriarContrato();
        this.gerenciador.abrirPerfilCliente(clienteLogado);
    }

    @FXML
    private void handleTelaLoginCliente(MouseEvent event) {
        this.resetarBtCriarContrato();
        this.gerenciador.abrirTelaEscolhaLogin();
    }

    @FXML
    private void handleTelaGruposCliente(MouseEvent event) {
        this.resetarBtCriarContrato();
        //this.gerenciador.abrirTelaGruposCliente;
    }

    private void limparDadosContrato() {
        lbNomeGrupo.setText("");
        lbValorParcela.setText("");
        lbTaxaAdmin.setText("");
        lbCpfCliente.setText("");
        lbDataInicio.setText("");
    }

    private void resetarBtCriarContrato() {
        btCriarContrato.setDisable(false);
        btCriarContrato.setText("Criar contrato");
    }
}
