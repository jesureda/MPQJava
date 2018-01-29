package es.studium.minijuego;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Jugador extends Sprite {
	private int dx;
	private int dy;
	private ArrayList<Laser> lasers;
	private String animacion []={"iron0.png","iron1.png","iron2.png","iron3.png","iron4.png","iron5.png"};

	public Jugador(int x, int y) {
		super(x, y);
		initJugador();
	}

	// Nuevo array, carga la imagen y toma sus dimensiones
	private void initJugador() {
		lasers = new ArrayList<Laser>();
		cargarImagen(animacion[0]);
		getImageDimensions();
	}

	// Método move y condiciones a la hora de limitar el movimiento
	public void mover() {

			y += dy;
			x += dx;
			
			if (x < 1) {
	            x = 1;
	        }
			if (x > 740) {
	            x = 740;
	        }

	        if (y < 1) {
	            y = 1;
	        }
	        if (y > 340) {
	            y = 340;
	        }
		} 

	// Devuelve misiles del array
	public ArrayList<Laser> getLasers() {
		return lasers;
	}

	// Añade un kunai al array si hay menos de 3
	public void lanzar() {
		if (lasers.size() < 3) {
			lasers.add(new Laser(x + ancho, (y + alto/2)-40));
		}
	}

	// Al pulsar las teclas modifica dx-dy o llama a fire
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			lanzar();
			cargarImagen(animacion[5]);
		}
		if (key == KeyEvent.VK_LEFT) {
			dx = -3;
			cargarImagen(animacion[4]);
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 3;
			cargarImagen(animacion[3]);
		}
		if (key == KeyEvent.VK_UP) {
			dy = -3;
			cargarImagen(animacion[2]);
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = 3;
			cargarImagen(animacion[1]);
		}
	}

	// Al dejar de pulsar inicializa dx-dy a 0
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
			cargarImagen(animacion[0]);
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
			cargarImagen(animacion[0]);
		}
		if (key == KeyEvent.VK_UP) {
			dy = 0;
			cargarImagen(animacion[0]);
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
			cargarImagen(animacion[0]);
		}
		if (key == KeyEvent.VK_SPACE) {
			cargarImagen(animacion[0]);
		}
		}
	}

