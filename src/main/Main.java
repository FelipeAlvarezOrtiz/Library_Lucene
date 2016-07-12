package main;	

import controlador.Controlador;
import interfaz.GUI;

public class Main {
	
	public final static String path = "C:/Users/Falva/Java Work/Library_Lucene/SourceFiles";
	public final static String indexPath = "C:/Users/Falva/OneDrive/Documentos/Lucene/Indice";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI interfaz = new GUI();
		new Controlador(interfaz);
	}

}
