package model.bioskop;

/**
 * Representasi kursi standar (Reguler) di studio bioskop.
 * Merupakan turunan dari {@link Kursi} dengan faktor pengali harga standar (1.0).
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public class KursiReguler extends Kursi {

    /**
     * Membuat kursi reguler baru.
     *
     * @param baris huruf baris kursi
     * @param nomor nomor kursi pada baris
     */
    public KursiReguler(char baris, int nomor) {
        super(baris, nomor);
    }

    /**
     * Copy constructor untuk membuat salinan kursi reguler baru.
     *
     * @param sumber kursi reguler yang disalin
     */
    public KursiReguler(KursiReguler sumber) {
        super(sumber);
    }

    @Override
    public String getTipeKursi() {
        return "Reguler";
    }

    @Override
    public double getMultiplier() {
        return 1.0;
    }

    @Override
    public String getFasilitas() {
        return "Kursi Bioskop Standar";
    }

    @Override
    public Kursi salin() {
        return new KursiReguler(this);
    }
}
