package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.ContemplacaoInexistenteException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contemplacao;
import br.ufrpe.ip.projeto.models.Contrato;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpSorteioController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private Contemplacao contemplacao;
    private Stage popupStage;

    @FXML
    private Button btEnviarMensagem;

    @FXML
    private Label lbNomeUserContemplado;

    @FXML
    private Label lbCPFContemplado;

    @FXML
    private Label lbTelefoneContemplado;

    @FXML
    private Label lbNomeGrupoContemplado;

    @FXML
    private Label lbDataContemplacao;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    @FXML
    public void initialize() {
        limparDadosContemplacao();
        this.setContemplacaoAtual(contemplacao);
    }

    public void setContemplacaoAtual(Contemplacao contemplacao) {
        limparDadosContemplacao();
        this.contemplacao = contemplacao;

        if (contemplacao != null) {
            carregarDadosContemplacao(contemplacao.getIdContemplacao());
        }
    }

    private void carregarDadosContemplacao(String idContemplacao) {
        try {
            this.contemplacao = this.sistema.getContemplacaoById(idContemplacao);

            if (contemplacao != null) {
                lbCPFContemplado.setText(contemplacao.getContratoContemplacao().getCliente().getCpf());
                lbTelefoneContemplado.setText(contemplacao.getContratoContemplacao().getCliente().getTelefone());
                lbNomeUserContemplado.setText(contemplacao.getContratoContemplacao().getCliente().getNome());
                lbNomeGrupoContemplado.setText(contemplacao.getContratoContemplacao().getNomeGrupoConsorcio());
                lbDataContemplacao.setText(contemplacao.getDataContemplacao().toString());
            }
        } catch (ContemplacaoInexistenteException e) {
            exibirAlertaErro(e);
        }
    }

    private void exibirAlertaErro(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");

        if (e instanceof ContemplacaoInexistenteException) {
            alert.setHeaderText("Contemplação Não Encontrada!");
        } else {
            alert.setHeaderText("Erro Desconhecido!");
        }

        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    private void limparDadosContemplacao() {
        lbNomeUserContemplado.setText("");
        lbCPFContemplado.setText("");
        lbTelefoneContemplado.setText("");
        lbNomeGrupoContemplado.setText("");
        lbDataContemplacao.setText("");
    }
}
