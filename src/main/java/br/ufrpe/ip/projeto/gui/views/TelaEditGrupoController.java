package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contemplacao;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaEditGrupoController {
	private IConsorcio sistema = ConsorcioFachada.getInstance();
	private Gerenciador gerenciador;
	private GrupoConsorcio grupoAtual;

	@FXML
	private Label lbHomeADM;

	@FXML
	private Label lbPerfilADM;

	@FXML
	private Label lbSair;

	@FXML
	private TableView<Contrato> tbvParticipantes;

	@FXML
	private TableColumn<Contrato, String> tbvcolumnNome;

	@FXML
	private TableColumn<Contrato, String> tbvcolumnStatus;

	@FXML
	private TableColumn<Contrato, String> tbvcolumnSaldo;

	@FXML
	private Button btEncerrarGrupo;

	@FXML
	private Button btRemoverParticipante;

	@FXML
	private Label lbAutomovel;

	@FXML
	private Label lbValorTotal;

	@FXML
	private Label lbStatus;

	@FXML
	private Label lbTaxa;

	@FXML
	private Button btAlterarTaxa;

	@FXML
	private Button btRealizarSorteio;

	public void setGerenciador(Gerenciador gerenciador) {
		this.gerenciador = gerenciador;
	}

	public void setGrupoAtual(GrupoConsorcio grupoAtual) {
		limparDadosGrupo();
		this.grupoAtual = grupoAtual;
		if (grupoAtual != null) {
			carregarDadosGrupo(grupoAtual.getIdGrupo());
		}
	}

	@FXML
	public void initialize() {
		if (grupoAtual != null) {
			lbAutomovel.setText(String.valueOf(grupoAtual.getNomeGrupo()));
			lbValorTotal.setText(String.valueOf(grupoAtual.getValorTotal()));
			lbStatus.setText(String.valueOf(grupoAtual.getStatusGrupoConsorcio()));
			lbTaxa.setText(String.valueOf(grupoAtual.getTaxaAdmin() * 100));
			this.configurarTabela();
			this.carregarDadosDaTabela();
		}
	}

	@FXML
	public void handleTelaPrincipalADM() {
		System.out.println("Redirecionando para a home do ADM");
		this.gerenciador.abrirTelaPrincipalADM();
	}

	@FXML
	public void handleTelaPerfilAdmin() {
		this.gerenciador.abrirTelaPerfilAdmin(this.sistema.getClienteLogado());
	}

	@FXML
	public void handleTelaEscolherLogin() {
		System.out.println("Redirecionando para a tela escolher login");
		this.gerenciador.abrirTelaEscolhaLogin();
	}

	@FXML
	public void handleAlterarTaxaAdmin() {
		System.out.println("Redirecionando para a tela de escolha da taxa");
		this.gerenciador.abrirPopUpAtualizarTaxa(grupoAtual);
	}

	@FXML
	public void handleRealizarContemplacao() {
		String idContemplacao = this.sistema.sorteioContemplacao();
		this.gerenciador.abrirPopUpSorteio(this.sistema.getContemplacaoById(idContemplacao));
	}

	@FXML
	public void handleMudarStatusGrupo() {
		if (grupoAtual != null) {
			this.sistema.updateStatusGrupo(grupoAtual, StatusGrupoConsorcioEnum.ENCERRADO);
			System.out.println("Grupo Encerrado com sucesso!");
			carregarDadosGrupo(grupoAtual.getIdGrupo());
			btEncerrarGrupo.setText("Grupo Encerrado");
			btEncerrarGrupo.setDisable(true);
		}
	}

	@FXML
	private void handleDeleteCliente() {
		Contrato clienteSelecionado = tbvParticipantes.getSelectionModel().getSelectedItem();

		if(clienteSelecionado != null) {
			this.sistema.deleteCliente(clienteSelecionado.getCliente().getCpf());
			tbvParticipantes.getItems().remove(clienteSelecionado);
			mostrarAlerta("Cliente deletado", "Cliente " + clienteSelecionado.getCliente().getCpf());
		} else {
			mostrarAlerta("Not found!", "Nenhum cliente encontrado!");
		}
	}

	private void mostrarAlerta(String titulo, String mensagem) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}

	private void carregarDadosGrupo(String idGrupo) {
		grupoAtual = sistema.getGrupoById(idGrupo);
		if (grupoAtual != null) {
			if (lbAutomovel != null) {
				lbAutomovel.setText(grupoAtual.getNomeGrupo());
			} else {
				System.out.println("lbAutoConsor está nulo!");
			}

			lbValorTotal.setText(String.format("%.2f", grupoAtual.getValorTotal()));
			lbTaxa.setText(String.format("%.2f", grupoAtual.getTaxaAdmin()));
			lbStatus.setText(grupoAtual.getStatusGrupoConsorcioString());
		} else {
			System.out.println("Nenhum grupo encontrado com o ID fornecido.");
		}
	}

	private void configurarTabela() {
		tbvcolumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		tbvcolumnStatus.setCellValueFactory(new PropertyValueFactory<>("statusContratoString"));
		tbvcolumnSaldo.setCellValueFactory(new PropertyValueFactory<>("saldoDevedor"));
	}

	private void carregarDadosDaTabela() {
		ObservableList<Contrato> contratos = FXCollections.observableArrayList(sistema.getContratosByIdGrupo(grupoAtual));
		tbvParticipantes.setItems(contratos);
	}

	private void limparDadosGrupo() {
		lbAutomovel.setText("");
		lbValorTotal.setText("");
		lbStatus.setText("");
		lbTaxa.setText("");
	}
}
