package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


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
	private ListView<Cliente> ltvParticipantes;

	@FXML
	private Button btEncerrarGrupo;

	@FXML
	private Button btRemoverParticipante;

	@FXML
	private Label lbQntdParticipantes;

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
		lbAutomovel.setText("");
		lbQntdParticipantes.setText("0");
		lbValorTotal.setText("");
		lbStatus.setText("");
		lbTaxa.setText("");
		ltvParticipantes.getItems().setAll(sistema.getAllClientes()); //ajustar pra pegar somente os cliente do grupo selecionado
	}

	@FXML
	public void handleTelaPrincipalADM() {
		System.out.println("Redirecionando para a home do ADM");
		this.gerenciador.abrirTelaPrincipalADM();
	}

	@FXML
	public void handleTelaPerfilAdmin() {
		//this.gerenciador.abrirTelaPerfilAdmin();
	}

	@FXML
	public void handleTelaEscolherLogin() {
		System.out.println("Redirecionando para a tela escolher login");
		this.gerenciador.abrirTelaEscolhaLogin();
	}

	@FXML
	public void handleAlterarTaxaAdmin() {
		System.out.println("Redirecionando para a tela de escolha da taxa");
		//this.gerenciador.abrirTelaAlterarTaxaAdmin();
	}

	@FXML
	public void handleRealizarContemplacao() {
		this.sistema.sorteioContemplacao();
		//this.gerenciador.abrirTelaContratoContemplado();
	}

	@FXML
	public void handleMudarStatusGrupo() {
		if (grupoAtual != null) {
			this.sistema.updateStatusGrupo(grupoAtual, StatusGrupoConsorcioEnum.ENCERRADO);
			System.out.println("Grupo Encerrado com sucesso!");
			carregarDadosGrupo(grupoAtual.getIdGrupo()); // Atualiza a interface
			btEncerrarGrupo.setText("Grupo Encerrado");
			btEncerrarGrupo.setDisable(true);
		}
	}

	@FXML
	private void handleDeleteCliente() {
		Cliente clienteSelecionado = ltvParticipantes.getSelectionModel().getSelectedItem();

		if(clienteSelecionado != null) {
			this.sistema.deleteCliente(clienteSelecionado.getCpf());
			ltvParticipantes.getItems().remove(clienteSelecionado);
			mostrarAlerta("Cliente deletado", "Cliente " + clienteSelecionado.getCpf());
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

			int participantesAtuais = grupoAtual.getNumeroParticipantes();
			lbQntdParticipantes.setText(String.valueOf(participantesAtuais));
			lbStatus.setText(grupoAtual.getStatusGrupoConsorcioString());
		} else {
			System.out.println("Nenhum grupo encontrado com o ID fornecido.");
		}
	}

	private void limparDadosGrupo() {
		lbAutomovel.setText("");
		lbQntdParticipantes.setText("0");
		lbValorTotal.setText("");
		lbStatus.setText("");
		lbTaxa.setText("");
	}
}
