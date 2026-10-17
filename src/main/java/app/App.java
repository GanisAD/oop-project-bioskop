package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Kelas utama aplikasi JavaFX. Memuat tampilan utama dari FXML dan stylesheet,
 * lalu menampilkan jendela aplikasi Cinema XXI.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class App extends Application {
    /**
     * Jendela utama aplikasi.
     */
    private static Stage primaryStage;

    /**
     * Dipanggil JavaFX saat aplikasi dimulai: memuat {@code /fxml/main-view.fxml},
     * memasang {@code /css/style.css}, lalu menampilkan jendela (1150x750, minimum 1000x680).
     *
     * @param stage jendela utama yang disediakan JavaFX
     * @throws IOException jika file FXML gagal dimuat
     */
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

    /**
     * Mengambil jendela utama aplikasi.
     *
     * @return jendela utama
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Titik masuk aplikasi; meluncurkan aplikasi JavaFX.
     *
     * @param args argumen baris perintah
     */
    public static void main(String[] args) {
        launch(args);
    }
}
