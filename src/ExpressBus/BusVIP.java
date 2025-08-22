package ExpressBus;

public class BusVIP implements Bus {
    private String modelo;
    private double precioBase;

    public BusVIP(String modelo, double precioBase) {
        this.modelo = modelo;
        this.precioBase = precioBase;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public double getPrecioBase() {
        return precioBase;
    }

    @Override
    public double calcularPrecioTotal(int numAsientos, int numEquipaje) {
        return (precioBase * 1.5) + (numEquipaje * RECARGO_EQUIPAJE);
    }

    @Override
    public String generarDetalle() {
        return "Bus VIP - Modelo: " + modelo + ", Precio Base: " + precioBase;
    }
}
