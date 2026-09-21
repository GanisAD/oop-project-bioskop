package model.bioskop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Jadwal {
    private Film film;
    private Studio studio;
    private LocalDateTime waktuTayang;
    private double hargaDasar;
    // Menyimpan kursi khusus untuk sesi penayangan ini
    private ArrayList<Kursi> daftarKursiJadwal;

    public Jadwal(Film film, Studio studio, LocalDateTime waktuTayang, double hargaDasar) {
        this.film = film;
        this.studio = studio;
        this.waktuTayang = waktuTayang;
        this.hargaDasar = hargaDasar;
        this.daftarKursiJadwal = new ArrayList<>();
        
        // Gandakan kursi dari denah studio agar status boolean tiap jadwal mandiri
        for (Kursi k : studio.getDaftarKursi()) {
            daftarKursiJadwal.add(new Kursi(k));
        }
    }

    // ===== Getter =====
    public Film getFilm() { return film; }
    public Studio getStudio() { return studio; }
    public LocalDateTime getWaktuTayang() { return waktuTayang; }
    public double getHargaDasar() { return hargaDasar; }
    public ArrayList<Kursi> getDaftarKursiJadwal() { return daftarKursiJadwal; }

    // Mencari kursi pada jadwal ini berdasarkan kode (misal "A1")
    public Kursi cariKursi(String kode) {
        for (Kursi k : daftarKursiJadwal) {
            if (k.getKode().equalsIgnoreCase(kode)) {
                return k;
            }
        }
        return null;
    }

    // ===== Logika kursi (Memanfaatkan boolean dari class Kursi) =====
    public boolean isKursiTersedia(String kodeKursi) {
        Kursi k = cariKursi(kodeKursi);
        return k != null && k.isTersedia();
    }

    public boolean pesanKursi(Kursi kursi) {
        Kursi k = cariKursi(kursi.getKode());
        if (k != null) {
            return k.pesan(); // Memanggil langsung method dari class Kursi
        }
        return false;
    }

    public int getJumlahKursiTerpesan() {
        int count = 0;
        for (Kursi k : daftarKursiJadwal) {
            if (!k.isTersedia()) {
                count++;
            }
        }
        return count;
    }

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
        System.out.println("Terjual: " + getJumlahKursiTerpesan() + " kursi");
    }

    // Menampilkan denah interaktif jadwal (O = Kosong, X = Terisi)
    public void tampilkanDenahKursi() {
        System.out.println("=== Denah Kursi Jadwal: " + waktuTayang + " ===");
        System.out.println("                 [ LAYAR ]");
        for (int i = 0; i < daftarKursiJadwal.size(); i++) {
            Kursi k = daftarKursiJadwal.get(i);
            String status = k.isTersedia() ? "[ ]" : "[X]";
            System.out.print(k.getKode() + status + " ");
            
            // Mengatur baris denah sesuai lebar studio
            if ((i + 1) % 8 == 0) { // sesuaikan dengan kursiPerBaris studio
                System.out.println();
            }
        }
    }
}