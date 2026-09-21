package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import model.bioskop.Film;
import model.bioskop.Jadwal;
import model.bioskop.Kursi;
import model.bioskop.Studio;
import model.tiket.Tiket;
import model.tiket.TiketReguler;
import model.tiket.TiketVIP;

public class TestJadwalTiket {
    public static void main(String[] args) {
        // 1. Inisialisasi Data Bioskop
        Film film = new Film("Petualangan Si Garuda", "Petualangan", 120, "13+");
        Studio studio = new Studio("Studio 1", 5, 8);
        Jadwal jadwal = new Jadwal(film, studio, LocalDateTime.of(2026, 9, 25, 19, 30), 50000);

        System.out.println("=== INFORMASI JADWAL TAYANG ===");
        jadwal.tampilkanInfo();
        System.out.println();

        // 2. Tampilkan Denah Sebelum Ada Kursi yang Dipesan
        System.out.println("--- Denah Kursi Sebelum Pemesanan ---");
        jadwal.tampilkanDenahKursi();
        System.out.println();

        ArrayList<Tiket> daftarTiket = new ArrayList<>();

        // ===== PEMBELIAN 1: Kursi A1 (Reguler) =====
        Kursi a1 = jadwal.cariKursi("A1");
        if (a1 != null) {
            // Mengecek boolean isTersedia() langsung dari objek Kursi
            if (a1.isTersedia() && a1.pesan()) {
                daftarTiket.add(new TiketReguler(jadwal, a1));
                System.out.println("[BERHASIL] Kursi " + a1.getKode() + " berhasil dipesan (Reguler).");
            } else {
                System.out.println("[GAGAL] Kursi " + a1.getKode() + " sudah tidak tersedia!");
            }
        }

        // ===== PEMBELIAN 2: Kursi B5 (VIP) =====
        Kursi b5 = jadwal.cariKursi("B5");
        if (b5 != null) {
            // Mengecek boolean isTersedia() langsung dari objek Kursi
            if (b5.isTersedia() && b5.pesan()) {
                daftarTiket.add(new TiketVIP(jadwal, b5));
                System.out.println("[BERHASIL] Kursi " + b5.getKode() + " berhasil dipesan (VIP).");
            } else {
                System.out.println("[GAGAL] Kursi " + b5.getKode() + " sudah tidak tersedia!");
            }
        }

        // ===== PEMBELIAN 3: Kursi A1 lagi (Harus GAGAL karena isTersedia bernilai false) =====
        Kursi a1Lagi = jadwal.cariKursi("A1");
        if (a1Lagi != null) {
            if (a1Lagi.isTersedia() && a1Lagi.pesan()) {
                daftarTiket.add(new TiketReguler(jadwal, a1Lagi));
            } else {
                System.out.println("[GAGAL] Kursi " + a1Lagi.getKode() + " sudah terjual! (Status tersedia: " + a1Lagi.isTersedia() + ")");
            }
        }
        System.out.println();

        // ===== PEMBELIAN 4: Kursi yang tidak terdaftar di denah =====
        if (jadwal.cariKursi("Z9") == null) {
            System.out.println("[INFO] Kursi Z9 tidak ada di " + studio.getNama() + "!\n");
        }

        // 3. Tampilkan Denah Setelah Pemesanan (A1 dan B5 akan bertanda [X])
        System.out.println("--- Denah Kursi Setelah Pemesanan ---");
        jadwal.tampilkanDenahKursi();
        System.out.println();

        // 4. Cetak Rincian Tiket dan Total Biaya
        double total = 0;
        for (Tiket t : daftarTiket) {
            t.cetakTiket();
            System.out.println();
            total += t.hitungHarga();
        }

        System.out.println("Total transaksi : Rp" + String.format("%,.0f", total));
    }
}