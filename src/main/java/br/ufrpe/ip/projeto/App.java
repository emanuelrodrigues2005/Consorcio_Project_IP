package br.ufrpe.ip.projeto;

import br.ufrpe.ip.projeto.gui.Gerenciador;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private static final Gerenciador gerenciador = Gerenciador.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        gerenciador.setStagePrincipal(stage);
    }
}

