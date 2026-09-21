package main;

import model.bioskop.Bioskop;
import model.bioskop.Jadwal;
import model.tiket.Tiket;

public class Main {
    public static void main(String[] args) {
        // 1. HANYA INI SATU-SATUNYA OBJECT YANG DICONSTRUCT SECARA MANUAL
        Bioskop xxi = new Bioskop("Cinema XXI Surabaya");

        // 2. Semua fasilitas langsung tersedia dan mengikuti objek Bioskop
        xxi.tampilkanDaftarJadwal();
        System.out.println();

        // 3. Memilih jadwal penayangan dari bioskop (misal jadwal index ke-1: Avatar jam 19:30)
        Jadwal jadwalPilihan = xxi.cariJadwal(1);
        
        System.out.println("--- Denah Kursi Sebelum Pemesanan ---");
        jadwalPilihan.tampilkanDenahKursi();
        System.out.println();

        // 4. Melakukan pemesanan langsung lewat Bioskop
        System.out.println("--- Proses Pemesanan Tiket ---");
        Tiket tiket1 = xxi.pesanTiket(jadwalPilihan, "A1", "Reguler");
        Tiket tiket2 = xxi.pesanTiket(jadwalPilihan, "B5", "VIP");

        // Tes pemesanan kursi A1 lagi (harus otomatis gagal karena boolean pada kursi A1 sudah false)
        Tiket tiketGagal = xxi.pesanTiket(jadwalPilihan, "A1", "Reguler");

        // Tes pemesanan kursi yang di luar denah studio
        Tiket tiketSalah = xxi.pesanTiket(jadwalPilihan, "Z9", "Reguler");
        System.out.println();

        // 5. Cek kembali denah kursi (A1 dan B5 sekarang bertanda [X])
        System.out.println("--- Denah Kursi Setelah Pemesanan ---");
        jadwalPilihan.tampilkanDenahKursi();
        System.out.println();

        // 6. Cetak tiket fisik yang berhasil dipesan
        System.out.println("--- Cetak Fisik Tiket ---");
        if (tiket1 != null) {
            tiket1.cetakTiket();
            System.out.println();
        }
        if (tiket2 != null) {
            tiket2.cetakTiket();
            System.out.println();
        }
    }
}