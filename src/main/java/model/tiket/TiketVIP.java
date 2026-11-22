package model.tiket;

import model.bioskop.Jadwal;
import model.bioskop.Kursi;

/**
 * Tiket jenis VIP dengan biaya tambahan di atas harga dasar jadwal tayang,
 * disertai fasilitas premium berupa kursi recliner, snack, dan minuman.
 * Merupakan turunan dari {@link Tiket}.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public class TiketVIP extends Tiket {
    /**
     * Biaya tambahan VIP sebesar Rp 25.000 di atas harga dasar jadwal.
     */
    private static final double BIAYA_VIP = 25000;

    /**
     * Deskripsi fasilitas yang didapat pemegang tiket VIP.
     */
    private String fasilitas;

    /**
     * Membuat tiket VIP dengan memanggil konstruktor superclass {@link Tiket}.
     *
     * @param jadwal jadwal tayang yang dipesan
     * @param kursi  kursi yang dipesan
     */
    public TiketVIP(Jadwal jadwal, Kursi kursi) {
        super(jadwal, kursi);
        this.fasilitas = "Kursi Recliner + Snack + Minuman";
    }

    @Override
    public double hitungHarga() {
        return jadwal.getHargaDasar() + BIAYA_VIP;
    }

    @Override
    public String getJenisTiket() {
        return "VIP";
    }

    @Override
    public String getFasilitas() {
        return fasilitas;
    }

    /**
     * Mengubah fasilitas tiket VIP.
     *
     * @param fasilitas deskripsi fasilitas baru
     */
    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    /**
     * Meng-override hook method dari superclass {@link Tiket} untuk mencetak
     * fasilitas tambahan tanpa menduplikasi method cetak tiket utama.
     */
    @Override
    protected void tampilkanFasilitasTambahan() {
        System.out.println("Fasilitas  : " + fasilitas);
    }
}
