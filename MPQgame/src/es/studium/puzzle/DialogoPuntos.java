package es.studium.puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//Muestra un diálogo con las puntuaciones recogidas en el fichero
public class DialogoPuntos extends JFrame {
	private static final long serialVersionUID = 1L;
	URL resource1 = getClass().getClassLoader().getResource("puntos.jpg");
	URL resource2 = getClass().getClassLoader().getResource("PuzzleQuest.jpg");
	JLabel lblbgr = new JLabel(new ImageIcon(resource1));
	JPanel pnl1 = new JPanel(new GridLayout(1, 3));
	JLabel lblNombre = new JLabel("Nombre: ");
	JLabel lblNivel = new JLabel("Tiempo: ");
	JLabel lblMin = new JLabel("Nivel: ");
	BufferedImage myImage;
	JFrame myJFrame = new JFrame("Image pane");

	public DialogoPuntos() {
		setLayout(new BorderLayout());
		setTitle("Puntuaciones");
		setVisible(true);
		setResizable(false);
		add("Center",lblbgr);
		lblbgr.setLayout(new FlowLayout());
		pnl1.add(lblNombre);
		pnl1.add(lblNivel);
		pnl1.add(lblMin);
		pnl1.setOpaque(false);
		lblNombre.setPreferredSize(new Dimension(90, 20));
		lblNivel.setPreferredSize(new Dimension(90, 20));
		lblMin.setPreferredSize(new Dimension(90, 20));
		lblNombre.setForeground(new Color(150, 0, 30));
		lblNivel.setForeground(new Color(150, 0, 30));
		lblMin.setForeground(new Color(150, 0, 30));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		lblbgr.add(pnl1);
		ImageIcon img = new ImageIcon(resource2);
		setIconImage(img.getImage());
		setBounds(350, 150, 400, 236);

		try {
			// Origen de los datos
			FileReader fr = new FileReader("resources/Puntuaciones.txt");
			// Buffer de lectura
			BufferedReader entrada = new BufferedReader(fr);
			String s;
			// Bucle para sacar la información del archivo
			while ((s = entrada.readLine()) != null) {
				String cadena[] = s.split("-");
				JPanel pnl = new JPanel(new GridLayout(1, 3));
				JLabel lblTab1 = new JLabel(cadena[0]);
				JLabel lblTab2 = new JLabel(cadena[1]/*.substring(0, 2)*/);
				JLabel lblTab3 = new JLabel(cadena[2]);
				pnl.add(lblTab1);
				pnl.add(lblTab2);
				pnl.add(lblTab3);
				pnl.setOpaque(false);
				lblTab1.setPreferredSize(new Dimension(90, 20));
				lblTab2.setPreferredSize(new Dimension(90, 20));
				lblTab3.setPreferredSize(new Dimension(90, 20));
				lblbgr.add(pnl);
			}
			// Cerrar el objeto entrada
			entrada.close();
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Archivo NO encontrado");
		} catch (IOException i) {
			System.out.println("Se produjo un error de Archivo");
		}
		pack();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new DialogoPuntos();
	}

}
