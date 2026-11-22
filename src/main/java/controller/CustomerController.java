package controller;

import app.AppContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.bioskop.Bioskop;
import model.bioskop.Film;
import model.bioskop.Jadwal;
import model.bioskop.Kursi;
import model.tiket.Pemesanan;
import model.user.Pelanggan;

import java.net.URL;
import java.util.*;

/**
 * Controller tampilan pelanggan ({@code customer-view.fxml}).
 * Mengatur tiga layar yang bergantian ditampilkan: katalog film, pemilihan kursi,
 * dan e-tiket (struk) setelah pemesanan berhasil.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class CustomerController implements Initializable {

    // View 1: Katalog
    @FXML private VBox catalogPane;
    @FXML private TextField txtSearchFilm;
    @FXML private VBox movieContainer;

    // View 2: Denah Kursi
    @FXML private VBox seatSelectionPane;
    @FXML private Button btnBackToCatalog;
    @FXML private Label lblSelectedMovieTitle;
    @FXML private Label lblSelectedStudio;
    @FXML private Label lblSelectedTime;
    @FXML private Label lblSelectedBasePrice;
    @FXML private GridPane gridKursi;
    @FXML private Label lblKursiTerpilih;
    @FXML private Label lblStatusKursiBadge;
    @FXML private ToggleGroup tipeTiketGroup;
    @FXML private RadioButton rbTiketReguler;
    @FXML private RadioButton rbTiketVIP;
    @FXML private TextField txtNamaPelanggan;
    @FXML private TextField txtNoHpPelanggan;
    @FXML private Label lblTotalHarga;
    @FXML private Button btnKonfirmasiPesan;

    // View 3: E-Tiket Struk
    @FXML private VBox receiptPane;
    @FXML private Label lblReceiptKodeBooking;
    @FXML private Label lblReceiptFilm;
    @FXML private Label lblReceiptStudio;
    @FXML private Label lblReceiptWaktu;
    @FXML private Label lblReceiptKursi;
    @FXML private Label lblReceiptTipe;
    @FXML private Label lblReceiptNama;
    @FXML private Label lblReceiptNoHp;
    @FXML private Label lblReceiptTotal;

    // State data
    private Jadwal currentJadwal;
    private Kursi selectedKursi;
    private Button selectedSeatButton;

    /**
     * Dipanggil JavaFX setelah FXML dimuat: memasang listener pencarian film dan pilihan tipe tiket,
     * lalu menampilkan katalog.
     *
     * @param location  lokasi FXML yang dimuat
     * @param resources resource bundle yang digunakan (boleh {@code null})
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Listener pencarian film
        txtSearchFilm.textProperty().addListener((obs, oldVal, newVal) -> filterAndRenderCatalog(newVal));

        // Listener perubahan tipe tiket
        if (tipeTiketGroup == null) {
            tipeTiketGroup = new ToggleGroup();
            rbTiketReguler.setToggleGroup(tipeTiketGroup);
            rbTiketVIP.setToggleGroup(tipeTiketGroup);
        }
        tipeTiketGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> updateTotalHarga());

        refreshData();
    }

    /**
     * Menyegarkan katalog film sesuai kata kunci pencarian saat ini.
     */
    public void refreshData() {
        filterAndRenderCatalog(txtSearchFilm.getText());
    }

    /**
     * Menampilkan kartu film yang judul atau genrenya cocok dengan kata kunci.
     * Jika tidak ada yang cocok, ditampilkan pesan kosong.
     *
     * @param query kata kunci pencarian; kosong atau {@code null} menampilkan semua film
     */
    private void filterAndRenderCatalog(String query) {
        movieContainer.getChildren().clear();
        Bioskop bioskop = AppContext.getInstance().getBioskop();
        ArrayList<Film> films = bioskop.getDaftarFilm();

        String q = (query == null) ? "" : query.trim().toLowerCase();

        for (Film film : films) {
            if (!q.isEmpty() && !film.getJudul().toLowerCase().contains(q) && !film.getGenre().toLowerCase().contains(q)) {
                continue;
            }

            // Cari jadwal untuk film ini
            List<Jadwal> jadwalList = new ArrayList<>();
            for (Jadwal j : bioskop.getDaftarJadwal()) {
                if (j.getFilm().getJudul().equalsIgnoreCase(film.getJudul())) {
                    jadwalList.add(j);
                }
            }

            movieContainer.getChildren().add(createMovieCard(film, jadwalList));
        }

        if (movieContainer.getChildren().isEmpty()) {
            VBox emptyBox = new VBox(10);
            emptyBox.setAlignment(Pos.CENTER);
            emptyBox.setPadding(new Insets(40));
            Label lblEmpty = new Label("🎬 Tidak ada film yang cocok dengan pencarian");
            lblEmpty.setStyle("-fx-text-fill: #94A3B8; -fx-font-size: 14px;");
            emptyBox.getChildren().add(lblEmpty);
            movieContainer.getChildren().add(emptyBox);
        }
    }

    /**
     * Membuat kartu tampilan satu film: poster, judul, badge rating/genre/durasi,
     * dan tombol jam tayang yang dikelompokkan per studio.
     *
     * @param film       film yang ditampilkan
     * @param jadwalList jadwal tayang milik film tersebut
     * @return kartu film siap dimasukkan ke katalog
     */
    private HBox createMovieCard(Film film, List<Jadwal> jadwalList) {
        HBox card = new HBox(20);
        card.getStyleClass().add("movie-card");
        card.setAlignment(Pos.CENTER_LEFT);

        // Poster Box
        VBox poster = new VBox(6);
        poster.getStyleClass().add("poster-box");
        poster.setPrefSize(110, 140);
        poster.setMinSize(110, 140);
        poster.setMaxSize(110, 140);
        poster.setAlignment(Pos.CENTER);

        Label icon = new Label("🎬");
        icon.setStyle("-fx-font-size: 36px;");
        Label lblPosterTitle = new Label(film.getJudul());
        lblPosterTitle.setWrapText(true);
        lblPosterTitle.setAlignment(Pos.CENTER);
        lblPosterTitle.setStyle("-fx-font-size: 10px; -fx-text-fill: #CBD5E1; -fx-text-alignment: center; -fx-padding: 0 4;");
        poster.getChildren().addAll(icon, lblPosterTitle);

        // Detail Film
        VBox details = new VBox(10);
        HBox.setHgrow(details, Priority.ALWAYS);

        HBox topInfo = new HBox(10);
        topInfo.setAlignment(Pos.CENTER_LEFT);

        Label lblJudul = new Label(film.getJudul());
        lblJudul.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #F8FAFC;");

        Label badgeRating = new Label(film.getRating());
        badgeRating.getStyleClass().addAll("badge-rating", getRatingBadgeClass(film.getRating()));

        Label badgeGenre = new Label(film.getGenre());
        badgeGenre.getStyleClass().add("badge-tag");

        Label badgeDurasi = new Label(film.getDurasi() + " Menit");
        badgeDurasi.getStyleClass().add("badge-tag");

        topInfo.getChildren().addAll(lblJudul, badgeRating, badgeGenre, badgeDurasi);

        // Showtimes Group by Studio
        VBox showtimesSection = new VBox(8);
        Label lblJadwalHeader = new Label("Jadwal Tayang Hari Ini (Klik jam tayang untuk pesan):");
        lblJadwalHeader.setStyle("-fx-font-size: 12px; -fx-text-fill: #94A3B8;");
        showtimesSection.getChildren().add(lblJadwalHeader);

        if (jadwalList.isEmpty()) {
            Label lblNoSchedule = new Label("Belum ada jadwal tayang yang dibuka oleh admin.");
            lblNoSchedule.setStyle("-fx-text-fill: #64748B; -fx-font-size: 12px; -fx-font-style: italic;");
            showtimesSection.getChildren().add(lblNoSchedule);
        } else {
            // Kelompokkan berdasarkan nama studio
            Map<String, List<Jadwal>> byStudio = new LinkedHashMap<>();
            for (Jadwal j : jadwalList) {
                byStudio.computeIfAbsent(j.getStudio().getNama(), k -> new ArrayList<>()).add(j);
            }

            for (Map.Entry<String, List<Jadwal>> entry : byStudio.entrySet()) {
                HBox rowStudio = new HBox(12);
                rowStudio.setAlignment(Pos.CENTER_LEFT);

                Label lblStudioName = new Label(entry.getKey() + ":");
                lblStudioName.setPrefWidth(120);
                lblStudioName.setStyle("-fx-font-weight: bold; -fx-text-fill: #E2E8F0; -fx-font-size: 12px;");
                rowStudio.getChildren().add(lblStudioName);

                HBox chips = new HBox(8);
                for (Jadwal j : entry.getValue()) {
                    Button chip = new Button(j.getWaktuFormat().substring(11) + " (Rp" + String.format("%,.0f", j.getHargaDasar()) + ")");
                    chip.getStyleClass().add("chip-showtime");
                    chip.setOnAction(e -> openSeatSelection(j));
                    chips.getChildren().add(chip);
                }
                rowStudio.getChildren().add(chips);
                showtimesSection.getChildren().add(rowStudio);
            }
        }

        details.getChildren().addAll(topInfo, showtimesSection);
        card.getChildren().addAll(poster, details);
        return card;
    }

    /**
     * Membuka layar pemilihan kursi untuk sebuah jadwal: mengisi informasi jadwal,
     * mengosongkan form, dan membangun denah kursi.
     *
     * @param jadwal jadwal yang dipilih pelanggan
     */
    private void openSeatSelection(Jadwal jadwal) {
        this.currentJadwal = jadwal;
        this.selectedKursi = null;
        this.selectedSeatButton = null;

        // Populate Header Data
        lblSelectedMovieTitle.setText(jadwal.getFilm().getJudul().toUpperCase());
        lblSelectedStudio.setText(jadwal.getStudio().getNama());
        lblSelectedTime.setText("Tayang: " + jadwal.getWaktuFormat());
        lblSelectedBasePrice.setText(String.format("Rp %,.0f / tiket", jadwal.getHargaDasar()));

        lblKursiTerpilih.setText("(Belum dipilih)");
        lblStatusKursiBadge.setText("PILIH 1 KURSI");
        lblStatusKursiBadge.setStyle("-fx-background-color: #334155; -fx-text-fill: #CBD5E1;");

        txtNamaPelanggan.clear();
        txtNoHpPelanggan.clear();
        rbTiketReguler.setSelected(true);
        updateTotalHarga();

        // Bangun Denah Kursi Dinamis
        renderSeatGrid();

        // Switch View
        catalogPane.setVisible(false);
        receiptPane.setVisible(false);
        seatSelectionPane.setVisible(true);
    }

    /**
     * Membangun denah kursi dari baris A ke bawah dengan lorong di tengah. Kursi terisi dinonaktifkan, kursi tersedia dapat diklik.
     */
    private void renderSeatGrid() {
        gridKursi.getChildren().clear();
        gridKursi.getColumnConstraints().clear();
        gridKursi.getRowConstraints().clear();

        ArrayList<Kursi> kursiList = currentJadwal.getDaftarKursiJadwal();
        int rows = currentJadwal.getStudio().getJumlahBaris();
        int cols = currentJadwal.getStudio().getKursiPerBaris();

        // Kursi diatur dari baris A ke bawah
        for (int r = 0; r < rows; r++) {
            char rowChar = (char) ('A' + r);

            // Label Huruf Baris di sebelah kiri
            Label lblRow = new Label(String.valueOf(rowChar));
            lblRow.getStyleClass().add("seat-row-label");
            gridKursi.add(lblRow, 0, r);

            for (int c = 1; c <= cols; c++) {
                String kode = "" + rowChar + c;
                Kursi kursi = currentJadwal.cariKursi(kode);

                Button btnSeat = new Button(String.valueOf(c));
                btnSeat.getStyleClass().add("seat-btn");

                if (kursi != null && !kursi.isTersedia()) {
                    btnSeat.getStyleClass().add("seat-sold");
                    btnSeat.setDisable(true);
                } else if (kursi != null) {
                    btnSeat.getStyleClass().add("seat-available");
                    btnSeat.setOnAction(e -> onSeatClicked(btnSeat, kursi));
                }

                // Tambahkan lorong di tengah studio jika kolom > 4
                int colIndex = c;
                if (cols > 4 && c > (cols / 2)) {
                    colIndex = c + 1; // Beri ruang spasi di tengah
                }
                gridKursi.add(btnSeat, colIndex, r);
            }

            // Label Huruf Baris di sebelah kanan
            Label lblRowRight = new Label(String.valueOf(rowChar));
            lblRowRight.getStyleClass().add("seat-row-label");
            gridKursi.add(lblRowRight, (cols > 4 ? cols + 2 : cols + 1), r);
        }
    }

    /**
     * Menangani klik pada kursi: memilih kursi baru, atau membatalkan pilihan jika kursi yang sama diklik lagi.
     *
     * @param btn   tombol kursi yang diklik
     * @param kursi kursi yang terkait dengan tombol
     */
    private void onSeatClicked(Button btn, Kursi kursi) {
        // Batalkan seleksi sebelumnya
        if (selectedSeatButton != null && selectedSeatButton != btn) {
            selectedSeatButton.getStyleClass().remove("seat-selected");
            selectedSeatButton.getStyleClass().add("seat-available");
        }

        if (btn == selectedSeatButton) {
            // Klik ulang = batalkan pilihan
            btn.getStyleClass().remove("seat-selected");
            btn.getStyleClass().add("seat-available");
            selectedKursi = null;
            selectedSeatButton = null;
            lblKursiTerpilih.setText("(Belum dipilih)");
            lblStatusKursiBadge.setText("PILIH 1 KURSI");
            lblStatusKursiBadge.setStyle("-fx-background-color: #334155; -fx-text-fill: #CBD5E1;");
        } else {
            // Pilih kursi baru
            btn.getStyleClass().remove("seat-available");
            btn.getStyleClass().add("seat-selected");
            selectedKursi = kursi;
            selectedSeatButton = btn;
            lblKursiTerpilih.setText(kursi.getKode());
            lblStatusKursiBadge.setText("TERPILIH");
            lblStatusKursiBadge.setStyle("-fx-background-color: #F59E0B; -fx-text-fill: #0F172A; -fx-font-weight: bold;");
        }

        updateTotalHarga();
    }

    /**
     * Menghitung ulang total harga: harga dasar jadwal ditambah Rp 25.000 jika tiket VIP dipilih, atau Rp 0 jika belum ada kursi dipilih.
     */
    private void updateTotalHarga() {
        if (currentJadwal == null || selectedKursi == null) {
            lblTotalHarga.setText("Rp 0");
            return;
        }

        double total = currentJadwal.getHargaDasar();
        if (rbTiketVIP.isSelected()) {
            total += 25000; // Fasilitas VIP
        }
        lblTotalHarga.setText(String.format("Rp %,.0f", total));
    }

    /**
     * Menangani tombol kembali: menutup layar kursi dan menampilkan katalog.
     */
    @FXML
    public void handleBackToCatalog() {
        seatSelectionPane.setVisible(false);
        receiptPane.setVisible(false);
        catalogPane.setVisible(true);
        refreshData();
    }

    /**
     * Menangani tombol konfirmasi: memvalidasi kursi, nama, dan nomor HP, membuat pemesanan, lalu menampilkan e-tiket (atau pesan gagal jika kursi sudah terisi).
     */
    @FXML
    public void handleKonfirmasiPemesanan() {
        if (selectedKursi == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Silakan pilih salah satu kursi yang tersedia di denah terlebih dahulu!");
            return;
        }

        String nama = txtNamaPelanggan.getText().trim();
        String noHp = txtNoHpPelanggan.getText().trim();

        if (nama.isEmpty() || noHp.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Harap lengkapi nama dan nomor HP pemesan!");
            return;
        }

        String tipeTiket = rbTiketVIP.isSelected() ? "VIP" : "Reguler";

        // Buat objek Pelanggan dan sesi Pemesanan di bioskop
        Bioskop bioskop = AppContext.getInstance().getBioskop();
        Pelanggan pelanggan = new Pelanggan("CUST-" + (System.currentTimeMillis() % 1000), nama, "-", noHp);
        Pemesanan pesanan = bioskop.buatPemesanan(pelanggan, currentJadwal);

        boolean sukses = pesanan.tambahKursi(selectedKursi.getKode(), tipeTiket);
        if (sukses) {
            showReceipt(pesanan, selectedKursi.getKode(), tipeTiket);
        } else {
            showAlert(Alert.AlertType.ERROR, "Pemesanan Gagal", "Kursi " + selectedKursi.getKode() + " gagal dipesan atau sudah terisi!");
            renderSeatGrid();
        }
    }

    /**
     * Mengisi dan menampilkan layar e-tiket (struk) dari pemesanan yang berhasil.
     *
     * @param pesanan   pemesanan yang berhasil dibuat
     * @param kodeKursi kode kursi yang dipesan
     * @param tipeTiket jenis tiket yang dipilih
     */
    private void showReceipt(Pemesanan pesanan, String kodeKursi, String tipeTiket) {
        lblReceiptKodeBooking.setText(pesanan.getKodeBooking());
        lblReceiptFilm.setText(currentJadwal.getFilm().getJudul());
        lblReceiptStudio.setText(currentJadwal.getStudio().getNama());
        lblReceiptWaktu.setText(currentJadwal.getWaktuFormat());
        lblReceiptKursi.setText(kodeKursi);
        lblReceiptTipe.setText(tipeTiket + (tipeTiket.equalsIgnoreCase("VIP") ? " (Recliner + Snack)" : " (Standar)"));
        lblReceiptNama.setText(pesanan.getPelanggan().getNama());
        lblReceiptNoHp.setText(pesanan.getPelanggan().getNoTelp());
        lblReceiptTotal.setText(String.format("Rp %,.0f", pesanan.getTotalHarga()));

        seatSelectionPane.setVisible(false);
        catalogPane.setVisible(false);
        receiptPane.setVisible(true);
    }

    /**
     * Menangani tombol selesai: menutup e-tiket dan kembali ke katalog.
     */
    @FXML
    public void handleFinishBooking() {
        receiptPane.setVisible(false);
        seatSelectionPane.setVisible(false);
        catalogPane.setVisible(true);
        refreshData();
    }

    /**
     * Menentukan nama kelas CSS badge sesuai rating usia.
     *
     * @param rating rating usia, misalnya {@code "SU"}, {@code "13+"}, {@code "17+"}, atau {@code "21+"}
     * @return nama kelas CSS badge; {@code "badge-rating-default"} jika rating {@code null} atau tidak dikenal
     */
    public static String getRatingBadgeClass(String rating) {
        if (rating == null) return "badge-rating-default";
        String r = rating.trim().toUpperCase();
        if (r.contains("SU")) {
            return "badge-rating-su";
        } else if (r.contains("13")) {
            return "badge-rating-13";
        } else if (r.contains("17")) {
            return "badge-rating-17";
        } else if (r.contains("21")) {
            return "badge-rating-21";
        }
        return "badge-rating-default";
    }

    /**
     * Menampilkan dialog pemberitahuan dan menunggu pengguna menutupnya.
     *
     * @param type    jenis dialog (peringatan, informasi, error, dan sebagainya)
     * @param title   judul dialog
     * @param message isi pesan dialog
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
