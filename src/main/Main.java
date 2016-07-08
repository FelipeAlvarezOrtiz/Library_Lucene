package main;

import controlador.Controlador;
import interfaz.GUI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI interfaz = new GUI();
		new Controlador(interfaz);
	}

}
