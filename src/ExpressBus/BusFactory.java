package ExpressBus;

public abstract class BusFactory {
    public abstract Bus crearBus(String modelo, double precioBase);

    protected boolean validarModelo(String modelo) {
        return modelo != null && !modelo.trim().isEmpty();
    }
}

