# Sistem Pemesanan Tiket Bioskop (OOP Java)

Aplikasi konsol interaktif berbasis Java yang mengimplementasikan prinsip-prinsip Pemrograman Berorientasi Objek (OOP) untuk mensimulasikan sistem bioskop lengkap. Aplikasi ini mencakup antarmuka baris perintah (CLI) untuk manajemen bioskop oleh admin serta alur pemesanan tiket oleh pelanggan.

---

## 📌 Fitur Utama

* **Antarmuka Interaktif CLI (`MainCLI`):**
  * Menu navigasi utama untuk login/peran Admin dan Pelanggan.
* **Manajemen Peran Pengguna (User Management):**
  * **Admin:** Mengelola data film, jadwal tayang, dan studio bioskop.
  * **Pelanggan:** Memilih jadwal tayang film, memilih kursi studio, melakukan pemesanan tiket, serta melihat struk transaksi dan riwayat pesanan.
* **Manajemen Bioskop & Studio:**
  * Pengelompokan bioskop berdasarkan studio, denah kursi, dan jadwal penayangan.
  * Pengecekan ketersediaan kursi secara dinamis saat pemilihan kursi berlangsung.
* **Sistem Tiket & Transaksi:**
  * Polimorfisme tiket melalui **Tiket Reguler** dan **Tiket VIP** (dengan fasilitas dan biaya tambahan).
  * Kalkulasi total pembayaran, status transaksi, dan pencetakan struk.

---

## 🏛️ Penerapan Konsep OOP

1. **Inheritance (Pewarisan):**
   * `Person` sebagai superclass yang diturunkan ke `Admin` dan `Pelanggan`.
   * `Tiket` sebagai superclass yang diturunkan ke `TiketReguler` dan `TiketVIP`.
2. **Polymorphism (Polimorfisme):**
   * Implementasi method `hitungHarga()` dan `cetakTiket()` yang memiliki perilaku berbeda pada `TiketReguler` dan `TiketVIP`.
3. **Encapsulation (Enkapsulasi):**
   * Semua variabel instans dikontrol menggunakan visibility modifier (`private` / `protected`) dan diakses melalui metode `getter` dan `setter`.
4. **Abstraction (Abstraksi):**
   * Pembuatan kelas abstrak (`Person`, `Tiket`) sebagai cetak biru fungsionalitas yang wajib diimplementasikan oleh kelas turunan.

---

## 📊 Class Diagram

```mermaid
classDiagram
    direction TB

    %% PACKAGE: model.user
    class Person {
        <<abstract>>
        #String id
        #String nama
        #String email
        #String noTelp
        +getId() String
        +getNama() String
        +getEmail() String
        +getNoTelp() String
        +tampilkanProfil()* void
    }

    class Admin {
        -String levelAkses
        +tampilkanProfil() void
        +tambahFilm(Bioskop bioskop, Film film) void
        +hapusFilm(Bioskop bioskop, String idFilm) boolean
        +tambahJadwal(Bioskop bioskop, Jadwal jadwal) void
        +kelolaStudio(Bioskop bioskop, Studio studio) void
    }

    class Pelanggan {
        -int poinLoyalti
        -List~Pemesanan~ riwayatPemesanan
        +getPoinLoyalti() int
        +tambahPoin(int poin) void
        +tampilkanProfil() void
        +buatPemesanan(Jadwal jadwal, List~Kursi~ kursiDipilih, String tipeTiket) Pemesanan
        +getRiwayatPemesanan() List~Pemesanan~
    }

    Person <|-- Admin : Inheritance
    Person <|-- Pelanggan : Inheritance

    %% PACKAGE: model.bioskop
    class Bioskop {
        -String namaBioskop
        -String lokasi
        -List~Studio~ daftarStudio
        -List~Film~ daftarFilm
        -List~Jadwal~ daftarJadwal
        +tambahStudio(Studio studio) void
        +tambahFilm(Film film) void
        +tambahJadwal(Jadwal jadwal) void
        +cariFilm(String keyword) Film
        +getDaftarStudio() List~Studio~
        +getDaftarFilm() List~Film~
        +getDaftarJadwal() List~Jadwal~
        +tampilkanDaftarFilm() void
        +tampilkanSemuaJadwal() void
    }

    class Film {
        -String idFilm
        -String judul
        -String genre
        -int durasiMenit
        -double ratingUsia
        +getIdFilm() String
        +getJudul() String
        +getGenre() String
        +getDurasiMenit() int
        +getDetailFilm() String
    }

    class Studio {
        -String idStudio
        -String namaStudio
        -String tipeStudio
        -List~Kursi~ daftarKursi
        +getIdStudio() String
        +getNamaStudio() String
        +getTipeStudio() String
        +getDaftarKursi() List~Kursi~
        +cariKursi(String nomorKursi) Kursi
    }

    class Kursi {
        -String nomorKursi
        -boolean isTersedia
        +getNomorKursi() String
        +isTersedia() boolean
        +setTersedia(boolean status) void
    }

    class Jadwal {
        -String idJadwal
        -Film film
        -Studio studio
        -String waktuMulai
        -double hargaDasar
        +getIdJadwal() String
        +getFilm() Film
        +getStudio() Studio
        +getWaktuMulai() String
        +getHargaDasar() double
        +tampilkanDenahKursi() void
        +pesanKursi(String nomorKursi) boolean
    }

    Bioskop "1" *-- "*" Studio : Composition
    Bioskop "1" o-- "*" Film : Aggregation
    Bioskop "1" o-- "*" Jadwal : Manages
    Studio "1" *-- "*" Kursi : Composition
    Jadwal "1" --> "1" Film : References
    Jadwal "1" --> "1" Studio : References

    %% PACKAGE: model.tiket
    class Tiket {
        <<abstract>>
        #String idTiket
        #Jadwal jadwal
        #Kursi kursi
        #double harga
        +getIdTiket() String
        +getJadwal() Jadwal
        +getKursi() Kursi
        +hitungHarga()* double
        +cetakTiket()* void
    }

    class TiketReguler {
        +hitungHarga() double
        +cetakTiket() void
    }

    class TiketVIP {
        -double serviceFee
        -List~String~ fasilitasTambahan
        +hitungHarga() double
        +cetakTiket() void
        +getFasilitas() List~String~
    }

    Tiket <|-- TiketReguler : Inheritance
    Tiket <|-- TiketVIP : Inheritance
    Tiket "1" --> "1" Jadwal : References
    Tiket "1" --> "1" Kursi : References

    class Pemesanan {
        -String idPemesanan
        -Pelanggan pelanggan
        -List~Tiket~ daftarTiket
        -double totalBayar
        -String statusPembayaran
        +tambahTiket(Tiket tiket) void
        +hitungTotal() double
        +prosesPembayaran(double jumlahBayar) boolean
        +cetakStruk() void
    }

    Pelanggan "1" o-- "*" Pemesanan : Has
    Pemesanan "1" *-- "*" Tiket : Composition
    Pemesanan --> Pelanggan : Associated with

    %% PACKAGE: main
    class MainCLI {
        -Bioskop bioskop
        -Scanner scanner
        +main(String[] args)$ void
        +menuUtama() void
        +menuAdmin() void
        +menuPelanggan() void
    }

    class TestAdmin {
        +main(String[] args)$ void
    }

    class TestPemesanan {
        +main(String[] args)$ void
    }

    MainCLI ..> Bioskop : Controls
    MainCLI ..> Admin : Uses
    MainCLI ..> Pelanggan : Uses
    TestAdmin ..> Admin : Tests
    TestAdmin ..> Bioskop : Tests
    TestPemesanan ..> Pelanggan : Tests
    TestPemesanan ..> Pemesanan : Tests
```

