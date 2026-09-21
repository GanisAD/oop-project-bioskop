package model.bioskop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import model.tiket.Tiket;
import model.tiket.TiketReguler;
import model.tiket.TiketVIP;

public class Bioskop {
    private String nama;
    private ArrayList<Film> daftarFilm;
    private ArrayList<Studio> daftarStudio;
    private ArrayList<Jadwal> daftarJadwal;
    private ArrayList<Tiket> daftarTiketTerjual;

    // SATU-SATUNYA KONSTRUKTOR YANG DIPANGGIL DI MAIN
    public Bioskop(String nama) {
        this.nama = nama;
        this.daftarFilm = new ArrayList<>();
        this.daftarStudio = new ArrayList<>();
        this.daftarJadwal = new ArrayList<>();
        this.daftarTiketTerjual = new ArrayList<>();

        // Otomatis membuat Film, Studio (kursi otomatis dibuat di studio), dan Jadwal
        inisialisasiDataDefault();
    }

    // Inisialisasi otomatis seluruh komponen bioskop saat objek bioskop di-construct
    private void inisialisasiDataDefault() {
        // 1. Otomatis buat Studio (Kursi otomatis digenerate oleh konstruktor Studio)
        Studio studio1 = new Studio("Studio 1", 5, 8); // 40 kursi (A1..E8)
        Studio studio2 = new Studio("Studio Premiere", 3, 6); // 18 kursi (A1..C6)
        daftarStudio.add(studio1);
        daftarStudio.add(studio2);

        // 2. Otomatis buat Film
        Film film1 = new Film("Petualangan Si Garuda", "Petualangan", 120, "13+");
        Film film2 = new Film("Avatar: The Way of Water", "Sci-Fi", 192, "13+");
        daftarFilm.add(film1);
        daftarFilm.add(film2);

        // 3. Otomatis buat Jadwal tayang yang mengaitkan film dan studio
        LocalDateTime waktu1 = LocalDateTime.of(2026, 9, 25, 14, 0);
        LocalDateTime waktu2 = LocalDateTime.of(2026, 9, 25, 19, 30);

        daftarJadwal.add(new Jadwal(film1, studio1, waktu1, 45000));
        daftarJadwal.add(new Jadwal(film2, studio1, waktu2, 50000));
        daftarJadwal.add(new Jadwal(film2, studio2, waktu2, 75000));
    }

    // ===== PENCARIAN & GETTER =====
    public String getNama() { return nama; }
    public ArrayList<Film> getDaftarFilm() { return daftarFilm; }
    public ArrayList<Studio> getDaftarStudio() { return daftarStudio; }
    public ArrayList<Jadwal> getDaftarJadwal() { return daftarJadwal; }

    public Jadwal cariJadwal(int index) {
        if (index >= 0 && index < daftarJadwal.size()) {
            return daftarJadwal.get(index);
        }
        return null;
    }

    // ===== FITUR PEMESANAN MELALUI BIOSKOP =====
    // Pengguna cukup memanggil method ini tanpa perlu repot mengurus new Tiket di luar
    public Tiket pesanTiket(Jadwal jadwal, String kodeKursi, String jenisTiket) {
        if (jadwal == null) {
            System.out.println("[GAGAL] Jadwal tidak valid.");
            return null;
        }

        Kursi kursi = jadwal.cariKursi(kodeKursi);
        if (kursi == null) {
            System.out.println("[GAGAL] Kursi " + kodeKursi + " tidak ditemukan di studio " + jadwal.getStudio().getNama());
            return null;
        }

        // Pengecekan ketersediaan kursi (sesuai arahan dosen memanfaatkan atribut di class Kursi)
        if (!kursi.isTersedia() || !kursi.pesan()) {
            System.out.println("[GAGAL] Kursi " + kodeKursi + " sudah terisi untuk jadwal ini!");
            return null;
        }

        // Pembuatan polimorfisme Tiket
        Tiket tiketBaru;
        if ("VIP".equalsIgnoreCase(jenisTiket)) {
            tiketBaru = new TiketVIP(jadwal, kursi);
        } else {
            tiketBaru = new TiketReguler(jadwal, kursi);
        }

        daftarTiketTerjual.add(tiketBaru);
        System.out.println("[BERHASIL] Tiket berhasil diterbitkan untuk kursi " + kodeKursi + " (" + tiketBaru.getJenisTiket() + ")");
        return tiketBaru;
    }

    // ===== FITUR TAMPILAN =====
    public void tampilkanDaftarJadwal() {
        System.out.println("==================================================");
        System.out.println("       DAFTAR JADWAL TAYANG DI " + nama.toUpperCase());
        System.out.println("==================================================");
        for (int i = 0; i < daftarJadwal.size(); i++) {
            Jadwal j = daftarJadwal.get(i);
            System.out.println("[" + i + "] " + j.getFilm().getJudul() + 
                               " | " + j.getStudio().getNama() + 
                               " | Tayang: " + j.getWaktuFormat() + 
                               " | Rp" + String.format("%,.0f", j.getHargaDasar()));
        }
        System.out.println("==================================================");
    }
}