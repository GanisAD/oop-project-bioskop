package app;

/**
 * Kelas peluncur aplikasi yang meneruskan ke {@link App#main(String[])}.
 * Dipakai sebagai {@code mainClass} pada konfigurasi javafx-maven-plugin di {@code pom.xml}.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class MainApp {
    /**
     * Titik masuk aplikasi.
     *
     * @param args argumen baris perintah
     */
    public static void main(String[] args) {
        App.main(args);
    }
}
