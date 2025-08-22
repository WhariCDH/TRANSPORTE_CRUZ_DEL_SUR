package ExpressBus;

import java.util.List;


public interface IEmpresaTransporteFacade {
    boolean validarUsuario(String usuario, String contrasena);
    void registrarUsuario(String usuario, String contrasena, String rol);
    void guardarBoletaReserva(String modeloBus, int cantidadAsientos, int cantidadEquipaje, String metodoPago, double precioPorAsiento, double costoEquipaje, double total, String ruta, int tiempoViaje);
    Personal_General registrarPersonal(String nombre, String tipoPersonal, String terminal, Bus bus, Ruta ruta);
    Bus crearBusEstandar(String modelo, double precio);
    Ruta crearRuta(String origen, String destino, String nombreRuta);
    Reserva registrarReserva(Pasajero pasajero, Ruta ruta, String fecha, int asiento, double precio);
    List<String> obtenerOrigenes();
    List<String> obtenerDestinosPorOrigen(String origen);
}
