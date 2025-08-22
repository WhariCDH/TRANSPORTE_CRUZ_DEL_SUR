package ExpressBus;

public class BusEstandar implements Bus {
    String modelo;
    private double precioBase;

    public BusEstandar(String modelo, double precioBase) {
        this.modelo = modelo;
        this.precioBase = precioBase;
    }
    
    public BusEstandar(){}

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
        return precioBase + (numEquipaje * RECARGO_EQUIPAJE);
    }

    @Override
    public String generarDetalle() {
        return "Bus Estándar - Modelo: " + modelo + ", Precio Base: " + precioBase;
    }
}
