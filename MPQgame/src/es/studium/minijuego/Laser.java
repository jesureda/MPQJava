package es.studium.minijuego;

public class Laser extends Sprite {

	private final int recorrido = 750;
	private final int laserVel = 4;

	public Laser(int x, int y) {
		super(x, y);
		initLaser();
	}

	private void initLaser() {
		cargarImagen("laser.png");
		getImageDimensions();
	}

	public void move() {
		x += laserVel;

		if (x > recorrido) {
			mostrar = false;
		}
	}
}