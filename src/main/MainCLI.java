package main;

import java.util.Scanner;
import model.bioskop.Bioskop;
import model.bioskop.Jadwal;
import model.tiket.Pemesanan;
import model.user.Admin;
import model.user.Pelanggan;

public class MainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Sesuai instruksi dosen: hanya Bioskop yang di-construct manual di awal
        Bioskop bioskop = new Bioskop("Cinema XXI Surabaya");
        Admin admin = new Admin("ADM-01", "Budi Admin");

        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n=== APLIKASI SISTEM " + bioskop.getNama().toUpperCase() + " ===");
            System.out.println("1. Masuk sebagai Pelanggan (Pesan Tiket)");
            System.out.println("2. Masuk sebagai Admin (Kelola Bioskop)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline buffer

            switch (menu) {
                case 1:
                    // Alur Pelanggan
                    System.out.print("\nMasukkan Nama Anda : ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan No HP     : ");
                    String noHp = scanner.nextLine();
                    Pelanggan pelanggan = new Pelanggan("CUST-" + System.currentTimeMillis() % 1000, nama, "-", noHp);

                    // Tampilkan jadwal yang tersedia di bioskop
                    bioskop.tampilkanDaftarJadwal();
                    System.out.print("Pilih nomor jadwal [0, 1, 2, ...]: ");
                    int idxJadwal = scanner.nextInt();
                    scanner.nextLine();

                    Jadwal jadwalTerpilih = bioskop.cariJadwal(idxJadwal);
                    if (jadwalTerpilih == null) {
                        System.out.println("Jadwal tidak valid!");
                        break;
                    }

                    // Tampilkan denah kursi jadwal tersebut
                    System.out.println("\nDenah Kursi Saat Ini:");
                    jadwalTerpilih.tampilkanDenahKursi();

                    // Buat sesi pemesanan baru
                    Pemesanan pesanan = bioskop.buatPemesanan(pelanggan, jadwalTerpilih);

                    System.out.print("\nMasukkan Kode Kursi yang dipilih (misal: A1): ");
                    String kodeKursi = scanner.nextLine().toUpperCase();
                    System.out.print("Pilih Tipe Tiket (Reguler/VIP): ");
                    String jenis = scanner.nextLine();

                    // Masukkan kursi ke transaksi
                    boolean sukses = pesanan.tambahKursi(kodeKursi, jenis);
                    if (sukses) {
                        System.out.println("\nPemesanan Berhasil! Berikut Struk Anda:");
                        pesanan.cetakStruk();
                    } else {
                        System.out.println("\nPemesanan Gagal dilakukan!");
                    }
                    break;

                case 2:
                    // Alur Admin
                    System.out.println("\n--- MENU ADMIN (" + admin.getNama() + ") ---");
                    System.out.println("1. Lihat Rekap Seluruh Transaksi");
                    System.out.println("2. Tambah Film Baru");
                    System.out.print("Pilihan: ");
                    int subMenu = scanner.nextInt();
                    scanner.nextLine();

                    if (subMenu == 1) {
                        admin.lihatTransaksi(bioskop);
                    } else if (subMenu == 2) {
                        System.out.print("Judul Film : ");
                        String judul = scanner.nextLine();
                        System.out.print("Genre      : ");
                        String genre = scanner.nextLine();
                        System.out.print("Durasi (menit): ");
                        int durasi = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Rating Usia: ");
                        String rating = scanner.nextLine();

                        admin.tambahFilm(bioskop, judul, genre, durasi, rating);
                    }
                    break;

                case 3:
                    System.out.println("Terima kasih telah menggunakan sistem bioskop!");
                    berjalan = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }
}