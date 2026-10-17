package model.user;
/**
 * Kelas induk (superclass) untuk semua jenis pengguna sistem bioskop.
 * Menyimpan identitas dasar berupa ID dan nama, lalu diwarisi oleh {@link Admin} dan {@link Pelanggan}.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class Person {
    /**
     * ID unik pengguna, misalnya {@code "CUST-001"} atau {@code "ADM-01"}.
     */
    public String id;
    /**
     * Nama lengkap pengguna.
     */
    public String nama;

    /**
     * Membuat pengguna baru.
     *
     * @param id   ID unik pengguna
     * @param nama nama lengkap pengguna
     */
    public Person(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    /**
     * Mengambil ID pengguna.
     *
     * @return ID pengguna
     */
    public String getId() {
        return id;
    }

    /**
     * Mengambil nama pengguna.
     *
     * @return nama pengguna
     */
    public String getNama() {
        return nama;
    }

    /**
     * Mencetak ID dan nama pengguna ke konsol.
     */
    public void tampilkanInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nama: " + nama);
    }
}
