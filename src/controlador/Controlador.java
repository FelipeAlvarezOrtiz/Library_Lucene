package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaz.GUI;
import modelo.TextEngine;

public class Controlador {
	
	GUI inter;
	TextEngine engine = new TextEngine("C:/Users/Falva/Java Work/Library_Lucene/SourceFiles/");
	
	public Controlador(GUI interfaz){
		inter = interfaz;
		inter = interfaz;
		inter.botonBuscar.addActionListener(new botonActioner());
		inter.botonIndexar.addActionListener(new botonActioner());
		inter.ExaminarIndex.addActionListener(new botonActioner());
		
	}
	
	class botonActioner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == inter.botonBuscar){
				System.out.println("PRESIONADO BOTON BUSCAR");
			}
			if(e.getSource() == inter.botonIndexar){
				System.out.println("PRESIONADO BOTON Indexar");
			}
			if(e.getSource() == inter.ExaminarIndex){
				System.out.println("PRESIONADO BOTON examinar");
			}
		}
		
	}
}
