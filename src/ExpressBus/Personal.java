package ExpressBus;

public class Personal {
    private String codigo;
    private String contraseña;
    private String EMAIL;
    private String cargo;
    private String Area;
    private String nombre;
    private String dni;

    public Personal(String codigo, String contraseña, String EMAIL, String cargo, String Area, String nombre, String dni) {
        this.codigo = codigo;
        this.contraseña = contraseña;
        this.EMAIL = EMAIL;
        this.cargo = cargo;
        this.Area = Area;
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    

    
    

}