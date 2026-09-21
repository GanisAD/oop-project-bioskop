package model.bioskop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import model.tiket.Pemesanan;
import model.user.Pelanggan;

public class Bioskop {
    private String nama;
    private ArrayList<Film> daftarFilm;
    private ArrayList<Studio> daftarStudio;
    private ArrayList<Jadwal> daftarJadwal;
    private ArrayList<Pemesanan> daftarPemesanan;

    public Bioskop(String nama) {
        this.nama = nama;
        this.daftarFilm = new ArrayList<>();
        this.daftarStudio = new ArrayList<>();
        this.daftarJadwal = new ArrayList<>();
        this.daftarPemesanan = new ArrayList<>();

        inisialisasiDataDefault();
    }

    private void inisialisasiDataDefault() {
        Studio studio1 = new Studio("Studio 1", 5, 8);
        Studio studio2 = new Studio("Studio Premiere", 3, 6);
        daftarStudio.add(studio1);
        daftarStudio.add(studio2);

        Film film1 = new Film("Petualangan Si Garuda", "Petualangan", 120, "13+");
        Film film2 = new Film("Avatar: The Way of Water", "Sci-Fi", 192, "13+");
        daftarFilm.add(film1);
        daftarFilm.add(film2);

        LocalDateTime waktu1 = LocalDateTime.of(2026, 9, 25, 14, 0);
        LocalDateTime waktu2 = LocalDateTime.of(2026, 9, 25, 19, 30);
        daftarJadwal.add(new Jadwal(film1, studio1, waktu1, 45000));
        daftarJadwal.add(new Jadwal(film2, studio1, waktu2, 50000));
        daftarJadwal.add(new Jadwal(film2, studio2, waktu2, 75000));
    }

    // ===== GETTER & SETTER NAMA BIOSKOP =====
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public ArrayList<Film> getDaftarFilm() { return daftarFilm; }
    public ArrayList<Studio> getDaftarStudio() { return daftarStudio; }
    public ArrayList<Jadwal> getDaftarJadwal() { return daftarJadwal; }
    public ArrayList<Pemesanan> getDaftarPemesanan() { return daftarPemesanan; }

    // ===== METODE PENGELOLAAN DATA OLEH ADMIN =====
    public void tambahFilm(Film film) {
        if (film != null && !daftarFilm.contains(film)) {
            daftarFilm.add(film);
        }
    }

    public boolean hapusFilm(Film film) {
        return daftarFilm.remove(film);
    }

    public void tambahStudio(Studio studio) {
        if (studio != null && !daftarStudio.contains(studio)) {
            daftarStudio.add(studio);
        }
    }

    public void tambahJadwal(Jadwal jadwal) {
        if (jadwal != null && !daftarJadwal.contains(jadwal)) {
            daftarJadwal.add(jadwal);
        }
    }

    public Jadwal cariJadwal(int index) {
        if (index >= 0 && index < daftarJadwal.size()) {
            return daftarJadwal.get(index);
        }
        return null;
    }

    public Pemesanan buatPemesanan(Pelanggan pelanggan, Jadwal jadwal) {
        if (pelanggan == null || jadwal == null) {
            System.out.println("[GAGAL] Data pelanggan atau jadwal tidak valid.");
            return null;
        }
        Pemesanan pemesananBaru = new Pemesanan(pelanggan, jadwal);
        daftarPemesanan.add(pemesananBaru);
        return pemesananBaru;
    }

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

    public void tampilkanRiwayatTransaksi() {
        System.out.println("==================================================");
        System.out.println("       RIWAYAT TRANSAKSI DI " + nama.toUpperCase());
        System.out.println("==================================================");
        if (daftarPemesanan.isEmpty()) {
            System.out.println("(Belum ada transaksi pemesanan)");
            return;
        }
        for (Pemesanan p : daftarPemesanan) {
            p.cetakStruk();
            System.out.println();
        }
    }
}