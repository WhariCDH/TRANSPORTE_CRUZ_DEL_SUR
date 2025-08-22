package ExpressBus;

public class VIPBusFactory extends BusFactory {
    @Override
    public Bus crearBus(String modelo, double precioBase) {
        if (!validarModelo(modelo)) {
            throw new IllegalArgumentException("Modelo no válido para bus VIP");
        }
        return new BusVIP(modelo, precioBase);
    }
}
