public class Admin extends Person {
    public Admin(String id, String nama) {
        super(id, nama);
    }

    public void tambahFilm(Film film) {
        System.out.println("Film berhasil ditambahkan: " + film.getJudul());
    }

    public void hapusFilm(Film film) {
        System.out.println("Film berhasil dihapus: " + film.getJudul());
    }

    public void tambahStudio(Studio studio) {
        System.out.println("Studio berhasil ditambahkan: " + studio.getNama());
    }

    public void tambahJadwal(Film film) {
        System.out.println("Jadwal film berhasil ditambahkan: " + film.getJudul());
    }
    public void lihatTransaksi() {
        System.out.println("Menampilkan daftar transaksi");
    }
}
