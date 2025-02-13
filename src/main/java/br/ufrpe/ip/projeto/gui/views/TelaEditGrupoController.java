package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.models.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class TelaEditGrupoController {
	
	@FXML
	private TextField nGrupo;
	
	@FXML
	private TextField taxaAd;
	
	@FXML
	private Button add;

	@FXML
	private Button remove;

	@FXML
	private Button endG;
	
	@FXML
	private Label nMembros;
	
	@FXML
	private ToggleButton statusG;
	
	@FXML
    private TableView<Cliente> tabela;
	
	@FXML
	private TableColumn<Cliente, String> tableColumnNome;

	/*@FXML
	public void onBtAddAction() {
		
	}
	
	@FXML
	public void onBtRemoveAction() {
		
	}
	@FXML
	public void onBtEndGAction() {
		
	}*/
	
	 @FXML
	 public void initialize() {
	        ObservableList<Cliente> pessoas = FXCollections.observableArrayList(
	            new Cliente("João Ricardo", "54121", "85442", "email@jao"),
	            new Cliente("Josué Costa", "54121", "85442", "email@josu"),
	            new Cliente("Emanuel Tenório", "54121", "85442", "email@manoas"),
	            new Cliente("Lucas Cavalcanti", "57121", "85442", "email@lusca"),
	            new Cliente("Gustavo Henrique ", "54121", "85442", "email@gus")
	        );

	        tabela.setItems(pessoas);
	        return;
	    }
	 
	 @FXML
	 public void toggleButtonHandler() {
	     if (statusG.isSelected()) {
	         System.out.println("O botão está ativado.");
	     } else {
	         System.out.println("O botão está desativado.");
	     }
	     return;
	 }

}
