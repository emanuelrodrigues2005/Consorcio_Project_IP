package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.models.Cliente;
import javafx.beans.property.SimpleStringProperty;
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
	private TextField nomeAdd;
	
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
	
	private ObservableList<Cliente> pessoas = FXCollections.observableArrayList();

	/*@FXML
	public void onBtAddAction() {
		
		 Cliente novoCliente = new Cliente(nomeAdd.getText(), "12345", "99999", "novo@cliente.com");

	     pessoas.add(novoCliente);

	     nMembros.setText(String.valueOf(pessoas.size()));
	}*/
	
	@FXML
	public void onBtRemoveAction() {
		//por enquanto só ta removendo o último da lista
		if (!pessoas.isEmpty()) {
            pessoas.remove(pessoas.size() - 1); 
            nMembros.setText(String.valueOf(pessoas.size())); 
        }
	}
	/*
	@FXML
	public void onBtEndGAction() {
		
	}*/
	
	 @FXML
	 public void initialize() {
		    
		 tabela.setItems(pessoas);
		 
	     tableColumnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
	     
	     nMembros.setText(String.valueOf(pessoas.size()));
	    }
	 
	 @FXML
	 public void toggleButtonHandler() {
	     if (statusG.isSelected()) {
	         System.out.println("O Grupo está ativo!");
	     } else {
	         System.out.println("O grupo está desativado!");
	     }
	 }

}
