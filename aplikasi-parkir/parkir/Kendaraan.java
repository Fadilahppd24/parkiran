package parkir;

public abstract class Kendaraan {
    protected String plat;
    protected String jenis; // Mobil / Motor

    public Kendaraan(String plat, String jenis){
        this.plat = plat;
        this.jenis = jenis;
    }
    public String getPlat(){ return plat; }
    public String getJenis(){ return jenis; }

    public abstract int getTarifPerJam();
}
