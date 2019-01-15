import Controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/FXML/mainScreen.fxml"));

        Node root = new VBox();

        fxmlLoader.setRoot(root);
        VBox vBox = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        controller.sendStage(primaryStage);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jar Explorer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
