package main;

import model.bioskop.Bioskop;
import model.bioskop.Jadwal;
import model.bioskop.Kursi;
import model.tiket.Pemesanan;
import model.user.Pelanggan;

public class TestPemesanan {
    public static void main(String[] args) {
        // 1. Inisialisasi Bioskop (data film, studio, dan jadwal otomatis dibuat)
        Bioskop xxi = new Bioskop("Cinema XXI Surabaya");

        // 2. Inisialisasi Pelanggan
        Pelanggan pelanggan1 = new Pelanggan("CUST-001", "Ganis", "ganis@email.com", "08123456789");
        Pelanggan pelanggan2 = new Pelanggan("CUST-002", "Ahmad", "ahmad@email.com", "08987654321");

        // 3. Memilih Jadwal Tayang (misal jadwal index 1: Avatar di Studio 1)
        Jadwal jadwalDipilih = xxi.cariJadwal(1);
        if (jadwalDipilih == null) {
            System.out.println("Jadwal tidak ditemukan!");
            return;
        }

        System.out.println("=== INFORMASI JADWAL TAYANG ===");
        jadwalDipilih.tampilkanInfo();
        System.out.println();

        // 4. Denah Sebelum Ada Transaksi
        System.out.println("--- Denah Kursi Sebelum Pemesanan ---");
        jadwalDipilih.tampilkanDenahKursi();
        System.out.println();

        // ===== TRANSAKSI 1: Pelanggan 1 (Ganis) Memesan Kursi A1 & B5 =====
        System.out.println("=== PROSES PEMESANAN: PELANGGAN 1 ===");
        Pemesanan pesananGanis = xxi.buatPemesanan(pelanggan1, jadwalDipilih);

        Kursi kursiA1 = jadwalDipilih.cariKursi("A1");
        Kursi kursiB5 = jadwalDipilih.cariKursi("B5");

        pesananGanis.tambahKursi(kursiA1, "Reguler");
        pesananGanis.tambahKursi(kursiB5, "VIP");
        System.out.println();

        // Cetak Struk Transaksi 1
        pesananGanis.cetakStruk();
        System.out.println();

        // ===== TRANSAKSI 2: Pelanggan 2 (Ahmad) Mencoba Memesan Kursi yang Sama =====
        System.out.println("=== PROSES PEMESANAN: PELANGGAN 2 (UJI VALIDASI) ===");
        Pemesanan pesananAhmad = xxi.buatPemesanan(pelanggan2, jadwalDipilih);

        // A. Coba pesan A1 lagi (Harus GAGAL karena status boolean kursi A1 sudah false)
        System.out.print("Percobaan 1 (Pesan A1 lagi): ");
        pesananAhmad.tambahKursi(jadwalDipilih.cariKursi("A1"), "Reguler");

        // B. Coba pesan kursi yang tidak ada di denah studio
        System.out.print("Percobaan 2 (Pesan Z9): ");
        pesananAhmad.tambahKursi(jadwalDipilih.cariKursi("Z9"), "Reguler");

        // C. Pesan kursi yang benar-benar masih kosong (B1)
        System.out.print("Percobaan 3 (Pesan B1): ");
        pesananAhmad.tambahKursi(jadwalDipilih.cariKursi("B1"), "VIP");
        System.out.println();

        // Cetak Struk Transaksi 2
        pesananAhmad.cetakStruk();
        System.out.println();

        // 5. Denah Setelah Pemesanan (A1, B5, dan B1 bertanda [X])
        System.out.println("--- Denah Kursi Setelah Seluruh Pemesanan ---");
        jadwalDipilih.tampilkanDenahKursi();
        System.out.println();

        // 6. Verifikasi Riwayat Seluruh Transaksi yang Dicatat Bioskop
        xxi.tampilkanRiwayatTransaksi();
    }
}