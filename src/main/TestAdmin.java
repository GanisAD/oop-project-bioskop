package main;

import java.time.LocalDateTime;
import model.bioskop.Bioskop;
import model.user.Admin;

public class TestAdmin {
    public static void main(String[] args) {
        Bioskop xxi = new Bioskop("Cinema XXI Surabaya");
        Admin admin = new Admin("ADM-001", "Budi");

        // Simulasi input dari form GUI (hanya mengirim String & int)
        admin.tambahFilm(xxi, "Interstellar", "Sci-Fi", 169, "13+");
        admin.tambahStudio(xxi, "Studio IMAX 3D", 4, 6);

        // Menjadwalkan film hanya berbekal judul dan nama studio (sesuai pilihan dropdown GUI)
        admin.tambahJadwal(
            xxi, 
            "Interstellar", 
            "Studio IMAX 3D", 
            LocalDateTime.of(2026, 9, 26, 20, 0), 
            100000
        );
        System.out.println();

        xxi.tampilkanDaftarJadwal();
    }
}