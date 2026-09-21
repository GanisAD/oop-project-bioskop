package model.bioskop;
public class Kursi {
    private char baris;   // 'A', 'B', 'C', ...
    private int nomor;    // 1, 2, 3, ...

    public Kursi(char baris, int nomor) {
        this.baris = baris;
        this.nomor = nomor;
    }

    public char getBaris() { return baris; }
    public int getNomor() { return nomor; }

    // Kode kursi, contoh: "A1", "B5"
    public String getKode() {
        return "" + baris + nomor;
    }

    @Override
    public String toString() {
        return getKode();
    }
}