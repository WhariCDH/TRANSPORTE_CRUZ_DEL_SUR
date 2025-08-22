package ExpressBus;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.concurrent.TimeUnit;

public class MongoConnection {
    //PATRON SINGLENTON
    private static MongoConnection instance;
    //CONECTOR PRIVADO
    private MongoClient client;
    private MongoDatabase database;

    // Credenciales de MongoDB Atlas
    private static final String USERNAME = "u22311204";
    private static final String PASSWORD = "frank";
    private static final String CLUSTER = "cluster0.zknqatn.mongodb.net";
    private static final String DB_NAME = "empresa_transporte";
    
    // Constructor la URI con el formato correcto
    private static final String URI = String.format(
        "mongodb+srv://%s:%s@%s/?retryWrites=true&w=majority",
        USERNAME, PASSWORD, CLUSTER
    );

    // Constructor privado para evitar instanciación externa
    private MongoConnection() {
        try {
            System.out.println("Intentando conectar a MongoDB Atlas...");
            
            // Configurar opciones del cliente con timeouts más largos
            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(URI))
                .applyToSocketSettings(builder -> 
                    builder.connectTimeout(30000, TimeUnit.MILLISECONDS)  // 30 segundos
                )
                .applyToServerSettings(builder ->
                    builder.heartbeatFrequency(20000, TimeUnit.MILLISECONDS)  // 20 segundos
                )
                .build();

            // Intentar crear el cliente de MongoDB
            client = MongoClients.create(settings);
            
            // Obtener la base de datos
            database = client.getDatabase(DB_NAME);
            
            // Verificar la conexión
            database.runCommand(new Document("ping", 1));
            System.out.println("Conexión exitosa a MongoDB Atlas");
            
        } catch (Exception e) {
            String errorMsg = "Error al conectar a MongoDB: " + e.getMessage();
            System.err.println(errorMsg);
            // Cerrar el cliente si se creó
            if (client != null) {
                client.close();
            }
            throw new RuntimeException(errorMsg);
        }
    }

     // METODO ESTATICO PARA OBTENER LA INSTANCIA UNICA
    public static synchronized MongoConnection getInstance() {
        if (instance == null) {
            instance = new MongoConnection();
        }
        return instance;
    }

    // Método para obtener la base de datos
    public MongoDatabase getDatabase() {
        return database;
    }
    
    // Método para obtener una colección específica
    public com.mongodb.client.MongoCollection<org.bson.Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }
    
    // Método para cerrar la conexión
    public void close() {
        if (client != null) {
            client.close();
        }
    }
}