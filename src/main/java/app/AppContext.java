package app;

import model.bioskop.Bioskop;
import model.user.Admin;
import model.user.Pelanggan;

public class AppContext {
    private static AppContext instance;
    private final Bioskop bioskop;
    private final Admin admin;
    private Pelanggan activePelanggan;

    private AppContext() {
        this.bioskop = new Bioskop("Cinema XXI Surabaya");
        this.admin = new Admin("ADM-01", "Budi Admin");
        this.activePelanggan = new Pelanggan("CUST-001", "Pengunjung", "-", "081234567890");
    }

    public static synchronized AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Pelanggan getActivePelanggan() {
        return activePelanggan;
    }

    public void setActivePelanggan(Pelanggan activePelanggan) {
        this.activePelanggan = activePelanggan;
    }
}
