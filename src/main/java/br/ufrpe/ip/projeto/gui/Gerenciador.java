package br.ufrpe.ip.projeto.gui;

import br.ufrpe.ip.projeto.gui.views.TelaVisuGrupoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Gerenciador {
    private static Gerenciador instance;

    private Parent telaVisuGrupo;

    private Stage stagePrincipal;

    private TelaVisuGrupoController telaVisuGrupoController;

    private Gerenciador() {
        try{
                FXMLLoader loaderTelaVisuGrupo = new FXMLLoader(getClass().getResource(TelasEnum.TELA_VISUALIZACAO_GRUPO.getCaminho()));
                telaVisuGrupo = loaderTelaVisuGrupo.load();
                telaVisuGrupoController = loaderTelaVisuGrupo.getController();
                telaVisuGrupoController.setScreenManager(this);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static Gerenciador getInstance() {
        if (instance == null) {
            instance = new Gerenciador();
        }
        return instance;
    }

    public void setStagePrincipal(Stage stage) {
        stagePrincipal = stage;
    }
}
