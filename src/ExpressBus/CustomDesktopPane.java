
package ExpressBus;
import javax.swing.*;
import java.awt.*;
import java.net.URL; // Importa java.net.URL para cargar recursos

public class CustomDesktopPane extends JDesktopPane {

    private Image backgroundImage;

    // Constructor para cargar la imagen
    public CustomDesktopPane(String imagePath) {
        // Carga la imagen desde los recursos de tu aplicación
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            this.backgroundImage = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Error: Imagen de fondo no encontrada en " + imagePath);
            // Opcional: podrías cargar una imagen por defecto o dejar el fondo sin imagen
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Siempre llama a este método para que el dibujo básico funcione

        if (backgroundImage != null) {
            // Dibuja la imagen escalada para que llene todo el JDesktopPane
            // Los parámetros 0, 0 son las coordenadas (x, y) de inicio.
            // getWidth(), getHeight() obtienen el ancho y alto actual del panel,
            // lo que hace que la imagen se estire para cubrirlo todo.
            // 'this' es el ImageObserver, necesario para que la imagen se cargue completamente.
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}