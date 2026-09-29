package controller;

import app.AppContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bioskop.Bioskop;
import model.bioskop.Film;
import model.bioskop.Jadwal;
import model.bioskop.Studio;
import model.tiket.Pemesanan;
import model.user.Admin;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    // Metric Summary Labels
    @FXML private Label lblStatPendapatan;
    @FXML private Label lblStatTotalPemesanan;
    @FXML private Label lblStatTotalFilm;
    @FXML private Label lblStatTotalStudio;

    // Tab 1: Film
    @FXML private TableView<Film> tblFilm;
    @FXML private TableColumn<Film, String> colFilmJudul;
    @FXML private TableColumn<Film, String> colFilmGenre;
    @FXML private TableColumn<Film, Integer> colFilmDurasi;
    @FXML private TableColumn<Film, String> colFilmRating;
    @FXML private TextField txtFilmJudul;
    @FXML private ComboBox<String> cbFilmGenre;
    @FXML private TextField txtFilmDurasi;
    @FXML private ComboBox<String> cbFilmRating;
    @FXML private Button btnHapusFilm;

    // Tab 2: Jadwal
    @FXML private TableView<Jadwal> tblJadwal;
    @FXML private TableColumn<Jadwal, String> colJadwalFilm;
    @FXML private TableColumn<Jadwal, String> colJadwalStudio;
    @FXML private TableColumn<Jadwal, String> colJadwalWaktu;
    @FXML private TableColumn<Jadwal, String> colJadwalHarga;
    @FXML private TableColumn<Jadwal, String> colJadwalKursi;
    @FXML private ComboBox<Film> cbJadwalFilm;
    @FXML private ComboBox<Studio> cbJadwalStudio;
    @FXML private DatePicker dpJadwalTanggal;
    @FXML private ComboBox<String> cbJadwalJam;
    @FXML private TextField txtJadwalHarga;

    // Tab 3: Transaksi
    @FXML private TextField txtSearchTransaksi;
    @FXML private TableView<Pemesanan> tblTransaksi;
    @FXML private TableColumn<Pemesanan, String> colTrxKode;
    @FXML private TableColumn<Pemesanan, String> colTrxPelanggan;
    @FXML private TableColumn<Pemesanan, String> colTrxNoHp;
    @FXML private TableColumn<Pemesanan, String> colTrxFilm;
    @FXML private TableColumn<Pemesanan, String> colTrxStudio;
    @FXML private TableColumn<Pemesanan, String> colTrxKursi;
    @FXML private TableColumn<Pemesanan, String> colTrxTipe;
    @FXML private TableColumn<Pemesanan, String> colTrxTotal;
    @FXML private TableColumn<Pemesanan, String> colTrxWaktu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        setupFormOptions();

        // Search transaksi listener
        txtSearchTransaksi.textProperty().addListener((obs, oldVal, newVal) -> filterTransactions(newVal));

        refreshData();
    }

    private void setupTableColumns() {
        // Kolom Film
        colFilmJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colFilmGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colFilmDurasi.setCellValueFactory(new PropertyValueFactory<>("durasi"));
        colFilmRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        // Kolom Jadwal
        colJadwalFilm.setCellValueFactory(new PropertyValueFactory<>("judulFilm"));
        colJadwalStudio.setCellValueFactory(new PropertyValueFactory<>("namaStudio"));
        colJadwalWaktu.setCellValueFactory(new PropertyValueFactory<>("waktuFormat"));
        colJadwalHarga.setCellValueFactory(new PropertyValueFactory<>("hargaDasarFormat"));
        colJadwalKursi.setCellValueFactory(new PropertyValueFactory<>("statusKeterisian"));

        // Kolom Transaksi
        colTrxKode.setCellValueFactory(new PropertyValueFactory<>("kodeBooking"));
        colTrxPelanggan.setCellValueFactory(new PropertyValueFactory<>("namaPelanggan"));
        colTrxNoHp.setCellValueFactory(new PropertyValueFactory<>("noTelpPelanggan"));
        colTrxFilm.setCellValueFactory(new PropertyValueFactory<>("judulFilm"));
        colTrxStudio.setCellValueFactory(new PropertyValueFactory<>("namaStudio"));
        colTrxKursi.setCellValueFactory(new PropertyValueFactory<>("kodeKursiList"));
        colTrxTipe.setCellValueFactory(new PropertyValueFactory<>("jenisTiketList"));
        colTrxTotal.setCellValueFactory(new PropertyValueFactory<>("totalHargaFormat"));
        colTrxWaktu.setCellValueFactory(new PropertyValueFactory<>("waktuFormat"));
    }

    private void setupFormOptions() {
        cbFilmGenre.setItems(FXCollections.observableArrayList(
                "Action", "Sci-Fi", "Petualangan", "Horor", "Komedi", "Drama", "Animasi", "Thriller"
        ));
        cbFilmRating.setItems(FXCollections.observableArrayList(
                "SU", "13+", "17+", "21+"
        ));

        cbJadwalJam.setItems(FXCollections.observableArrayList(
                "10:00", "12:30", "14:00", "16:30", "19:00", "19:30", "21:30"
        ));
        cbJadwalJam.setValue("19:00");
        dpJadwalTanggal.setValue(LocalDate.now());
        txtJadwalHarga.setText("45000");
    }

    public void refreshData() {
        Bioskop bioskop = AppContext.getInstance().getBioskop();

        // 1. Metric Cards
        double totalOmset = 0.0;
        for (Pemesanan p : bioskop.getDaftarPemesanan()) {
            totalOmset += p.getTotalHarga();
        }
        lblStatPendapatan.setText(String.format("Rp %,.0f", totalOmset));
        lblStatTotalPemesanan.setText(String.valueOf(bioskop.getDaftarPemesanan().size()));
        lblStatTotalFilm.setText(String.valueOf(bioskop.getDaftarFilm().size()));
        lblStatTotalStudio.setText(String.valueOf(bioskop.getDaftarStudio().size()));

        // 2. Table Film
        tblFilm.setItems(FXCollections.observableArrayList(bioskop.getDaftarFilm()));

        // 3. Table Jadwal
        tblJadwal.setItems(FXCollections.observableArrayList(bioskop.getDaftarJadwal()));

        // 4. Dropdowns Jadwal
        cbJadwalFilm.setItems(FXCollections.observableArrayList(bioskop.getDaftarFilm()));
        cbJadwalStudio.setItems(FXCollections.observableArrayList(bioskop.getDaftarStudio()));

        // 5. Table Transaksi
        filterTransactions(txtSearchTransaksi.getText());
    }

    private void filterTransactions(String query) {
        Bioskop bioskop = AppContext.getInstance().getBioskop();
        ObservableList<Pemesanan> list = FXCollections.observableArrayList();

        String q = (query == null) ? "" : query.trim().toLowerCase();
        for (Pemesanan p : bioskop.getDaftarPemesanan()) {
            if (q.isEmpty() ||
                p.getKodeBooking().toLowerCase().contains(q) ||
                p.getNamaPelanggan().toLowerCase().contains(q) ||
                p.getJudulFilm().toLowerCase().contains(q)) {
                list.add(p);
            }
        }
        tblTransaksi.setItems(list);
    }

    @FXML
    public void handleTambahFilm() {
        String judul = txtFilmJudul.getText().trim();
        String genre = cbFilmGenre.getValue();
        String durasiStr = txtFilmDurasi.getText().trim();
        String rating = cbFilmRating.getValue();

        if (judul.isEmpty() || genre == null || durasiStr.isEmpty() || rating == null) {
            showAlert(Alert.AlertType.WARNING, "Validasi Gagal", "Harap lengkapi semua kolom informasi film!");
            return;
        }

        int durasi;
        try {
            durasi = Integer.parseInt(durasiStr);
            if (durasi <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Validasi Gagal", "Durasi film harus berupa angka lebih dari 0 menit!");
            return;
        }

        Bioskop bioskop = AppContext.getInstance().getBioskop();
        Admin admin = AppContext.getInstance().getAdmin();

        admin.tambahFilm(bioskop, judul, genre, durasi, rating);

        txtFilmJudul.clear();
        txtFilmDurasi.clear();
        cbFilmGenre.setValue(null);
        cbFilmRating.setValue(null);

        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Film '" + judul + "' berhasil didaftarkan ke sistem bioskop!");
        refreshData();
    }

    @FXML
    public void handleHapusFilm() {
        Film selected = tblFilm.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih film di tabel yang ingin dihapus!");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Konfirmasi Hapus");
        confirm.setHeaderText(null);
        confirm.setContentText("Apakah Anda yakin ingin menghapus film '" + selected.getJudul() + "' beserta jadwalnya?");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Bioskop bioskop = AppContext.getInstance().getBioskop();
            Admin admin = AppContext.getInstance().getAdmin();
            admin.hapusFilm(bioskop, selected.getJudul());
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Film '" + selected.getJudul() + "' berhasil dihapus.");
            refreshData();
        }
    }

    @FXML
    public void handleTambahJadwal() {
        Film film = cbJadwalFilm.getValue();
        Studio studio = cbJadwalStudio.getValue();
        LocalDate tanggal = dpJadwalTanggal.getValue();
        String jamStr = cbJadwalJam.getValue();
        String hargaStr = txtJadwalHarga.getText().trim();

        if (film == null || studio == null || tanggal == null || jamStr == null || hargaStr.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validasi Gagal", "Harap lengkapi seluruh formulir pembukaan jadwal!");
            return;
        }

        double hargaDasar;
        try {
            hargaDasar = Double.parseDouble(hargaStr);
            if (hargaDasar <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Validasi Gagal", "Harga dasar tiket harus berupa angka positif!");
            return;
        }

        LocalTime jam = LocalTime.parse(jamStr);
        LocalDateTime waktuTayang = LocalDateTime.of(tanggal, jam);

        Bioskop bioskop = AppContext.getInstance().getBioskop();
        Admin admin = AppContext.getInstance().getAdmin();

        Jadwal baru = admin.tambahJadwal(bioskop, film.getJudul(), studio.getNama(), waktuTayang, hargaDasar);
        if (baru != null) {
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Jadwal tayang film '" + film.getJudul() + "' di " + studio.getNama() + " berhasil dibuka!");
            refreshData();
        } else {
            showAlert(Alert.AlertType.ERROR, "Gagal", "Tidak dapat membuka jadwal tayang.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
