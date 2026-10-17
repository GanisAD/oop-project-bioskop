package app;

import model.bioskop.Bioskop;
import model.user.Admin;
import model.user.Pelanggan;

/**
 * Penyimpan data bersama (shared state) aplikasi dengan pola Singleton.
 * Menyimpan satu {@link Bioskop}, satu {@link Admin}, dan pelanggan aktif
 * yang dipakai oleh semua controller.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class AppContext {
    /**
     * Satu-satunya instance AppContext.
     */
    private static AppContext instance;
    /**
     * Bioskop yang dikelola aplikasi.
     */
    private final Bioskop bioskop;
    /**
     * Admin yang mengelola bioskop.
     */
    private final Admin admin;
    /**
     * Pelanggan yang sedang aktif.
     */
    private Pelanggan activePelanggan;

    /**
     * Membuat konteks dengan bioskop {@code "Cinema XXI Surabaya"}, admin bawaan, dan pelanggan default. Konstruktor private agar hanya bisa dibuat lewat {@link #getInstance()}.
     */
    private AppContext() {
        this.bioskop = new Bioskop("Cinema XXI Surabaya");
        this.admin = new Admin("ADM-01", "Budi Admin");
        this.activePelanggan = new Pelanggan("CUST-001", "Pengunjung", "-", "081234567890");
    }

    /**
     * Mengambil instance tunggal AppContext; dibuat saat pertama kali dipanggil.
     *
     * @return instance AppContext
     */
    public static synchronized AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    /**
     * Mengambil bioskop.
     *
     * @return bioskop yang dikelola aplikasi
     */
    public Bioskop getBioskop() {
        return bioskop;
    }

    /**
     * Mengambil admin.
     *
     * @return admin bioskop
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Mengambil pelanggan aktif.
     *
     * @return pelanggan yang sedang aktif
     */
    public Pelanggan getActivePelanggan() {
        return activePelanggan;
    }

    /**
     * Mengganti pelanggan aktif.
     *
     * @param activePelanggan pelanggan yang menjadi aktif
     */
    public void setActivePelanggan(Pelanggan activePelanggan) {
        this.activePelanggan = activePelanggan;
    }
}
