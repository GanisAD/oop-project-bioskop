# 🍿 Cinema XXI - Sistem Pemesanan & Manajemen Bioskop (OOP Java)

Aplikasi Desktop sistem bioskop modern berbasis **JavaFX**, **FXML**, dan **Apache Maven** untuk mata kuliah Object-Oriented Programming (OOP).

---

## 🌟 Fitur Utama

### 🎬 1. Alur Pelanggan (Customer Booking Experience)
- **Katalog Film Sedang Tayang**: Tampilan kartu film modern lengkap dengan poster box, judul, genre, durasi, dan badge batas usia (misal `13+`).
- **Jadwal & Studio Chips**: Jadwal tayang dikelompokkan berdasarkan studio dengan tombol chip waktu dan harga tiket yang interaktif.
- **Pencarian Film Real-time**: Fitur pencarian cepat berdasarkan judul film atau genre.
- **Denah Kursi Studio Interaktif**:
  - Representasi lengkung visual layar bioskop.
  - Matriks kursi dinamis (`Studio.getJumlahBaris()`, `Studio.getKursiPerBaris()`) dengan lorong jalan.
  - Indikator visual real-time: **Tersedia (Emerald)**, **Terpilih (Cinema Gold)**, dan **Sudah Terisi (Disabled Muted)**.
- **Opsi Tipe Tiket**:
  - **Reguler**: Sesuai harga dasar studio/jadwal.
  - **VIP**: Tambahan fasilitas kursi *recliner*, snack, dan minuman (+Rp 25.000).
- **E-Tiket & Struk Transaksi Eksklusif**: Tampilan struk bergaya *boarding pass* lengkap dengan kode booking (`BOOK-xxxx`), detail pemesanan, dan mock barcode.

### ⚙️ 2. Panel Pengelola Bioskop (Admin Mode)
- **Ringkasan Metrik / KPI**: Kartu statistik total omset pendapatan, total transaksi pemesanan tiket, total film aktif, dan jumlah studio.
- **Manajemen Film**:
  - Tabel daftar seluruh film yang terdaftar.
  - Formulir pendaftaran film baru dengan validasi otomatis (judul, genre, durasi > 0, rating usia).
  - Aksi hapus film beserta jadwal terkait dengan dialog konfirmasi.
- **Manajemen Jadwal Tayang**:
  - Formulir pembukaan jadwal baru dengan relasi objek Film, Studio, DatePicker tanggal, ComboBox jam tayang, dan harga tiket.
  - Tabel jadwal tayang aktif beserta indikator kapasitas kursi yang sudah laku terjual.
- **Rekapitulasi Transaksi**:
  - Tabel riwayat seluruh pemesanan tiket.
  - Pencarian transaksi berdasarkan kode booking, nama pemesan, atau judul film.

---

## 🏛️ Penerapan Konsep OOP

1. **Inheritance (Pewarisan)**:
   - `Person` diturunkan menjadi `Pelanggan` dan `Admin`.
   - `Tiket` (abstract) diturunkan menjadi `TiketReguler` dan `TiketVIP`.
2. **Polymorphism (Polimorfisme)**:
   - Override metode `hitungHarga()` dan `getJenisTiket()` pada `TiketReguler` dan `TiketVIP`.
   - Overloading metode `tambahKursi(String kodeKursi, String jenis)` dan `tambahKursi(Kursi kursi, String jenis)`.
3. **Encapsulation (Enkapsulasi)**:
   - Penerapan *access modifier* `private` pada data atribut.
   - Validasi ketat pada setter (misal: judul film tidak boleh kosong, durasi > 0, kapasitas studio 1-26 baris).
4. **Abstraction & Aggregation**:
   - `Bioskop` sebagai *aggregate root* yang mengelola koleksi `Film`, `Studio`, `Jadwal`, dan `Pemesanan`.
   - Pemisahan arsitektur **MVC (Model-View-Controller)** yang bersih antara UI JavaFX/FXML dan model bisnis.

---

## 🚀 Cara Menjalankan Aplikasi

### Persyaratan:
- JDK 17 atau lebih baru
- Apache Maven 3.8+

### 1. Menjalankan Aplikasi GUI (JavaFX):
Gunakan perintah Maven berikut di terminal:
```bash
mvn javafx:run
```

### 2. Menjalankan Versi CLI (Opsional):
Jika ingin menjalankan antarmuka teks lama:
```bash
mvn compile exec:java -Dexec.mainClass="app.MainCLI"
```

### 3. Kompilasi & Pengujian:
```bash
mvn clean test
```
---
## Class Diagram

```mermaid
classDiagram
    direction TB

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
## 📁 Struktur Direktori Proyek

```
oop-project-bioskop/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── app/
    │   │   │   ├── App.java              # JavaFX Application Setup
    │   │   │   ├── AppContext.java       # Shared State / Singleton Bioskop & Admin
    │   │   │   ├── MainApp.java          # Launcher class
    │   │   │   └── MainCLI.java          # CLI interface lama
    │   │   ├── controller/
    │   │   │   ├── AdminController.java  # Controller Panel Admin & Rekap Transaksi
    │   │   │   ├── CustomerController.java # Controller Katalog, Denah Kursi, E-Tiket
    │   │   │   └── MainController.java   # Controller Navigasi Utama & Switcher View
    │   │   └── model/
    │   │       ├── bioskop/              # Bioskop, Film, Jadwal, Kursi, Studio
    │   │       ├── tiket/                # Pemesanan, Tiket, TiketReguler, TiketVIP
    │   │       └── user/                 # Admin, Pelanggan, Person
    │   └── resources/
    │       ├── css/
    │       │   └── style.css             # Tema modern gelap Cinema XXI
    │       └── fxml/
    │           ├── main-view.fxml        # Shell Navigasi Header & Kontainer Dinamis
    │           ├── admin/
    │           │   └── admin-view.fxml   # Tampilan Tab Admin
    │           └── customer/
    │               └── customer-view.fxml # Tampilan Katalog, Denah Kursi, E-Tiket
    └── test/
        └── java/
            └── app/                      # TestAdmin & TestPemesanan
```
