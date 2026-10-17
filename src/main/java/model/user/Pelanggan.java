package model.user;
import model.tiket.Tiket;

/**
 * Pengguna dengan peran pelanggan yang memesan tiket bioskop.
 * Selain ID dan nama (dari {@link Person}), pelanggan memiliki email dan nomor telepon.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class Pelanggan extends Person {
    /**
     * Alamat email pelanggan.
     */
    private String email;
    /**
     * Nomor telepon pelanggan.
     */
    private String noTelp;

    /**
     * Membuat pelanggan baru.
     *
     * @param id     ID unik pelanggan
     * @param nama   nama pelanggan
     * @param email  alamat email pelanggan
     * @param noTelp nomor telepon pelanggan
     */
    public Pelanggan(String id, String nama, String email, String noTelp) {
        super(id, nama);
        this.email = email;
        this.noTelp = noTelp;
    }

    /**
     * Mencetak pesan simulasi bahwa daftar film ditampilkan.
     */
    public void lihatFilm(){
        System.out.println("Menampilkan daftar film yang tersedia");
    }

    /**
     * Mencetak pesan simulasi bahwa jadwal film ditampilkan.
     */
    public void lihatJadwal(){
        System.out.println("Menampilkan jadwal film yang tersedia");
    }
    /**
     * Mencetak pesan simulasi pembelian tiket.
     * Pembelian sebenarnya dilakukan melalui {@link model.tiket.Pemesanan}.
     *
     * @return selalu {@code null} (belum ada tiket yang dibuat oleh method ini)
     */
    public Tiket beliTiket() {
        System.out.println("Membeli tiket film");
        return null;
    }

    /**
     * Mengambil email pelanggan.
     *
     * @return alamat email pelanggan
     */
    public String getEmail() { return email; }
    /**
     * Mengambil nomor telepon pelanggan.
     *
     * @return nomor telepon pelanggan
     */
    public String getNoTelp() { return noTelp; }
}
