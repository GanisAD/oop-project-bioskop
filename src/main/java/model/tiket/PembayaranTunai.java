package model.tiket;

/**
 * Implementasi metode pembayaran Tunai (Cash) di loket bioskop.
 * Mengimplementasikan antarmuka {@link MetodePembayaran}.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public class PembayaranTunai implements MetodePembayaran {

    @Override
    public String getNamaMetode() {
        return "Tunai (Cash)";
    }

    @Override
    public boolean prosesPembayaran(double jumlah) {
        System.out.println("[PEMBAYARAN TUNAI] Pembayaran sebesar Rp" 
                + String.format("%,.0f", jumlah) + " berhasil diterima di kasir.");
        return true;
    }
}
