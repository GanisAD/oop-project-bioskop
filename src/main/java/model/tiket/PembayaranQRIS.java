package model.tiket;

/**
 * Implementasi metode pembayaran non-tunai melalui QRIS (Quick Response Code Indonesian Standard).
 * Mengimplementasikan antarmuka {@link MetodePembayaran}.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public class PembayaranQRIS implements MetodePembayaran {
    /**
     * Kode unik transaksi QRIS.
     */
    private final String idTransaksiQRIS;

    /**
     * Membuat sesi pembayaran QRIS baru dengan identifier acak/berbasis waktu.
     */
    public PembayaranQRIS() {
        this.idTransaksiQRIS = "QRIS-" + (System.currentTimeMillis() % 1000000);
    }

    @Override
    public String getNamaMetode() {
        return "QRIS";
    }

    @Override
    public boolean prosesPembayaran(double jumlah) {
        System.out.println("[PEMBAYARAN QRIS] Memvalidasi transaksi " + idTransaksiQRIS 
                + "... Pembayaran sebesar Rp" + String.format("%,.0f", jumlah) + " BERHASIL.");
        return true;
    }

    /**
     * Mengambil ID transaksi QRIS.
     *
     * @return ID unik transaksi QRIS
     */
    public String getIdTransaksiQRIS() {
        return idTransaksiQRIS;
    }
}
