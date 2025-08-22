/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Jframes;

import ExpressBus.GestorRuta;
import ExpressBus.MongoConnection;
import ExpressBus.EmpresaTransporteFacade;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

/**
 *
 * @author User
 */
public class SITES extends javax.swing.JInternalFrame {
     private final EmpresaTransporteFacade facade = new EmpresaTransporteFacade();
    /**
     * Creates new form SITES
     */
    private DefaultTableModel tblModel = new DefaultTableModel(
            new Object[][]{},
            /*new String[]{"ID", "Ruta", "Tiempo de Viaje", "Precio", "Origen", "Destino", "Bus"}*/
            /*new String[]{"ID", "Tiempo de Viaje", "Precio", "Origen", "Destino", "Pasajeros", "Bus"}*/
            new String[]{"ID", "Tiempo de Viaje", "Precio", "Origen", "Destino", "Capacidad Pasajeros", "Bus"}
    );
    private void cargarRutas() {
    try {
            // Limpia la tabla antes de cargar nuevos datos
            while (tblModel.getRowCount() > 0) {
                tblModel.removeRow(0);
            }
            
            // Conectar a MongoDB y obtener la colección de rutas
            MongoDatabase db = MongoConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = db.getCollection("rutas");
            
            // Obtener todos los documentos de la colección
            FindIterable<Document> rutas = collection.find();
            
            // Iterar a través de los documentos y agregarlos al modelo de tabla
            for (Document doc : rutas) {
                String origen = doc.getString("origen");
                String destino = doc.getString("destino");
                int tiempoViaje = doc.getInteger("tiempoViaje", 0);
                double precio = doc.getDouble("precio");
                int id = doc.getInteger("id", 0);
                int pasajeros = doc.getInteger("pasajeros", 0);
                String bus = doc.getString("bus");
                
                // Crear objeto GestorRuta con los datos obtenidos
                GestorRuta ruta = new GestorRuta(origen, destino, tiempoViaje, precio, id, pasajeros, bus);
                
                // Agregar a la tabla
                tblModel.addRow(ruta.toArray());
            }
            
            // Si no hay rutas en la BD, agregar algunas rutas de ejemplo
            if (tblModel.getRowCount() == 0) {
                GestorRuta ruta1 = new GestorRuta("Lima", "Cusco", 18, 150.0, 1, 42, "Mercedes Benz O500");
                tblModel.addRow(ruta1.toArray());
                
                GestorRuta ruta2 = new GestorRuta("Lima", "Arequipa", 14, 120.0, 2, 36, "Volvo 9800");
                tblModel.addRow(ruta2.toArray());
                
                GestorRuta ruta3 = new GestorRuta("Chiclayo", "Lima", 12, 90.0, 3, 48, "Scania K410");
                tblModel.addRow(ruta3.toArray());
            }
            
            // Informar al usuario que se han cargado las rutas
            System.out.println("Se han cargado " + tblModel.getRowCount() + " rutas.");
            
        } catch (Exception e) {
            System.err.println("Error al cargar rutas: " + e.getMessage());
            e.printStackTrace();
            
            // Si hay error al conectar a la BD, cargar algunas rutas de ejemplo sin intentar guardarlas
            if (tblModel.getRowCount() == 0) {
                try {
                    GestorRuta ruta1 = new GestorRuta("Lima", "Cusco", 18, 150.0, 1, 42, "Mercedes Benz O500");
                    tblModel.addRow(ruta1.toArray());
                    
                    GestorRuta ruta2 = new GestorRuta("Lima", "Arequipa", 14, 120.0, 2, 36, "Volvo 9800");
                    tblModel.addRow(ruta2.toArray());
                } catch (Exception ex) {
                    System.err.println("Error al cargar rutas de ejemplo: " + ex.getMessage());
                }
            }
        }
    }
    
    // Método para cargar los orígenes en el combo
    private void cargarOrigenes() {
        comboOrigenNuevo.removeAllItems();
        for (String origen : facade.obtenerOrigenes()) {
            comboOrigenNuevo.addItem(origen);
        }
    }
    
