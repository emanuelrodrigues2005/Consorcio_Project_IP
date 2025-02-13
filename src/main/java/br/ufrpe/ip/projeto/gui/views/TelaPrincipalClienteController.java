package br.ufrpe.ip.projeto.gui.views;

import br.ufrpe.ip.projeto.controllers.ConsorcioFachada;
import br.ufrpe.ip.projeto.controllers.IConsorcio;
import br.ufrpe.ip.projeto.gui.Gerenciador;
import javafx.fxml.FXML;

import javax.swing.text.TableView;
import java.awt.*;

public class TelaPrincipalClienteController {
    private final IConsorcio sistema = ConsorcioFachada.getInstance();
    private Gerenciador screenManager;

    @FXML
    Button lbHomeCliente;

    @FXML
    Button lbPerfilCliente;

    @FXML
    TableView tbvGrupos;

    @FXML
    public void handleTelaPerfilCliente() {
        this.screenManager.abrirPerfilCliente(this.sistema.getClienteLogado());
    }
}
