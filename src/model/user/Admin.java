package model.user;

import java.time.LocalDateTime;
import model.bioskop.Bioskop;
import model.bioskop.Film;
import model.bioskop.Jadwal;
import model.bioskop.Studio;

public class Admin extends Person {

    public Admin(String id, String nama) {
        super(id, nama);
    }

    // Menerima input teks form, Bioskop yang membuat objek Film
    public Film tambahFilm(Bioskop bioskop, String judul, String genre, int durasi, String rating) {
        Film f = bioskop.tambahFilm(judul, genre, durasi, rating);
        System.out.println("[ADMIN: " + getNama() + "] Berhasil mendaftarkan film: " + judul);
        return f;
    }

    public void hapusFilm(Bioskop bioskop, String judulFilm) {
        boolean berhasil = bioskop.hapusFilm(judulFilm);
        if (berhasil) {
            System.out.println("[ADMIN: " + getNama() + "] Berhasil menghapus film: " + judulFilm);
        } else {
            System.out.println("[ADMIN: " + getNama() + "] Gagal menghapus: Film '" + judulFilm + "' tidak ditemukan.");
        }
    }

    // Menerima input teks & angka form, Bioskop yang membuat objek Studio dan Kursi
    public Studio tambahStudio(Bioskop bioskop, String namaStudio, int jumlahBaris, int kursiPerBaris) {
        Studio s = bioskop.tambahStudio(namaStudio, jumlahBaris, kursiPerBaris);
        System.out.println("[ADMIN: " + getNama() + "] Berhasil menambahkan studio: " + namaStudio 
                           + " (" + s.getKapasitas() + " kursi)");
        return s;
    }

    // Menerima pilihan dropdown dan waktu, Bioskop yang memetakan relasi objeknya
    public Jadwal tambahJadwal(Bioskop bioskop, String judulFilm, String namaStudio, LocalDateTime waktuTayang, double hargaDasar) {
        Jadwal j = bioskop.tambahJadwal(judulFilm, namaStudio, waktuTayang, hargaDasar);
        if (j != null) {
            System.out.println("[ADMIN: " + getNama() + "] Berhasil membuka jadwal: " + judulFilm + " di " + namaStudio);
        }
        return j;
    }

    public void lihatTransaksi(Bioskop bioskop) {
        System.out.println("[ADMIN: " + getNama() + "] Membuka rekapitulasi transaksi...");
        bioskop.tampilkanRiwayatTransaksi();
    }
}