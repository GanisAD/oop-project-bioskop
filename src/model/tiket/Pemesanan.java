package model.tiket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.bioskop.Jadwal;
import model.bioskop.Kursi;
import model.user.Pelanggan;

public class Pemesanan {
    private static int counter = 0;
    private String kodeBooking;
    private Pelanggan pelanggan;
    private Jadwal jadwal;
    private ArrayList<Tiket> daftarTiket;
    private LocalDateTime waktuTransaksi;
    private double totalHarga;

    public Pemesanan(Pelanggan pelanggan, Jadwal jadwal) {
        counter++;
        this.kodeBooking = String.format("BOOK-%04d", counter);
        this.pelanggan = pelanggan;
        this.jadwal = jadwal;
        this.daftarTiket = new ArrayList<>();
        this.waktuTransaksi = LocalDateTime.now();
        this.totalHarga = 0.0;
    }

    // Method untuk memesan kursi dan membuat tiket
    public boolean tambahKursi(Kursi kursi, String jenisTiket) {
        if (kursi == null) {
            System.out.println("Kursi tidak ditemukan!");
            return false;
        }

        // Validasi dan pesan kursi di jadwal
        if (!jadwal.pesanKursi(kursi)) {
            System.out.println("Kursi " + kursi.getKode() + " gagal dipesan (sudah terisi).");
            return false;
        }

        // Buat objek tiket berdasarkan jenisnya
        Tiket tiket;
        if (jenisTiket.equalsIgnoreCase("VIP")) {
            tiket = new TiketVIP(jadwal, kursi);
        } else {
            tiket = new TiketReguler(jadwal, kursi);
        }

        daftarTiket.add(tiket);
        totalHarga += tiket.hitungHarga();
        return true;
    }

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

    // Getter
    public String getKodeBooking() { return kodeBooking; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public Jadwal getJadwal() { return jadwal; }
    public ArrayList<Tiket> getDaftarTiket() { return daftarTiket; }
    public double getTotalHarga() { return totalHarga; }
}