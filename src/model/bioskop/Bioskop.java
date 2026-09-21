package model.bioskop;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Bioskop {
    private String nama;
    private ArrayList<Film> daftarFilm;
    private ArrayList<Studio> daftarStudio;
    private ArrayList<Jadwal> daftarJadwal;

    public Bioskop(String nama) {
        this.nama = nama;
        this.daftarFilm = new ArrayList<>();
        this.daftarStudio = new ArrayList<>();
        this.daftarJadwal = new ArrayList<>();
    }

    // ===== GETTER =====
    public String getNama() {
        return nama;
    }

    public ArrayList<Film> getDaftarFilm() {
        return new ArrayList<>(daftarFilm);
    }

    public ArrayList<Studio> getDaftarStudio() {
        return new ArrayList<>(daftarStudio);
    }

    public ArrayList<Jadwal> getDaftarJadwal() {
        return new ArrayList<>(daftarJadwal);
    }

    // ===== PEMBUATAN & PENAMBAHAN FILM =====
    public Film tambahFilm(String judul, String genre, int durasi, String rating) {
        Film filmBaru = new Film(judul, genre, durasi, rating);
        daftarFilm.add(filmBaru);
        return filmBaru;
    }

    public void tambahFilm(Film film) {
        if (film != null && !daftarFilm.contains(film)) {
            daftarFilm.add(film);
        }
    }

    // ===== PEMBUATAN & PENAMBAHAN STUDIO (Kursi otomatis terbuat di Studio) =====
    public Studio tambahStudio(String namaStudio, int jumlahBaris, int kursiPerBaris) {
        Studio studioBaru = new Studio(namaStudio, jumlahBaris, kursiPerBaris);
        daftarStudio.add(studioBaru);
        return studioBaru;
    }

    public void tambahStudio(Studio studio) {
        if (studio != null && !daftarStudio.contains(studio)) {
            daftarStudio.add(studio);
        }
    }

    // ===== PENCARIAN ENTITAS =====
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

    // ===== PEMBUATAN & PENAMBAHAN JADWAL =====
    // Membuat jadwal menggunakan objek Film dan Studio langsung
    public Jadwal tambahJadwal(Film film, Studio studio, LocalDateTime waktuTayang, double hargaDasar) {
        Jadwal jadwalBaru = new Jadwal(film, studio, waktuTayang, hargaDasar);
        daftarJadwal.add(jadwalBaru);
        return jadwalBaru;
    }

    // Membuat jadwal berdasarkan judul film dan nama studio yang sudah ada di bioskop
    public Jadwal tambahJadwal(String judulFilm, String namaStudio, LocalDateTime waktuTayang, double hargaDasar) {
        Film film = cariFilm(judulFilm);
        Studio studio = cariStudio(namaStudio);

        if (film == null) {
            throw new IllegalArgumentException("Film dengan judul '" + judulFilm + "' tidak ditemukan di " + nama);
        }
        if (studio == null) {
            throw new IllegalArgumentException("Studio dengan nama '" + namaStudio + "' tidak ditemukan di " + nama);
        }

        Jadwal jadwalBaru = new Jadwal(film, studio, waktuTayang, hargaDasar);
        daftarJadwal.add(jadwalBaru);
        return jadwalBaru;
    }

    // ===== METODE TAMPILAN =====
    public void tampilkanSemuaFilm() {
        System.out.println("=== DAFTAR FILM DI " + nama.toUpperCase() + " ===");
        if (daftarFilm.isEmpty()) {
            System.out.println("(Belum ada film tersedia)");
            return;
        }
        for (int i = 0; i < daftarFilm.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarFilm.get(i).tampilkanInfo();
        }
    }

    public void tampilkanSemuaJadwal() {
        System.out.println("=== DAFTAR JADWAL TAYANG DI " + nama.toUpperCase() + " ===");
        if (daftarJadwal.isEmpty()) {
            System.out.println("(Belum ada jadwal tayang)");
            return;
        }
        for (int i = 0; i < daftarJadwal.size(); i++) {
            System.out.println("Jadwal #" + (i + 1));
            daftarJadwal.get(i).tampilkanInfo();
            System.out.println("------------------------------------");
        }
    }
}