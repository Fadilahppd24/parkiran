package parkir;

public class TempatParkir {
    private final String kode;   // S1, S2, ...
    private boolean terisi;

    public TempatParkir(String kode){
        this.kode = kode;
        this.terisi = false;
    }
    public String getKode(){ return kode; }
    public boolean isTerisi(){ return terisi; }

    public void tandaiTerisi(){ terisi = true; }
    public void tandaiKosong(){ terisi = false; }
}
