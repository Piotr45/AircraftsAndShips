package graphicalUserInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Main extends Application {

    int maxMapHeight = 1080;
    int maxMapWidth = 1920;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        ControlPanel controlPanel = new ControlPanel();
//        Button button = new Button("Button");
//        stackPane.getChildren().add(button);

        Scene scene = new Scene(controlPanel.createControlPane(), 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Control Panel");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
