package model.user;

import model.tiket.Tiket;

/**
 * Pengguna dengan peran pelanggan yang memesan tiket bioskop.
 * Merupakan turunan dari {@link Person} dan mengimplementasikan perilaku polimorfik peran Pelanggan.
 * Selain ID dan nama (dari {@link Person}), pelanggan memiliki email dan nomor telepon dengan enkapsulasi ketat.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
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
     * Membuat pelanggan baru dengan memanggil konstruktor superclass {@link Person}.
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

    @Override
    public String getRole() {
        return "Pelanggan";
    }

    @Override
    public void tampilkanDashboardInfo() {
        System.out.println("=== DASHBOARD PELANGGAN ===");
        System.out.println("ID      : " + getId());
        System.out.println("Nama    : " + getNama());
        System.out.println("Peran   : " + getRole());
        System.out.println("Email   : " + email);
        System.out.println("No Telp : " + noTelp);
    }

    /**
     * Mencetak pesan simulasi bahwa daftar film ditampilkan.
     */
    public void lihatFilm() {
        System.out.println("Menampilkan daftar film yang tersedia");
    }

    /**
     * Mencetak pesan simulasi bahwa jadwal film ditampilkan.
     */
    public void lihatJadwal() {
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
    public String getEmail() {
        return email;
    }

    /**
     * Mengubah email pelanggan.
     *
     * @param email alamat email baru
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Mengambil nomor telepon pelanggan.
     *
     * @return nomor telepon pelanggan
     */
    public String getNoTelp() {
        return noTelp;
    }

    /**
     * Mengubah nomor telepon pelanggan.
     *
     * @param noTelp nomor telepon baru
     */
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
}
