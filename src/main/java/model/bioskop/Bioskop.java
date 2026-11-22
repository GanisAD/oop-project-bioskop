package model.bioskop;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.tiket.Pemesanan;
import model.user.Pelanggan;

/**
 * Kelas utama (aggregate root) yang mengelola seluruh data bioskop:
 * {@link Film}, {@link Studio}, {@link Jadwal}, dan {@link model.tiket.Pemesanan}.
 * Objek film, studio, jadwal, dan pemesanan dibuat melalui method di kelas ini.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class Bioskop {
    /**
     * Nama bioskop.
     */
    private String nama;
    /**
     * Daftar semua film yang terdaftar.
     */
    private ArrayList<Film> daftarFilm;
    /**
     * Daftar semua studio.
     */
    private ArrayList<Studio> daftarStudio;
    /**
     * Daftar semua jadwal tayang.
     */
    private ArrayList<Jadwal> daftarJadwal;
    /**
     * Daftar semua pemesanan (riwayat transaksi).
     */
    private ArrayList<Pemesanan> daftarPemesanan;

    /**
     * Membuat bioskop baru dan langsung mengisi data contoh (studio, film, dan jadwal).
     *
     * @param nama nama bioskop
     */
    public Bioskop(String nama) {
        this.nama = nama;
        this.daftarFilm = new ArrayList<>();
        this.daftarStudio = new ArrayList<>();
        this.daftarJadwal = new ArrayList<>();
        this.daftarPemesanan = new ArrayList<>();

        inisialisasiDataDefault();
    }

    /**
     * Mengisi data contoh: dua studio, dua film, dan tiga jadwal tayang.
     */
    private void inisialisasiDataDefault() {
        tambahStudio("Studio 1", 5, 8);
        tambahStudio("Studio Premiere", 3, 6);

        tambahFilm("Petualangan Si Garuda", "Petualangan", 120, "13+");
        tambahFilm("Avatar: The Way of Water", "Sci-Fi", 192, "13+");

        tambahJadwal("Petualangan Si Garuda", "Studio 1", LocalDateTime.of(2026, 9, 25, 14, 0), 45000);
        tambahJadwal("Avatar: The Way of Water", "Studio 1", LocalDateTime.of(2026, 9, 25, 19, 30), 50000);
        tambahJadwal("Avatar: The Way of Water", "Studio Premiere", LocalDateTime.of(2026, 9, 25, 19, 30), 75000);
    }

    /**
     * Mengambil nama bioskop.
     *
     * @return nama bioskop
     */
    public String getNama() { return nama; }
    /**
     * Mengubah nama bioskop.
     *
     * @param nama nama baru bioskop
     */
    public void setNama(String nama) { this.nama = nama; }

    /**
     * Mengambil daftar film.
     *
     * @return daftar film
     */
    public ArrayList<Film> getDaftarFilm() { return daftarFilm; }
    /**
     * Mengambil daftar studio.
     *
     * @return daftar studio
     */
    public ArrayList<Studio> getDaftarStudio() { return daftarStudio; }
    /**
     * Mengambil daftar jadwal tayang.
     *
     * @return daftar jadwal
     */
    public ArrayList<Jadwal> getDaftarJadwal() { return daftarJadwal; }
    /**
     * Mengambil daftar pemesanan.
     *
     * @return daftar pemesanan
     */
    public ArrayList<Pemesanan> getDaftarPemesanan() { return daftarPemesanan; }

    // ===== FACTORY / CREATOR METHODS (MENERIMA INPUT GUI) =====

    /**
     * Membuat film baru dan menambahkannya ke daftar film.
     *
     * @param judul  judul film (tidak boleh kosong)
     * @param genre  genre film
     * @param durasi durasi dalam menit (lebih dari 0)
     * @param rating batas usia, misalnya {@code "13+"}
     * @return film yang baru dibuat
     * @throws IllegalArgumentException jika judul kosong atau durasi tidak lebih dari 0
     */
    public Film tambahFilm(String judul, String genre, int durasi, String rating) {
        Film filmBaru = new Film(judul, genre, durasi, rating);
        daftarFilm.add(filmBaru);
        return filmBaru;
    }

    /**
     * Membuat studio baru beserta kursinya dan menambahkannya ke daftar studio.
     *
     * @param namaStudio    nama studio
     * @param jumlahBaris   jumlah baris kursi (1 sampai 26)
     * @param kursiPerBaris jumlah kursi tiap baris
     * @return studio yang baru dibuat
     * @throws IllegalArgumentException jika ukuran studio tidak valid
     */
    public Studio tambahStudio(String namaStudio, int jumlahBaris, int kursiPerBaris) {
        Studio studioBaru = new Studio(namaStudio, jumlahBaris, kursiPerBaris);
        daftarStudio.add(studioBaru);
        return studioBaru;
    }

    /**
     * Membuat jadwal tayang baru dengan mencari film dan studio berdasarkan namanya.
     *
     * @param judulFilm   judul film
     * @param namaStudio  nama studio
     * @param waktuTayang tanggal dan jam tayang
     * @param hargaDasar  harga dasar tiket dalam rupiah
     * @return jadwal yang baru dibuat, atau {@code null} jika film atau studio tidak ditemukan
     */
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
    /**
     * Mencari film berdasarkan judul tanpa membedakan huruf besar/kecil.
     *
     * @param judul judul film yang dicari
     * @return film yang ditemukan, atau {@code null} jika tidak ada
     */
    public Film cariFilm(String judul) {
        for (Film f : daftarFilm) {
            if (f.getJudul().equalsIgnoreCase(judul)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Mencari studio berdasarkan nama tanpa membedakan huruf besar/kecil.
     *
     * @param namaStudio nama studio yang dicari
     * @return studio yang ditemukan, atau {@code null} jika tidak ada
     */
    public Studio cariStudio(String namaStudio) {
        for (Studio s : daftarStudio) {
            if (s.getNama().equalsIgnoreCase(namaStudio)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Mengambil jadwal berdasarkan urutannya pada daftar jadwal.
     *
     * @param index nomor urut jadwal (dimulai dari 0)
     * @return jadwal pada urutan tersebut, atau {@code null} jika di luar rentang
     */
    public Jadwal cariJadwal(int index) {
        if (index >= 0 && index < daftarJadwal.size()) {
            return daftarJadwal.get(index);
        }
        return null;
    }

    /**
     * Menghapus film beserta semua jadwal tayangnya.
     *
     * @param judulFilm judul film yang dihapus
     * @return {@code true} jika film ditemukan dan dihapus, {@code false} jika tidak ditemukan
     */
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
    /**
     * Membuat sesi pemesanan baru dan menyimpannya ke daftar pemesanan.
     *
     * @param pelanggan pelanggan yang memesan
     * @param jadwal    jadwal tayang yang dipesan
     * @return pemesanan baru, atau {@code null} jika pelanggan atau jadwal {@code null}
     */
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
    /**
     * Mencetak daftar semua jadwal tayang (lengkap dengan nomor urut) ke konsol.
     */
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

    /**
     * Mencetak struk semua pemesanan yang pernah dibuat ke konsol.
     */
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