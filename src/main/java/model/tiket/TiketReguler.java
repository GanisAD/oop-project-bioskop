package model.tiket;
import model.bioskop.Jadwal;
import model.bioskop.Kursi;

/**
 * Tiket jenis reguler dengan harga standar sesuai harga dasar jadwal.
 * Merupakan turunan dari {@link Tiket}.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class TiketReguler extends Tiket {

    /**
     * Membuat tiket reguler.
     *
     * @param jadwal jadwal tayang yang dipesan
     * @param kursi  kursi yang dipesan
     */
    public TiketReguler(Jadwal jadwal, Kursi kursi) {
        super(jadwal, kursi);   // memanggil constructor Tiket
    }

    @Override
    public double hitungHarga() {
        return jadwal.getHargaDasar();   // harga standar
    }

    @Override
    public String getJenisTiket() {
        return "Reguler";
    }
}