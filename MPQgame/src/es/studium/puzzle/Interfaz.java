package es.studium.puzzle;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import es.studium.minijuego.EjecutarVS;

public class Interfaz extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JButton btnPlay = new JButton("Partida Normal");
	JButton btnPlay2 = new JButton("MiniJuego");
	JButton btnPunt = new JButton("Puntuaciones");
	JButton btnExit = new JButton("Salir");
	URL resource1 = getClass().getClassLoader().getResource("marvelPuzzle.jpg");
	URL resource2 = getClass().getClassLoader().getResource("PuzzleQuest.jpg");
	URL resource3 = getClass().getClassLoader().getResource("main.wav");
	JLabel lblImg = new JLabel(new ImageIcon(resource1));
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	Clip clip;
	Puzzle puz;

	public Interfaz() {
		ImageIcon img = new ImageIcon(resource2);
		setIconImage(img.getImage());
		setTitle("Marvel Puzzle Quest");
		setResizable(false);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(350, 150, 600, 337);
		//Audio del menú
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(resource3);
			clip.open(ais);
			clip.loop(1000);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException ioe) {
			ioe.printStackTrace();
		}

		btnPlay.setPreferredSize(new Dimension(150, 40));
		btnPlay2.setPreferredSize(new Dimension(150, 40));
		btnPunt.setPreferredSize(new Dimension(150, 40));
		btnExit.setPreferredSize(new Dimension(150, 40));

		gbc.weightx = 0.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		add(lblImg, gbc);

		gbc.weightx = 0.0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(btnPlay, gbc);

		gbc.weightx = 0.0;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(btnPlay2, gbc);

		gbc.weightx = 0.0;
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(btnPunt, gbc);

		gbc.weightx = 0.0;
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(btnExit, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		pack();
		setVisible(true);
		btnPlay.addActionListener(this);
		btnPlay2.addActionListener(this);
		btnPunt.addActionListener(this);
		btnExit.addActionListener(this);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Interfaz();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
	//Abre el puzzle	
		if (btnPlay.equals(ae.getSource())) {
			setVisible(false);
			clip.stop();
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					Puzzle puzzle = new Puzzle(1);
					puzzle.setVisible(true);
				}
			});
		}
		//Abre el minijuego
		if (btnPlay2.equals(ae.getSource())) {
			setVisible(false);
			clip.stop();
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new EjecutarVS();
				}
			});
		}
		//Abre puntuaciones
		if(btnPunt.equals(ae.getSource()))
		{
			new DialogoPuntos();
		}
		//Salir
		if (btnExit.equals(ae.getSource())) {
			System.exit(0);
		}

	}
}
