
package ExpressBus;

public interface Bus {
    double RECARGO_EQUIPAJE = 10.0;

    String getModelo();
    double getPrecioBase();
    double calcularPrecioTotal(int numAsientos, int numEquipaje);
    String generarDetalle();
}
