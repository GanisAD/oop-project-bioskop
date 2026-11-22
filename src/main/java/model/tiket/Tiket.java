package model.tiket;

import model.bioskop.Jadwal;
import model.bioskop.Kursi;

/**
 * Kelas abstrak (abstract superclass) yang merepresentasikan satu tiket bioskop.
 * Mengimplementasikan antarmuka {@link Printable} untuk standardisasi pencetakan dokumen.
 * Menerapkan pola Template Method pada {@link #cetakTiket()} sehingga kerangka
 * informasi umum dikelola di superclass dan detail fasilitas khusus di-hook oleh subclass.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public abstract class Tiket implements Printable {
    /**
     * Penghitung bersama semua tiket untuk menghasilkan kode tiket berurutan.
     */
    private static int counter = 0;

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

    /**
     * Menghitung harga tiket secara polimorfik. Wajib di-override oleh subclass.
     *
     * @return harga tiket dalam rupiah
     */
    public abstract double hitungHarga();

    /**
     * Mengambil nama jenis tiket secara polimorfik. Wajib di-override oleh subclass.
     *
     * @return nama jenis tiket, misalnya {@code "Reguler"} atau {@code "VIP"}
     */
    public abstract String getJenisTiket();

    /**
     * Mengambil deskripsi fasilitas tiket secara polimorfik.
     *
     * @return deskripsi fasilitas yang didapatkan
     */
    public abstract String getFasilitas();

    /**
     * Mengambil kode tiket.
     *
     * @return kode tiket
     */
    public String getKodeTiket() {
        return kodeTiket;
    }

    /**
     * Mengambil jadwal tayang tiket ini.
     *
     * @return jadwal tayang
     */
    public Jadwal getJadwal() {
        return jadwal;
    }

    /**
     * Mengambil kursi tiket ini.
     *
     * @return kursi yang dipesan
     */
    public Kursi getKursi() {
        return kursi;
    }

    /**
     * Template Method untuk mencetak detail tiket ke konsol.
     * Bagian umum dicetak di superclass, sedangkan fasilitas tambahan
     * dieksekusi melalui hook method {@link #tampilkanFasilitasTambahan()}.
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
        tampilkanFasilitasTambahan();
    }

    /**
     * Hook method dalam Template Method pattern.
     * Default tidak mencetak fasilitas tambahan (misal untuk Tiket Reguler).
     * Dapat di-override oleh subclass (seperti {@link TiketVIP}) untuk menampilkan fasilitas khusus.
     */
    protected void tampilkanFasilitasTambahan() {
        // Default kosong, di-override oleh subclass jika ada fasilitas tambahan
    }

    /**
     * Implementasi kontrak antarmuka {@link Printable}.
     */
    @Override
    public void cetak() {
        cetakTiket();
    }
}
