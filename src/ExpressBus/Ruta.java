package ExpressBus;


public class Ruta {
    
    private String ruta;
    private String origen;
    private String destino;
    private int tiempoViaje;
    private double precio;

    public Ruta(String origen, String destino, String ruta) {
        this.origen = origen;
        this.destino = destino;
        this.ruta = ruta;
    }

    public Ruta(String origen, String destino, String ruta, int tiempoViaje, double precio) {
        this.origen = origen;
        this.destino = destino;
        this.ruta = ruta;
        this.tiempoViaje = tiempoViaje;
        this.precio = precio;
    }

    public Ruta() {}

    // Getters y Setters
    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getTiempoViaje() {
        return tiempoViaje;
    }

    public void setTiempoViaje(int tiempoViaje) {
        this.tiempoViaje = tiempoViaje;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

    
