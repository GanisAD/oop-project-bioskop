package model.tiket;

import model.bioskop.Jadwal;
import model.bioskop.Kursi;

/**
 * Tiket jenis reguler dengan harga standar sesuai harga dasar jadwal tayang.
 * Merupakan turunan dari {@link Tiket}.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public class TiketReguler extends Tiket {

    /**
     * Membuat tiket reguler dengan memanggil konstruktor superclass {@link Tiket}.
     *
     * @param jadwal jadwal tayang yang dipesan
     * @param kursi  kursi yang dipesan
     */
    public TiketReguler(Jadwal jadwal, Kursi kursi) {
        super(jadwal, kursi);
    }

    @Override
    public double hitungHarga() {
        return jadwal.getHargaDasar();
    }

    @Override
    public String getJenisTiket() {
        return "Reguler";
    }

    @Override
    public String getFasilitas() {
        return "Standar";
    }
}
