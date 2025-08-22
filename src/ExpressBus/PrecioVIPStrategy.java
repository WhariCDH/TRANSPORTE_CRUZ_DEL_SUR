package ExpressBus;

public class PrecioVIPStrategy implements PrecioStrategy {

    @Override
    public double calcularTotal(double precioBase, int cantidadAsientos, int cantidadEquipaje) {
        double precioVIP = precioBase * 1.10;
        return (precioVIP * cantidadAsientos) + (cantidadEquipaje * 10);
    }
}
