package model.user;
import model.tiket.Tiket;

public class Pelanggan extends Person {
    private String email;
    private String noTelp;

    public Pelanggan(String id, String nama, String email, String noTelp) {
        super(id, nama);
        this.email = email;
        this.noTelp = noTelp;
    }

    public void lihatFilm(){
        System.out.println("Menampilkan daftar film yang tersedia");
    }

    public void lihatJadwal(){
        System.out.println("Menampilkan jadwal film yang tersedia");
    }

    public Tiket beliTiket(){
        System.out.println("Membeli tiket film");
        return null;
    }
}
