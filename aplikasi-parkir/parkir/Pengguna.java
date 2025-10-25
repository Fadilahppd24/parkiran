package parkir;

public abstract class Pengguna {
    private final String id;
    private final String nama;

    public Pengguna(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId()   { return id; }
    public String getNama() { return nama; }
}
