package es.studium.minijuego;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import es.studium.puzzle.Interfaz;

public class EjecutarVS extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	public static Clip clip;

	public EjecutarVS() {
		initUI();
	}
	private void initUI() {
		URL resource1 = getClass().getClassLoader().getResource("PuzzleQuest.jpg");
		URL resource2 = getClass().getClassLoader().getResource("iron.wav");
		add(new Board());
		setSize(800, 450);
		setResizable(false);
		setTitle("Ironman minigame");
		setLocationRelativeTo(null);
		addWindowListener(this);
		setVisible(true);
		ImageIcon img = new ImageIcon(resource1);
		setIconImage(img.getImage());
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(resource2);
			clip.open(ais);
			clip.loop(1000);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		clip.stop();
		setVisible(false);
		new Interfaz();

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}