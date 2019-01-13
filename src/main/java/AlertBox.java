package main.java;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class AlertBox {
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


    public String getInputCode() {
        return null;
    }
}
