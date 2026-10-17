package model.bioskop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Jadwal tayang sebuah {@link Film} di sebuah {@link Studio} pada waktu tertentu.
 * Setiap jadwal memiliki salinan kursinya sendiri, sehingga status kursi terisi
 * pada satu jadwal tidak memengaruhi jadwal lain.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class Jadwal {
    /**
     * Film yang ditayangkan.
     */
    private Film film;
    /**
     * Studio tempat film ditayangkan.
     */
    private Studio studio;
    /**
     * Tanggal dan jam mulai tayang.
     */
    private LocalDateTime waktuTayang;
    /**
     * Harga dasar tiket dalam rupiah.
     */
    private double hargaDasar;
    /**
     * Salinan kursi studio khusus untuk jadwal ini.
     */
    private ArrayList<Kursi> daftarKursiJadwal;

    /**
     * Membuat jadwal baru dan menyalin seluruh kursi studio (semuanya berstatus tersedia).
     *
     * @param film        film yang ditayangkan
     * @param studio      studio tempat film ditayangkan
     * @param waktuTayang tanggal dan jam mulai tayang
     * @param hargaDasar  harga dasar tiket dalam rupiah
     */
    public Jadwal(Film film, Studio studio, LocalDateTime waktuTayang, double hargaDasar) {
        this.film = film;
        this.studio = studio;
        this.waktuTayang = waktuTayang;
        this.hargaDasar = hargaDasar;
        this.daftarKursiJadwal = new ArrayList<>();
        
        for (Kursi k : studio.getDaftarKursi()) {
            daftarKursiJadwal.add(new Kursi(k));
        }
    }

    /**
     * Mengambil film jadwal ini.
     *
     * @return film yang ditayangkan
     */
    public Film getFilm() { return film; }
    /**
     * Mengambil studio jadwal ini.
     *
     * @return studio tempat film ditayangkan
     */
    public Studio getStudio() { return studio; }
    /**
     * Mengambil waktu mulai tayang.
     *
     * @return tanggal dan jam mulai tayang
     */
    public LocalDateTime getWaktuTayang() { return waktuTayang; }
    /**
     * Mengambil harga dasar tiket.
     *
     * @return harga dasar dalam rupiah
     */
    public double getHargaDasar() { return hargaDasar; }
    /**
     * Mengambil daftar kursi milik jadwal ini.
     *
     * @return daftar kursi beserta status keterisiannya
     */
    public ArrayList<Kursi> getDaftarKursiJadwal() { return daftarKursiJadwal; }

    /**
     * Mencari kursi pada jadwal ini berdasarkan kode, tanpa membedakan huruf besar/kecil.
     *
     * @param kode kode kursi, misalnya {@code "A1"}
     * @return kursi yang ditemukan, atau {@code null} jika tidak ada
     */
    public Kursi cariKursi(String kode) {
        for (Kursi k : daftarKursiJadwal) {
            if (k.getKode().equalsIgnoreCase(kode)) {
                return k;
            }
        }
        return null;
    }

    /**
     * Memeriksa apakah sebuah kursi masih tersedia.
     *
     * @param kodeKursi kode kursi yang diperiksa
     * @return {@code true} jika kursi ada dan masih tersedia
     */
    public boolean isKursiTersedia(String kodeKursi) {
        Kursi k = cariKursi(kodeKursi);
        return k != null && k.isTersedia();
    }

    /**
     * Menandai kursi pada jadwal ini sebagai terisi.
     *
     * @param kursi kursi yang dipesan (dicocokkan lewat kodenya)
     * @return {@code true} jika berhasil dipesan; {@code false} jika kursi {@code null}, tidak ada, atau sudah terisi
     */
    public boolean pesanKursi(Kursi kursi) {
        if (kursi == null) return false;
        Kursi k = cariKursi(kursi.getKode());
        return k != null && k.pesan();
    }

    /**
     * Menghitung kursi yang sudah terisi.
     *
     * @return jumlah kursi terisi
     */
    public int getJumlahKursiTerpesan() {
        int count = 0;
        for (Kursi k : daftarKursiJadwal) {
            if (!k.isTersedia()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Menghitung perkiraan waktu selesai (waktu tayang ditambah durasi film).
     *
     * @return tanggal dan jam selesai
     */
    public LocalDateTime getWaktuSelesai() {
        return waktuTayang.plusMinutes(film.getDurasi());
    }

    /**
     * Mengambil waktu tayang dalam format teks.
     *
     * @return waktu tayang berformat {@code dd-MM-yyyy HH:mm}
     */
    public String getWaktuFormat() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return waktuTayang.format(fmt);
    }

    /**
     * Mencetak informasi jadwal (film, studio, waktu, harga, kursi terjual) ke konsol.
     */
    public void tampilkanInfo() {
        System.out.println("Film   : " + film.getJudul());
        System.out.println("Studio : " + studio.getNama());
        System.out.println("Tayang : " + getWaktuFormat());
        System.out.println("Harga  : Rp" + String.format("%,.0f", hargaDasar));
        System.out.println("Terjual: " + getJumlahKursiTerpesan() + " kursi");
    }

    /**
     * Mencetak denah kursi jadwal ini ke konsol; {@code [ ]} berarti tersedia dan {@code [X]} berarti terisi.
     */
    public void tampilkanDenahKursi() {
        System.out.println("=== Denah Kursi Jadwal: " + waktuTayang + " ===");
        System.out.println("                 [ LAYAR ]");
        for (int i = 0; i < daftarKursiJadwal.size(); i++) {
            Kursi k = daftarKursiJadwal.get(i);
            String status = k.isTersedia() ? "[ ]" : "[X]";
            System.out.print(k.getKode() + status + " ");
            
            // Dinamis sesuai kapasitas kolom studio
            if ((i + 1) % studio.getKursiPerBaris() == 0) {
                System.out.println();
            }
        }
    }

    /**
     * Mengambil judul film untuk ditampilkan di tabel.
     *
     * @return judul film, atau {@code "-"} jika film tidak ada
     */
    public String getJudulFilm() {
        return film != null ? film.getJudul() : "-";
    }

    /**
     * Mengambil nama studio untuk ditampilkan di tabel.
     *
     * @return nama studio, atau {@code "-"} jika studio tidak ada
     */
    public String getNamaStudio() {
        return studio != null ? studio.getNama() : "-";
    }

    /**
     * Mengambil harga dasar dalam format rupiah.
     *
     * @return harga berformat seperti {@code "Rp45.000"}
     */
    public String getHargaDasarFormat() {
        return String.format("Rp%,.0f", hargaDasar);
    }

    /**
     * Mengambil status keterisian kursi dalam bentuk teks.
     *
     * @return teks seperti {@code "3 / 40 Kursi"} (terisi / total)
     */
    public String getStatusKeterisian() {
        return getJumlahKursiTerpesan() + " / " + daftarKursiJadwal.size() + " Kursi";
    }

    @Override
    public String toString() {
        return getJudulFilm() + " - " + getNamaStudio() + " (" + getWaktuFormat() + ")";
    }
}