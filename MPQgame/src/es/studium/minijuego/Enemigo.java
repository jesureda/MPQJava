package es.studium.minijuego;

public class Enemigo extends Sprite {

    private final int inicialX = 400;

    public Enemigo(int x, int y) {
        super(x, y);

        initEnemigo();
    }

    private void initEnemigo() {

        cargarImagen("bat1.gif");
        getImageDimensions();
    }
    public void move() {

        if (x < 0) {
            x = inicialX;
        }

        x -= 1;
    }
}
