package ExpressBus;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase utilitaria para generar PDFs de boletas/facturas
 * Requiere la librería iText (se debe agregar al classpath)
 */
public class PDFGenerator {
    
    /**
     * Genera un PDF de la boleta con los datos proporcionados
     * @param modeloBus Modelo del bus seleccionado
     * @param cantidadAsientos Cantidad de asientos reservados
     * @param cantidadEquipaje Cantidad de equipaje
     * @param metodoPago Método de pago seleccionado
     * @param precioAsiento Precio por asiento
     * @param costoEquipaje Costo total del equipaje
     * @param total Total a pagar
     * @param nombreArchivo Nombre del archivo PDF a generar
     * @param nombrePasajero Nombre del pasajero
     * @param dniPasajero DNI del pasajero
     * @param telefonoPasajero Teléfono del pasajero
     * @param correoPasajero Correo electrónico del pasajero
     * @param rutaViaje Ruta del viaje
     * @param tiempoViaje Tiempo estimado del viaje en horas
     * @return true si se generó correctamente, false en caso contrario
     */
    public static boolean generarBoletaPDF(String modeloBus, int cantidadAsientos, 
                                         int cantidadEquipaje, String metodoPago, 
                                         double precioAsiento, double costoEquipaje, 
                                         double total, String nombreArchivo,
                                         String nombrePasajero, String dniPasajero,
                                         String telefonoPasajero, String correoPasajero,
                                         String rutaViaje, int tiempoViaje) {
        try {
            // Crear el documento PDF
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();
            
            // Establecer metadatos del documento
            document.addTitle("Boleta de Reserva - Cruz del Sur");
            document.addAuthor("Cruz del Sur");
            document.addSubject("Reserva de Pasajes");
            document.addKeywords("boleta, reserva, bus, viaje");
            
            // Configurar márgenes
            document.setMargins(50, 50, 50, 50);
            
            // Título principal
            com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD);
            titleFont.setColor(0, 51, 153);
            com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph("CRUZ DEL SUR", titleFont);
            title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(title);
            
            // Subtítulo
            com.itextpdf.text.Font subtitleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Paragraph subtitle = new com.itextpdf.text.Paragraph("BOLETA DE RESERVA", subtitleFont);
            subtitle.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(subtitle);
            
            // Fecha
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            com.itextpdf.text.Font normalFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12);
            com.itextpdf.text.Paragraph fecha = new com.itextpdf.text.Paragraph("Fecha: " + now.format(formatter), normalFont);
            fecha.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            document.add(fecha);
            
            /*// Línea separadora
            document.add(new com.itextpdf.text.Paragraph(" "));
            document.add(new com.itextpdf.text.Paragraph("======================================="));
            document.add(new com.itextpdf.text.Paragraph(" "));*/
            
