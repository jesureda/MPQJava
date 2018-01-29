package es.studium.minijuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private final int jugInicialX = 60;
	private final int jugInicialY = 250;
	private final int delay = 15;
	private Timer timer;
	private Jugador player;
	private final int bordeWidth = 800;
	private final int bordeHeight = 450;
	private ArrayList<Enemigo> enemies;
	private boolean ingame = true;
	URL resource1 = getClass().getClassLoader().getResource("fondo.png");
	Image img = Toolkit.getDefaultToolkit().createImage(resource1);
	private Random rand = new Random();
	private final int[][] pos = { { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) },
			{ 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, {800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) },
			{ 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) }, { 800 + rand.nextInt((1300 - 800)), 50 + rand.nextInt((350 - 50)) } };

	public Board() {
		initBoard();
	}

	private void initBoard() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		player = new Jugador(jugInicialX, jugInicialY);
		initEnemigos();
		timer = new Timer(delay, this);
		timer.start();
		
		
	}
	//Rellena el array de enemigos teniendo en cuenta los valores definidos en pos
	public void initEnemigos() {
		enemies = new ArrayList<>();
		for (int[] p : pos) {
			enemies.add(new Enemigo(p[0], p[1]));
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		//Si estamos en partida dibuja el fondo y el contenido del metodo doDrawing
		super.paintComponent(g);
		if (ingame) {
			g.drawImage(img, 0, 0, null);
			doDrawing(g);
		//Si no, dibuja pantalla de victoria o derrota
		} else {
			if (enemies.isEmpty()) {
				drawHasGanado(g);
			} 
			else {		
				drawGameOver(g);
			}
		}
		Toolkit.getDefaultToolkit().sync();
	}

	//Dibuja al jugador, enemigos y lasers
	private void doDrawing(Graphics g) {
		if (player.isVisible()) {
			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}
		
		ArrayList<Laser> ms = player.getLasers();
		for (Laser m : ms) {
			if (m.isVisible()) {
				g.drawImage(m.getImage(), m.getX(), m.getY(), this);			}
		}

		for (Enemigo a : enemies) {
			if (a.isVisible()) {
				g.drawImage(a.getImage(), a.getX(), a.getY(), this);			}
		}

		g.setColor(Color.WHITE);
		g.drawString("Enemigos restantes: " + enemies.size(), 5, 15);
	}

	//Dibuja la pantalla de game over
	private void drawGameOver(Graphics g) {
		g.drawImage(img, 0, 0, null);
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 30);
		FontMetrics fm = getFontMetrics(small);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (bordeWidth - fm.stringWidth(msg)) / 2, bordeHeight / 2);
	}

	//Dibuja la pantalla de victoria
	private void drawHasGanado(Graphics g) {
		g.drawImage(img, 0, 0, null);
		String msg = "Has Ganado";
		Font small = new Font("Helvetica", Font.BOLD, 30);
		FontMetrics fm = getFontMetrics(small);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (bordeWidth - fm.stringWidth(msg)) / 2, bordeHeight / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();
		updateLasers();
		updatePlayer();
		updateEnemies();
		checkCollisions();
		repaint();
	}

	//Si terminael juego se detiene
	private void inGame() {
		if (!ingame) {
			timer.stop();
		}
	}

	//Si hay lasers se mueven
	private void updateLasers() {
		ArrayList<Laser> ms = player.getLasers();
		for (int i = 0; i < ms.size(); i++) {
			Laser m = (Laser) ms.get(i);
			if (m.isVisible()) {
				m.move();
			} else {
				ms.remove(i);
			}
		}
	}

	private void updateEnemies() {
		//Si el array de enemigos está vacio se acaba
		if (enemies.isEmpty()) {
			ingame = false;
			return;
		}
		//Si hay enemigos se mueven, si llegan a x=0 se acaba
		for (int i = 0; i < enemies.size(); i++) {
			Enemigo a = enemies.get(i);
			if (a.isVisible()) {
				a.move();
				if (a.getX()==0)
				{ingame=false;}
			} else {
				enemies.remove(i);
			}
		}
	}
	//Si player es visible permite que se mueva
	private void updatePlayer() {
		if (player.isVisible()) {
			player.mover();
		}
	}

	//Evalua las colisiones player/enemigo
	public void checkCollisions() {
		Rectangle r3 = player.getBounds();
		for (Enemigo alien : enemies) {
			Rectangle r2 = alien.getBounds();
			if (r3.intersects(r2)) {
				player.setVisible(false);
				alien.setVisible(false);
				ingame = false;
			}
		}
	//Evalua las colisiones laser/enemigo 
		ArrayList<Laser> ms = player.getLasers();
		for (Laser m : ms) {
			Rectangle r1 = m.getBounds();
			for (Enemigo alien : enemies) {
				Rectangle r2 = alien.getBounds();
				if (r1.intersects(r2)) {
					m.setVisible(false);
					alien.setVisible(false);
				}
			}
		}
	}
	
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
	}
}
