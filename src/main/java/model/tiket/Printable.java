package model.tiket;

/**
 * Antarmuka (interface) untuk semua entitas dalam sistem tiket bioskop
 * yang dapat dicetak informasinya ke konsol atau media cetak lainnya.
 * Menerapkan prinsip abstraksi dan polimorfisme antarmuka.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public interface Printable {
    /**
     * Mencetak representasi dokumen atau informasi entitas.
     */
    void cetak();
}
