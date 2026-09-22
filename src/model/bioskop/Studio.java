package model.bioskop;

import java.util.ArrayList;

public class Studio {
    private String nama;
    private int jumlahBaris;
    private int kursiPerBaris;
    private ArrayList<Kursi> daftarKursi;

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

    private void buatKursi() {
        for (int i = 0; i < jumlahBaris; i++) {
            char baris = (char) ('A' + i);
            for (int nomor = 1; nomor <= kursiPerBaris; nomor++) {
                daftarKursi.add(new Kursi(baris, nomor));
            }
        }
    }

    public String getNama() { return nama; }
    public int getJumlahBaris() { return jumlahBaris; }
    public int getKursiPerBaris() { return kursiPerBaris; } // Ditambahkan untuk denah dinamis
    public int getKapasitas() { return daftarKursi.size(); }

    public ArrayList<Kursi> getDaftarKursi() {
        return new ArrayList<>(daftarKursi);
    }

    public Kursi cariKursi(String kode) {
        for (Kursi k : daftarKursi) {
            if (k.getKode().equalsIgnoreCase(kode)) {
                return k;
            }
        }
        return null;
    }

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
}