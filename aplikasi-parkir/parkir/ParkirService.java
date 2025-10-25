package parkir;

import java.time.LocalDateTime;
import java.util.*;

public class ParkirService {

    private final List<TempatParkir> slots = new ArrayList<>();
    private final Map<String, TiketParkir> aktif = new LinkedHashMap<>();
    private final List<TiketParkir> riwayat = new ArrayList<>();
    private int seqTiket = 1;

    public ParkirService(){
        // ====== SEED SLOT (S1..S6) ======
        for (int i = 1; i <= 6; i++) slots.add(new TempatParkir("S" + i));

        // ====== SEED kendaraan MASUK (biar menu 2 & 4 bisa langsung dicoba) ======
        masukSeed("B1234CD", "Mobil", LocalDateTime.now().minusHours(3));   // T-0001
        masukSeed("D7777ZZ", "Motor", LocalDateTime.now().minusMinutes(20)); // T-0002
    }

    private void masukSeed(String plat, String jenis, LocalDateTime waktu){
        TempatParkir s = alokasiSlot();
        if (s == null) return;
        Kendaraan k = "Mobil".equalsIgnoreCase(jenis) ? new Mobil(plat) : new Motor(plat);
        String id = nextId();
        s.tandaiTerisi();
        TiketParkir t = new TiketParkir(id, k, s, waktu);
        aktif.put(id, t);
        // juga bisa dicari pakai plat (opsional, sudah difasilitasi di cariTiket)
    }

    private String nextId(){ return String.format("T-%04d", seqTiket++); }

    public TempatParkir alokasiSlot(){
        for (TempatParkir s : slots) {
            if (!s.isTerisi()) return s;
        }
        return null; // penuh
    }

    /* ========== 1. MASUK ========== */
    public TiketParkir masukKendaraan(String plat, String jenis, LocalDateTime now){
        TempatParkir s = alokasiSlot();
        if (s == null) return null;
        Kendaraan k = "Mobil".equalsIgnoreCase(jenis) ? new Mobil(plat) : new Motor(plat);
        String id = nextId();
        s.tandaiTerisi();
        TiketParkir t = new TiketParkir(id, k, s, now);
        aktif.put(id, t);
        return t;
    }

    /* ========== 2. KELUAR ========== */
    public TiketParkir keluarKendaraan(String tiketId, LocalDateTime now){
        TiketParkir t = aktif.remove(tiketId);
        if (t == null) return null;
        t.tutup(now);
        riwayat.add(t);
        return t;
    }

    /* dipakai fitur 4 & 2 */
    public TiketParkir cariTiket(String key){
        if (aktif.containsKey(key)) return aktif.get(key);
        for (TiketParkir t : aktif.values()) {
            if (t.getKendaraan().getPlat().equalsIgnoreCase(key)) return t;
        }
        return null;
    }

    public List<TempatParkir> getSlots(){ return slots; }
    public Collection<TiketParkir> getTiketAktif(){ return aktif.values(); }
    public List<TiketParkir> getRiwayat(){ return riwayat; }
}
