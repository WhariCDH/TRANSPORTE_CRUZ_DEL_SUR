package ExpressBus;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class EmpresaTransporteFacade implements IEmpresaTransporteFacade {

    @Override
    public boolean validarUsuario(String usuario, String contrasena) {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = db.getCollection("usuarios");
        Document doc = collection.find(new Document("usuario", usuario)
            .append("contrasena", contrasena)).first();
        return doc != null;
    }

    @Override
    public void registrarUsuario(String usuario, String contrasena, String rol) {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = db.getCollection("usuarios");
        Document doc = new Document("usuario", usuario)
            .append("contrasena", contrasena)
            .append("rol", rol);
        collection.insertOne(doc);
    }

    @Override
    public void guardarBoletaReserva(String modeloBus, int cantidadAsientos, int cantidadEquipaje, String metodoPago, double precioPorAsiento, double costoEquipaje, double total, String ruta, int tiempoViaje) {
        String nombrePasajero = DatosPasajero.getNombre();
        String dniPasajero = DatosPasajero.getDni();
        String telefonoPasajero = DatosPasajero.getTelefono();
        String correoPasajero = DatosPasajero.getCorreo();
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = db.getCollection("boletas_reserva");
        Document doc = new Document("modeloBus", modeloBus)
            .append("cantidadAsientos", cantidadAsientos)
            .append("cantidadEquipaje", cantidadEquipaje)
            .append("metodoPago", metodoPago)
            .append("precioPorAsiento", precioPorAsiento)
            .append("costoEquipaje", costoEquipaje)
            .append("total", total)
            .append("fecha", new java.util.Date())
            .append("nombrePasajero", nombrePasajero)
            .append("dniPasajero", dniPasajero)
            .append("telefonoPasajero", telefonoPasajero)
            .append("correoPasajero", correoPasajero)
            .append("rutaViaje", ruta)
            .append("tiempoViaje", tiempoViaje);
        collection.insertOne(doc);
    }

    @Override
    public Personal_General registrarPersonal(String nombre, String tipoPersonal, String terminal, Bus bus, Ruta ruta) {
        Personal_General empleado = new Personal_General(nombre, tipoPersonal, terminal, bus, ruta);
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = db.getCollection("personal");
        Document doc = new Document("nombre", nombre)
            .append("tipoPersonal", tipoPersonal)
            .append("terminal", terminal)
            .append("modeloBus", bus.getModelo())
            .append("ruta", ruta.getRuta())
            .append("origen", ruta.getOrigen())
            .append("destino", ruta.getDestino());
        collection.insertOne(doc);
        return empleado;
    }

    @Override
    public Bus crearBusEstandar(String modelo, double precio) {
        return new BusEstandar(modelo, precio);
    }

    @Override
    public Ruta crearRuta(String origen, String destino, String nombreRuta) {
        return new Ruta(origen, destino, nombreRuta);
    }

    @Override
    public Reserva registrarReserva(Pasajero pasajero, Ruta ruta, String fecha, int asiento, double precio) {
        return new Reserva("codigo", pasajero, ruta, fecha, asiento, precio);
    }
    @Override
    public List<String> obtenerOrigenes() {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = db.getCollection("rutas");
        DistinctIterable<String> origenes = collection.distinct("origen", String.class);
        List<String> lista = new ArrayList<>();
        for (String origen : origenes) {
            lista.add(origen);
        }
        System.out.println("Total origenes: " + lista.size());
  
        return lista;
    }
    @Override
    public List<String> obtenerDestinosPorOrigen(String origen) {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = db.getCollection("rutas");
        List<String> destinos = new ArrayList<>();
        for (Document doc : collection.find(new Document("origen", origen))) {
            destinos.add(doc.getString("destino"));
        }
        return destinos;
    }
    
 
}