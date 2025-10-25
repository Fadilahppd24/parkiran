package parkir;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Program Aplikasi Console Parkiran ===");
        Scanner scan = new Scanner(System.in);
        AplikasiParkir app = new AplikasiParkir(scan); // sudah seed data (petugas, slot, tiket contoh)

        // loop login
        while (!app.loginPetugas()) { /* ulang hingga benar */ }

        // loop menu 1–6
        while (true) {
            app.tampilkanMenuUtama();
            String pilih = scan.nextLine().trim();
            System.out.println();
            switch (pilih) {
                case "1": app.kelolaMasuk(); break;
                case "2": app.kelolaKeluar(); break;
                case "3": app.kelolaLihatSlot(); break;
                case "4": app.kelolaCariTiket(); break;
                case "5": app.tampilLaporanHarian(); break;
                case "6": System.out.println("Bye!"); return;
                default : System.out.println("Pilihan tidak valid.\n");
            }
        }
    }
}
