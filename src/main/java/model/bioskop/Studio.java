package model.bioskop;

import java.util.ArrayList;

/**
 * Studio bioskop dengan susunan kursi berbentuk baris dan kolom.
 * Kursi dibuat otomatis (baris A dan seterusnya) saat studio dibuat.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class Studio {
    /**
     * Nama studio.
     */
    private String nama;
    /**
     * Jumlah baris kursi.
     */
    private int jumlahBaris;
    /**
     * Jumlah kursi pada setiap baris.
     */
    private int kursiPerBaris;
    /**
     * Daftar seluruh kursi di studio.
     */
    private ArrayList<Kursi> daftarKursi;

    /**
     * Membuat studio baru beserta seluruh kursinya.
     *
     * @param nama          nama studio
     * @param jumlahBaris   jumlah baris kursi (1 sampai 26)
     * @param kursiPerBaris jumlah kursi tiap baris (lebih dari 0)
     * @throws IllegalArgumentException jika ukuran studio tidak valid
     */
    public Studio(String nama, int jumlahBaris, int kursiPerBaris) {
        if (jumlahBaris <= 0 || jumlahBaris > 26 || kursiPerBaris <= 0) {
            throw new IllegalArgumentException("Ukuran studio tidak valid (baris 1-26)");
        }
        this.nama = nama;
        this.jumlahBaris = jumlahBaris;
        this.kursiPerBaris = kursiPerBaris;
        this.daftarKursi = new ArrayList<>();
        buatKursi();
    }

    /**
     * Membuat semua kursi: baris dari 'A' dan nomor dari 1 sampai {@code kursiPerBaris}.
     */
    private void buatKursi() {
        for (int i = 0; i < jumlahBaris; i++) {
            char baris = (char) ('A' + i);
            for (int nomor = 1; nomor <= kursiPerBaris; nomor++) {
                daftarKursi.add(new Kursi(baris, nomor));
            }
        }
    }

    /**
     * Mengambil nama studio.
     *
     * @return nama studio
     */
    public String getNama() { return nama; }
    /**
     * Mengambil jumlah baris.
     *
     * @return jumlah baris kursi
     */
    public int getJumlahBaris() { return jumlahBaris; }
    /**
     * Mengambil jumlah kursi per baris.
     *
     * @return jumlah kursi tiap baris
     */
    public int getKursiPerBaris() { return kursiPerBaris; } // Ditambahkan untuk denah dinamis
    /**
     * Menghitung kapasitas studio.
     *
     * @return jumlah seluruh kursi
     */
    public int getKapasitas() { return daftarKursi.size(); }

    /**
     * Mengambil daftar kursi studio.
     *
     * @return daftar baru yang berisi kursi-kursi studio (menambah atau menghapus isinya tidak mengubah studio)
     */
    public ArrayList<Kursi> getDaftarKursi() {
        return new ArrayList<>(daftarKursi);
    }

    /**
     * Mencari kursi berdasarkan kode, tanpa membedakan huruf besar/kecil.
     *
     * @param kode kode kursi, misalnya {@code "A1"}
     * @return kursi yang ditemukan, atau {@code null} jika tidak ada
     */
    public Kursi cariKursi(String kode) {
        for (Kursi k : daftarKursi) {
            if (k.getKode().equalsIgnoreCase(kode)) {
                return k;
            }
        }
        return null;
    }

    /**
     * Mencetak denah kode kursi studio ke konsol.
     */
    public void tampilkanDenah() {
        System.out.println("=== Denah " + nama + " (" + getKapasitas() + " kursi) ===");
        System.out.println("            [ LAYAR ]");
        for (int i = 0; i < daftarKursi.size(); i++) {
            System.out.print(String.format("%-4s", daftarKursi.get(i).getKode()));
            if ((i + 1) % kursiPerBaris == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        return nama + " (" + getKapasitas() + " kursi)";
    }
}