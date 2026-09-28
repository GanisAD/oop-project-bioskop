package main;

import model.bioskop.Bioskop;
import model.bioskop.Jadwal;
import model.tiket.Pemesanan;
import model.user.Pelanggan;

public class TestPemesanan {
    public static void main(String[] args) {
        // 1. Inisialisasi Bioskop (data film, studio, dan jadwal otomatis dibuat)
        Bioskop xxi = new Bioskop("Cinema XXI Surabaya");

        // 2. Inisialisasi Pelanggan
        Pelanggan pelanggan1 = new Pelanggan("CUST-001", "Ganis", "ganis@email.com", "08123456789");
        Pelanggan pelanggan2 = new Pelanggan("CUST-002", "Ahmad", "ahmad@email.com", "08987654321");

        // 3. Memilih Jadwal Tayang (misal jadwal indeks 1: Avatar di Studio 1)
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

        // Input langsung berupa teks kode kursi (simulasi klik tombol denah di antarmuka)
        pesananGanis.tambahKursi("A1", "Reguler");
        pesananGanis.tambahKursi("B5", "VIP");
        System.out.println();

        // Cetak Struk Transaksi 1
        pesananGanis.cetakStruk();
        System.out.println();

        // ===== TRANSAKSI 2: Pelanggan 2 (Ahmad) Menguji Validasi Pemesanan =====
        System.out.println("=== PROSES PEMESANAN: PELANGGAN 2 (UJI VALIDASI) ===");
        Pemesanan pesananAhmad = xxi.buatPemesanan(pelanggan2, jadwalDipilih);

        // Percobaan 1: Pesan kursi A1 yang sudah terisi (Harus GAGAL)
        System.out.print("Percobaan 1 (Pesan A1 lagi): ");
        pesananAhmad.tambahKursi("A1", "Reguler");

        // Percobaan 2: Pesan kursi yang tidak terdaftar di denah (Harus GAGAL)
        System.out.print("Percobaan 2 (Pesan Z9): ");
        pesananAhmad.tambahKursi("Z9", "Reguler");

        // Percobaan 3: Pesan kursi B1 yang masih kosong (Harus BERHASIL)
        System.out.print("Percobaan 3 (Pesan B1): ");
        pesananAhmad.tambahKursi("B1", "VIP");
        System.out.println();

        // Cetak Struk Transaksi 2
        pesananAhmad.cetakStruk();
        System.out.println();

        // 5. Denah Setelah Pemesanan (Kursi A1, B5, dan B1 kini berstatus [X])
        System.out.println("--- Denah Kursi Setelah Seluruh Pemesanan ---");
        jadwalDipilih.tampilkanDenahKursi();
        System.out.println();

        // 6. Verifikasi Rekap Seluruh Transaksi di Bioskop
        xxi.tampilkanRiwayatTransaksi();
    }
}