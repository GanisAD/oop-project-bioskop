package model.tiket;
import model.bioskop.Jadwal;
import model.bioskop.Kursi;

/**
 * Kelas abstrak yang merepresentasikan satu tiket bioskop.
 * Cara menghitung harga dan nama jenis tiket ditentukan oleh subclass
 * ({@link TiketReguler} dan {@link TiketVIP}), sehingga menerapkan polimorfisme.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public abstract class Tiket {
    /**
     * Penghitung bersama semua tiket, dipakai untuk membuat kode tiket berurutan.
     */
    private static int counter = 0;   // dipakai bersama semua tiket

    /**
     * Kode unik tiket, formatnya {@code TKT-001}, {@code TKT-002}, dan seterusnya.
     */
    private String kodeTiket;
    /**
     * Jadwal tayang yang dipesan melalui tiket ini.
     */
    protected Jadwal jadwal;
    /**
     * Kursi yang dipesan melalui tiket ini.
     */
    protected Kursi kursi;

    /**
     * Membuat tiket baru dan otomatis membuat kode tiket berurutan.
     *
     * @param jadwal jadwal tayang yang dipesan
     * @param kursi  kursi yang dipesan
     */
    public Tiket(Jadwal jadwal, Kursi kursi) {
        counter++;
        this.kodeTiket = String.format("TKT-%03d", counter);
        this.jadwal = jadwal;
        this.kursi = kursi;
    }

    // Wajib di-override oleh anak class
    /**
     * Menghitung harga tiket. Wajib di-override oleh subclass.
     *
     * @return harga tiket dalam rupiah
     */
    public abstract double hitungHarga();
    /**
     * Mengambil nama jenis tiket. Wajib di-override oleh subclass.
     *
     * @return nama jenis tiket, misalnya {@code "Reguler"} atau {@code "VIP"}
     */
    public abstract String getJenisTiket();

    /**
     * Mengambil kode tiket.
     *
     * @return kode tiket
     */
    public String getKodeTiket() { return kodeTiket; }
    /**
     * Mengambil jadwal tayang tiket ini.
     *
     * @return jadwal tayang
     */
    public Jadwal getJadwal() { return jadwal; }
    /**
     * Mengambil kursi tiket ini.
     *
     * @return kursi yang dipesan
     */
    public Kursi getKursi() { return kursi; }

    /**
     * Mencetak detail tiket (kode, jenis, film, studio, waktu, kursi, harga) ke konsol.
     */
    public void cetakTiket() {
        System.out.println("========== TIKET BIOSKOP ==========");
        System.out.println("Kode Tiket : " + kodeTiket);
        System.out.println("Jenis      : " + getJenisTiket());
        System.out.println("Film       : " + jadwal.getFilm().getJudul());
        System.out.println("Studio     : " + jadwal.getStudio().getNama());
        System.out.println("Tayang     : " + jadwal.getWaktuFormat());
        System.out.println("Kursi      : " + kursi.getKode());
        System.out.println("Harga      : Rp" + String.format("%,.0f", hitungHarga()));
    }
}