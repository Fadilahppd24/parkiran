package parkir;

public class Motor extends Kendaraan {
    public Motor(String plat){ super(plat, "Motor"); }
    @Override public int getTarifPerJam(){ return Tarif.getTarifMotor(); }
}
