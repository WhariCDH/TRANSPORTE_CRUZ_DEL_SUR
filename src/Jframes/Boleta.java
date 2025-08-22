/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Jframes;
import ExpressBus.MongoConnection;
import ExpressBus.PDFGenerator;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author EQUIPO
 */
public class Boleta extends javax.swing.JInternalFrame {


    private DefaultTableModel tblModel;
    /*private final String[] header = {"ID", "Nombre Pasajero", "DNI", "Modelo de Bus", "Asientos", "Equipaje", "Método Pago", "Precio Asiento", "Costo Equipaje", "Total", "Fecha"};*/
    private final String[] header = {"ID", "Nombre Pasajero", "DNI", "Teléfono", "Correo", "Modelo de Bus", "Asientos", "Equipaje", "Método Pago", "Precio Asiento", "Costo Equipaje", "Total", "Fecha", "Ruta", "Tiempo Viaje"};
    private String selectedId = "";
    public Boleta() {
        initComponents();
        initTable();
        cargarDatosDesdeMongo();
    }
    private void cargarDatosDesdeMongo() {
        tblModel.setRowCount(0); // Limpia la tabla

        MongoCursor<Document> cursor = null;
        try {
            MongoDatabase database = MongoConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection("boletas_reserva");
            
            cursor = collection.find().iterator();
            
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // Debug: Imprimir todos los campos del documento
                System.out.println("\n=== DOCUMENTO MONGODB ===");
                for (String key : doc.keySet()) {
                    Object value = doc.get(key);
                    System.out.println("Campo: '" + key + "', Valor: '" + (value != null ? value.toString() : "null") + "'");
                }
                System.out.println("=======================");
                Object[] row = new Object[15]; // Actualizado a 15 columnas
                row[0] = doc.getObjectId("_id").toString();
                row[1] = getValueAsString(doc, "nombrePasajero");
                row[2] = getValueAsString(doc, "dniPasajero");
                row[3] = getValueAsString(doc, "telefonoPasajero");
                row[4] = getValueAsString(doc, "correoPasajero");
                row[5] = getValueAsString(doc, "modeloBus");
                row[6] = getValueAsString(doc, "cantidadAsientos");
                row[7] = getValueAsString(doc, "cantidadEquipaje");
                row[8] = getValueAsString(doc, "metodoPago");
                row[9] = getValueAsString(doc, "precioPorAsiento");
                row[10] = getValueAsString(doc, "costoEquipaje");
                row[11] = getValueAsString(doc, "total");
                row[12] = getValueAsString(doc, "fecha");
                row[13] = getValueAsString(doc, "rutaViaje");
                row[14] = getValueAsString(doc, "tiempoViaje");
                // Intentar obtener la ruta usando diferentes nombres de campo posibles
                String rutaValue = getValueAsString(doc, "ruta");
                if (rutaValue.isEmpty()) {
                    rutaValue = getValueAsString(doc, "rutaViaje");
                }
                
                String tiempoValue = getValueAsString(doc, "tiempoViaje");
                System.out.println("Ruta recuperada de MongoDB: '" + rutaValue + "'");
                System.out.println("Tiempo recuperado de MongoDB: '" + tiempoValue + "'");
                
                row[13] = rutaValue;
                row[14] = tiempoValue;
                
                tblModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos desde MongoDB: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    
    /**
     * Método auxiliar para obtener cualquier valor de un documento MongoDB como String
     * @param doc Documento MongoDB
     * @param key Clave a obtener
     * @return Valor como String o cadena vacía si es nulo
     */
    private String getValueAsString(Document doc, String key) {
        Object value = doc.get(key);
        return value != null ? value.toString() : "";
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
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btonAgregar = new javax.swing.JButton();
        btonEliminar = new javax.swing.JButton();
        btonEliminarTodo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Boleta");
        setPreferredSize(new java.awt.Dimension(900, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        tblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegisterMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRegister);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 790, 300));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel1.setText("Busqueda por numero de DNI:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 210, 20));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 110, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/loupe.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        btonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/estate-agent.png"))); // NOI18N
        btonAgregar.setText("Nueva Venta");
        btonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 140, -1));

        btonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cross.png"))); // NOI18N
        btonEliminar.setText("Anular");
        btonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 130, -1));

        btonEliminarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/printer.png"))); // NOI18N
        btonEliminarTodo.setText("Imprimir");
        btonEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonEliminarTodoActionPerformed(evt);
            }
        });
        getContentPane().add(btonEliminarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 140, -1));

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/magic-wand.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 130, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegisterMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblRegisterMouseClicked

    private void btonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonAgregarActionPerformed
        // TODO add your handling code here:
        RESERVA reserva = new RESERVA();
        INICIO.escritorio.add(reserva);
        reserva.setVisible(true);
    }//GEN-LAST:event_btonAgregarActionPerformed

    private void btonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonEliminarActionPerformed
        // TODO add your handling code here:
        int fila = tblRegister.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una boleta para anular");
            return;
        }
        
        String id = tblModel.getValueAt(fila, 0).toString();
        
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de anular esta boleta?", 
                "Confirmar anulación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                MongoDatabase database = MongoConnection.getInstance().getDatabase();
                MongoCollection<Document> collection = database.getCollection("boletas_reserva");
                
                collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
                
                JOptionPane.showMessageDialog(this, "Boleta anulada correctamente");
                cargarDatosDesdeMongo();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al anular la boleta: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btonEliminarActionPerformed

    private void btonEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonEliminarTodoActionPerformed
        // TODO add your handling code here:
      int fila = tblRegister.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una boleta para imprimir");
            return;
        }
        
        try {
            // Obtener los datos de la boleta seleccionada con verificación de valores nulos
            String id = getTableValueSafely(tblModel, fila, 0);
            String nombrePasajero = getTableValueSafely(tblModel, fila, 1);
            String dni = getTableValueSafely(tblModel, fila, 2);
            String telefono = getTableValueSafely(tblModel, fila, 3);
            String correo = getTableValueSafely(tblModel, fila, 4);
            String modeloBus = getTableValueSafely(tblModel, fila, 5);
            String asientos = getTableValueSafely(tblModel, fila, 6);
            String equipaje = getTableValueSafely(tblModel, fila, 7);
            String metodoPago = getTableValueSafely(tblModel, fila, 8);
            String precioPorAsiento = getTableValueSafely(tblModel, fila, 9);
            String costoEquipaje = getTableValueSafely(tblModel, fila, 10);
            String total = getTableValueSafely(tblModel, fila, 11);
            String fecha = getTableValueSafely(tblModel, fila, 12);
            String rutaViaje = getTableValueSafely(tblModel, fila, 13);
            String tiempoViaje = getTableValueSafely(tblModel, fila, 14);
            
            // Verificar que todos los datos esenciales están presentes
            if (nombrePasajero.isEmpty() || dni.isEmpty() || modeloBus.isEmpty() || 
                asientos.isEmpty() || total.isEmpty()) {
                throw new IllegalArgumentException("Faltan datos esenciales en la boleta seleccionada");
            }
            
            // Crear el contenido del PDF
            StringBuilder contenido = new StringBuilder();
            contenido.append("BOLETA DE VIAJE\n");
            contenido.append("======================\n\n");
            contenido.append("DATOS DEL PASAJERO\n");
            contenido.append("======================\n");
            contenido.append("Nombre: ").append(nombrePasajero).append("\n");
            contenido.append("DNI: ").append(dni).append("\n");
            contenido.append("Teléfono: ").append(telefono.isEmpty() ? "No especificado" : telefono).append("\n");
            contenido.append("Correo: ").append(correo.isEmpty() ? "No especificado" : correo).append("\n\n");
            
            contenido.append("INFORMACIÓN DE VIAJE\n");
            contenido.append("======================\n");
            contenido.append("Ruta: ").append(rutaViaje.isEmpty() ? "No especificada" : rutaViaje).append("\n");
            contenido.append("Tiempo de viaje (hrs): ").append(tiempoViaje.isEmpty() ? "No especificado" : tiempoViaje).append("\n\n");
            
            contenido.append("DETALLES DEL SERVICIO\n");
            contenido.append("======================\n");
            contenido.append("Modelo de Bus: ").append(modeloBus).append("\n");
            contenido.append("Cantidad de Asientos: ").append(asientos).append("\n");
            contenido.append("Cantidad de Equipaje: ").append(equipaje).append("\n");
            contenido.append("Método de Pago: ").append(metodoPago).append("\n\n");
            
            contenido.append("DETALLE DE PAGO\n");
            contenido.append("======================\n");
            contenido.append("Precio por asiento: S/ ").append(precioPorAsiento).append("\n");
            contenido.append("Costo total equipaje: S/ ").append(costoEquipaje).append("\n\n");
            contenido.append("======================\n");
            contenido.append("TOTAL A PAGAR: S/ ").append(total).append("\n");
            contenido.append("======================\n\n");
            contenido.append("Fecha: ").append(fecha).append("\n\n");
            contenido.append("Gracias por elegir Cruz del Sur");

            // Mostrar diálogo para guardar el archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Boleta");
            fileChooser.setSelectedFile(new File("Boleta_" + dni + ".pdf"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf"));
            
            int userSelection = fileChooser.showSaveDialog(this);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                // Asegurarse que el archivo termine en .pdf
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                    fileToSave = new File(filePath);
                }
                
                // Mostrar indicador de progreso
                setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                
                // Crear diálogo de progreso
                final javax.swing.JDialog progressDialog = new javax.swing.JDialog();
                progressDialog.setTitle("Generando PDF");
                progressDialog.setModal(false);
                progressDialog.setSize(300, 80);
                progressDialog.setLocationRelativeTo(this);
                
                javax.swing.JPanel panel = new javax.swing.JPanel();
                panel.setLayout(new java.awt.BorderLayout(10, 10));
                panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
                javax.swing.JLabel label = new javax.swing.JLabel("Generando PDF, por favor espere...");
                javax.swing.JProgressBar progressBar = new javax.swing.JProgressBar();
                progressBar.setIndeterminate(true);
                
                panel.add(label, java.awt.BorderLayout.NORTH);
                panel.add(progressBar, java.awt.BorderLayout.CENTER);
                progressDialog.add(panel);
                
                // Mostrar diálogo en hilo separado
                progressDialog.setVisible(true);
                
                try {
                    // Llamar al método correcto para generar un PDF con formato adecuado
                    boolean resultado = PDFGenerator.generarBoletaPDF(
                        modeloBus, 
                        Integer.parseInt(asientos), 
                        Integer.parseInt(equipaje), 
                        metodoPago,
                        Double.parseDouble(precioPorAsiento),
                        Double.parseDouble(costoEquipaje),
                        Double.parseDouble(total),
                        fileToSave.getAbsolutePath(),
                        nombrePasajero,
                        dni,
                        telefono.isEmpty() ? "No especificado" : telefono,  // Teléfono
                        correo.isEmpty() ? "No especificado" : correo,      // Correo
                        rutaViaje.isEmpty() ? "No especificada" : rutaViaje, // Ruta
                        tiempoViaje.isEmpty() ? 0 : parseTimeToInt(tiempoViaje) // Tiempo de viaje
                    );
                    
                    // Cerrar diálogo de progreso
                    progressDialog.dispose();
                    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    
                    if (!resultado) {
                        throw new IOException("Error generando el PDF");
                    }
                    JOptionPane.showMessageDialog(this, "PDF generado correctamente en: " + fileToSave.getAbsolutePath());
                } catch (Exception ex) {
                    progressDialog.dispose();
                    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    throw ex; // Re-lanzar la excepción para que sea manejada por el catch externo
                }
                
                // Verificar si hay un visor de PDF instalado
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                    try {
                        Desktop.getDesktop().open(fileToSave);
                        JOptionPane.showMessageDialog(this, "El PDF se ha generado y abierto correctamente.");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo PDF automáticamente: " + e.getMessage() +
                                "\nPuede abrirlo manualmente desde: " + fileToSave.getAbsolutePath());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Su sistema no permite abrir archivos automáticamente.\n" +
                            "El PDF se ha generado correctamente en: " + fileToSave.getAbsolutePath());
                }
            }
        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Datos faltantes o con formato incorrecto en la tabla.\n" +
                    "Verifique que todos los campos estén completos y con el formato adecuado: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Log de errores, reemplaza la impresión de la pila de seguimiento
            System.err.println("Error al procesar datos: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de entrada/salida al generar el PDF: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Log de errores, reemplaza la impresión de la pila de seguimiento
            System.err.println("Error de I/O: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Log de errores, reemplaza la impresión de la pila de seguimiento
            System.err.println("Error general: " + e.getMessage());
        }
    }//GEN-LAST:event_btonEliminarTodoActionPerformed
    //tabla asergurada
     /**
     * Método auxiliar para obtener un valor de la tabla de forma segura,
     * evitando NullPointerException
     * @param model El modelo de tabla
     * @param row Número de fila
     * @param column Número de columna
     * @return El valor como String o cadena vacía si es nulo
     */
    private String getTableValueSafely(javax.swing.table.DefaultTableModel model, int row, int column) {
        Object value = model.getValueAt(row, column);
        return value != null ? value.toString() : "";
    }
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // Refrescar la tabla para mostrar los nuevos registros
        JOptionPane.showMessageDialog(this, "Actualizando datos desde la base de datos...");
        // Recrear la tabla para asegurarnos de que muestre todas las columnas correctamente
            initTable();
            cargarDatosDesdeMongo();
            
            // Forzar el repintado completo de la tabla
            tblRegister.repaint();
            tblRegister.revalidate();
        JOptionPane.showMessageDialog(this, "Tabla de boletas actualizada con los últimos datos de MongoDB");
    
    }//GEN-LAST:event_btnActualizarActionPerformed
    /**
     * Convierte una cadena de tiempo (ejemplo: "10 hrs" o "10") a un entero
     * @param timeString La cadena de tiempo a convertir
     * @return El valor entero del tiempo, 0 si hay error
     */
    private int parseTimeToInt(String timeString) {
        try {
            // Eliminar cualquier texto no numérico
            String numericPart = timeString.replaceAll("[^0-9]", "").trim();
            if (numericPart.isEmpty()) {
                return 0;
            }
            return Integer.parseInt(numericPart);
        } catch (Exception e) {
            System.err.println("Error al convertir tiempo a entero: " + e.getMessage());
            return 0;
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String dni = jTextField1.getText().trim();
        // Verificar si es el placeholder o está vacío
        if (dni.isEmpty() || dni.equals("Ingrese DNI")) {
            JOptionPane.showMessageDialog(this, "Ingrese un DNI para buscar");
            return;
        }
        
        buscarPorDNI(dni);
    }
    private void initTable() {
        tblModel = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblRegister.setModel(tblModel);
        // Ajustar el ancho de las columnas para que se vean mejor
        if (tblRegister.getColumnModel().getColumnCount() > 0) {
            // Columnas con ID, nombres largos, etc.
            tblRegister.getColumnModel().getColumn(0).setPreferredWidth(70);  // ID
            tblRegister.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre
            tblRegister.getColumnModel().getColumn(2).setPreferredWidth(80);  // DNI
            tblRegister.getColumnModel().getColumn(3).setPreferredWidth(100); // Teléfono
            tblRegister.getColumnModel().getColumn(4).setPreferredWidth(150); // Correo
            tblRegister.getColumnModel().getColumn(5).setPreferredWidth(150); // Modelo Bus
            tblRegister.getColumnModel().getColumn(13).setPreferredWidth(150); // Ruta
            tblRegister.getColumnModel().getColumn(14).setPreferredWidth(80); // Tiempo
            
            // Usar un renderizador personalizado para que los valores nulos se muestren como "No especificado"
            javax.swing.table.DefaultTableCellRenderer renderer = new javax.swing.table.DefaultTableCellRenderer() {
                @Override
                public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (value == null || value.toString().isEmpty()) {
                        value = "No especificado";
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            };
            
            // Aplicar el renderizador a todas las columnas
            for (int i = 0; i < tblRegister.getColumnCount(); i++) {
                tblRegister.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }
        }
    
    }
    private void buscarPorDNI(String dni) {
        tblModel.setRowCount(0); // Limpia la tabla

        MongoCursor<Document> cursor = null;
        try {
            MongoDatabase database = MongoConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection("boletas_reserva");
            
            cursor = collection.find(Filters.eq("dniPasajero", dni)).iterator();
            
            boolean encontrado = false;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                encontrado = true;
                
                // Debug: Imprimir todos los campos del documento
                System.out.println("\n=== DOCUMENTO MONGODB (buscarPorDNI) ===");
                for (String key : doc.keySet()) {
                    Object value = doc.get(key);
                    System.out.println("Campo: '" + key + "', Valor: '" + (value != null ? value.toString() : "null") + "'");
                }
                System.out.println("=======================");
                
                Object[] row = new Object[15]; // Actualizado a 15 columnas
                row[0] = doc.getObjectId("_id").toString();
                row[1] = getValueAsString(doc, "nombrePasajero");
                row[2] = getValueAsString(doc, "dniPasajero");
                row[3] = getValueAsString(doc, "telefonoPasajero");
                row[4] = getValueAsString(doc, "correoPasajero");
                row[5] = getValueAsString(doc, "modeloBus");
                row[6] = getValueAsString(doc, "cantidadAsientos");
                row[7] = getValueAsString(doc, "cantidadEquipaje");
                row[8] = getValueAsString(doc, "metodoPago");
                row[9] = getValueAsString(doc, "precioPorAsiento");
                row[10] = getValueAsString(doc, "costoEquipaje");
                row[11] = getValueAsString(doc, "total");
                row[12] = getValueAsString(doc, "fecha");
                
                // Intentar obtener la ruta usando diferentes nombres de campo posibles
                String rutaValue = getValueAsString(doc, "ruta");
                if (rutaValue.isEmpty()) {
                    rutaValue = getValueAsString(doc, "rutaViaje");
                }
                
                String tiempoValue = getValueAsString(doc, "tiempoViaje");
                System.out.println("Ruta recuperada de MongoDB: '" + rutaValue + "'");
                System.out.println("Tiempo recuperado de MongoDB: '" + tiempoValue + "'");
                
                row[13] = rutaValue;
                row[14] = tiempoValue;
                
                tblModel.addRow(row);
            }
            
            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna boleta con el DNI: " + dni);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar por DNI: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btonAgregar;
    private javax.swing.JButton btonEliminar;
    private javax.swing.JButton btonEliminarTodo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblRegister;
    // End of variables declaration//GEN-END:variables
}
