package model.bioskop;

/**
 * Data sebuah film: judul, genre, durasi, dan rating usia.
 * Setter judul dan durasi memiliki validasi (penerapan enkapsulasi).
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class Film {
    /**
     * Judul film.
     */
    private String judul;
    /**
     * Genre film.
     */
    private String genre;
    /**
     * Durasi film dalam menit.
     */
    private int durasi;       // dalam menit
    /**
     * Rating usia film, misalnya {@code "SU"}, {@code "13+"}, atau {@code "17+"}.
     */
    private String rating;    // contoh: "SU", "13+", "17+"

    /**
     * Membuat film baru.
     *
     * @param judul  judul film (tidak boleh kosong)
     * @param genre  genre film
     * @param durasi durasi dalam menit (lebih dari 0)
     * @param rating rating usia film
     * @throws IllegalArgumentException jika judul kosong atau durasi tidak lebih dari 0
     */
    public Film(String judul, String genre, int durasi, String rating) {
        setJudul(judul);
        this.genre = genre;
        setDurasi(durasi);
        this.rating = rating;
    }

    // ===== Getter =====
    /**
     * Mengambil judul film.
     *
     * @return judul film
     */
    public String getJudul() { return judul; }
    /**
     * Mengambil genre film.
     *
     * @return genre film
     */
    public String getGenre() { return genre; }
    /**
     * Mengambil durasi film.
     *
     * @return durasi dalam menit
     */
    public int getDurasi() { return durasi; }
    /**
     * Mengambil rating usia film.
     *
     * @return rating usia
     */
    public String getRating() { return rating; }

    // ===== Setter dengan validasi (pengelolaan data) =====
    /**
     * Mengubah judul film.
     *
     * @param judul judul baru (tidak boleh kosong)
     * @throws IllegalArgumentException jika judul kosong
     */
    public void setJudul(String judul) {
        if (judul == null || judul.isBlank()) {
            throw new IllegalArgumentException("Judul film tidak boleh kosong");
        }
        this.judul = judul;
    }

    /**
     * Mengubah durasi film.
     *
     * @param durasi durasi baru dalam menit (harus lebih dari 0)
     * @throws IllegalArgumentException jika durasi tidak lebih dari 0
     */
    public void setDurasi(int durasi) {
        if (durasi <= 0) {
            throw new IllegalArgumentException("Durasi harus lebih dari 0 menit");
        }
        this.durasi = durasi;
    }

    /**
     * Mengubah genre film.
     *
     * @param genre genre baru
     */
    public void setGenre(String genre) { this.genre = genre; }
    /**
     * Mengubah rating usia film.
     *
     * @param rating rating usia baru
     */
    public void setRating(String rating) { this.rating = rating; }

    /**
     * Mencetak ringkasan film (judul, genre, durasi, rating) ke konsol.
     */
    public void tampilkanInfo() {
        System.out.println(judul + " | " + genre + " | " + durasi + " menit | " + rating);
    }

    @Override
    public String toString() {
        return judul;
    }
}