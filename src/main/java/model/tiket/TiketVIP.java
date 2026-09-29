package model.tiket;
import model.bioskop.Jadwal;
import model.bioskop.Kursi;

public class TiketVIP extends Tiket {
    private static final double BIAYA_VIP = 25000;
    private String fasilitas;

    public TiketVIP(Jadwal jadwal, Kursi kursi) {
        super(jadwal, kursi);
        this.fasilitas = "Kursi Recliner + Snack + Minuman";
    }

    @Override
    public double hitungHarga() {
        return jadwal.getHargaDasar() + BIAYA_VIP;   // harga standar + biaya VIP
    }

    @Override
    public String getJenisTiket() {
        return "VIP";
    }

    @Override
    public void cetakTiket() {
        super.cetakTiket();   // pakai cetak bawaan dulu
        System.out.println("Fasilitas  : " + fasilitas);   // lalu tambah khusus VIP
    }
}