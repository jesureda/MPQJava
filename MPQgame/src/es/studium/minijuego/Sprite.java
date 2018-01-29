package es.studium.minijuego;

import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int ancho;
    protected int alto;
    protected boolean mostrar;
    protected Image imagen;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        mostrar = true;
    }

    protected void cargarImagen(String imageName) {
    	URL resource1 = getClass().getClassLoader().getResource(imageName);
        ImageIcon ii = new ImageIcon(resource1);
        imagen = ii.getImage();
    }
    
    protected void getImageDimensions() {

        ancho = imagen.getWidth(null);
        alto = imagen.getHeight(null);
    }    

    public Image getImage() {
        return imagen;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return mostrar;
    }

    public void setVisible(Boolean visible) {
        mostrar = visible;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
