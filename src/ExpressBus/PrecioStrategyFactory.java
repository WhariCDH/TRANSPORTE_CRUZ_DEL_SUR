package ExpressBus;

public class PrecioStrategyFactory {

    public static PrecioStrategy crearPrecioStrategy(String modeloSeleccionado) {
        if (modeloSeleccionado != null) {
            if (modeloSeleccionado.startsWith("VIP")) {
                return new PrecioVIPStrategy();
            } else if (modeloSeleccionado.startsWith("Estandar")) {
                return new PrecioEstandarStrategy();
            }
        }
        return null;
    }
}
