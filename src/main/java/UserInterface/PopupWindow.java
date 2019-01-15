package UserInterface;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupWindow {
    public static void displayError(String title, String message) {
        final Stage stage = new Stage();
        // u must take care about popup before doing enything on main box
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        Label label = new Label(message);
        Button button = new Button("Okey");
        button.setOnAction(e -> stage.close());
        VBox layout = new VBox();
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait(); // displayError and before u go back it needs to be closed;

    }

    static String result;

    public static String getInputCode(String title) {

        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(400);
        Label label = new Label("Type input code here: ");
        TextArea textArea = new TextArea();

        Button button = new Button("Okey");
        button.setOnAction(e -> {
            result = textArea.getText();
            stage.close();
        });
        VBox layout = new VBox();
        layout.getChildren().addAll(label, textArea, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait(); // displayError and before u go back it needs to be closed;
        return result;
    }
}