            // Datos del Pasajero
            com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD);
            document.add(new com.itextpdf.text.Paragraph("DATOS DEL PASAJERO", boldFont));
            document.add(new com.itextpdf.text.Paragraph(" "));
            
            if (nombrePasajero != null && !nombrePasajero.isEmpty()) {
                document.add(new com.itextpdf.text.Paragraph("Nombre: " + nombrePasajero, normalFont));
                document.add(new com.itextpdf.text.Paragraph("DNI: " + dniPasajero, normalFont));
                document.add(new com.itextpdf.text.Paragraph("Teléfono: " + telefonoPasajero, normalFont));
                document.add(new com.itextpdf.text.Paragraph("Correo: " + correoPasajero, normalFont));
            } else {
                document.add(new com.itextpdf.text.Paragraph("No se han proporcionado datos del pasajero", normalFont));
            }
            
            // Línea separadora
            document.add(new com.itextpdf.text.Paragraph(" "));
            document.add(new com.itextpdf.text.Paragraph("======================================="));
            document.add(new com.itextpdf.text.Paragraph(" "));
            
            // Información de la ruta
            document.add(new com.itextpdf.text.Paragraph("INFORMACIÓN DE VIAJE", boldFont));
            document.add(new com.itextpdf.text.Paragraph(" "));
            
            if (rutaViaje != null && !rutaViaje.isEmpty()) {
                document.add(new com.itextpdf.text.Paragraph("Ruta: " + rutaViaje, normalFont));
                document.add(new com.itextpdf.text.Paragraph("Tiempo de viaje (hrs): " + tiempoViaje, normalFont));
            } else {
                document.add(new com.itextpdf.text.Paragraph("No se ha especificado la ruta del viaje", normalFont));
            }
            
            // Línea separadora
            document.add(new com.itextpdf.text.Paragraph(" "));
            document.add(new com.itextpdf.text.Paragraph("======================================="));
            document.add(new com.itextpdf.text.Paragraph(" "));
            
            // Detalles del viaje
            document.add(new com.itextpdf.text.Paragraph("DETALLES DEL SERVICIO", boldFont));
            document.add(new com.itextpdf.text.Paragraph(" "));
            
            // Información del viaje
            document.add(new com.itextpdf.text.Paragraph("Modelo de Bus: " + modeloBus, normalFont));
            document.add(new com.itextpdf.text.Paragraph("Cantidad de Asientos: " + cantidadAsientos, normalFont));
            document.add(new com.itextpdf.text.Paragraph("Cantidad de Equipaje: " + cantidadEquipaje, normalFont));
            document.add(new com.itextpdf.text.Paragraph("Método de Pago: " + metodoPago, normalFont));
            
            // Línea separadora
            document.add(new com.itextpdf.text.Paragraph(" "));
            document.add(new com.itextpdf.text.Paragraph("======================================="));
            document.add(new com.itextpdf.text.Paragraph(" "));
            
            // Detalles del pago
            document.add(new com.itextpdf.text.Paragraph("DETALLE DE PAGO", boldFont));
            document.add(new com.itextpdf.text.Paragraph(" "));
            document.add(new com.itextpdf.text.Paragraph("Precio por asiento: S/. " + String.format("%.2f", precioAsiento), normalFont));
            document.add(new com.itextpdf.text.Paragraph("Costo total equipaje: S/. " + String.format("%.2f", costoEquipaje), normalFont));
            
            // Línea separadora
            document.add(new com.itextpdf.text.Paragraph(" "));
            document.add(new com.itextpdf.text.Paragraph("======================================="));
            document.add(new com.itextpdf.text.Paragraph(" "));
            
            // Verificar que el total sea correcto (precio por asiento * cantidad de asientos + costo equipaje)
            double totalCalculado = (precioAsiento * cantidadAsientos) + costoEquipaje;
            
            // Si hay discrepancia, usar el valor calculado
            if (Math.abs(totalCalculado - total) > 0.01) {
                System.out.println("Advertencia: Total proporcionado (" + total + ") no coincide con el calculado (" + totalCalculado + ")");
                total = totalCalculado;
            }
            
            // Total a pagar
            com.itextpdf.text.Font totalFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD);
            totalFont.setColor(0, 102, 0);
            com.itextpdf.text.Paragraph totalParagraph = new com.itextpdf.text.Paragraph("TOTAL A PAGAR: S/. " + String.format("%.2f", total), totalFont);
            totalParagraph.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            document.add(totalParagraph);
            
            // Pie de página
            document.add(new com.itextpdf.text.Paragraph(" "));
            document.add(new com.itextpdf.text.Paragraph("======================================="));
            document.add(new com.itextpdf.text.Paragraph(" "));
            
            com.itextpdf.text.Font footerFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.ITALIC);
            footerFont.setColor(0, 51, 153);
            com.itextpdf.text.Paragraph footer = new com.itextpdf.text.Paragraph("Gracias por elegir Cruz del Sur", footerFont);
            footer.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(footer);
            
            document.close();
            
            // Información de depuración
            System.out.println("PDF generado exitosamente en: " + nombreArchivo);
            System.out.println("Modelo de Bus: " + modeloBus);
            System.out.println("Asientos: " + cantidadAsientos);
            System.out.println("Equipaje: " + cantidadEquipaje);
            System.out.println("Método de Pago: " + metodoPago);
            System.out.println("Total: S/. " + String.format("%.2f", total));
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Genera un nombre de archivo único para el PDF
     * @return Nombre del archivo con timestamp
     */
    public static String generarNombreArchivo() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return "Boleta_" + now.format(formatter) + ".pdf";
    }
    
    /**
     * Versión simplificada que llama al método completo con valores predeterminados para los datos adicionales
     */
    public static boolean generarBoletaPDF(String modeloBus, int cantidadAsientos, 
                                         int cantidadEquipaje, String metodoPago, 
                                         double precioAsiento, double costoEquipaje, 
                                         double total, String nombreArchivo) {
        return generarBoletaPDF(modeloBus, cantidadAsientos, cantidadEquipaje, metodoPago,
                              precioAsiento, costoEquipaje, total, nombreArchivo,
                              "No especificado", "No especificado", "No especificado", "No especificado",
                              "No especificada", 0);
    }
    
    /**
     * Método utilitario para generar el archivo PDF usando la librería iText directamente
     * Este método usa la versión 5.5.13.3 de iTextPDF que está incluida en el proyecto
     * @return true si se generó correctamente, false en caso contrario
     */
    public static boolean generarPDF(String contenido, String rutaArchivo) {
        try {
            // Cambiar la extensión a PDF
            if (rutaArchivo.toLowerCase().endsWith(".txt")) {
                rutaArchivo = rutaArchivo.substring(0, rutaArchivo.length() - 4) + ".pdf";
            }
            
            // Usar la librería directamente con importaciones completas
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(rutaArchivo));
            document.open();
            
            // Añadir contenido
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 12);
            String[] lines = contenido.split("\n");
            
            for (String line : lines) {
                document.add(new com.itextpdf.text.Paragraph(line, font));
            }
            
            document.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Obtiene la ruta donde se guardarán los PDFs
     * @return Ruta del directorio de PDFs
     */
    public static String obtenerRutaPDFs() {
        // Crear directorio si no existe
        String rutaPDFs = System.getProperty("user.home") + "\\Documents\\Cruz_del_Sur_PDFs";
        java.io.File directorio = new java.io.File(rutaPDFs);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        return rutaPDFs;
    }
    
    /**
     * Genera un archivo de texto simple con la información de la boleta
     * @param fileName Nombre del archivo sin extensión
     * @param contenido Texto a incluir en el archivo
     * @return true si se generó correctamente, false en caso contrario
     */
    public static boolean generarArchivoTextoSimple(String fileName, String contenido) {
        try {
            String rutaPDFs = obtenerRutaPDFs();
            String filePath = rutaPDFs + java.io.File.separator + fileName + ".txt";
            java.io.File file = new java.io.File(filePath);
            
            try (java.io.FileWriter writer = new java.io.FileWriter(file)) {
                writer.write(contenido);
            }
            
            System.out.println("Archivo generado correctamente en: " + file.getAbsolutePath());
            return true;
        } catch (java.io.IOException e) {
            System.err.println("Error al generar el archivo: " + e.getMessage());
            return false;
        }
    }
}
