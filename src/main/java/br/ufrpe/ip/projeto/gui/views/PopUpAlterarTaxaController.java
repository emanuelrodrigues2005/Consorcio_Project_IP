package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.exceptions.CampoInvalidoException;
import br.ufrpe.ip.projeto.exceptions.GrupoConsorcioInexistenteException;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PopUpAlterarTaxaController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador gerenciador;
    private Stage popupStage;
    private GrupoConsorcio grupoAtual;

    @FXML
    private Button btAtualizarTaxa;

    @FXML
    private TextField txtNovaTaxa;

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    public void setGrupoAtual(GrupoConsorcio grupoAtual) {
        this.grupoAtual = grupoAtual;
    }

    @FXML
    public void initialize() {
        txtNovaTaxa.setText("");
    }

    @FXML
    private void handleAtualizarTaxa(MouseEvent event) {
        try {
            double novaTaxa = Double.parseDouble(txtNovaTaxa.getText());

            this.sistema.updateTaxaAdmin(grupoAtual, novaTaxa);
            this.atualizarValorParcela();
            this.gerenciador.abrirTelaEdicaoGrupo(grupoAtual);
            popupStage.close();
        } catch (GrupoConsorcioInexistenteException e) {
            exibirAlertaErro("Grupo Não Encontrado", "O grupo selecionado não foi encontrado.");
        } catch (CampoInvalidoException e) {
            exibirAlertaErro("Taxa Inválida", "A taxa administrativa deve ser maior que zero.");
        } catch (Exception e) {
            exibirAlertaErro("Erro Desconhecido", "Ocorreu um erro inesperado ao atualizar a taxa.");
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

    private void atualizarValorParcela() {
        double novaTaxa = Double.parseDouble(txtNovaTaxa.getText()) / 100;
        double novaParcela = (grupoAtual.getValorTotal() + grupoAtual.getValorTotal() * novaTaxa) / grupoAtual.getNumeroMaximoParticipantes();

        grupoAtual.setValorParcela(novaParcela);
    }
}
