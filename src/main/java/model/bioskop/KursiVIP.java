package model.bioskop;

/**
 * Representasi kursi premium (VIP / SweetBox) di studio bioskop.
 * Merupakan turunan dari {@link Kursi} dengan faktor pengali harga lebih tinggi (1.5)
 * dan fasilitas kenyamanan ekstra.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public class KursiVIP extends Kursi {

    /**
     * Membuat kursi VIP baru.
     *
     * @param baris huruf baris kursi
     * @param nomor nomor kursi pada baris
     */
    public KursiVIP(char baris, int nomor) {
        super(baris, nomor);
    }

    /**
     * Copy constructor untuk membuat salinan kursi VIP baru.
     *
     * @param sumber kursi VIP yang disalin
     */
    public KursiVIP(KursiVIP sumber) {
        super(sumber);
    }

    @Override
    public String getTipeKursi() {
        return "VIP";
    }

    @Override
    public double getMultiplier() {
        return 1.5;
    }

    @Override
    public String getFasilitas() {
        return "Kursi Recliner Mewah + Ruang Kaki Ekstra";
    }

    @Override
    public Kursi salin() {
        return new KursiVIP(this);
    }
}
