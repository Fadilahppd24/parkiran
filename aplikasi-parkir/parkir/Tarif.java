package parkir;

public class Tarif {
    // seed default
    private static int tarifMobil = 5000;
    private static int tarifMotor = 3000;

    public static int getTarifMobil(){ return tarifMobil; }
    public static int getTarifMotor(){ return tarifMotor; }
    public static int ambilTarif(String jenis){
        return "Mobil".equalsIgnoreCase(jenis) ? tarifMobil : tarifMotor;
    }
}
