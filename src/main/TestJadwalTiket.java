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
        Film film = new Film("Petualangan Si Garuda", "Petualangan", 120, "13+");
        Studio studio = new Studio("Studio 1", 5, 8);
        Jadwal jadwal = new Jadwal(film, studio, LocalDateTime.of(2026, 9, 25, 19, 30), 50000);

        studio.tampilkanDenah();
        System.out.println();
        jadwal.tampilkanInfo();
        System.out.println();

        ArrayList<Tiket> daftarTiket = new ArrayList<>();

        // Pembelian 1: A1, reguler
        Kursi a1 = studio.cariKursi("A1");
        if (a1 != null && jadwal.pesanKursi(a1)) {
            daftarTiket.add(new TiketReguler(jadwal, a1));
        }

        // Pembelian 2: B5, VIP
        Kursi b5 = studio.cariKursi("B5");
        if (b5 != null && jadwal.pesanKursi(b5)) {
            daftarTiket.add(new TiketVIP(jadwal, b5));
        }

        // Pembelian 3: A1 lagi (harus GAGAL karena sudah terjual)
        Kursi a1Lagi = studio.cariKursi("A1");
        if (a1Lagi != null && jadwal.pesanKursi(a1Lagi)) {
            daftarTiket.add(new TiketReguler(jadwal, a1Lagi));
        } else {
            System.out.println("Kursi A1 sudah terjual, pilih kursi lain!\n");
        }

        // Pembelian 4: kursi yang tidak ada di studio
        if (studio.cariKursi("Z9") == null) {
            System.out.println("Kursi Z9 tidak ada di " + studio.getNama() + "!\n");
        }

        double total = 0;
        for (Tiket t : daftarTiket) {
            t.cetakTiket();
            System.out.println();
            total += t.hitungHarga();
        }
        System.out.println("Total transaksi : Rp" + String.format("%,.0f", total));
    }
}