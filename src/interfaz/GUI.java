package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class GUI {

	public JFrame frmBiblioteca;
	public JTextField indexField;
	public JButton ExaminarIndex;
	public JButton botonIndexar;
	public Box hbIndice;
	public Box hbBuscar;
	public JPanel panel;
	public JLabel lblBusqueda;
	public JTextField textField;
	public JButton botonBuscar;
	public JCheckBox BusquedaIntensivaCBox;

	public GUI() {
		initialize();
		frmBiblioteca.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBiblioteca = new JFrame();
		frmBiblioteca.setResizable(false);
		frmBiblioteca.setTitle("Biblioteca");
		frmBiblioteca.setBounds(100, 100, 1062, 644);
		frmBiblioteca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBiblioteca.getContentPane().setLayout(null);
		
		JLabel labelIndexPath = new JLabel("Ruta del Indice");
		labelIndexPath.setBounds(24, 33, 96, 27);
		frmBiblioteca.getContentPane().add(labelIndexPath);
		
		indexField = new JTextField();
		indexField.setEditable(false);
		indexField.setBounds(125, 35, 780, 25);
		frmBiblioteca.getContentPane().add(indexField);
		indexField.setColumns(10);
		
		ExaminarIndex = new JButton("Examinar");
		ExaminarIndex.setBounds(926, 34, 97, 25);
		frmBiblioteca.getContentPane().add(ExaminarIndex);
		
		botonIndexar = new JButton("Indexar");
		botonIndexar.setBounds(487, 73, 97, 25);
		frmBiblioteca.getContentPane().add(botonIndexar);
		
		hbIndice = Box.createHorizontalBox();
		hbIndice.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Indice", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		hbIndice.setBounds(12, 13, 1020, 101);
		frmBiblioteca.getContentPane().add(hbIndice);
		
		hbBuscar = Box.createHorizontalBox();
		hbBuscar.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		hbBuscar.setBounds(12, 127, 1020, 126);
		frmBiblioteca.getContentPane().add(hbBuscar);
		
		panel = new JPanel();
		hbBuscar.add(panel);
		panel.setLayout(null);
		
		lblBusqueda = new JLabel("Busqueda");
		lblBusqueda.setBounds(12, 13, 67, 22);
		panel.add(lblBusqueda);
		
		textField = new JTextField();
		textField.setBounds(99, 13, 788, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		botonBuscar = new JButton("Buscar");
		botonBuscar.setBounds(899, 12, 97, 25);
		panel.add(botonBuscar);
		
		BusquedaIntensivaCBox = new JCheckBox("Busqueda Intensiva");
		BusquedaIntensivaCBox.setToolTipText("Busqueda intensiva aumenta un ratio de busqueda por el indice, permitiendo encontrar elementos similares.");
		BusquedaIntensivaCBox.setBounds(8, 55, 147, 25);
		panel.add(BusquedaIntensivaCBox);
	}
}
