
package ExpressBus;

        
public class Personal_General {
    
    private String nombre;
    private String personal;
    private String terminal;
    private String modeloBus;
    private String ruta;
    private String origen;
    private String destino;

    public Personal_General(String nombre, String personal, String terminal,Bus bus, Ruta rutaObj) {
        
        this.nombre = nombre;
        this.personal = personal;
        this.terminal = terminal;
        this.modeloBus=bus.getModelo();
        this.ruta = rutaObj.getRuta();
        this.origen = rutaObj.getOrigen();
        this.destino = rutaObj.getDestino();
    }
    
    public Personal_General(){}

  
    
    public Object[] toArray(){
    
        Object[] obj = new Object[7];
        obj[0]=getNombre();
        obj[1]=getPersonal();
        obj[2]=getTerminal();
        obj[3]=getModeloBus();
        obj[4]=getRuta();
        obj[5]=getOrigen();
        obj[6]=getDestino();
        
        return obj;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getModeloBus() {
        return modeloBus;
    }

    public void setModeloBus(String modeloBus) {
        this.modeloBus = modeloBus;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

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

   
    
    
}
