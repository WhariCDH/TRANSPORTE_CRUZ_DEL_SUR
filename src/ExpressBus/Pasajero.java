
package ExpressBus;


public class Pasajero extends Persona{
    private String telefono;
    private String correo;

    public Pasajero(String telefono, String correo, String nombre, String DNI, String apellido) {
        super(nombre, DNI, apellido);
        this.telefono = telefono;
        this.correo = correo;
    }
   
    
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

 
    
    
    
    
    
}
