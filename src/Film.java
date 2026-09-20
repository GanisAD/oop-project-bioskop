public class Film {
    private String judul;
    private String genre;
    private int durasi;       // dalam menit
    private String rating;    // contoh: "SU", "13+", "17+"

    public Film(String judul, String genre, int durasi, String rating) {
        setJudul(judul);
        this.genre = genre;
        setDurasi(durasi);
        this.rating = rating;
    }

    // ===== Getter =====
    public String getJudul() { return judul; }
    public String getGenre() { return genre; }
    public int getDurasi() { return durasi; }
    public String getRating() { return rating; }

    // ===== Setter dengan validasi (pengelolaan data) =====
    public void setJudul(String judul) {
        if (judul == null || judul.isBlank()) {
            throw new IllegalArgumentException("Judul film tidak boleh kosong");
        }
        this.judul = judul;
    }

    public void setDurasi(int durasi) {
        if (durasi <= 0) {
            throw new IllegalArgumentException("Durasi harus lebih dari 0 menit");
        }
        this.durasi = durasi;
    }

    public void setGenre(String genre) { this.genre = genre; }
    public void setRating(String rating) { this.rating = rating; }

    public void tampilkanInfo() {
        System.out.println(judul + " | " + genre + " | " + durasi + " menit | " + rating);
    }
}