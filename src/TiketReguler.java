public class TiketReguler extends Tiket {

    public TiketReguler(Jadwal jadwal, Kursi kursi) {
        super(jadwal, kursi);   // memanggil constructor Tiket
    }

    @Override
    public double hitungHarga() {
        return jadwal.getHargaDasar();   // harga standar
    }

    @Override
    public String getJenisTiket() {
        return "Reguler";
    }
}