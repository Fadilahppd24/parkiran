package parkir;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TiketParkir {
    private final String id;               // T-0001, T-0002, ...
    private final Kendaraan kendaraan;
    private final TempatParkir slot;
    private final LocalDateTime masuk;
    private LocalDateTime keluar;
    private int biaya;

    public TiketParkir(String id, Kendaraan kendaraan, TempatParkir slot, LocalDateTime masuk){
        this.id = id;
        this.kendaraan = kendaraan;
        this.slot = slot;
        this.masuk = masuk;
    }

    public String getId(){ return id; }
    public Kendaraan getKendaraan(){ return kendaraan; }
    public TempatParkir getSlot(){ return slot; }
    public LocalDateTime getMasuk(){ return masuk; }
    public LocalDateTime getKeluar(){ return keluar; }
    public int getBiaya(){ return biaya; }

    public void tutup(LocalDateTime waktuKeluar){
        this.keluar = waktuKeluar;
        long menit = ChronoUnit.MINUTES.between(masuk, keluar);
        long jam = Math.max(1, (menit + 59) / 60); // bulat ke atas min 1 jam
        int tarif = kendaraan.getTarifPerJam();
        this.biaya = (int) (jam * tarif);
        slot.tandaiKosong();
    }

    @Override public String toString(){
        return "Tiket " + id + " | " + kendaraan.getJenis() + " " + kendaraan.getPlat() +
               " | Slot=" + slot.getKode() + " | Masuk=" + masuk +
               (keluar != null ? " | Keluar=" + keluar + " | Biaya=Rp" + biaya : "");
    }
}
