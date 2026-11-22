package model.tiket;

/**
 * Antarmuka (interface) untuk strategi metode pembayaran pemesanan tiket bioskop.
 * Memungkinkan pemrosesan pembayaran secara polimorfik untuk berbagai saluran
 * seperti tunai (cash) maupun digital (QRIS).
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public interface MetodePembayaran {
    /**
     * Mengambil nama metode pembayaran.
     *
     * @return nama metode pembayaran (misal: "Tunai" atau "QRIS")
     */
    String getNamaMetode();

    /**
     * Memproses pembayaran sebesar jumlah tagihan tertentu.
     *
     * @param jumlah total nominal yang harus dibayar
     * @return {@code true} jika pembayaran berhasil diproses, {@code false} jika gagal
     */
    boolean prosesPembayaran(double jumlah);
}
