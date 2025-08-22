/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpressBus;

/**
 * Clase auxiliar para almacenar temporalmente los datos del pasajero
 * entre diferentes pantallas de la aplicación.
 */
public class DatosPasajero {
    private static String nombre;
    private static String dni;
    private static String telefono;
    private static String correo;
    
    /**
     * Establece los datos del pasajero
     * @param nombre Nombre completo del pasajero
     * @param dni Documento de identidad del pasajero
     * @param telefono Teléfono de contacto del pasajero
     * @param correo Correo electrónico del pasajero
     */
    
    public static void setDatos(String nombre, String dni, String telefono, String correo) {
        DatosPasajero.nombre = nombre;
        DatosPasajero.dni = dni;
        DatosPasajero.telefono = telefono;
        DatosPasajero.correo = correo;
    }
    
    /**
     * @return El nombre del pasajero
     */
    public static String getNombre() {
        return nombre;
    }
    
    /**
     * @return El DNI del pasajero
     */
    public static String getDni() {
        return dni;
    }
    
    /**
     * @return El teléfono del pasajero
     */
    public static String getTelefono() {
        return telefono;
    }
    
    /**
     * @return El correo electrónico del pasajero
     */
    public static String getCorreo() {
        return correo;
    }
    
    /**
     * Limpia los datos almacenados
     */
    public static void limpiarDatos() {
        nombre = null;
        dni = null;
        telefono = null;
        correo = null;
    }
}
