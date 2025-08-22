/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpressBus;

/**
 *
 * @author EQUIPO
 */
public class GestorRuta extends Ruta{
    
    private String Bus;
    private int cantidadPasajeros;
    private int ID;



    public GestorRuta(String origen, String destino, int tiempoViaje, double precio, int ID, int cantidadPasajeros, String bus) {
        super(origen, destino, "", tiempoViaje, precio);
    }

    public GestorRuta() {
    }
    
    public Object[] toArray(){
    
        Object[] obj = new Object[7];
        obj[0]=getID();
        obj[1]=getTiempoViaje();
        obj[2]=getPrecio();
        obj[3]=getOrigen();
        obj[4]=getDestino();
        obj[5]=getCantidadPasajeros();
        obj[6]=getBus();
        
        return obj;
    }
    
    public String getBus() {
        return Bus;
    }

    public void setBus(String Bus) {
        this.Bus = Bus;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
    
    
}
