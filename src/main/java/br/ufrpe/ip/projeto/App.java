package br.ufrpe.ip.projeto;

import br.ufrpe.ip.projeto.gui.Gerenciador;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Gerenciador gerenciador = Gerenciador.getInstance();
        gerenciador.setStagePrincipal(stage);
        gerenciador.iniciarTelas();
    }

    public static void main(String[] args) {
        launch();
    }
}
