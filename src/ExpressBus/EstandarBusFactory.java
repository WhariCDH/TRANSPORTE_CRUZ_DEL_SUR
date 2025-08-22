package ExpressBus;

public class EstandarBusFactory extends BusFactory {
    @Override
    public Bus crearBus(String modelo, double precioBase) {
        if (!validarModelo(modelo)) {
            throw new IllegalArgumentException("Modelo no válido para bus estándar");
        }
        return new BusEstandar(modelo, precioBase);
    }
}
