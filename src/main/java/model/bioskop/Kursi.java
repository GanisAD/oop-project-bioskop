package model.bioskop;

/**
 * Kelas abstrak (abstract superclass) yang merepresentasikan kursi di dalam studio bioskop.
 * Dikenali dari baris (huruf) dan nomor kursi (misal: A1, B5).
 * Menyediakan kerangka dasar status ketersediaan kursi dan kontrak polimorfik
 * untuk tipe kursi, multiplier harga, fasilitas, dan penggandaan (cloning).
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public abstract class Kursi {
    /**
     * Huruf baris kursi: 'A', 'B', 'C', dan seterusnya.
     */
    private char baris;

    /**
     * Nomor kursi pada baris: 1, 2, 3, dan seterusnya.
     */
    private int nomor;

    /**
     * Status ketersediaan kursi; {@code true} berarti masih tersedia.
     */
    private boolean tersedia;

    /**
     * Membuat kursi baru yang berstatus tersedia.
     *
     * @param baris huruf baris kursi
     * @param nomor nomor kursi pada baris
     */
    public Kursi(char baris, int nomor) {
        this.baris = baris;
        this.nomor = nomor;
        this.tersedia = true;
    }

    /**
     * Copy constructor: membuat salinan kursi dari objek sumber untuk jadwal tertentu.
     * Salinan selalu berstatus tersedia secara default untuk mencegah shared reference bug.
     *
     * @param sumber kursi yang disalin
     */
    public Kursi(Kursi sumber) {
        this.baris = sumber.baris;
        this.nomor = sumber.nomor;
        this.tersedia = true;
    }

    /**
     * Mengambil nama jenis/tipe kursi secara polimorfik.
     *
     * @return jenis kursi (misal: "Reguler" atau "VIP")
     */
    public abstract String getTipeKursi();

    /**
     * Mengambil pengali harga (multiplier) untuk jenis kursi ini.
     *
     * @return faktor pengali harga
     */
    public abstract double getMultiplier();

    /**
     * Mengambil deskripsi fasilitas yang menyertai kursi ini.
     *
     * @return deskripsi fasilitas kursi
     */
    public abstract String getFasilitas();

    /**
     * Membuat salinan (clone) polimorfik dari kursi ini dengan status tersedia.
     * Mencegah shared reference antar-jadwal tayang.
     *
     * @return objek Kursi baru hasil salinan
     */
    public abstract Kursi salin();

    /**
     * Menghitung harga tiket untuk kursi ini berdasarkan harga dasar jadwal tayang.
     *
     * @param hargaDasar harga dasar jadwal dalam rupiah
     * @return estimasi harga kursi setelah dikalikan multiplier
     */
    public double hitungHargaKursi(double hargaDasar) {
        return hargaDasar * getMultiplier();
    }

    /**
     * Mengambil huruf baris.
     *
     * @return huruf baris kursi
     */
    public char getBaris() {
        return baris;
    }

    /**
     * Mengubah huruf baris kursi.
     *
     * @param baris huruf baris baru
     */
    public void setBaris(char baris) {
        this.baris = baris;
    }

    /**
     * Mengambil nomor kursi.
     *
     * @return nomor kursi pada baris
     */
    public int getNomor() {
        return nomor;
    }

    /**
     * Mengubah nomor kursi.
     *
     * @param nomor nomor kursi baru
     */
    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    /**
     * Memeriksa status ketersediaan kursi.
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

    /**
     * Memesan kursi ini jika masih tersedia.
     *
     * @return {@code true} jika berhasil dipesan; {@code false} jika kursi sudah terisi
     */
    public boolean pesan() {
        if (!tersedia) {
            return false;
        }
        tersedia = false;
        return true;
    }

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
