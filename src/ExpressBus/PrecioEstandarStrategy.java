package ExpressBus;

public class PrecioEstandarStrategy implements PrecioStrategy {

    @Override
    public double calcularTotal(double precioBase, int cantidadAsientos, int cantidadEquipaje) {
        return (precioBase * cantidadAsientos) + (cantidadEquipaje * 10);
    }
}
