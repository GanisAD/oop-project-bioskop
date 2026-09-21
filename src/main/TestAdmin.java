package main;

import java.time.LocalDateTime;
import model.bioskop.Bioskop;
import model.bioskop.Film;
import model.bioskop.Jadwal;
import model.bioskop.Studio;
import model.user.Admin;

public class TestAdmin {
    public static void main(String[] args) {
        // 1. Inisialisasi Bioskop dan Admin
        Bioskop xxi = new Bioskop("Cinema XXI Surabaya");
        Admin adminUtama = new Admin("ADM-001", "Budi Hartono");

        System.out.println("=== PROFIL ADMIN ===");
        adminUtama.tampilkanInfo();
        System.out.println();

        System.out.println("--- JADWAL AWAL BIOSKOP ---");
        xxi.tampilkanDaftarJadwal();
        System.out.println();

        // 2. Admin Menambah Studio Baru (Kursi otomatis dibuat di dalam Studio)
        System.out.println("--- AKSI ADMIN: MENAMBAH STUDIO ---");
        Studio studioIMAX = new Studio("Studio IMAX 3D", 4, 6); // 24 kursi (A1..D6)
        adminUtama.tambahStudio(xxi, studioIMAX);
        System.out.println();

        // 3. Admin Menambah Film Baru
        System.out.println("--- AKSI ADMIN: MENAMBAH FILM ---");
        Film filmBaru = new Film("Interstellar", "Sci-Fi", 169, "13+");
        adminUtama.tambahFilm(xxi, filmBaru);
        System.out.println();

        // 4. Admin Membuka Jadwal Baru untuk Film dan Studio Tersebut
        System.out.println("--- AKSI ADMIN: MEMBUAT JADWAL TAYANG ---");
        Jadwal jadwalIMAX = new Jadwal(
            filmBaru,
            studioIMAX,
            LocalDateTime.of(2026, 9, 26, 20, 0),
            100000
        );
        adminUtama.tambahJadwal(xxi, jadwalIMAX);
        System.out.println();

        // 5. Verifikasi Pembaruan Jadwal di Bioskop
        System.out.println("--- JADWAL SETELAH DITAMBAH ADMIN ---");
        xxi.tampilkanDaftarJadwal();
        System.out.println();

        // 6. Admin Menghapus Film
        System.out.println("--- AKSI ADMIN: MENGHAPUS FILM ---");
        adminUtama.hapusFilm(xxi, filmBaru);

        // Tes hapus film yang sudah tidak ada/tidak terdaftar
        Film filmFiktif = new Film("Film Tidak Ada", "Drama", 90, "SU");
        adminUtama.hapusFilm(xxi, filmFiktif);
        System.out.println();

        // 7. Admin Memeriksa Rekapitulasi Transaksi Bioskop
        System.out.println("--- AKSI ADMIN: CEK TRANSAKSI ---");
        adminUtama.lihatTransaksi(xxi);
    }
}