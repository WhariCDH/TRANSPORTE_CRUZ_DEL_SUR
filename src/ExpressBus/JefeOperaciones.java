package ExpressBus;

public class JefeOperaciones extends Personal {
    private String area;
    private int añosExperiencia;

    public JefeOperaciones(String area, int añosExperiencia, String codigo, String contraseña, String EMAIL, String cargo, String Area, String nombre, String dni) {
        super(codigo, contraseña, EMAIL, cargo, Area, nombre, dni);
        this.area = area;
        this.añosExperiencia = añosExperiencia;
    }


    

    
    public String getArea() { return area; }
    public int getAñosExperiencia() { return añosExperiencia; }

    
    public String descripcion_Operaciones() {
      
        return "Jefe de Operaciones: " + this.getNombre() + ", Área: " + this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }
}
