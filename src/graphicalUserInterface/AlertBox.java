package graphicalUserInterface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * AlertBox is used to notify the user of an error.
 */
public class AlertBox {

    /**
     * This function creates and displays alert box with information about an error.
     * @param title - title of window.
     * @param message - message we want to display.
     */
    public static void displayAlertBoxWindow(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        VBox layout = new VBox();
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

}
