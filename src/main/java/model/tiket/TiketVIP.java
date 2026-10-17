package model.tiket;
import model.bioskop.Jadwal;
import model.bioskop.Kursi;

/**
 * Tiket jenis VIP: harga dasar jadwal ditambah biaya VIP,
 * dengan fasilitas kursi recliner, snack, dan minuman.
 * Merupakan turunan dari {@link Tiket}.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
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
     * Membuat tiket VIP.
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
        return jadwal.getHargaDasar() + BIAYA_VIP;   // harga standar + biaya VIP
    }

    @Override
    public String getJenisTiket() {
        return "VIP";
    }

    @Override
    public void cetakTiket() {
        super.cetakTiket();   // pakai cetak bawaan dulu
        System.out.println("Fasilitas  : " + fasilitas);   // lalu tambah khusus VIP
    }
}