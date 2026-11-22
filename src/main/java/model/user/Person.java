package model.user;

/**
 * Kelas induk abstrak (abstract superclass) untuk semua jenis pengguna sistem bioskop.
 * Menyimpan identitas dasar berupa ID dan nama dengan enkapsulasi ketat (private),
 * lalu diwarisi oleh {@link Admin} dan {@link Pelanggan}.
 * Mendefinisikan kontrak method abstrak untuk perilaku polimorfik pengguna.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 2.0
 */
public abstract class Person {
    /**
     * ID unik pengguna, misalnya {@code "CUST-001"} atau {@code "ADM-01"}.
     */
    private String id;

    /**
     * Nama lengkap pengguna.
     */
    private String nama;

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
     * Mengambil peran (role) pengguna secara polimorfik.
     * Wajib di-override oleh subclass seperti {@link Admin} dan {@link Pelanggan}.
     *
     * @return nama peran pengguna (misal: "Admin" atau "Pelanggan")
     */
    public abstract String getRole();

    /**
     * Menampilkan informasi dashboard khusus sesuai peran pengguna secara polimorfik.
     */
    public abstract void tampilkanDashboardInfo();

    /**
     * Mengambil ID pengguna.
     *
     * @return ID pengguna
     */
    public String getId() {
        return id;
    }

    /**
     * Mengubah ID pengguna.
     *
     * @param id ID baru pengguna
     */
    public void setId(String id) {
        this.id = id;
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
     * Mengubah nama pengguna.
     *
     * @param nama nama baru pengguna
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * Mencetak ID, nama, dan peran pengguna ke konsol.
     */
    public void tampilkanInfo() {
        System.out.println("ID   : " + id);
        System.out.println("Nama : " + nama);
        System.out.println("Role : " + getRole());
    }
}
