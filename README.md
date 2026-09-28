Sistem Pemesanan Tiket Bioskop (OOP Java)

Aplikasi konsol berbasis Java yang mengimplementasikan prinsip-prinsip Pemrograman Berorientasi Objek (OOP) untuk mensimulasikan operasional sistem bioskop, mulai dari manajemen film dan jadwal oleh admin, hingga pemesanan kursi dan tiket oleh pelanggan.

📌 Fitur Utama

Manajemen Entitas Pengguna (User Management):

Admin: Mengelola data bioskop, studio, film, dan jadwal penayangan.

Pelanggan: Memilih jadwal tayang, memilih nomor kursi, dan melakukan reservasi tiket.

Manajemen Bioskop & Studio:

Pengelompokan data berdasarkan bioskop, studio, dan kapasitas kursi.

Penjadwalan tayang film per studio.

Sistem Tiket & Transaksi:

Perhitungan tiket berdasarkan kategori: Tiket Reguler dan Tiket VIP.

Validasi ketersediaan kursi saat pemesanan berlangsung.

Pencatatan transaksi pemesanan tiket.

🏛️ Penerapan Konsep OOP

Proyek ini dirancang dengan mematuhi pilar-pilar OOP:

Inheritance (Pewarisan):

Person diturunkan ke Admin dan Pelanggan.

Tiket diturunkan ke TiketReguler dan TiketVIP.

Polymorphism (Polimorfisme):

Override metode kalkulasi harga atau fasilitas khusus antara TiketReguler dan TiketVIP.

Encapsulation (Enkapsulasi):

Penggunaan akses modifier (private / protected) dengan metode getter dan setter untuk melindungi integritas atribut data pada tiap model.

Abstraction (Abstraksi):

Penggunaan class abstrak atau antarmuka model untuk mendefinisikan kontrak fungsi utama sebelum diimplementasikan secara spesifik.

📂 Struktur Proyek

oop-project-bioskop/
├── src/
│   ├── main/
│   │   ├── TestAdmin.java          # Kelas pengujian alur kerja Admin
│   │   └── TestPemesanan.java      # Kelas pengujian transaksi pemesanan
│   └── model/
│       ├── bioskop/
│       │   ├── Bioskop.java        # Representasi data bioskop
│       │   ├── Film.java           # Model informasi film
│       │   ├── Jadwal.java         # Model jadwal penayangan
│       │   ├── Kursi.java          # Model kursi dan status keterisian
│       │   └── Studio.java         # Model studio penayangan
│       ├── tiket/
│       │   ├── Pemesanan.java      # Transaksi pemesanan tiket
│       │   ├── Tiket.java          # Base class tiket
│       │   ├── TiketReguler.java   # Subclass tiket reguler
│       │   └── TiketVIP.java       # Subclass tiket VIP
│       └── user/
│           ├── Admin.java          # Subclass untuk akun administrator
│           ├── Pelanggan.java      # Subclass untuk akun pelanggan
│           └── Person.java         # Base class untuk data personal
├── .gitignore
├── LICENSE
└── README.md


🚀 Panduan Menjalankan Program

Prasyarat

Java Development Kit (JDK) versi 8 atau lebih baru.

Kompilasi

Buka terminal / command prompt pada root direktori proyek, lalu jalankan perintah kompilasi:

# Untuk Linux / macOS
javac -d bin $(find src -name "*.java")

# Untuk Windows (Command Prompt)
dir /s /B src\*.java > sources.txt
javac -d bin @sources.txt
del sources.txt


Menjalankan Program Pengujian

Menjalankan pengujian fungsionalitas Admin:

java -cp bin main.TestAdmin


Menjalankan simulasi Pemesanan Tiket:

java -cp bin main.TestPemesanan


📄 Lisensi

Proyek ini didistribusikan di bawah lisensi open-source sesuai file LICENSE.
