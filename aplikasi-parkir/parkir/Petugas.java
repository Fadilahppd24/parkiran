package parkir;

import java.util.*;

public class Petugas {
    private final String id;
    private final String nama;
    private final String username;
    private final String password;

    // ====== SEED USER ======
    private static final List<Petugas> DATA = new ArrayList<>();
    static {
        DATA.add(new Petugas("P1", "Admin",     "admin",    "pass"));
        DATA.add(new Petugas("P2", "Petugas 1", "petugas1", "1234"));
        DATA.add(new Petugas("P3", "Papad",     "papad",    "123"));
    }

    public Petugas(String id, String nama, String username, String password){
        this.id = id; this.nama = nama; this.username = username; this.password = password;
    }

    public String getId(){ return id; }
    public String getNama(){ return nama; }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }

    public static Petugas login(String u, String p){
        String uu = u == null ? "" : u.trim();
        String pp = p == null ? "" : p.trim();
        for (Petugas x : DATA){
            if (x.username.equals(uu) && x.password.equals(pp)) return x;
        }
        return null;
    }
}
