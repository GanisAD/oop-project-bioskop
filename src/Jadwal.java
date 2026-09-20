import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Jadwal {
    private Film film;
    private Studio studio;
    private LocalDateTime waktuTayang;
    private double hargaDasar;
    private ArrayList<String> kursiTerpesan;

    public Jadwal(Film film, Studio studio, LocalDateTime waktuTayang, double hargaDasar) {
        this.film = film;
        this.studio = studio;
        this.waktuTayang = waktuTayang;
        this.hargaDasar = hargaDasar;
        this.kursiTerpesan = new ArrayList<>();
    }

    // ===== Getter =====
    public Film getFilm() { return film; }
    public Studio getStudio() { return studio; }
    public LocalDateTime getWaktuTayang() { return waktuTayang; }
    public double getHargaDasar() { return hargaDasar; }

    // ===== Logika kursi =====
    public boolean isKursiTersedia(Kursi kursi) {
        return !kursiTerpesan.contains(kursi.getKode());
    }

    // Mengembalikan true jika berhasil, false jika kursi sudah terjual
    public boolean pesanKursi(Kursi kursi) {
        if (!isKursiTersedia(kursi)) {
            return false;
        }
        kursiTerpesan.add(kursi.getKode());
        return true;
    }

    public int getJumlahKursiTerpesan() {
        return kursiTerpesan.size();
    }

    // ===== Waktu =====
    public LocalDateTime getWaktuSelesai() {
        return waktuTayang.plusMinutes(film.getDurasi());
    }

    public String getWaktuFormat() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return waktuTayang.format(fmt);
    }

    public void tampilkanInfo() {
        System.out.println("Film   : " + film.getJudul());
        System.out.println("Studio : " + studio.getNama());
        System.out.println("Tayang : " + getWaktuFormat());
        System.out.println("Harga  : Rp" + String.format("%,.0f", hargaDasar));
        System.out.println("Terjual: " + kursiTerpesan.size() + " kursi");
    }
}