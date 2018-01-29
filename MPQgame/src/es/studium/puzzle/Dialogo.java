package es.studium.puzzle;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//Este diálogo aparece cuando se supera un nivel
public class Dialogo extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;
	URL resource1 = getClass().getClassLoader().getResource("youwin.jpg");
	URL resource2 = getClass().getClassLoader().getResource("PuzzleQuest.jpg");
	JButton btnAvanzar = new JButton("Avanzar");
	JButton btnMenu = new JButton("Volver al menú");
	JLabel lblImg = new JLabel(new ImageIcon(resource1));
	JPanel pnl = new JPanel(new GridLayout(1, 2));
	Puzzle puzzle;
	private static int nivel = 1;

	public Dialogo() {
		setTitle("Nivel " + nivel + " completado");
		setLayout(new BorderLayout());
		setBounds(350, 150, 400, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		add("Center", lblImg);
		pnl.add(btnAvanzar);
		pnl.add(btnMenu);
		add("South", pnl);
		setSize(400, 300);
		ImageIcon img = new ImageIcon(resource2);
		setIconImage(img.getImage());
		btnAvanzar.addActionListener(this);
		btnMenu.addActionListener(this);
		addWindowListener(this);

	}

	public static void main(String[] args) {
		new Dialogo();
	}

	public static int getNivel() {
		return nivel;
	}
//Nos da la opción de pasar al siguiente nivel o de regresar al menú
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (btnAvanzar.equals(ae.getSource())) {
			if (nivel == 4) {
				setVisible(false);
				new DialogoNombre();
				nivel = 1;
			} else {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						puzzle = new Puzzle(nivel);
						puzzle.setVisible(true);
					}
				});
				setVisible(false);
				nivel++;
			}	
		}
		if (btnMenu.equals(ae.getSource())) {
			setVisible(false);
			new DialogoNombre();
			nivel = 1;
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
