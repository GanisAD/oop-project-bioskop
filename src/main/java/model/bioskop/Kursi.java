package model.bioskop;

/**
 * Satu kursi di studio, dikenali dari baris (huruf) dan nomor, misalnya {@code A1}.
 * Menyimpan status tersedia atau terisi.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class Kursi {
    /**
     * Huruf baris kursi: 'A', 'B', 'C', dan seterusnya.
     */
    private char baris;   // 'A', 'B', 'C', ...
    /**
     * Nomor kursi pada baris: 1, 2, 3, dan seterusnya.
     */
    private int nomor;    // 1, 2, 3, ...
    /**
     * Status kursi; {@code true} berarti masih tersedia.
     */
    private boolean tersedia; // Saran dari dosen

    /**
     * Membuat kursi baru yang berstatus tersedia.
     *
     * @param baris huruf baris kursi
     * @param nomor nomor kursi pada baris
     */
    public Kursi(char baris, int nomor) {
        this.baris = baris;
        this.nomor = nomor;
        this.tersedia = true; // Default selalu tersedia saat awal dibuat
    }

    // Copy Constructor: Memungkinkan pembuatan salinan kursi khusus untuk tiap jadwal
    /**
     * Copy constructor: membuat salinan kursi (baris dan nomor sama) untuk tiap jadwal.
     * Salinan selalu berstatus tersedia.
     *
     * @param sumber kursi yang disalin
     */
    public Kursi(Kursi sumber) {
        this.baris = sumber.baris;
        this.nomor = sumber.nomor;
        this.tersedia = true;
    }

    /**
     * Mengambil huruf baris.
     *
     * @return huruf baris kursi
     */
    public char getBaris() { return baris; }
    /**
     * Mengambil nomor kursi.
     *
     * @return nomor kursi pada baris
     */
    public int getNomor() { return nomor; }

    // Getter & Setter status ketersediaan
    /**
     * Memeriksa status kursi.
     *
     * @return {@code true} jika kursi masih tersedia
     */
    public boolean isTersedia() {
        return tersedia;
    }

    /**
     * Mengubah status ketersediaan kursi.
     *
     * @param tersedia {@code true} jika kursi tersedia
     */
    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    // Behavior untuk memesan kursi
    /**
     * Memesan kursi ini.
     *
     * @return {@code true} jika berhasil; {@code false} jika kursi sudah terisi
     */
    public boolean pesan() {
        if (!tersedia) {
            return false; // Gagal karena sudah dipesan
        }
        tersedia = false;
        return true;
    }

    // Behavior untuk membatalkan pesanan jika diperlukan
    /**
     * Membatalkan pesanan sehingga kursi tersedia kembali.
     */
    public void batalkanPesanan() {
        this.tersedia = true;
    }

    /**
     * Mengambil kode kursi gabungan baris dan nomor.
     *
     * @return kode kursi, misalnya {@code "A1"}
     */
    public String getKode() {
        return "" + baris + nomor;
    }

    @Override
    public String toString() {
        return getKode() + (tersedia ? "[O]" : "[X]");
    }
}