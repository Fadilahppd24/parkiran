package parkir;

public class Mobil extends Kendaraan {
    public Mobil(String plat){ super(plat, "Mobil"); }
    @Override public int getTarifPerJam(){ return Tarif.getTarifMobil(); }
}