---

## 📂 Struktur Proyek

```
oop-project-bioskop/
├── src/
│   ├── main/
│   │   ├── MainCLI.java            # Entry point antarmuka interaktif CLI
│   │   ├── TestAdmin.java          # Kelas pengujian fungsionalitas Admin
│   │   └── TestPemesanan.java      # Kelas pengujian transaksi pemesanan
│   └── model/
│       ├── bioskop/
│       │   ├── Bioskop.java        # Representasi data bioskop & manajemen jadwal
│       │   ├── Film.java           # Model film
│       │   ├── Jadwal.java         # Model jadwal pemutaran film & denah kursi
│       │   ├── Kursi.java          # Model kursi & status ketersediaan
│       │   └── Studio.java         # Model studio penayangan
│       ├── tiket/
│       │   ├── Pemesanan.java      # Model transaksi pemesanan tiket
│       │   ├── Tiket.java          # Base abstract class tiket
│       │   ├── TiketReguler.java   # Subclass tiket reguler
│       │   └── TiketVIP.java       # Subclass tiket VIP
│       └── user/
│           ├── Admin.java          # Subclass akun administrator
│           ├── Pelanggan.java      # Subclass akun pelanggan
│           └── Person.java         # Base abstract class entitas pengguna
├── .gitattributes
├── .gitignore
├── LICENSE
└── README.md
```

---

## 🚀 Panduan Menjalankan Program

### Prasyarat
* **Java Development Kit (JDK)** versi 8 atau yang lebih baru.

### Kompilasi

Buka terminal pada direktori root proyek dan jalankan perintah berikut:

```bash
# Untuk Linux / macOS
javac -d bin $(find src -name "*.java")

# Untuk Windows (Command Prompt)
dir /s /B src\*.java > sources.txt
javac -d bin @sources.txt
del sources.txt

# Untuk Windows (PowerShell)
javac -d bin (Get-ChildItem -Recurse -Filter *.java src | Resolve-Path)
```

### Menjalankan Program

1. **Menjalankan Program Utama (Interactive CLI):**
   ```bash
   java -cp bin main.MainCLI
   ```

2. **Menjalankan Pengujian Modul Admin:**
   ```bash
   java -cp bin main.TestAdmin
   ```

3. **Menjalankan Pengujian Alur Pemesanan:**
   ```bash
   java -cp bin main.TestPemesanan
   ```

---

## 📄 Lisensi

Didistribusikan di bawah ketentuan lisensi open-source sesuai file [LICENSE](LICENSE).