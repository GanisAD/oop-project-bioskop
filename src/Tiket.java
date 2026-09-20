public abstract class Tiket {
    private static int counter = 0;   // dipakai bersama semua tiket

    private String kodeTiket;
    protected Jadwal jadwal;
    protected Kursi kursi;

    public Tiket(Jadwal jadwal, Kursi kursi) {
        counter++;
        this.kodeTiket = String.format("TKT-%03d", counter);
        this.jadwal = jadwal;
        this.kursi = kursi;
    }

    // Wajib di-override oleh anak class
    public abstract double hitungHarga();
    public abstract String getJenisTiket();

    public String getKodeTiket() { return kodeTiket; }
    public Jadwal getJadwal() { return jadwal; }
    public Kursi getKursi() { return kursi; }

    public void cetakTiket() {
        System.out.println("========== TIKET BIOSKOP ==========");
        System.out.println("Kode Tiket : " + kodeTiket);
        System.out.println("Jenis      : " + getJenisTiket());
        System.out.println("Film       : " + jadwal.getFilm().getJudul());
        System.out.println("Studio     : " + jadwal.getStudio().getNama());
        System.out.println("Tayang     : " + jadwal.getWaktuFormat());
        System.out.println("Kursi      : " + kursi.getKode());
        System.out.println("Harga      : Rp" + String.format("%,.0f", hitungHarga()));
    }
}