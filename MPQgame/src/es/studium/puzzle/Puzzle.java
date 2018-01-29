package es.studium.puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Puzzle extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	URL resource1;
	URL resource2 = getClass().getClassLoader().getResource("pac.wav");
	URL resource3 = getClass().getClassLoader().getResource("PuzzleQuest.jpg");
	private JPanel panel;
	private BufferedImage imgBuff;
	private ArrayList<Piezas> listaPiezas;
	private ArrayList<Point> solucion = new ArrayList<Point>();
	String imagenes[]= new String [] {"deadpool.jpg","magneto.jpg","guardians.jpg","thor.jpg","ironman.png","spiderman.jpg","thorloki.jpg","venom.jpg","wolverine.jpg","spiderlizard.jpg"};
	private Image image;
	private Piezas libre;
	private int ancho, alto;
	private final int anchoImagen = 800;
	private BufferedImage imgBuffRedimens;
	private int valor =(int) (Math.random()*10);
	private int filCol;
	private static long startTime=0;
	private static long endTime=0;

	public Puzzle(int nivel) {

		initUI(nivel);
	}

	private void initUI(int nivel) {
	//Cargamos la solucion dependiendo del nivel
		if (nivel==1)
		{
			filCol=3;
			//Añadimos al Array solucion la posición (x,y) de las piezas
			solucion.add(new Point(0, 0));
			solucion.add(new Point(0, 1));
			solucion.add(new Point(0, 2));
			solucion.add(new Point(1, 0));
			solucion.add(new Point(1, 1));
			solucion.add(new Point(1, 2));
			solucion.add(new Point(2, 0));
			solucion.add(new Point(2, 1));
			solucion.add(new Point(2, 2));
		}
		else if (nivel==2)
		{
			filCol=4;
			solucion.add(new Point(0, 0));
			solucion.add(new Point(0, 1));
			solucion.add(new Point(0, 2));
			solucion.add(new Point(0, 3));
			solucion.add(new Point(1, 0));
			solucion.add(new Point(1, 1));
			solucion.add(new Point(1, 2));
			solucion.add(new Point(1, 3));
			solucion.add(new Point(2, 0));
			solucion.add(new Point(2, 1));
			solucion.add(new Point(2, 2));
			solucion.add(new Point(2, 3));
			solucion.add(new Point(3, 0));
			solucion.add(new Point(3, 1));
			solucion.add(new Point(3, 2));
			solucion.add(new Point(3, 3));
		}
		else if (nivel==3)
		{
			filCol=5;
			solucion.add(new Point(0, 0));
			solucion.add(new Point(0, 1));
			solucion.add(new Point(0, 2));
			solucion.add(new Point(0, 3));
			solucion.add(new Point(0, 4));
			solucion.add(new Point(1, 0));
			solucion.add(new Point(1, 1));
			solucion.add(new Point(1, 2));
			solucion.add(new Point(1, 3));
			solucion.add(new Point(1, 4));
			solucion.add(new Point(2, 0));
			solucion.add(new Point(2, 1));
			solucion.add(new Point(2, 2));
			solucion.add(new Point(2, 3));
			solucion.add(new Point(2, 4));
			solucion.add(new Point(3, 0));
			solucion.add(new Point(3, 1));
			solucion.add(new Point(3, 2));
			solucion.add(new Point(3, 3));
			solucion.add(new Point(3, 4));
			solucion.add(new Point(4, 0));
			solucion.add(new Point(4, 1));
			solucion.add(new Point(4, 2));
			solucion.add(new Point(4, 3));
			solucion.add(new Point(4, 4));
			
		}else if (nivel==4)
		{
			filCol=6;
			solucion.add(new Point(0, 0));
			solucion.add(new Point(0, 1));
			solucion.add(new Point(0, 2));
			solucion.add(new Point(0, 3));
			solucion.add(new Point(0, 4));
			solucion.add(new Point(0, 5));
			solucion.add(new Point(1, 0));
			solucion.add(new Point(1, 1));
			solucion.add(new Point(1, 2));
			solucion.add(new Point(1, 3));
			solucion.add(new Point(1, 4));
			solucion.add(new Point(1, 5));
			solucion.add(new Point(2, 0));
			solucion.add(new Point(2, 1));
			solucion.add(new Point(2, 2));
			solucion.add(new Point(2, 3));
			solucion.add(new Point(2, 4));
			solucion.add(new Point(2, 5));
			solucion.add(new Point(3, 0));
			solucion.add(new Point(3, 1));
			solucion.add(new Point(3, 2));
			solucion.add(new Point(3, 3));
			solucion.add(new Point(3, 4));
			solucion.add(new Point(3, 5));
			solucion.add(new Point(4, 0));
			solucion.add(new Point(4, 1));
			solucion.add(new Point(4, 2));
			solucion.add(new Point(4, 3));
			solucion.add(new Point(4, 4));
			solucion.add(new Point(4, 5));
			solucion.add(new Point(5, 0));
			solucion.add(new Point(5, 1));
			solucion.add(new Point(5, 2));
			solucion.add(new Point(5, 3));
			solucion.add(new Point(5, 4));
			solucion.add(new Point(5, 5));
		}
		ImageIcon img = new ImageIcon(resource3);
		setIconImage(img.getImage());
		setTitle("Puzzle");
		setResizable(false);
		setBounds(300, 60, 800, 600);
		addWindowListener(this);
		startTime = System.currentTimeMillis();
		listaPiezas = new ArrayList<Piezas>();

		//Creamos un panel con GridLayout, el numero de celdas será el mismo que de piezas
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel.setLayout(new GridLayout(filCol, filCol, 0, 0));
		add(panel, BorderLayout.CENTER);
		
		//Cargamos la imagen original y la redimensionamos
		try {
			imgBuff = cargarImagen();
			int altura = getNuevaAltura(imgBuff.getWidth(), imgBuff.getHeight());
			imgBuffRedimens = redimensionarImagen(imgBuff, anchoImagen, altura, BufferedImage.TYPE_INT_ARGB);

		} catch (IOException ex) {
			Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
		}
		//De la imagen redimensionada tomamos su altura y anchura
		ancho = imgBuffRedimens.getWidth(null);
		alto = imgBuffRedimens.getHeight(null);

		//Recorreremos las celdas del panel para trocear la imagen en las distintas piezas y asignar
		for (int i = 0; i < filCol; i++) {
			for (int j = 0; j < filCol; j++) {
				image = createImage(new FilteredImageSource(imgBuffRedimens.getSource(),
						new CropImageFilter(j * ancho / filCol, i * alto / filCol, (ancho / filCol), alto / filCol)));
				Piezas pieza = new Piezas(image);
				//Asigna a cada pieza su valor de posicion (x,y)
				pieza.putClientProperty("posicion", new Point(i, j));

				//Si la pieza está en la posición final la deja en blanco
				if (i == filCol-1 && j == filCol-1) {
					libre = new Piezas();
					libre.setBorderPainted(false);
					libre.setContentAreaFilled(false);
					libre.setLastButton();
					libre.putClientProperty("posicion", new Point(i, j));
				} else {
					listaPiezas.add(pieza);
				}
			}
		}
		//Se desordenan las piezas
		Collections.shuffle(listaPiezas);
		//Añadimos el hueco en blanco
		listaPiezas.add(libre);

		//Recorre el array y añade las piezas al panel
		for (int i = 0; i < (filCol*filCol); i++) {
			Piezas btnPieza = listaPiezas.get(i);
			panel.add(btnPieza);
			btnPieza.setBorder(BorderFactory.createLineBorder(Color.gray));
			btnPieza.addActionListener(new ClickAction());
		}
		pack();
	}

	//Método que genera la altura nueva parala imagen redimensionada
	private int getNuevaAltura(int w, int h) {
		double ratio = anchoImagen / (double) w;
		int nuevaAltura = (int) (h * ratio);
		return nuevaAltura;
	}

	//Método que carga la imagen modelo
	private BufferedImage cargarImagen() throws IOException {
		resource1 = getClass().getClassLoader().getResource(imagenes[valor]);
		BufferedImage bimg = ImageIO.read(resource1);
		return bimg;
	}

	//Método que redimensiona la imagen
	private BufferedImage redimensionarImagen(BufferedImage originalImage, int width, int height, int type) throws IOException {
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}
	
	
	//Creacion de clase interna que gestiona los clics
	private class ClickAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			checkButton(e);
			checkSolution();
		}

		private void checkButton(ActionEvent e) {
			//Recoge el valor actual del índice del botón libre
			int indLastBtn = 0;
			for (Piezas btnPieza : listaPiezas) {
				if (btnPieza.isLastButton()) {
					indLastBtn = listaPiezas.indexOf(btnPieza);
				}
			}
			//Recoge el valor del índice del seleccionado
			JButton button = (JButton) e.getSource();
			int indBtn = listaPiezas.indexOf(button);

			//Condiciones para realizar el intercambio de posiciones
			if (filCol==3)
			{
				if ((indBtn - 1 == indLastBtn) || (indBtn + 1== indLastBtn) || (indBtn - 3 == indLastBtn)
						|| (indBtn + 3 == indLastBtn)) {
					//Swap permuta la posicion de las 2 piezas colindantes
					Collections.swap(listaPiezas, indBtn, indLastBtn);
					updateButtons();
				}
			}
			else if (filCol == 4) {
				if ((indBtn - 1 == indLastBtn) || (indBtn + 1 == indLastBtn) || (indBtn - 4 == indLastBtn)
						|| (indBtn + 4 == indLastBtn)) {
					Collections.swap(listaPiezas, indBtn, indLastBtn);
					updateButtons();
				}
			}
			else if (filCol==5)
			{
				if ((indBtn - 1 == indLastBtn) || (indBtn + 1 == indLastBtn) || (indBtn - 5 == indLastBtn)
						|| (indBtn + 5 == indLastBtn)) {
					Collections.swap(listaPiezas, indBtn, indLastBtn);
					updateButtons();
				}
			}
			else if (filCol==6)
			{
				if ((indBtn - 1 == indLastBtn) || (indBtn + 1 == indLastBtn) || (indBtn - 6 == indLastBtn)
						|| (indBtn + 6 == indLastBtn)) {
					Collections.swap(listaPiezas, indBtn, indLastBtn);
					updateButtons();
				}
			}
		}
		//Actualiza los cambios
		private void updateButtons() {

			panel.removeAll();
			for (JComponent btn : listaPiezas) {
				panel.add(btn);
			}
			//Sonido cada vez que movemos una pieza
			panel.validate();
			try {
				Clip clip = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(resource2);
				clip.open(ais);
				clip.loop(0);
			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private void checkSolution() {
		ArrayList<Point> posActual = new ArrayList<Point>();
		for (JComponent btn : listaPiezas) {
			posActual.add((Point)btn.getClientProperty("posicion"));
		}
		//Si coinciden muestra una ventana de diálogo.
//boolean papaya=true;
//if (papaya){
		if (compareList(solucion, posActual)) {
			endTime=System.currentTimeMillis();
			new Dialogo();
			setVisible(false);
		}
	}

	//Método para comparar
	public static boolean compareList(List<Point> ls1, List<Point> ls2) {
		return ls1.toString().contentEquals(ls2.toString());
	}

	public static void main(String[] args) {

	}
	
	public static long getTiempoJuego ()
	{
		return endTime-startTime;
		
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
