package graphicalUserInterface;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlPanelView {

    @FXML
    private TextField test;

    @FXML
    private ComboBox<String> entityHandler;

    private Scene scene;
    private Stage controlPanelWindow;
    private BorderPane borderPane;

    public ControlPanelView(Stage controlPanelWindow) {
        this.controlPanelWindow = controlPanelWindow;
        assert entityHandler != null;
        initializeEntityHandler();
        buildUI();
    }

    // JavaFX methods

    private void buildUI() {
        borderPane = new BorderPane();
        borderPane.getStyleClass().add("bg-1");
        borderPane.setPadding(new Insets(5));

        scene = new Scene(borderPane, 800, 600);

        initializeStage();

        BorderPane.setAlignment(entityHandler, Pos.TOP_RIGHT);
        borderPane.setRight(entityHandler);
        controlPanelWindow.show();
    }


    public void initializeStage(){
        controlPanelWindow.setTitle("Control Panel");
        controlPanelWindow.setMinHeight(600);
        controlPanelWindow.setMinWidth(400);
        controlPanelWindow.setScene(scene);
    }

    private void initializeEntityHandler(){
        entityHandler = new ComboBox<>();
        entityHandler.getItems().addAll("Aircraft", "Ship");
        entityHandler.getSelectionModel().select("Aircraft");
        entityHandler.setPrefSize(100, 20);
    }

    private static Label createLabel(String text, String styleClass){
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        BorderPane.setMargin(label, new Insets(5));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }


}