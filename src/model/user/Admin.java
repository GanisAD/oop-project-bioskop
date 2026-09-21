package model.user;

import model.bioskop.Bioskop;
import model.bioskop.Film;
import model.bioskop.Jadwal;
import model.bioskop.Studio;

public class Admin extends Person {

    public Admin(String id, String nama) {
        super(id, nama);
    }

    // Admin menambahkan film ke sistem bioskop
    public void tambahFilm(Bioskop bioskop, Film film) {
        bioskop.tambahFilm(film);
        System.out.println("[ADMIN: " + getNama() + "] Sukses mendaftarkan film: " + film.getJudul());
    }

    // Admin menghapus film dari sistem bioskop
    public void hapusFilm(Bioskop bioskop, Film film) {
        boolean terhapus = bioskop.hapusFilm(film);
        if (terhapus) {
            System.out.println("[ADMIN: " + getNama() + "] Sukses menghapus film: " + film.getJudul());
        } else {
            System.out.println("[ADMIN: " + getNama() + "] Gagal menghapus: Film tidak ditemukan.");
        }
    }

    // Admin menambahkan studio baru (kursi otomatis dibuat di dalam studio)
    public void tambahStudio(Bioskop bioskop, Studio studio) {
        bioskop.tambahStudio(studio);
        System.out.println("[ADMIN: " + getNama() + "] Sukses menambahkan studio: " + studio.getNama() 
                           + " (" + studio.getKapasitas() + " kursi)");
    }

    // Admin menambahkan jadwal penayangan baru
    public void tambahJadwal(Bioskop bioskop, Jadwal jadwal) {
        bioskop.tambahJadwal(jadwal);
        System.out.println("[ADMIN: " + getNama() + "] Sukses membuka jadwal baru: " 
                           + jadwal.getFilm().getJudul() + " di " + jadwal.getStudio().getNama());
    }

    // Admin menginspeksi seluruh laporan transaksi
    public void lihatTransaksi(Bioskop bioskop) {
        System.out.println("[ADMIN: " + getNama() + "] Mengakses rekapitulasi transaksi...");
        bioskop.tampilkanRiwayatTransaksi();
    }
}