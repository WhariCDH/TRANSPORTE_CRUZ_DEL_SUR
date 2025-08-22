package ExpressBus;

public class Chofer_clase extends Personal implements Cloneable {
    private String licencia;
    private int añosExperiencia;

    public Chofer_clase(String licencia, int añosExperiencia, String codigo, String contraseña, String EMAIL, String cargo, String Area, String nombre, String dni) {
        super(codigo, contraseña, EMAIL, cargo, Area, nombre, dni);
        this.licencia = licencia;
        this.añosExperiencia = añosExperiencia;
    }

 

    // Getters y setters
    public String getLicencia() { return licencia; }
    public int getAñosExperiencia() { return añosExperiencia; }

    // Método clone para prototipo
    @Override
    public Chofer_clase clone() {
        try {
            // Intentamos hacer clone superficial usando super.clone()
            return (Chofer_clase) super.clone();
        } catch (CloneNotSupportedException e) {
            // Si Personal no implementa Cloneable, hacemos clonado manual:
            return new Chofer_clase(
                this.licencia,
                this.añosExperiencia,
                this.getCodigo(),
                "********",  // O decide cómo manejar la contraseña (por seguridad no clonar literal)
                this.getCargo(),
                this.getNombre(),
                this.getDni(),
                    this.getArea(),
                    this.getEMAIL()
              
            );
        }
    }

    // Otros métodos específicos de Chofer
    public String descripcion() {
        return  """
    ===== Datos del Chofer =====
           """+  "\n" +
           "Nombre completo: " + getNombre() + "\n" +
           "Código: " + getCodigo() + "\n" +
           "Contraseña: ********\n" +  // Nunca mostrar contraseña real
           "Cargo: " + getCargo() + "\n" +
           "Licencia: " + getLicencia() + "\n" +
           "============================";
    }
}

