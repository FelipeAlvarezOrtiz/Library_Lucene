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
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class GUI {

	public JFrame frmBiblioteca;
	public JTabbedPane tabbedPane;
	public JPanel Indexador_Panel;
	public JPanel Buscar_Panel;
	public Panel Editor_Panel;
	public JLabel lblArchivoSeleccionado;
	public JTextField editorFilePath;
	public JButton editorFileExaminar;
	public Box horizontalBox;
	public JLabel lblIndexpath;
	public JTextField indexPath_Field;
	public Box horizontalBox_1;
	public JButton ExaminarIndexButton;
	public JButton IndexarButton;
	public JButton VerificarButton;
	public JLabel lblBuscar;
	public JTextField queryTextField;
	public JButton buscarBoton;
	public Box horizontalBox_2;
	public JCheckBox checkBoxContenido;
	public JList<String> ListaResultados;
	public JButton AbrirPDF;
	public JScrollPane scrollPane;
	public JCheckBox checkBoxTitulo;
	public JCheckBox chckbxBusquedaAvanzada;
	public JCheckBox chckbxBusquedaOnline;

	public GUI() {
		initialize();
		frmBiblioteca.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBiblioteca = new JFrame();
		frmBiblioteca.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Falva\\Downloads\\icons-842865_960_720.png"));
		frmBiblioteca.setResizable(false);
		frmBiblioteca.setTitle("Biblioteca");
		frmBiblioteca.setBounds(100, 100, 1062, 644);
		frmBiblioteca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBiblioteca.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1056, 609);
		frmBiblioteca.getContentPane().add(tabbedPane);
		
		Indexador_Panel = new JPanel();
		Indexador_Panel.setToolTipText("Indexa cantidad considerable de archivos, o bien verifica la integridad de indice.");
		tabbedPane.addTab("Indexador", null, Indexador_Panel, null);
		Indexador_Panel.setLayout(null);
		
		lblIndexpath = new JLabel("IndexPath :");
		lblIndexpath.setToolTipText("Direccion del indice");
		lblIndexpath.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIndexpath.setBounds(22, 30, 79, 24);
		Indexador_Panel.add(lblIndexpath);
		
		indexPath_Field = new JTextField();
		indexPath_Field.setBounds(113, 32, 723, 22);
		Indexador_Panel.add(indexPath_Field);
		indexPath_Field.setColumns(10);
		
		horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Indexador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		horizontalBox_1.setBounds(12, 2, 1027, 139);
		Indexador_Panel.add(horizontalBox_1);
		
		ExaminarIndexButton = new JButton("Examinar");
		ExaminarIndexButton.setBounds(870, 31, 121, 24);
		Indexador_Panel.add(ExaminarIndexButton);
		
		IndexarButton = new JButton("Indexar");
		IndexarButton.setBounds(340, 67, 112, 41);
		Indexador_Panel.add(IndexarButton);
		
		VerificarButton = new JButton("Verificar");
		VerificarButton.setToolTipText("Verifica la integridad del Indice");
		VerificarButton.setBounds(523, 67, 112, 41);
		Indexador_Panel.add(VerificarButton);
		
		Buscar_Panel = new JPanel();
		tabbedPane.addTab("Buscador", null, Buscar_Panel, null);
		Buscar_Panel.setLayout(null);
		
		lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBuscar.setBounds(24, 30, 63, 24);
		Buscar_Panel.add(lblBuscar);
		
		queryTextField = new JTextField();
		queryTextField.setBounds(99, 30, 845, 24);
		Buscar_Panel.add(queryTextField);
		queryTextField.setColumns(10);
		
		buscarBoton = new JButton("Buscar");
		buscarBoton.setBounds(821, 67, 104, 36);
		Buscar_Panel.add(buscarBoton);
		
		horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox_2.setFont(null);
		horizontalBox_2.setBounds(12, 13, 1013, 101);
		Buscar_Panel.add(horizontalBox_2);
		
		checkBoxContenido = new JCheckBox("Contenido");
		checkBoxContenido.setBounds(321, 63, 159, 25);
		Buscar_Panel.add(checkBoxContenido);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 138, 838, 403);
		Buscar_Panel.add(scrollPane);
		
		ListaResultados = new JList<String>();
		scrollPane.setViewportView(ListaResultados);
		ListaResultados.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		AbrirPDF = new JButton("Abrir");
		AbrirPDF.setBounds(882, 408, 97, 25);
		Buscar_Panel.add(AbrirPDF);
		
		checkBoxTitulo = new JCheckBox("Nombre");
		checkBoxTitulo.setBounds(173, 63, 159, 25);
		Buscar_Panel.add(checkBoxTitulo);
		
		chckbxBusquedaAvanzada = new JCheckBox("Busqueda Avanzada");
		chckbxBusquedaAvanzada.setEnabled(false);
		chckbxBusquedaAvanzada.setSelected(true);
		chckbxBusquedaAvanzada.setBounds(24, 63, 156, 25);
		Buscar_Panel.add(chckbxBusquedaAvanzada);
		
		chckbxBusquedaOnline = new JCheckBox("Busqueda On-Line");
		chckbxBusquedaOnline.setBounds(484, 63, 140, 25);
		Buscar_Panel.add(chckbxBusquedaOnline);
		
		Editor_Panel = new Panel();
		tabbedPane.addTab("Editor", null, Editor_Panel, null);
		Editor_Panel.setLayout(null);
		
		lblArchivoSeleccionado = new JLabel("Archivo Seleccionado");
		lblArchivoSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArchivoSeleccionado.setBounds(40, 33, 145, 32);
		Editor_Panel.add(lblArchivoSeleccionado);
		
		editorFilePath = new JTextField();
		editorFilePath.setEditable(false);
		editorFilePath.setBounds(197, 39, 679, 26);
		Editor_Panel.add(editorFilePath);
		editorFilePath.setColumns(10);
		
		editorFileExaminar = new JButton("Examinar");
		editorFileExaminar.setBounds(907, 40, 112, 25);
		Editor_Panel.add(editorFileExaminar);
		
		horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Cargar Archivo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox.setBounds(12, 13, 1027, 91);
		Editor_Panel.add(horizontalBox);
	}
}
