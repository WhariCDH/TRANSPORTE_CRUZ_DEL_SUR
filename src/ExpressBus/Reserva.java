
package ExpressBus;


public class Reserva implements MostrarDatos,Reservable{ 
    
    private String codigoReserva;
    private Pasajero pasajero;
    private Ruta ruta;
    private String fechareserva;
    private int asiento;
    private double precio;

    public Reserva(String codigoReserva, Pasajero pasajero, Ruta ruta, String fechareserva, int asiento, double precio) {
        this.codigoReserva = codigoReserva;
        this.pasajero = pasajero;
        this.ruta = ruta;
        this.fechareserva = fechareserva;
        this.asiento = asiento;
        this.precio = precio;
    }

   

    @Override
    public void mostrarDatos() {
        
        System.out.println("Codigo de reserva: "+getCodigoReserva());
        System.out.println("Pasajero: "+getPasajero().getNombre());
        System.out.println("Ruta: "+getRuta().getOrigen()+" - "+getRuta().getDestino());
        System.out.println("Fecha de Reserva: "+getFechareserva());
        System.out.println("Asientos: "+getAsiento());
        System.out.println("Precio: "+getPrecio());
        
    }
    
    @Override
    public boolean reserva(){
    
        System.out.println("Reserva realizada con exito.");
        return true;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public String getFechareserva() {
        return fechareserva;
    }

    public void setFechareserva(String fechareserva) {
        this.fechareserva = fechareserva;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
    
    
}
