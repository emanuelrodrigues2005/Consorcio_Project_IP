package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
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
        private Button btCriarContrato;

        public void setGerenciador(Gerenciador gerenciador) {
            this.gerenciador = gerenciador;
        }

        public void setGrupoAtual(GrupoConsorcio grupo) {
            this.grupoAtual = grupo;
        }

        @FXML
        public void initialize() {
            System.out.println("Tela de visualização de contrato inicializada.");
            setGrupoAtual(grupoAtual);
            carregarDadosContrato();
        }

        private void carregarDadosContrato() {
            if (grupoAtual != null) {
                lbNomeGrupo.setText(grupoAtual.getNomeGrupo());
                lbValorParcela.setText(String.format("%.2f", grupoAtual.getValorParcela()));
                lbTaxaAdmin.setText(String.format("%.2f", grupoAtual.getTaxaAdmin()));
                lbCpfCliente.setText(clienteLogado.getCpf());
                lbDataInicio.setText(LocalDate.now().toString());
            } else {
                System.out.println("Nenhum grupo foi definido.");
            }
        }

        @FXML
        private void handleCriarContrato(MouseEvent event) {
            this.sistema.createContrato(clienteLogado, grupoAtual);
            System.out.println("Contrato assinado!");
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
            this.gerenciador.abrirTelaLogin();
        }

        @FXML
        private void handleTelaGruposCliente(MouseEvent event) {
            //this.gerenciador.abrirTelaGruposCliente;
        }
}
