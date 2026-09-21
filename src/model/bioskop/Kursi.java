package model.bioskop;

public class Kursi {
    private char baris;   // 'A', 'B', 'C', ...
    private int nomor;    // 1, 2, 3, ...
    private boolean tersedia; // Saran dari dosen

    public Kursi(char baris, int nomor) {
        this.baris = baris;
        this.nomor = nomor;
        this.tersedia = true; // Default selalu tersedia saat awal dibuat
    }

    // Copy Constructor: Memungkinkan pembuatan salinan kursi khusus untuk tiap jadwal
    public Kursi(Kursi sumber) {
        this.baris = sumber.baris;
        this.nomor = sumber.nomor;
        this.tersedia = true;
    }

    public char getBaris() { return baris; }
    public int getNomor() { return nomor; }

    // Getter & Setter status ketersediaan
    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    // Behavior untuk memesan kursi
    public boolean pesan() {
        if (!tersedia) {
            return false; // Gagal karena sudah dipesan
        }
        tersedia = false;
        return true;
    }

    // Behavior untuk membatalkan pesanan jika diperlukan
    public void batalkanPesanan() {
        this.tersedia = true;
    }

    public String getKode() {
        return "" + baris + nomor;
    }

    @Override
    public String toString() {
        return getKode() + (tersedia ? "[O]" : "[X]");
    }
}