package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.lucene.queryparser.flexible.standard.parser.ParseException;

import interfaz.GUI;
import modelo.Lucene;
import modelo.TextEngine;

public class Controlador {
	
	GUI inter;
	TextEngine engine = new TextEngine("C:/Users/Falva/Java Work/Library_Lucene/SourceFiles/");
	Lucene lucene = new Lucene();
	
	
	//PATH INDICE ES EL PATH DONDE SE CARGARA EL INDICE, YA SEA PARA VERIFICARLO O PARA CREARLO
	//ESTE PATH DEBE CAMBIARSE SI LO HACE PARA PODER ABRIR LOS INDICES
	public String pathIndice;
	
	public Controlador(GUI interfaz){
		inter = interfaz;
		inter = interfaz;
		inter.IndexarButton.addActionListener(new botonActioner());
		inter.VerificarButton.addActionListener(new botonActioner());
		inter.ExaminarIndexButton.addActionListener(new botonActioner());
		
		inter.buscarBoton.addActionListener(new botonActioner());
		
		inter.editorFileExaminar.addActionListener(new botonActioner());
		
	}
	
	class botonActioner implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == inter.IndexarButton){
				File pathIndex = new File(inter.indexPath_Field.getText());
				if(!lucene.existeIni(pathIndex.getAbsolutePath())){
					try {
						lucene.CrearIndice(pathIndex.getAbsolutePath(), "C:/Users/Falva/Java Work/Library_Lucene/ConvertedTexts");
						lucene.CrearIni(pathIndex.getAbsolutePath());
						pathIndice = pathIndex.getAbsolutePath();
						JOptionPane.showMessageDialog(null, "Indice Creado Exitosamente");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Imposible crear indice");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Ya se ha indexado en el path");
				}
			}
			if(e.getSource() == inter.VerificarButton){
				File pathIndex = new File(inter.indexPath_Field.getText());
				if(lucene.existeIni(pathIndex.getAbsolutePath())){
					pathIndice = pathIndex.getAbsolutePath();
					JOptionPane.showMessageDialog(null, "existe un indice creado exitosamente");
				}
				else{
					JOptionPane.showMessageDialog(null, "No se ha creado ningun indice en este path");
				}
			}
			
			if(e.getSource() == inter.ExaminarIndexButton){
				File archivoIndex;
				JFileChooser file = new JFileChooser();
				file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				file.showOpenDialog(file);
				archivoIndex = file.getSelectedFile();
				if(archivoIndex== null){
					System.out.println("No se selecciono ningun archivo");
				}
				else{
					inter.indexPath_Field.setText(archivoIndex.getAbsolutePath());
					System.out.println("Examinar index");
				}
			}
			if(e.getSource() == inter.buscarBoton){
				//
				if(pathIndice != null){
					try {
						lucene.cargarIndice("C:/Users/Falva/OneDrive/Documentos/Lucene_Test1");
						lucene.SetList(inter.ListaResultados, lucene.buscar(inter.queryTextField.getText(), "Nombre", "C:/Users/Falva/OneDrive/Documentos/Lucene_Test1"));
					} catch (ParseException | IOException e1) {
						JOptionPane.showMessageDialog(null, "Imposible realizar la query");
						e1.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Debe verificar o crear un indice");
				}
			}
			if(e.getSource() == inter.editorFileExaminar){
				File archivo;
				JFileChooser file = new JFileChooser();
				file.showOpenDialog(file);
				archivo = file.getSelectedFile();
				System.out.println(archivo.getAbsolutePath());
				inter.editorFilePath.setText(archivo.getAbsolutePath());
			}
		}
	}

}
