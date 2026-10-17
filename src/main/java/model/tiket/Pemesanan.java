package model.tiket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.bioskop.Jadwal;
import model.bioskop.Kursi;
import model.user.Pelanggan;

/**
 * Satu sesi pemesanan tiket oleh seorang {@link Pelanggan} untuk satu {@link Jadwal}.
 * Pemesanan menyimpan daftar {@link Tiket}, menghitung total harga, dan dapat mencetak struk.
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
public class Pemesanan {
    /**
     * Penghitung bersama semua pemesanan, dipakai untuk membuat kode booking berurutan.
     */
    private static int counter = 0;
    /**
     * Kode booking unik, formatnya {@code BOOK-0001}, {@code BOOK-0002}, dan seterusnya.
     */
    private String kodeBooking;
    /**
     * Pelanggan yang melakukan pemesanan.
     */
    private Pelanggan pelanggan;
    /**
     * Jadwal tayang yang dipesan.
     */
    private Jadwal jadwal;
    /**
     * Daftar tiket yang sudah masuk ke pemesanan ini.
     */
    private ArrayList<Tiket> daftarTiket;
    /**
     * Waktu pemesanan dibuat.
     */
    private LocalDateTime waktuTransaksi;
    /**
     * Total harga seluruh tiket dalam pemesanan ini.
     */
    private double totalHarga;

    /**
     * Membuat pemesanan baru dengan kode booking otomatis dan waktu transaksi saat ini.
     *
     * @param pelanggan pelanggan yang memesan
     * @param jadwal    jadwal tayang yang dipesan
     */
    public Pemesanan(Pelanggan pelanggan, Jadwal jadwal) {
        counter++;
        this.kodeBooking = String.format("BOOK-%04d", counter);
        this.pelanggan = pelanggan;
        this.jadwal = jadwal;
        this.daftarTiket = new ArrayList<>();
        this.waktuTransaksi = LocalDateTime.now();
        this.totalHarga = 0.0;
    }

    
    /**
     * Memesan satu kursi berdasarkan kodenya lalu menambahkan tiketnya ke pemesanan.
     * Jenis tiket {@code "VIP"} (tanpa membedakan huruf besar/kecil) menghasilkan {@link TiketVIP};
     * selain itu menghasilkan {@link TiketReguler}. Total harga ikut bertambah.
     *
     * @param kodeKursi kode kursi, misalnya {@code "A1"}
     * @param jenisTiket jenis tiket, {@code "VIP"} atau {@code "Reguler"}
     * @return {@code true} jika berhasil; {@code false} jika kode kosong, kursi tidak ada, atau sudah terisi
     */
    public boolean tambahKursi(String kodeKursi, String jenisTiket) {
        if (kodeKursi == null || kodeKursi.isBlank()) {
            System.out.println("[GAGAL] Kode kursi tidak boleh kosong!");
            return false;
        }

        
        Kursi kursi = jadwal.cariKursi(kodeKursi);
        if (kursi == null) {
            System.out.println("[GAGAL] Kursi " + kodeKursi + " tidak ada di studio " + jadwal.getStudio().getNama());
            return false;
        }

        
        if (!jadwal.pesanKursi(kursi)) {
            System.out.println("[GAGAL] Kursi " + kodeKursi + " sudah terisi.");
            return false;
        }

        
        Tiket tiket;
        if ("VIP".equalsIgnoreCase(jenisTiket)) {
            tiket = new TiketVIP(jadwal, kursi);
        } else {
            tiket = new TiketReguler(jadwal, kursi);
        }

        daftarTiket.add(tiket);
        totalHarga += tiket.hitungHarga();
        return true;
    }

    // Overload Paramter Methode 1
    /**
     * Versi overload dari {@link #tambahKursi(String, String)} yang menerima objek {@link Kursi}.
     *
     * @param kursi      kursi yang dipesan
     * @param jenisTiket jenis tiket, {@code "VIP"} atau {@code "Reguler"}
     * @return {@code true} jika berhasil; {@code false} jika kursi {@code null} atau gagal dipesan
     */
    public boolean tambahKursi(Kursi kursi, String jenisTiket) {
        if (kursi == null) {
            System.out.println("[GAGAL] Objek kursi tidak valid (null)!");
            return false;
        }
        
        return tambahKursi(kursi.getKode(), jenisTiket);
    }

    /**
     * Mencetak struk pemesanan lengkap (detail tiket dan total harga) ke konsol.
     */
    public void cetakStruk() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("========================================");
        System.out.println("            STRUK PEMESANAN             ");
        System.out.println("========================================");
        System.out.println("Kode Booking : " + kodeBooking);
        System.out.println("Waktu Pesan  : " + waktuTransaksi.format(fmt));
        System.out.println("Pelanggan    : " + pelanggan.getNama() + " (" + pelanggan.getId() + ")");
        System.out.println("Film         : " + jadwal.getFilm().getJudul());
        System.out.println("Studio       : " + jadwal.getStudio().getNama());
        System.out.println("Tayang       : " + jadwal.getWaktuFormat());
        System.out.println("----------------------------------------");
        System.out.println("Detail Tiket (" + daftarTiket.size() + " tiket):");
        for (Tiket t : daftarTiket) {
            System.out.println(" - " + t.getKodeTiket() + " | " + t.getJenisTiket() 
                + " | Kursi: " + t.getKursi().getKode() 
                + " | Rp" + String.format("%,.0f", t.hitungHarga()));
        }
        System.out.println("----------------------------------------");
        System.out.println("TOTAL HARGA  : Rp" + String.format("%,.0f", totalHarga));
        System.out.println("========================================");
    }

    /**
     * Mengambil kode booking.
     *
     * @return kode booking
     */
    public String getKodeBooking() { return kodeBooking; }
    /**
     * Mengambil pelanggan pemesan.
     *
     * @return pelanggan pemesan
     */
    public Pelanggan getPelanggan() { return pelanggan; }
    /**
     * Mengambil jadwal yang dipesan.
     *
     * @return jadwal tayang
     */
    public Jadwal getJadwal() { return jadwal; }
    /**
     * Mengambil daftar tiket pada pemesanan ini.
     *
     * @return daftar tiket
     */
    public ArrayList<Tiket> getDaftarTiket() { return daftarTiket; }
    /**
     * Mengambil total harga pemesanan.
     *
     * @return total harga dalam rupiah
     */
    public double getTotalHarga() { return totalHarga; }
    /**
     * Mengambil waktu transaksi.
     *
     * @return waktu pemesanan dibuat
     */
    public LocalDateTime getWaktuTransaksi() { return waktuTransaksi; }

    /**
     * Mengambil nama pelanggan untuk ditampilkan di tabel.
     *
     * @return nama pelanggan, atau {@code "-"} jika tidak ada
     */
    public String getNamaPelanggan() {
        return pelanggan != null ? pelanggan.getNama() : "-";
    }

    /**
     * Mengambil nomor telepon pelanggan untuk ditampilkan di tabel.
     *
     * @return nomor telepon, atau {@code "-"} jika tidak ada
     */
    public String getNoTelpPelanggan() {
        return pelanggan != null ? pelanggan.getNoTelp() : "-";
    }

    /**
     * Mengambil judul film yang dipesan.
     *
     * @return judul film, atau {@code "-"} jika tidak ada
     */
    public String getJudulFilm() {
        return (jadwal != null && jadwal.getFilm() != null) ? jadwal.getFilm().getJudul() : "-";
    }

    /**
     * Mengambil nama studio tempat film tayang.
     *
     * @return nama studio, atau {@code "-"} jika tidak ada
     */
    public String getNamaStudio() {
        return (jadwal != null && jadwal.getStudio() != null) ? jadwal.getStudio().getNama() : "-";
    }

    /**
     * Mengambil semua kode kursi dalam satu teks.
     *
     * @return kode kursi dipisah koma (misalnya {@code "A1, A2"}), atau {@code "-"} jika belum ada tiket
     */
    public String getKodeKursiList() {
        if (daftarTiket.isEmpty()) return "-";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < daftarTiket.size(); i++) {
            sb.append(daftarTiket.get(i).getKursi().getKode());
            if (i < daftarTiket.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    /**
     * Mengambil semua jenis tiket dalam satu teks.
     *
     * @return jenis tiket dipisah koma, atau {@code "-"} jika belum ada tiket
     */
    public String getJenisTiketList() {
        if (daftarTiket.isEmpty()) return "-";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < daftarTiket.size(); i++) {
            sb.append(daftarTiket.get(i).getJenisTiket());
            if (i < daftarTiket.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    /**
     * Mengambil waktu transaksi dalam format teks.
     *
     * @return waktu transaksi berformat {@code dd-MM-yyyy HH:mm}
     */
    public String getWaktuFormat() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return waktuTransaksi.format(fmt);
    }
}