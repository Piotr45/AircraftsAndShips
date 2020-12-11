package graphicalUserInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Main extends Application {

    private int maxMapHeight = 1080;
    private int maxMapWidth = 1920;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        ControlPanel controlPanel = new ControlPanel();

        ControlPanelView controlPanelView = new ControlPanelView(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
