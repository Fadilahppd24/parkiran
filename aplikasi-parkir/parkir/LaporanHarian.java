package parkir;

import java.time.LocalDate;
import java.util.List;

public class LaporanHarian {
    public static void cetakHarian(List<TiketParkir> riwayat, LocalDate tanggal){
        int total = 0;
        System.out.println("=== Laporan Harian " + tanggal + " ===");
        for (TiketParkir t : riwayat){
            if (t.getKeluar() != null && t.getKeluar().toLocalDate().equals(tanggal)){
                System.out.println(t);
                total += t.getBiaya();
            }
        }
        System.out.println("Total Pendapatan: Rp" + total + "\n");
    }
}
