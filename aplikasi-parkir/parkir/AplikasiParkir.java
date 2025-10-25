package parkir;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class AplikasiParkir {

    private final Scanner scan;
    private final ParkirService service;
    private Petugas petugasAktif;

    public AplikasiParkir(Scanner scan){
        this.scan = scan;
        this.service = new ParkirService(); // ParkirService sudah seed slot & tiket contoh
    }

    /* ====== LOGIN ====== */
    public boolean loginPetugas(){
        System.out.print("Username: ");
        String u = scan.nextLine().trim();
        System.out.print("Password: ");
        String p = scan.nextLine().trim();
        Petugas ptg = Petugas.login(u, p);
        if (ptg == null) {
            System.out.println("Login gagal! Coba lagi.\n");
            return false;
        }
        petugasAktif = ptg;
        System.out.println("Login berhasil. Selamat datang, " + ptg.getNama() + "!\n");
        return true;
    }

    public void tampilkanMenuUtama(){
        System.out.println("Menu Utama:");
        System.out.println("1. Kendaraan Masuk");
        System.out.println("2. Kendaraan Keluar");
        System.out.println("3. Lihat Slot");
        System.out.println("4. Cari Tiket");
        System.out.println("5. Laporan Harian");
        System.out.println("6. Keluar");
        System.out.print("Pilih menu: ");
    }

    /* ====== 1. MASUK ====== */
    public void kelolaMasuk(){
        System.out.print("Plat nomor        : ");
        String plat = scan.nextLine().trim();
        System.out.print("Jenis (Mobil/Motor): ");
        String jenis = scan.nextLine().trim();
        var t = service.masukKendaraan(plat, jenis, LocalDateTime.now());
        if (t == null) {
            System.out.println("Gagal masuk: Slot penuh!\n");
        } else {
            System.out.println("Tiket dibuat:");
            System.out.println(t + "\n");
        }
    }

    /* ====== 2. KELUAR ====== */
    public void kelolaKeluar(){
        System.out.print("Masukkan ID tiket / PLAT: ");
        String key = scan.nextLine().trim();
        var target = service.cariTiket(key);
        if (target == null) {
            System.out.println("Tiket tidak ditemukan.\n");
            return;
        }
        var t = service.keluarKendaraan(target.getId(), LocalDateTime.now());
        System.out.println("Struk pembayaran:");
        System.out.println(t);
        System.out.println("Total bayar: Rp" + t.getBiaya() + "\n");
    }

    /* ====== 3. LIHAT SLOT ====== */
    public void kelolaLihatSlot(){
        System.out.println("Status Slot:");
        service.getSlots().forEach(s ->
            System.out.println(s.getKode() + " : " + (s.isTerisi() ? "TERISI" : "KOSONG")));
        System.out.println();
    }

    /* ====== 4. CARI TIKET ====== */
    public void kelolaCariTiket(){
        System.out.print("Cari (ID/PLAT): ");
        String k = scan.nextLine().trim();
        var t = service.cariTiket(k);
        if (t == null) System.out.println("NOT FOUND.\n");
        else System.out.println(t + "\n");
    }

    /* ====== 5. LAPORAN HARIAN ====== */
    public void tampilLaporanHarian(){
        LocalDate tgl = LocalDate.now();
        LaporanHarian.cetakHarian(service.getRiwayat(), tgl);
    }
}