    // Método para cargar los destinos en el combo según el origen seleccionado
    private void cargarDestinos(String origenSeleccionado) {
        comboDestino.removeAllItems();
        for (String destino : facade.obtenerDestinosPorOrigen(origenSeleccionado)) {
            if (!destino.equals(origenSeleccionado)) { // Evita que destino sea igual a origen
                comboDestino.addItem(destino);
            }
        }
    }
    public SITES() {
        initComponents();
        this.setTitle("Gestión de Rutas");
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
        
        // Inicializar el modelo de tabla
        tblModel = new DefaultTableModel(
                new Object[][]{},
               /* new String[]{"ID", "Ruta", "Tiempo de Viaje", "Precio", "Origen", "Destino", "Bus"}*/
                new String[]{"ID", "Tiempo de Viaje", "Precio", "Origen", "Destino", "Pasajeros", "Bus"}
        );
        tblRegister.setModel(tblModel);
        
        // Para evitar que el usuario edite directamente las celdas
        tblRegister.setDefaultEditor(Object.class, null);
        // Cargar los orígenes en el combo
        cargarOrigenes();
        // Opcionalmente, cargar datos existentes
        cargarRutas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegister = new javax.swing.JTable();
        btonAgregar = new javax.swing.JButton();
        btonEditar = new javax.swing.JButton();
        btonEliminar = new javax.swing.JButton();
        btonEliminarTodo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comboOrigenNuevo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        comboDestino = new javax.swing.JComboBox<>();

        setTitle("Gestor de Rutas");

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        tblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegisterMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRegister);

        jPanel2.add(jScrollPane2);

        btonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btonAgregar.setText("Agregar");
        btonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonAgregarActionPerformed(evt);
            }
        });

        btonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/letter.png"))); // NOI18N
        btonEditar.setText("Editar");
        btonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonEditarActionPerformed(evt);
            }
        });

        btonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/trash.png"))); // NOI18N
        btonEliminar.setText("Eliminar");
        btonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonEliminarActionPerformed(evt);
            }
        });

        btonEliminarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cross.png"))); // NOI18N
        btonEliminarTodo.setText("Cancelar");
        btonEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonEliminarTodoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/loupe.png"))); // NOI18N
        jLabel1.setText("Buscar:");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Lima", "Huancayo", "Cajamarca", "Huanuco", "Chiclayo", "Ayacucho", "Cusco", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel6.setText("Origen");
        jPanel1.add(jLabel6);

        comboOrigenNuevo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lima", "Junin", "Chiclayo", "Huaraz", "Huancayo", "Piura", "Cusco", "Pucallpa", "Trujillo", "Arequipa" }));
        comboOrigenNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrigenNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(comboOrigenNuevo);

        jLabel7.setText("Destino");
        jPanel1.add(jLabel7);

        comboDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lima", "Junin", "Chiclayo", "Huaraz", "Huancayo", "Piura", "Cusco", "Pucallpa", "Trujillo", "Arequipa" }));
        comboDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDestinoActionPerformed(evt);
            }
        });
        jPanel1.add(comboDestino);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 943, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 56, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(btonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(btonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(btonEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(120, 120, 120)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(0, 57, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 49, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btonAgregar)
                        .addComponent(btonEditar)
                        .addComponent(btonEliminar)
                        .addComponent(btonEliminarTodo)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(7, 7, 7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 49, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegisterMouseClicked
        // TODO add your handling code here:
        // Al hacer clic en una ruta, mostrar sus detalles
        if (tblRegister.getSelectedRowCount() == 1) {
            int fila = tblRegister.getSelectedRow();
            // Aquí puedes hacer algo con la selección, como cargar los datos en los combos
            String origen = tblModel.getValueAt(fila, 3).toString();
            String destino = tblModel.getValueAt(fila, 4).toString();
            
            // Seleccionar el origen en el combo
            for (int i = 0; i < comboOrigenNuevo.getItemCount(); i++) {
                if (comboOrigenNuevo.getItemAt(i).equals(origen)) {
                    comboOrigenNuevo.setSelectedIndex(i);
                    break;
                }
            }
            
            // Seleccionar el destino en el combo
            for (int i = 0; i < comboDestino.getItemCount(); i++) {
                if (comboDestino.getItemAt(i).equals(destino)) {
                    comboDestino.setSelectedIndex(i);
                    break;
                }
            }
        }
    }//GEN-LAST:event_tblRegisterMouseClicked

    private void btonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonAgregarActionPerformed
        // TODO add your handling code here:

        try {
            // Validar que se hayan seleccionado origen y destino
            if (comboOrigenNuevo.getSelectedItem() == null || comboDestino.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione origen y destino", 
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Pedir el tiempo de viaje y precio al usuario mediante un diálogo
            String tiempoStr = JOptionPane.showInputDialog(this, 
                "Ingrese el tiempo de viaje en horas:", "Tiempo de viaje", JOptionPane.QUESTION_MESSAGE);
            
            if (tiempoStr == null || tiempoStr.trim().isEmpty()) {
                return; // El usuario canceló o dejó vacío
            }
            
            int tiempoViaje = Integer.parseInt(tiempoStr);
            
            String precioStr = JOptionPane.showInputDialog(this, 
                "Ingrese el precio del viaje:", "Precio", JOptionPane.QUESTION_MESSAGE);
            
            if (precioStr == null || precioStr.trim().isEmpty()) {
                return; // El usuario canceló o dejó vacío
            }
            
            double precio = Double.parseDouble(precioStr);
            
            String busStr = JOptionPane.showInputDialog(this, 
                "Ingrese el modelo del bus:", "Bus", JOptionPane.QUESTION_MESSAGE);
            
            if (busStr == null || busStr.trim().isEmpty()) {
                return; // El usuario canceló o dejó vacío
            }
            
            // Generar un ID único (podría ser el timestamp o un contador)
            int id = (int) (System.currentTimeMillis() % 10000);
            int pasajeros = 0; // Inicialmente no hay pasajeros
            
            // Solicitar la capacidad de pasajeros
            String pasajerosStr = JOptionPane.showInputDialog(this, 
                "Ingrese la capacidad de pasajeros del bus:", "Capacidad", JOptionPane.QUESTION_MESSAGE);
            
            if (pasajerosStr == null || pasajerosStr.trim().isEmpty()) {
                return; // El usuario canceló o dejó vacío
            }
            
           
            String origen = comboOrigenNuevo.getSelectedItem().toString().trim();
            String destino = comboDestino.getSelectedItem().toString().trim();
            
            // Crear objeto para la tabla
            GestorRuta gestorRuta = new GestorRuta(origen, destino, tiempoViaje, precio, id, pasajeros, busStr);
            
            // Agregar a la base de datos MongoDB
            MongoDatabase db = MongoConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = db.getCollection("rutas");
            
            Document doc = new Document("origen", origen)
                    .append("destino", destino)
                    .append("tiempoViaje", tiempoViaje)
                    .append("precio", precio)
                    .append("id", id)
                    .append("pasajeros", pasajeros)
                    .append("bus", busStr);
            
            collection.insertOne(doc);
            
            // Agregar a la tabla
            tblModel.addRow(gestorRuta.toArray());
            
            JOptionPane.showMessageDialog(this, 
                "Ruta agregada correctamente", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese valores numéricos válidos para tiempo y precio", 
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar la ruta: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btonAgregarActionPerformed

    private void btonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonEditarActionPerformed
        // TODO add your handling code here:

        if (tblRegister.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this,
                "Seleccione una ruta para editar",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;

        }

        try {
            int fila = tblRegister.getSelectedRow();
            int idOriginal = Integer.parseInt(tblModel.getValueAt(fila, 0).toString());
            String origenOriginal = tblModel.getValueAt(fila, 3).toString();
            String destinoOriginal = tblModel.getValueAt(fila, 4).toString();
            
            // Obtener nuevos valores
            String origen = comboOrigenNuevo.getSelectedItem().toString().trim();
            String destino = comboDestino.getSelectedItem().toString().trim();
            
            // Pedir el tiempo de viaje y precio al usuario
            String tiempoStr = JOptionPane.showInputDialog(this, 
                "Ingrese el nuevo tiempo de viaje en horas:", 
                "Tiempo de viaje", JOptionPane.QUESTION_MESSAGE);
            
            if (tiempoStr == null || tiempoStr.trim().isEmpty()) {
                return;
            }
            
            int tiempoViaje = Integer.parseInt(tiempoStr);
            
            String precioStr = JOptionPane.showInputDialog(this, 
                "Ingrese el nuevo precio del viaje:", 
                "Precio", JOptionPane.QUESTION_MESSAGE);
            
            if (precioStr == null || precioStr.trim().isEmpty()) {
                return;
            }
            
            double precio = Double.parseDouble(precioStr);
            
            String busStr = JOptionPane.showInputDialog(this, 
                "Ingrese el nuevo modelo del bus:", 
                "Bus", JOptionPane.QUESTION_MESSAGE);
            
            if (busStr == null || busStr.trim().isEmpty()) {
                return;
            }
            
            // Solicitar la nueva capacidad de pasajeros
            String pasajerosStr = JOptionPane.showInputDialog(this, 
                "Ingrese la capacidad de pasajeros del bus:", 
                "Capacidad", JOptionPane.QUESTION_MESSAGE);
            
            if (pasajerosStr == null || pasajerosStr.trim().isEmpty()) {
                return;
            }
            
            int pasajeros = Integer.parseInt(pasajerosStr);
            
            // Actualizar en MongoDB
            MongoDatabase db = MongoConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = db.getCollection("rutas");
            
            Document filtro = new Document("id", idOriginal);
            
            Document actualizacion = new Document("$set", new Document("origen", origen)
                    .append("destino", destino)
                    .append("tiempoViaje", tiempoViaje)
                    .append("precio", precio)
                    .append("pasajeros", pasajeros)
                    .append("bus", busStr));
            
            collection.updateOne(filtro, actualizacion);
            
            // Actualizar en la tabla
            GestorRuta rutaActualizada = new GestorRuta(origen, destino, tiempoViaje, precio, idOriginal, pasajeros, busStr);
            
            // Actualizar fila
            tblModel.setValueAt(idOriginal, fila, 0);
            tblModel.setValueAt(tiempoViaje, fila, 1);
            tblModel.setValueAt(precio, fila, 2);
            tblModel.setValueAt(origen, fila, 3);
            tblModel.setValueAt(destino, fila, 4);
            tblModel.setValueAt(pasajeros, fila, 5);
            tblModel.setValueAt(busStr, fila, 6);
            
            JOptionPane.showMessageDialog(this, 
                "Ruta actualizada correctamente", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese valores numéricos válidos para tiempo y precio", 
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al editar la ruta: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btonEditarActionPerformed

    private void btonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonEliminarActionPerformed
        // TODO add your handling code here:

        if (tblRegister.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this,
                "Seleccione una ruta para eliminar",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmación para eliminar
        int opcion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea eliminar esta ruta?",
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // Obtener datos del registro seleccionado
            int fila = tblRegister.getSelectedRow();
            int id = Integer.parseInt(tblModel.getValueAt(fila, 0).toString());
            String origen = tblModel.getValueAt(fila, 3).toString();
            String destino = tblModel.getValueAt(fila, 4).toString();

            // Eliminar de MongoDB
            MongoDatabase db = MongoConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = db.getCollection("rutas");
            Document filtro = new Document("id", id);

            collection.deleteOne(filtro);

            // Eliminar de la tabla
            tblModel.removeRow(fila);

            JOptionPane.showMessageDialog(this,
                "Ruta eliminada correctamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al eliminar la ruta: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btonEliminarActionPerformed

    private void btonEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonEliminarTodoActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btonEliminarTodoActionPerformed

    private void comboOrigenNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrigenNuevoActionPerformed
        // TODO add your handling code here:
        String origenSeleccionado = (String) comboOrigenNuevo.getSelectedItem();
        if (origenSeleccionado != null) {
            cargarDestinos(origenSeleccionado);
        }
    }//GEN-LAST:event_comboOrigenNuevoActionPerformed

    private void comboDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDestinoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btonAgregar;
    private javax.swing.JButton btonEditar;
    private javax.swing.JButton btonEliminar;
    private javax.swing.JButton btonEliminarTodo;
    private javax.swing.JComboBox<String> comboDestino;
    private javax.swing.JComboBox<String> comboOrigenNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblRegister;
    // End of variables declaration//GEN-END:variables
}
