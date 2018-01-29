package es.studium.puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//Pide el nombre al jugador y lo almacena en un fichero de texto junto a otros datos
public class DialogoNombre extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;
	URL resource1 = getClass().getClassLoader().getResource("spiderman.png");
	URL resource2 = getClass().getClassLoader().getResource("PuzzleQuest.jpg");
	JLabel lblImg = new JLabel(new ImageIcon(resource1));
	JLabel lblNombre = new JLabel("Indique su nombre",JLabel.CENTER);
	JTextField text = new JTextField("");
	JButton btnGuardar = new JButton("Guardar");
	JPanel pnl = new JPanel(new GridLayout(1,2));
	Puzzle puzzle;
	int nivel;
	long tiempo;
	
	public DialogoNombre()
	{
		setTitle("Bien jugado");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(350, 150, 250, 285);
		pnl.add(lblNombre);
		pnl.add(text);
		add("North",lblImg);
		add("Center",pnl);
		add("South",btnGuardar);
		setSize(250,285);
		getContentPane().setBackground(Color.WHITE); 
		pnl.setBackground(null);
		ImageIcon img = new ImageIcon(resource2);
		setIconImage(img.getImage());
		addWindowListener(this);
		btnGuardar.addActionListener(this);
		nivel=Dialogo.getNivel();
		tiempo=Puzzle.getTiempoJuego()/1000;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new DialogoNombre();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (btnGuardar.equals(ae.getSource()))
		{
			try {
				// Destino de los datos
				FileWriter fw = new FileWriter("resources/Puntuaciones.txt", true);
				// Buffer de escritura
				BufferedWriter bw = new BufferedWriter(fw);
				// Objeto para la escritura
				PrintWriter salida = new PrintWriter(bw);
				// Añadimos la segunda frase y un double
				salida.println(text.getText()+"-"+tiempo+"-"+nivel);
				salida.close();
				bw.close();
				fw.close();
				System.out.println("Archivo actualizado correctamente!");
			} catch (IOException i) {
				System.out.println("Se produjo un error de Archivo");
			}
			setVisible(false);
			Interfaz inter = new Interfaz();
			inter.setVisible(true);
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
