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
        tambahStudio("Studio 1", 5, 8);
        tambahStudio("Studio Premiere", 3, 6);

        tambahFilm("Petualangan Si Garuda", "Petualangan", 120, "13+");
        tambahFilm("Avatar: The Way of Water", "Sci-Fi", 192, "13+");

        tambahJadwal("Petualangan Si Garuda", "Studio 1", LocalDateTime.of(2026, 9, 25, 14, 0), 45000);
        tambahJadwal("Avatar: The Way of Water", "Studio 1", LocalDateTime.of(2026, 9, 25, 19, 30), 50000);
        tambahJadwal("Avatar: The Way of Water", "Studio Premiere", LocalDateTime.of(2026, 9, 25, 19, 30), 75000);
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public ArrayList<Film> getDaftarFilm() { return daftarFilm; }
    public ArrayList<Studio> getDaftarStudio() { return daftarStudio; }
    public ArrayList<Jadwal> getDaftarJadwal() { return daftarJadwal; }
    public ArrayList<Pemesanan> getDaftarPemesanan() { return daftarPemesanan; }

    // ===== FACTORY / CREATOR METHODS (MENERIMA INPUT GUI) =====

    public Film tambahFilm(String judul, String genre, int durasi, String rating) {
        Film filmBaru = new Film(judul, genre, durasi, rating);
        daftarFilm.add(filmBaru);
        return filmBaru;
    }

    public Studio tambahStudio(String namaStudio, int jumlahBaris, int kursiPerBaris) {
        Studio studioBaru = new Studio(namaStudio, jumlahBaris, kursiPerBaris);
        daftarStudio.add(studioBaru);
        return studioBaru;
    }

    public Jadwal tambahJadwal(String judulFilm, String namaStudio, LocalDateTime waktuTayang, double hargaDasar) {
        Film film = cariFilm(judulFilm);
        if (film == null) {
            System.out.println("[GAGAL] Film '" + judulFilm + "' tidak ditemukan.");
            return null;
        }

        Studio studio = cariStudio(namaStudio);
        if (studio == null) {
            System.out.println("[GAGAL] Studio '" + namaStudio + "' tidak ditemukan.");
            return null;
        }

        Jadwal jadwalBaru = new Jadwal(film, studio, waktuTayang, hargaDasar);
        daftarJadwal.add(jadwalBaru);
        return jadwalBaru;
    }

    // ===== METODE PENCARIAN & VALIDASI =====
    public Film cariFilm(String judul) {
        for (Film f : daftarFilm) {
            if (f.getJudul().equalsIgnoreCase(judul)) {
                return f;
            }
        }
        return null;
    }

    public Studio cariStudio(String namaStudio) {
        for (Studio s : daftarStudio) {
            if (s.getNama().equalsIgnoreCase(namaStudio)) {
                return s;
            }
        }
        return null;
    }

    public Jadwal cariJadwal(int index) {
        if (index >= 0 && index < daftarJadwal.size()) {
            return daftarJadwal.get(index);
        }
        return null;
    }

    public boolean hapusFilm(String judulFilm) {
        Film target = cariFilm(judulFilm);
        if (target != null) {
            daftarFilm.remove(target);
            daftarJadwal.removeIf(j -> j.getFilm().getJudul().equalsIgnoreCase(judulFilm));
            return true;
        }
        return false;
    }

    // ===== TRANSAKSI =====
    public Pemesanan buatPemesanan(Pelanggan pelanggan, Jadwal jadwal) {
        if (pelanggan == null || jadwal == null) {
            System.out.println("[GAGAL] Data pelanggan atau jadwal tidak valid.");
            return null;
        }
        Pemesanan pemesananBaru = new Pemesanan(pelanggan, jadwal);
        daftarPemesanan.add(pemesananBaru);
        return pemesananBaru;
    }

    // ===== TAMPILAN =====
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