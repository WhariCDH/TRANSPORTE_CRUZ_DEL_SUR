
package ExpressBus;


public class Persona {
    
    private String nombre;
    private String apellido;
    private String DNI;

    public Persona(String nombre, String DNI,String apellido) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.apellido=apellido;
    }

    public Persona (String nombre){
        this.nombre = nombre;
    }
    

    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
}
