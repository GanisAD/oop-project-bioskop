package model.user;

import java.time.LocalDateTime;

import model.bioskop.Bioskop;
import model.bioskop.Film;
import model.bioskop.Jadwal;
import model.bioskop.Studio;

/**
 * Pengguna dengan peran pengelola bioskop.
 * Admin menerima data mentah dari form (teks dan angka), lalu meneruskannya ke {@link Bioskop}
 * yang bertugas membuat objek {@link Film}, {@link Studio}, dan {@link Jadwal}.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class Admin extends Person {

    /**
     * Membuat admin baru.
     *
     * @param id   ID unik admin
     * @param nama nama admin
     */
    public Admin(String id, String nama) {
        super(id, nama);
    }

    // Menerima input teks form, Bioskop yang membuat objek Film
    /**
     * Mendaftarkan film baru ke bioskop.
     *
     * @param bioskop bioskop tempat film didaftarkan
     * @param judul   judul film (tidak boleh kosong)
     * @param genre   genre film
     * @param durasi  durasi film dalam menit (harus lebih dari 0)
     * @param rating  batas usia film, misalnya {@code "13+"}
     * @return objek {@link Film} yang baru dibuat
     * @throws IllegalArgumentException jika judul kosong atau durasi tidak lebih dari 0
     */
    public Film tambahFilm(Bioskop bioskop, String judul, String genre, int durasi, String rating) {
        Film f = bioskop.tambahFilm(judul, genre, durasi, rating);
        System.out.println("[ADMIN: " + getNama() + "] Berhasil mendaftarkan film: " + judul);
        return f;
    }

    /**
     * Menghapus film beserta seluruh jadwal tayangnya dari bioskop.
     * Hasil (berhasil atau gagal) dicetak ke konsol.
     *
     * @param bioskop   bioskop yang menyimpan film
     * @param judulFilm judul film yang akan dihapus
     */
    public void hapusFilm(Bioskop bioskop, String judulFilm) {
        boolean berhasil = bioskop.hapusFilm(judulFilm);
        if (berhasil) {
            System.out.println("[ADMIN: " + getNama() + "] Berhasil menghapus film: " + judulFilm);
        } else {
            System.out.println("[ADMIN: " + getNama() + "] Gagal menghapus: Film '" + judulFilm + "' tidak ditemukan.");
        }
    }

    // Menerima input teks & angka form, Bioskop yang membuat objek Studio dan Kursi
    /**
     * Menambahkan studio baru beserta kursinya ke bioskop.
     *
     * @param bioskop       bioskop tempat studio ditambahkan
     * @param namaStudio    nama studio
     * @param jumlahBaris   jumlah baris kursi (1 sampai 26)
     * @param kursiPerBaris jumlah kursi pada setiap baris (lebih dari 0)
     * @return objek {@link Studio} yang baru dibuat
     * @throws IllegalArgumentException jika ukuran studio tidak valid
     */
    public Studio tambahStudio(Bioskop bioskop, String namaStudio, int jumlahBaris, int kursiPerBaris) {
        Studio s = bioskop.tambahStudio(namaStudio, jumlahBaris, kursiPerBaris);
        System.out.println("[ADMIN: " + getNama() + "] Berhasil menambahkan studio: " + namaStudio 
                           + " (" + s.getKapasitas() + " kursi)");
        return s;
    }

    // Menerima pilihan dropdown dan waktu, Bioskop yang memetakan relasi objeknya
    /**
     * Membuka jadwal tayang baru berdasarkan judul film dan nama studio.
     *
     * @param bioskop     bioskop tempat jadwal dibuka
     * @param judulFilm   judul film yang akan ditayangkan
     * @param namaStudio  nama studio tempat film ditayangkan
     * @param waktuTayang tanggal dan jam tayang
     * @param hargaDasar  harga dasar tiket dalam rupiah
     * @return objek {@link Jadwal} yang baru dibuat, atau {@code null} jika film atau studio tidak ditemukan
     */
    public Jadwal tambahJadwal(Bioskop bioskop, String judulFilm, String namaStudio, LocalDateTime waktuTayang, double hargaDasar) {
        Jadwal j = bioskop.tambahJadwal(judulFilm, namaStudio, waktuTayang, hargaDasar);
        if (j != null) {
            System.out.println("[ADMIN: " + getNama() + "] Berhasil membuka jadwal: " + judulFilm + " di " + namaStudio);
        }
        return j;
    }

    /**
     * Menampilkan rekapitulasi seluruh transaksi pemesanan ke konsol.
     *
     * @param bioskop bioskop yang menyimpan riwayat transaksi
     */
    public void lihatTransaksi(Bioskop bioskop) {
        System.out.println("[ADMIN: " + getNama() + "] Membuka rekapitulasi transaksi...");
        bioskop.tampilkanRiwayatTransaksi();
    }
}