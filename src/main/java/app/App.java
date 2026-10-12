package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1150, 750);
        String css = getClass().getResource("/css/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Cinema XXI - Sistem Pemesanan Tiket Bioskop");
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(680);
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
